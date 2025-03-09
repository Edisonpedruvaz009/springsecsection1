package com.edison.springsecsection1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Setter
@Getter
@Table(name="account_transactions")
public class AccountTransactions {

    @Id
    @Column(name="transaction_id")
    private String transactionId;

    @Column(name="account_number")
    private Long accountNumber;

    @Column(name="customer_id")
    private Long customerId;

    @Column(name="transaction_dt")
    private Date transactionDt;

    @Column(name="transaction_summary")
    private String transactionSummary;

    @Column(name="transaction_type")
    private String transactionType;

    @Column(name="transaction_amt")
    private Long transactionAmt;

    @Column(name="closing_balance")
    private Long closingBalance;

    @Column(name="create_dt")
    private Date createDt;

}
