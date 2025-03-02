package com.gotocode.nota.contoller;


import com.gotocode.nota.dto.TransactionSumDTO;
import com.gotocode.nota.dto.TransactionDTO;
import com.gotocode.nota.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/transaction")
@RequiredArgsConstructor
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Validated TransactionDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Erro(s): ");
            result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append(" "));
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        dto = transactionService.save(dto);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/total")
    public ResponseEntity<TransactionSumDTO> getTotalAmount(){
        //BigDecimal list = transactionService.sumAllTransactions();
        TransactionSumDTO total = transactionService.sumAllTransactionsByDTO();
        return ResponseEntity.ok(total);
    }

}
