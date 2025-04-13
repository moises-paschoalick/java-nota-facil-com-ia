package com.gotocode.nota.services;

import com.gotocode.nota.dto.ItemTransactionDTO;
import com.gotocode.nota.entity.ItemTransaction;
import com.gotocode.nota.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemTransactionService {

    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public List<ItemTransactionDTO> findByTransactionId(Long transactionId) {
        List<ItemTransaction> itens = itemRepository.findByTransacaoId(transactionId);
        return itens.stream()
                .map(ItemTransactionDTO::new)
                .collect(Collectors.toList());
    }
}