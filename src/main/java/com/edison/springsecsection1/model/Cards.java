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
@Table(name="cards")
public class Cards {

    @Id
    @Column(name="card_id")
    private Long cardId;

    @Column(name="card_number")
    private String cardNumber;

    @Column(name="customer_id")
    private Long customerId;

    @Column(name="card_type")
    private String cardType;

    @Column(name="total_limit")
    private Long totalLimit;

    @Column(name="amount_used")
    private Long amountUsed;

    @Column(name="available_amount")
    private Long availableAmount;

    @Column(name="create_dt")
    private Date createDt;
}
