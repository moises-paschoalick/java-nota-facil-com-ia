package com.gotocode.nota.services;

import com.gotocode.nota.dto.ItemTransactionDTO;
import com.gotocode.nota.dto.TransactionDTO;
import com.gotocode.nota.entity.ItemTransaction;
import com.gotocode.nota.entity.Transaction;
import com.gotocode.nota.repository.ItemRepository;
import com.gotocode.nota.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public TransactionDTO save(TransactionDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setValor(dto.getValor());
        transaction.setSubTotal(dto.getSubTotal());
        transaction.setValorPago(dto.getValorPago());
        transaction.setNomeEstabelecimento(dto.getNomeEstabelecimento());

        transaction.setQtdeTotalItens(dto.getQtdeTotalItens());
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

}
