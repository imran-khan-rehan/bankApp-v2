package com.redmath.Bank.App.Account;

import com.redmath.Bank.App.User.User;
import com.redmath.Bank.App.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody AccountRequest accountRequest) {
        User user = userRepository.findById(accountRequest.getUserId()).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        Account account = accountService.createAccount(user, accountRequest.getAccountType());
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId) {
        Account account = accountService.getAccountById(accountId);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId) {
        boolean isDeleted = accountService.deleteAccount(accountId);
        if (isDeleted) {
            return ResponseEntity.ok("Account deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
