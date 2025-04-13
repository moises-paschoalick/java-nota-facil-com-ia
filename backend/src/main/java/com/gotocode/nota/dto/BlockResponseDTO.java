package com.gotocode.nota.dto;

import lombok.Data;

@Data
public class BlockResponseDTO {
    private String id;
    private String text;
    private Double confidence;

    public BlockResponseDTO(String id, String text, Double confidence) {
        this.id = id;
        this.text = text;
        this.confidence = confidence;
    }
}