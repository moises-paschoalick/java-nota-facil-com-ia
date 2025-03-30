package com.gotocode.nota.services;

import com.gotocode.nota.dto.ItemTransactionDTO;
import com.gotocode.nota.dto.TransactionDTO;
import com.gotocode.nota.dto.TransactionSumDTO;
import com.gotocode.nota.entity.ItemTransaction;
import com.gotocode.nota.entity.Transaction;
import com.gotocode.nota.repository.ItemRepository;
import com.gotocode.nota.repository.TransactionRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public TransactionDTO save(TransactionDTO dto) {
        Transaction transaction = new Transaction();

        // Dados da transação
        transaction.setDataEmissao(dto.getDataEmissao());
        transaction.setValor(dto.getValor());
        transaction.setSubTotal(dto.getSubTotal());
        transaction.setValorPago(dto.getValorPago());
        transaction.setQtdeTotalItens(dto.getQtdeTotalItens());
        transaction.setFormaPagamento(dto.getFormaPagamento());
        // Tributos
        transaction.setTaxaImposto(dto.getTaxaImposto());
        transaction.setValorImposto(dto.getValorImposto());
        transaction.setTributoEstadual(dto.getTributoEstadual());
        transaction.setTributoFederal(dto.getTributoFederal());

        // Dados do estabelecimento
        transaction.setNomeEstabelecimento(dto.getNomeEstabelecimento());
        transaction.setFone(dto.getFone());
        transaction.setCep(dto.getCep());
        transaction.setCidade(dto.getCidade());
        transaction.setEndereco(dto.getEndereco());
        transaction.setCnpj(dto.getCnpj());
        transaction.setCpf(dto.getCpf());
        transaction.setConsumidor(dto.getConsumidor());
        transaction.setInscricaoEstadual(dto.getInscricaoEstadual());
        transaction.setChaveAcesso(dto.getChaveAcesso());

        List<ItemTransaction> items = new ArrayList<>();
        for(ItemTransactionDTO itemDto: dto.getItens()) {
            ItemTransaction item = ItemTransaction.builder()
                    .codigoProduto(itemDto.getCodigoProduto())
                    .nomeProduto(itemDto.getNomeProduto())
                    .descricao(itemDto.getDescricao())
                    .quantidade(itemDto.getQuantidade())
                    .unidade(itemDto.getUnidade())
                    .valorUnitario(itemDto.getValorUnitario())
                    .valorTotal(itemDto.getValorTotal())
                    .build();

            item.setTransacao(transaction);
            items.add(item);
        }

        transaction.setItens(items);
        transaction = transactionRepository.save(transaction);

        return new TransactionDTO(transaction);
    }

    @Transactional(readOnly = true)
    public BigDecimal sumAllTransactions(){
        return transactionRepository.sumAllTransactions();
    }

    @Transactional(readOnly = true)
    public TransactionSumDTO sumAllTransactionsByDTO(){
        return transactionRepository.getSumDTO();
    }

    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> list = transactionRepository.findAll();
        return list.stream()
                .map(item -> new TransactionDTO(item))
                .collect(Collectors.toList());
    }
}
