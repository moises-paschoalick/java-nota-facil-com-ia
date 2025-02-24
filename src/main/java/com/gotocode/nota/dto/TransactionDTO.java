package com.gotocode.nota.dto;

import com.gotocode.nota.entity.ItemTransaction;
import com.gotocode.nota.entity.Transaction;
import com.gotocode.nota.utils.ConvertCurrency;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@RequiredArgsConstructor
public class TransactionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private BigDecimal valor;
    private String nomeEstabelecimento;
    private String cidade;
    private String endereco;
    private String cnpj;
    private String cpf;
    private String inscricaoEstadual;

    private List<ItemTransactionDTO> itens = new ArrayList<>();


    public TransactionDTO(Transaction entity) {
        id = entity.getId();
        valor = entity.getValor();
        nomeEstabelecimento = entity.getNomeEstabelecimento();
        cidade = entity.getCidade();
        endereco = entity.getEndereco();
        cnpj = entity.getCnpj();
        cpf = entity.getCpf();
        inscricaoEstadual = entity.getInscricaoEstadual();
    }

    public TransactionDTO(Transaction entity, Set<ItemTransaction> itemTransactions) {
        this(entity);
        itemTransactions.forEach(item -> this.itens.add(new ItemTransactionDTO(item)));
    }

    public void setValor(String valor) {
        this.valor = ConvertCurrency.convertToBigDecimal(valor);
    }

}
