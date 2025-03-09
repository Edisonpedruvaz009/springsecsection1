package com.edison.springsecsection1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name="loans")
public class Loans {

    @Id
    @Column(name="loan_number")
    private Long loanNumber;

    @Column(name="customer_id")
    private Long customerId;

    @Column(name="start_dt")
    private Date startDt;

    @Column(name="loan_type")
    private String loanType;

    @Column(name="total_loan")
    private Long totalLoan;

    @Column(name="amount_paid")
    private Long amountPaid;

    @Column(name="outstanding_amount")
    private Long outstandingAmount;

    @Column(name="create_dt")
    private Date createDt;
}
