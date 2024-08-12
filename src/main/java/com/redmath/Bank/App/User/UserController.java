package com.redmath.Bank.App.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v2/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
   // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getDetails(@PathVariable Long userId) {
        User user = userService.findUser(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserDTO userDTO = new UserDTO(user.getName(), user.getEmail(), user.getAddress());
        return ResponseEntity.ok(userDTO);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody User user) {

        if (!validateUser(user)) {
            return ResponseEntity
                    .badRequest()
                    .body("Invalid or empty data");
        }
        User existingUser = userService.findUser(userId);
        if (existingUser.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        if (!existingUser.getEmail().equals(user.getEmail()) && userService.alreadyRegister(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setAddress(user.getAddress());
        if (user.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        User updatedUser = userService.save(existingUser);

        UserDTO userDTO = new UserDTO(updatedUser.getName(), updatedUser.getEmail(), updatedUser.getAddress());
        return ResponseEntity.ok(userDTO);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {

        User existingUser = userService.findUser(userId);
        if (existingUser.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
    private boolean validateUser(User user) {
        return user.getEmail().trim().length() > 5 &&
//                user.getPassword().trim().length() > 7 &&
                user.getName().trim().length() > 2;
    }
}
