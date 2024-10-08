package com.redmath.Bank.App.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v2/transactions")
public class TransactionController {
    private static final Logger LOG = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<Transaction>> getAll() {
        List<Transaction> transactions = transactionService.getAll();
        return ResponseEntity.ok(transactions);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Transaction transaction) {
        try {
            Transaction savedTransaction = transactionService.createTransaction(transaction);
            return ResponseEntity.status(HttpStatus.CREATED).body("transaction created");
        } catch (ResponseStatusException e) {
            LOG.error("Error during transaction creation: ", e);
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (RuntimeException e) {
            LOG.error("Unexpected error during transaction creation: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }


    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getTransactionUser(@PathVariable Long userId) {
        try {
            List<Transaction> transactions = transactionService.allTransactionUser(userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(transactions);
        } catch (ResponseStatusException e) {
            LOG.error("Error during transaction creation: ", e);
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }
}
