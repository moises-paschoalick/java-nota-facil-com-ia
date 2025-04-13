package com.gotocode.nota.utils;

import java.math.BigDecimal;

public class ConvertCurrency {

    public static BigDecimal convertToBigDecimal(String value) {
        if(value == null || value.trim().isEmpty() || value.contains("não")) {
            return BigDecimal.ZERO;
        }

        // Remove "R$", "%", espaços extras e substitui "," por "."
        String numericValue = value.replaceAll("[^0-9,.-]", "").replace(",", ".");
        //numericValue.replace("R$", "").replace(",",".").trim();
        //numericValue.replace("%", "").replace(",",".").trim();

        try {
            return new BigDecimal(numericValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Valor inválido para conversão: " + value, e);
        }

    }

}
