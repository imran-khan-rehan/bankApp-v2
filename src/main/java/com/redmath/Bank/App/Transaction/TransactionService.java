package com.redmath.Bank.App.Transaction;

import com.redmath.Bank.App.Account.Account;
import com.redmath.Bank.App.Account.AccountRepository;
import com.redmath.Bank.App.User.User;
import com.redmath.Bank.App.User.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    public List<Transaction> allTransactionUser(Long userId) {
        Account accountHoler= accountRepository.findByAccountHolderId(userId).orElse(null);

        if(accountHoler == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no user of this id");
        }
        return transactionRepository.findBySenderAccountHolderIdOrReceiverAccountHolderId(accountHoler.getId(),accountHoler.getId());
    }

    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        if (transaction.getSender() == null || transaction.getReceiver() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sender and receiver must be provided");
        }
        if (transaction.getAmount() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction amount must be greater than zero");
        }

        Account senderAccount = accountRepository.findByAccountHolderId(transaction.getSender().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender account not found"));

        if (senderAccount.getBalance() < transaction.getAmount()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient balance");
        }

        Account receiverAccount = accountRepository.findByAccountNumber(transaction.getReceiver().getAccountNumber())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receiver account not found"));

        senderAccount.setBalance(senderAccount.getBalance() - transaction.getAmount());
        accountRepository.save(senderAccount);

        receiverAccount.setBalance(receiverAccount.getBalance() + transaction.getAmount());
        accountRepository.save(receiverAccount);

        // Ensure the sender and receiver accounts are managed entities
        transaction.setSender(senderAccount);
        transaction.setReceiver(receiverAccount);
        transaction.setDate(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }
}
