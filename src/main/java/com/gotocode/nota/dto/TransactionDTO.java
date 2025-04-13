package com.gotocode.nota.dto;

import com.gotocode.nota.entity.ItemTransaction;
import com.gotocode.nota.entity.Transaction;
import com.gotocode.nota.utils.ConvertCurrency;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class TransactionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    // Dados da transação
    private LocalDate dataEmissao;
    private BigDecimal valor;
    private BigDecimal subTotal;
    private BigDecimal valorPago;
    private Long qtdeTotalItens;
    private String formaPagamento;
    private String taxaImposto; // Pode ser uma descrição
    private BigDecimal valorImposto;
    private String tributoFederal;
    private String tributoEstadual;

    // Dados da nota
    private String nomeEstabelecimento;
    private String fone;
    private String cep;
    private String cidade;
    private String endereco;
    private String cnpj;
    private String cpf;
    private String consumidor;
    private String inscricaoEstadual;
    private String chaveAcesso;

    private List<ItemTransactionDTO> itens = new ArrayList<>();


    public TransactionDTO(Transaction entity) {
        id = entity.getId();
        this.dataEmissao = entity.getDataEmissao();
        this.valor = entity.getValor();
        this.subTotal = entity.getSubTotal();
        this.valorPago = entity.getValorPago();
        this.qtdeTotalItens = entity.getQtdeTotalItens();
        this.formaPagamento = entity.getFormaPagamento();
        this.taxaImposto = entity.getTaxaImposto();
        this.valorImposto = entity.getValorImposto();
        this.tributoFederal = entity.getTributoFederal();
        this.tributoEstadual = entity.getTributoEstadual();

        this.nomeEstabelecimento = entity.getNomeEstabelecimento();
        this.fone = entity.getFone();
        this.cep = entity.getCep();
        this.cidade = entity.getCidade();
        this.endereco = entity.getEndereco();
        this.cnpj = entity.getCnpj();
        this.cpf = entity.getCpf();
        this.consumidor = entity.getConsumidor();
        this.inscricaoEstadual = entity.getInscricaoEstadual();
        this.chaveAcesso = entity.getChaveAcesso();

        this.itens = entity.getItens().stream()
                .map(ItemTransactionDTO::new)
                .collect(Collectors.toList());

    }

    public TransactionDTO(Transaction entity, Set<ItemTransaction> itemTransactions) {
        this(entity);
        itemTransactions.forEach(item -> this.itens.add(new ItemTransactionDTO(item)));
    }


    public void setDataEmissao(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            this.dataEmissao = null;
            return;
        }
        
        valor = valor.trim();
        try {
            if(valor.length() == 10){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                this.dataEmissao = LocalDate.parse(valor, formatter);
            }else{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(valor, formatter);
                this.dataEmissao = dateTime.toLocalDate();
            }
        } catch (DateTimeParseException e) {
            this.dataEmissao = null;
            //throw new IllegalArgumentException("Formato de data inválido. Use dd/MM/yyyy.");
        }
    }

    public void setValor(String valor) {
        this.valor = ConvertCurrency.convertToBigDecimal(valor);
    }

    public void setSubTotal(String valor) {
        this.subTotal = ConvertCurrency.convertToBigDecimal(valor);
    }

    public void setValorPago(String valor) {
        this.valorPago = ConvertCurrency.convertToBigDecimal(valor);
    }

    public void setValorImposto(String valorImposto) {
        this.valorImposto = ConvertCurrency.convertToBigDecimal(valorImposto);
    }

    public void setQtdeTotalItens(String value) {
        try {
            this.qtdeTotalItens = Long.parseLong(value.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            this.qtdeTotalItens = 0L;
        }
    }


}
