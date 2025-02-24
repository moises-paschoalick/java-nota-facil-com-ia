package com.gotocode.nota.utils;

import java.math.BigDecimal;

public class ConvertCurrency {

    public static BigDecimal convertToBigDecimal(String value) {
        if(value == null || value.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(value.replace("R$", "").replace(",",".").trim());
    }

}
