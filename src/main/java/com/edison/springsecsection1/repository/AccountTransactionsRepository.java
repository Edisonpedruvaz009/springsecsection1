package com.edison.springsecsection1.repository;

import com.edison.springsecsection1.model.AccountTransactions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTransactionsRepository extends CrudRepository<AccountTransactions, String> {
    List<AccountTransactions> findByCustomerIdOrderByTransactionDtDesc(long customerId);
}
