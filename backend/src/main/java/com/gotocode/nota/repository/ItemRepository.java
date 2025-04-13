package com.gotocode.nota.repository;

import com.gotocode.nota.entity.ItemTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemTransaction, Long> {

    List<ItemTransaction> findByTransacaoId(Long transacaoId);

}
