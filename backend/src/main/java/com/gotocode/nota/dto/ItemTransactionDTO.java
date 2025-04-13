package com.gotocode.nota.dto;

import com.gotocode.nota.entity.ItemTransaction;
import com.gotocode.nota.utils.ConvertCurrency;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class ItemTransactionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String codigoProduto;
    private String nomeProduto;
    private String descricao;
    private String quantidade;
    private String unidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;

    public ItemTransactionDTO(ItemTransaction entity) {
        id = entity.getId();
        codigoProduto = entity.getCodigoProduto();
        nomeProduto = entity.getNomeProduto();
        descricao = entity.getDescricao();
        quantidade = entity.getQuantidade();
        unidade = entity.getUnidade();
        valorUnitario = entity.getValorUnitario();
        valorTotal = entity.getValorTotal();
    }

    public void setValorUnitario(String valorUnitario) {
        this.valorUnitario = ConvertCurrency.convertToBigDecimal(valorUnitario);
    }

    public void setSubtotal(String subtotal) {
        this.valorTotal = ConvertCurrency.convertToBigDecimal(subtotal);
    }

}