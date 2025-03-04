package com.gotocode.nota.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/textract")
@RequiredArgsConstructor
public class VisionController {

    @Autowired
    private com.gotocode.nota.services.VisionService textractService;

    @PostMapping("/analyze")
    public ResponseEntity<String> analyzeImage(@RequestParam("file") MultipartFile file) {
        try {
            String extractedText = textractService.analyzeImage(file);
            //String jsonResponse = new ObjectMapper().writeValueAsString(Map.of("data", extractedText));
            return ResponseEntity.ok().body(extractedText);
        } catch (IOException e) {
            log.error("Erro ao processar imagem", e);
            return ResponseEntity.internalServerError().body("Erro ao processar imagem");
        }
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("ok");
    }


}
