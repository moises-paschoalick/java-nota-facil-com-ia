package com.gotocode.nota.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_transaction")
public class ItemTransaction {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transacao_id", nullable = false)
    private Transaction transacao;

    private String codigoProduto;
    private String nomeProduto;
    private String descricao;
    private String quantidade;
    private String unidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
}
