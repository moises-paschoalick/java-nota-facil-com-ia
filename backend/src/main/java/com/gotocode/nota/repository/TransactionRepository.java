package com.gotocode.nota.repository;

import com.gotocode.nota.dto.TransactionSumDTO;
import com.gotocode.nota.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    @Query("SELECT obj FROM Transaction obj JOIN FETCH obj.itens WHERE obj IN :transactions")
    List<Transaction> findTransactionsWithItens(List<Transaction> transactions);

    @Query("SELECT t FROM Transaction t LEFT JOIN FETCH t.itens WHERE t.id = :id")
    Optional<Transaction> findByItensId(Long id);

    @Query("SELECT SUM(t.valor) FROM Transaction t")
    BigDecimal sumAllTransactions();

    @Query("SELECT new com.gotocode.nota.dto.TransactionSumDTO(SUM(t.valor))" +
            " FROM Transaction t")
    TransactionSumDTO getSumDTO();
}
