package niroj.movie.services;

import niroj.movie.models.UserModel;
import niroj.movie.repositories.UserRepository;
import niroj.movie.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

        @Autowired
    private JwtUtil jwtTokenUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String registerUser(UserModel user) {
        Optional<UserModel> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return "User already exists";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

    public Map<String, Object> loginUser(String email, String password) {
        Optional<UserModel> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return Map.of("user", user.get(), "token", jwtTokenUtil.generateToken(email));
        }
        return Map.of("error", "Invalid credentials");
    }

            // Fetch complete user details by email
            public Optional<UserModel> getUserDetailsByEmail(String token) {
                String email =  jwtTokenUtil.extractEmail(token);
                return userRepository.findByEmail(email);
            }
}
