package com.gotocode.nota.repository;

import com.gotocode.nota.dto.TransactionSumDTO;
import com.gotocode.nota.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT SUM(t.valor) FROM Transaction t")
    BigDecimal sumAllTransactions();

    @Query("SELECT new com.gotocode.nota.dto.TransactionSumDTO(SUM(t.valor))" +
            " FROM Transaction t")
    TransactionSumDTO getSumDTO();
}
