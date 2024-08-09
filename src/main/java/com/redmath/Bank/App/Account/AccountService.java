package com.redmath.Bank.App.Account;

import com.redmath.Bank.App.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    private String generateUniqueAccountNumber(Long userId) {
        Random random = new Random();
        int randomDigits = 1000000000 + random.nextInt(900000000);
        return String.format("%010d", randomDigits) + userId;
    }

    public Account createAccount(User user, String accountType) {
        Account account = new Account();
        account.setAccountHolder(user);
        account.setAccountNumber(generateUniqueAccountNumber(user.getId()));
        account.setAccountType(accountType);
        account.setBalance(0L); // Initial balance is set to 0

        return accountRepository.save(account);
    }

    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    public boolean deleteAccount(Long accountId) {
        if (accountRepository.existsById(accountId)) {
            accountRepository.deleteById(accountId);
            return true;
        }
        return false;
    }
}
