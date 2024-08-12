package com.redmath.Bank.App.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountHolderId(Long accountHolderId);
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findAllByAccountHolderId(Long accountHolderId);
}
