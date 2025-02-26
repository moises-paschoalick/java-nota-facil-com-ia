package com.gotocode.nota.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Dados da transação
    private BigDecimal valor;
    private BigDecimal subTotal;
    private BigDecimal valorPago;
    private Long qtdeTotalItens;
    private String formaPagamento;
    private BigDecimal taxaImposto;
    private BigDecimal valorImposto;
    private String tributoFederal;
    private String tributoEstadual;

    // Dados da nota
    private String nomeEstabelecimento;
    private String cidade;
    private String endereco;
    private String cnpj;
    private String cpf;
    private String consumidor;
    private String inscricaoEstadual;
    private String chaveAcesso;

    @OneToMany(mappedBy = "transacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemTransaction> itens = new ArrayList<>();


}
