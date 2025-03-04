package com.gotocode.nota.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.textract.TextractClient;
import software.amazon.awssdk.services.textract.model.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VisionService {

    private final TextractClient textractClient = TextractClient.builder()
            .credentialsProvider(DefaultCredentialsProvider.create()) // Pega as credenciais da AWS
            .build();

    public String analyzeImage(MultipartFile file) throws IOException {
        // Converte a imagem para bytes
        byte[] imageBytes = file.getBytes();

        // Cria a requisição para o Textract
        DetectDocumentTextRequest request = DetectDocumentTextRequest.builder()
                .document(Document.builder().bytes(SdkBytes.fromByteArray(imageBytes)).build())
                .build();

        // Chama o serviço da AWS
        DetectDocumentTextResponse response = textractClient.detectDocumentText(request);

        List<Double> confidenceValues = response.blocks().stream()
                .filter(block -> block.blockType() == BlockType.LINE)
                .map(block -> Double.valueOf(block.confidence()))
                .collect(Collectors.toList());

        // Calcula a média de confiança
        Double averageConfidence = confidenceValues.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        // Processa a resposta e retorna o texto extraído
        String extractedText = response.blocks().stream()
                .filter(block -> block.blockType() == BlockType.LINE) // Filtra apenas as linhas de texto
                .map(block -> block.text() + ", " + block.confidence() + "\n")
                .collect(Collectors.joining());

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> resposneMap = Map.of(
                "data", extractedText,
                "averageConfidence", averageConfidence
        );

        return objectMapper.writeValueAsString(resposneMap);
    }

}