// @ts-ignore
import React, { useEffect, useState, useCallback } from "react";
import axios from "axios";
import { BASE_URL } from "utils/requests";
import { Transaction, TransactionItem, TransactionPage } from "types/transactions";
import { formatDate } from "utils/format";
import Pagination from "components/Pagination";
import "./styles.css";

const DataTable = () => {
    const [activePage, setActivePage] = useState(0);
    const [transactions, setTransactions] = useState<Transaction[]>([]);
    const [selectedTransaction, setSelectedTransaction] = useState<Transaction | null>(null);
    const [transactionItems, setTransactionItems] = useState<TransactionItem[]>([]);
    const [showModal, setShowModal] = useState(false);
    const [page, setPage] = useState<TransactionPage>({
        first: true,
        last: true,
        number: 0,
        totalElements: 0,
        totalPages: 0,
        size: 20,
        numberOfElements: 0,
        empty: true,
        content: []
    });

    const fetchTransactions = useCallback(async () => {
        try {
            const response = await axios.get(`${BASE_URL}/transactions?page=${activePage}&size=20&sort=dataEmissao,desc`);
            setTransactions(response.data.content);
            setPage(response.data);
        } catch (error) {
            console.error("Erro ao buscar transações:", error);
            setTransactions([]);
        }
    }, [activePage]);

    useEffect(() => {
        fetchTransactions();
        
        // Configura o intervalo para atualizar a cada 5 segundos
        const interval = setInterval(fetchTransactions, 5000);
        
        // Limpa o intervalo quando o componente é desmontado
        return () => clearInterval(interval);
    }, [fetchTransactions]);

    const handleTransactionClick = async (transaction: Transaction) => {
        setSelectedTransaction(transaction);
        try {
            const response = await axios.get(`${BASE_URL}/itens/${transaction.id}`);
            setTransactionItems(response.data);
            setShowModal(true);
        } catch (error) {
            console.error("Erro ao buscar itens da transação:", error);
            setTransactionItems([]);
        }
    };

    const handlePageChange = (index: number) => {
        setActivePage(index);
    };

    const formatCurrency = (value: number | null | undefined) => {
        return value !== null && value !== undefined ? `R$ ${value.toFixed(2)}` : 'R$ 0,00';
    };

    return (
        <>
            <div className="table-responsive">
                <table className="table table-striped table-sm">
                    <thead>
                        <tr>
                            <th>Data</th>
                            <th>Estabelecimento</th>
                            <th>Valor Total</th>
                            <th>Forma de Pagamento</th>
                            <th>Qtde. Itens</th>
                        </tr>
                    </thead>
                    <tbody>
                        {transactions.map(transaction => (
                            <tr key={transaction.id} onClick={() => handleTransactionClick(transaction)} style={{ cursor: 'pointer' }}>
                                <td>{formatDate(transaction.dataEmissao)}</td>
                                <td>{transaction.nomeEstabelecimento}</td>
                                <td>{formatCurrency(transaction.valor)}</td>
                                <td>{transaction.formaPagamento}</td>
                                <td>{transaction.qtdeTotalItens}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>

            <Pagination page={page} onPageChange={handlePageChange} />

            {showModal && selectedTransaction && (
                <div className="modal" style={{ display: 'block' }}>
                    <div className="modal-dialog modal-lg">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title">Detalhes da Transação</h5>
                                <button type="button" className="btn-close" onClick={() => setShowModal(false)}></button>
                            </div>
                            <div className="modal-body">
                                <div className="row">
                                    <div className="col-md-6">
                                        <p><strong>Estabelecimento:</strong> {selectedTransaction.nomeEstabelecimento}</p>
                                        <p><strong>Data:</strong> {formatDate(selectedTransaction.dataEmissao)}</p>
                                        <p><strong>Valor Total:</strong> {formatCurrency(selectedTransaction.valor)}</p>
                                        <p><strong>Forma de Pagamento:</strong> {selectedTransaction.formaPagamento}</p>
                                    </div>
                                    <div className="col-md-6">
                                        <p><strong>CNPJ:</strong> {selectedTransaction.cnpj}</p>
                                        <p><strong>Endereço:</strong> {selectedTransaction.endereco}</p>
                                        <p><strong>Cidade:</strong> {selectedTransaction.cidade}</p>
                                        <p><strong>CEP:</strong> {selectedTransaction.cep}</p>
                                    </div>
                                </div>
                                <div className="mt-4">
                                    <h6>Itens da Transação</h6>
                                    <table className="table table-sm">
                                        <thead>
                                            <tr>
                                                <th>Descrição</th>
                                                <th>Quantidade</th>
                                                <th>Valor Unitário</th>
                                                <th>Valor Total</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {transactionItems.map(item => (
                                                <tr key={item.id}>
                                                    <td>{item.descricao}</td>
                                                    <td>{item.quantidade} {item.unidade}</td>
                                                    <td>{formatCurrency(item.valorUnitario)}</td>
                                                    <td>{formatCurrency(item.valorTotal)}</td>
                                                </tr>
                                            ))}
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </>
    );
};

export default DataTable;