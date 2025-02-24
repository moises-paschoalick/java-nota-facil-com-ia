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

    private BigDecimal valor;
    private String nomeEstabelecimento;
    private String cidade;
    private String endereco;
    private String cnpj;
    private String cpf;
    private String inscricaoEstadual;

    @OneToMany(mappedBy = "transacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemTransaction> itens = new ArrayList<>();


}
