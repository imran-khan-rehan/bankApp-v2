package com.redmath.Bank.App.auth;

import com.redmath.Bank.App.Account.AccountService;
import com.redmath.Bank.App.Jwt.JwtUtil;
import com.redmath.Bank.App.User.User;
import com.redmath.Bank.App.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountService accountService;

    private static final int UNAUTHORIZED_STATUS = HttpStatus.UNAUTHORIZED.value();

    public ResponseEntity<?> authenticate(AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Incorrect password");
        }
        User user = userRepository.findByEmail(authRequest.getEmail());
        String token = jwtUtil.generateToken(authRequest.getEmail());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        AuthResponse authResponse = new AuthResponse(user.getId(), user.getRole());

        return ResponseEntity.ok().headers(headers).body(authResponse);
    }

    public String registerUser(User user) {
       // user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        // Generate a unique account number
        Long userId = user.getId();
        if (userId != null) {

            // Save user again to update account number
            userRepository.save(user);

            // Proceed with account creation
            if (userRepository.findByEmail(user.getEmail()).getId() != null) {
               accountService.createAccount(user,"saving");
            } else {
                return "failure";
            }
        }

        return "success";
    }

    private String generateUniqueAccountNumber(Long userId) {
        Random random = new Random();
        int randomDigits = 1000000000 + random.nextInt(900000000);
        return String.format("%010d", randomDigits) + userId;
    }
}
