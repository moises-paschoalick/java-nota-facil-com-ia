package com.gotocode.nota.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class ItemTransaction {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transacao_id", nullable = false)
    private Transaction transacao;

    private String nomeProduto;
    private String codigoProduto;
    private String descricao;
    private String quantidade;
    private String unidade;
    private BigDecimal precoUnitario;
    private BigDecimal subtotal;
    private BigDecimal taxaImposto;
    private BigDecimal valorImposto;
    private BigDecimal precoTotal;

}
