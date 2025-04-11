package niroj.movie.controllers;

import niroj.movie.models.UserModel;
import niroj.movie.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/fetch-user")
    public ResponseEntity<UserModel> getUserInfo(HttpServletRequest request) {
        String token = getJwtFromCookies(request);
        System.err.println(token);
        if (token == null) {
            return ResponseEntity.status(401).build();
        }
    
        Optional<UserModel> user = userService.getUserDetailsByEmail(token);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

@PostMapping("/register")
public ResponseEntity<?> registerUser(@RequestBody UserModel user, BindingResult result) {
    if (result.hasErrors()) {
        List<String> errors = result.getAllErrors()
            .stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(Map.of("errors", errors));
    }

    String message = userService.registerUser(user);
    return message.equals("User already exists") ? ResponseEntity.badRequest().body(message) : ResponseEntity.ok(Map.of("message", message));
}


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials, HttpServletResponse response) {
        Map<String, Object> loginResponse = userService.loginUser(credentials.get("email"), credentials.get("password"));
    
        if (loginResponse.containsKey("error")) {
            return ResponseEntity.status(401).body(loginResponse.get("error"));
        }
    
     // Extract token
     String token = (String) loginResponse.get("token");
    
     // Create and set the cookie
     Cookie cookie = new Cookie("jwt", token);
     cookie.setHttpOnly(true);
     cookie.setSecure(true);
     cookie.setPath("/");
     cookie.setMaxAge(60 * 60 * 24);  // 1 day
 
     response.addCookie(cookie);
     return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletResponse response) {
        // Invalidate the JWT cookie
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // Expire immediately

        response.addCookie(cookie);

        return ResponseEntity.ok("Logout successful");
    }
    // Helper method to extract JWT token from cookies 
    private String getJwtFromCookies(HttpServletRequest request) {
      Cookie[] cookies = request.getCookies();
      if (cookies == null) {
        return null;  // No cookies in request
      }
      for (Cookie cookie : cookies) {
        if ("jwt".equals(cookie.getName())) {
            return cookie.getValue();
        }
      }
     return null;  // No JWT token in cookies
    }
}
