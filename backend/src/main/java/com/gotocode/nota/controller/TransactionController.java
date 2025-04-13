package com.gotocode.nota.controller;


import com.gotocode.nota.dto.TransactionSumDTO;
import com.gotocode.nota.dto.TransactionDTO;
import com.gotocode.nota.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transactions")
@RequiredArgsConstructor
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TransactionDTO> findById(@PathVariable Long id) {
        TransactionDTO dto = transactionService.findById(id);
        return ResponseEntity.ok().body(dto);
    }


    @GetMapping
    public ResponseEntity<Page<TransactionDTO>> findAll(@PageableDefault(size=10, sort = "dataEmissao", direction = Sort.Direction.DESC) Pageable pageable){
        // Buscar todas transações
        Page<TransactionDTO> page = transactionService.getAllTransactions(pageable);
        return ResponseEntity.ok(page);
    };


    @GetMapping(value = "/total")
    public ResponseEntity<TransactionSumDTO> getTotalAmount(){
        //BigDecimal list = transactionService.sumAllTransactions();
        TransactionSumDTO total = transactionService.sumAllTransactionsByDTO();
        return ResponseEntity.ok(total);
    }


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

}
