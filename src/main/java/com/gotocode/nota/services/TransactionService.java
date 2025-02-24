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
        transaction.setNomeEstabelecimento(dto.getNomeEstabelecimento());

        List<ItemTransaction> items = new ArrayList<>();
        for(ItemTransactionDTO itemDto: dto.getItens()) {
            ItemTransaction item = ItemTransaction.builder()
                    .nomeProduto(itemDto.getNomeProduto())
                    .codigoProduto(itemDto.getCodigoProduto())
                    .descricao(itemDto.getDescricao())
                    .quantidade(itemDto.getQuantidade())
                    .unidade(itemDto.getUnidade())
                    .precoUnitario(itemDto.getPrecoUnitario())
                    .subtotal(itemDto.getSubtotal())
                    .taxaImposto(itemDto.getTaxaImposto())
                    .precoTotal(itemDto.getPrecoTotal())
                    .build();

            item.setTransacao(transaction);
            items.add(item);
        }

        transaction.setItens(items);
        transaction = transactionRepository.save(transaction);

        return new TransactionDTO(transaction);
    }

}
