package com.gotocode.nota.controller;

import com.gotocode.nota.dto.ItemTransactionDTO;
import com.gotocode.nota.services.ItemTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping(value = "/itens")
@RequiredArgsConstructor
public class ItemController {

    private final ItemTransactionService itemTransactionService;

    @GetMapping(value = "/{transactionId}")
    public ResponseEntity<List<ItemTransactionDTO>> findByTransactionId(@PathVariable Long transactionId) {
        List<ItemTransactionDTO> itens = itemTransactionService.findByTransactionId(transactionId);
        return ResponseEntity.ok(itens);
    }
}