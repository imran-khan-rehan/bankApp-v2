package com.redmath.Bank.App.Transaction;

import com.redmath.Bank.App.Account.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "sender",
            foreignKey = @ForeignKey(name = "FK_SENDER")
    )
    private Account sender;

    @ManyToOne
    @JoinColumn(
            name = "receiver",
            foreignKey = @ForeignKey(name = "FK_RECEIVER")
    )
    private Account receiver;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime date;
}
