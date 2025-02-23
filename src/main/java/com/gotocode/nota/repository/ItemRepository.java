package com.gotocode.nota.repository;

import com.gotocode.nota.entity.ItemTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemTransaction, Long> {

}
