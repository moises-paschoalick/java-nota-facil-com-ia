package com.gotocode.nota.services;

import com.gotocode.nota.dto.ItemTransactionDTO;
import com.gotocode.nota.dto.TransactionDTO;
import com.gotocode.nota.dto.TransactionSumDTO;
import com.gotocode.nota.entity.ItemTransaction;
import com.gotocode.nota.entity.Transaction;
import com.gotocode.nota.repository.ItemRepository;
import com.gotocode.nota.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public TransactionDTO findById(Long id) {
        Transaction transaction = transactionRepository.findByItensId(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada"));
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

    @Transactional(readOnly = true)
    public Page<TransactionDTO> getAllTransactions(Pageable pageable) {
        Page<Transaction> page = transactionRepository.findAll(pageable);

        List<Transaction> enrichedTransactions = transactionRepository.findTransactionsWithItens(page.getContent());

        Map<Long, Transaction> transactionMap = enrichedTransactions.stream()
                .collect(Collectors.toMap(Transaction::getId, t -> t));

        List<TransactionDTO> dtos = page.getContent().stream()
                .map(transaction -> new TransactionDTO(transactionMap.get(transaction.getId())))
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }
}
