package com.gotocode.nota.services;

import com.gotocode.nota.dto.BlockResponseDTO;
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
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VisionService {

    private final TextractClient textractClient = TextractClient.builder()
            .credentialsProvider(DefaultCredentialsProvider.create()) // Pega as credenciais da AWS
            .build();


    public Map<String, Object> analyzeImage(MultipartFile file) throws IOException {
        byte[] imageBytes = file.getBytes();

        DetectDocumentTextRequest request = DetectDocumentTextRequest.builder()
                .document(Document.builder().bytes(SdkBytes.fromByteArray(imageBytes)).build())
                .build();

        DetectDocumentTextResponse response = textractClient.detectDocumentText(request);

        // Filtra os blocos do tipo LINE
        List<Block> lineBlocks = response.blocks().stream()
                .filter(block -> block.blockType() == BlockType.LINE)
                .collect(Collectors.toList());

        // Lista de textos concatenados com quebra de linha
        String extractedText = lineBlocks.stream()
                .map(Block::text)
                .collect(Collectors.joining(System.lineSeparator()));

        // Média de confiança
        Double averageConfidence = lineBlocks.stream()
                .mapToDouble(Block::confidence)
                .average()
                .orElse(0.0);

        // Gera lista de blocos com id, texto e confiança
        List<BlockResponseDTO> blocksList = lineBlocks.stream()
                .map(block -> new BlockResponseDTO(
                        block.id() != null ? block.id() : UUID.randomUUID().toString(),
                        block.text() != null ? block.text() : "",
                        block.confidence() != null ? block.confidence() : 0.0
                ))
                .collect(Collectors.toList());

        return Map.of(
                "data", extractedText,
                "averageConfidence", averageConfidence,
                "blocks", blocksList
        );
    }

}