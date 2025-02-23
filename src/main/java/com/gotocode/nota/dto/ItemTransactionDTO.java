package com.gotocode.nota.dto;

import com.gotocode.nota.entity.ItemTransaction;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class ItemTransactionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

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


    public ItemTransactionDTO(ItemTransaction entity) {
        id = entity.getId();
        nomeProduto = entity.getNomeProduto();
        descricao = entity.getDescricao();
        quantidade = entity.getQuantidade();
        unidade = entity.getUnidade();
        precoUnitario = entity.getPrecoUnitario();
        subtotal = entity.getSubtotal();
        taxaImposto = entity.getTaxaImposto();
        valorImposto = entity.getValorImposto();
        precoTotal = entity.getPrecoTotal();
    }

    public void setPrecoUnitario(String precoUnitario) {
        this.precoUnitario = new BigDecimal(precoUnitario.replace(",", "."));
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = new BigDecimal(subtotal.replace(",", "."));
    }

    public void setTaxaImposto(String taxaImposto) {
        this.taxaImposto = new BigDecimal(taxaImposto.replace(",", "."));
    }

    public void setValorImposto(String valorImposto) {
        this.valorImposto = new BigDecimal(valorImposto.replace(",", "."));
    }

    public void setPrecoTotal(String precoTotal) {
        this.precoTotal = new BigDecimal(precoTotal.replace(",", "."));
    }
}