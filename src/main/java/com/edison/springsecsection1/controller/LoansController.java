package com.edison.springsecsection1.controller;

import com.edison.springsecsection1.model.Loans;
import com.edison.springsecsection1.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoanRepository loanRepository;

    @GetMapping("/myLoans")
    public List<Loans> getLoansDetails(@RequestParam long id) {
        List<Loans> loans=loanRepository.findByCustomerIdOrderByStartDtDesc(id);
        if(loans != null){
            return loans;
        }else{
            return null;
        }
    }
}
