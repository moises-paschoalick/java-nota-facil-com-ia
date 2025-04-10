package com.gotocode.nota.contoller;

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
    public ResponseEntity<Map<String, Object>> analyzeImage(@RequestParam("file") MultipartFile file) {
        try {
            Map<String, Object> result = textractService.analyzeImage(file);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            log.error("Erro ao processar imagem", e);
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Erro ao processar imagem"));
        }
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("ok");
    }


}
