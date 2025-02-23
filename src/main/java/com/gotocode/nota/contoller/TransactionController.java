package com.gotocode.nota.contoller;


import com.gotocode.nota.dto.TransactionDTO;
import com.gotocode.nota.entity.Transaction;
import com.gotocode.nota.services.TransactionService;
import jakarta.persistence.Access;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transaction")
@RequiredArgsConstructor
public class TransactionController {

    @Autowired
    private final TransactionService transactionService;

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
