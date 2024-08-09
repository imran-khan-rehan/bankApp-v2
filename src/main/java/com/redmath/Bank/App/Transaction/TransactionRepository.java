package com.redmath.Bank.App.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
 List<Transaction> findBySenderAccountHolderIdOrReceiverAccountHolderId(Long senderId, Long receiverId);
}
