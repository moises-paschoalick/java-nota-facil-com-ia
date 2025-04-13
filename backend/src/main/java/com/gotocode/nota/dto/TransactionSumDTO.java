package com.gotocode.nota.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransactionSumDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal sum;

    public TransactionSumDTO(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
