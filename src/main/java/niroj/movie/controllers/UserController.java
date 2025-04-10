package niroj.movie.controllers;

import niroj.movie.models.UserModel;
import niroj.movie.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    private UserService userService;
    

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
    
    
        return ResponseEntity.ok(loginResponse);
    }
}
