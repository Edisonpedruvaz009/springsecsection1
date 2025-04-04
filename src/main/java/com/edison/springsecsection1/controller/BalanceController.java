package com.edison.springsecsection1.controller;

import com.edison.springsecsection1.model.AccountTransactions;
import com.edison.springsecsection1.repository.AccountTransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final AccountTransactionsRepository accountTransactionsRepository;

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam long id) {
        List<AccountTransactions> accountTransactions=accountTransactionsRepository
                .findByCustomerIdOrderByTransactionDtDesc(id);
        if(accountTransactions != null){
            return accountTransactions;
        }else{
            return null;
        }


    }
}
