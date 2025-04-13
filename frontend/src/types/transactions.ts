export type TransactionItem = {
    id: number;
    codigoProduto: string | null;
    nomeProduto: string | null;
    descricao: string;
    quantidade: string;
    unidade: string | null;
    valorUnitario: number;
    valorTotal: number;
}

export type Transaction = {
    id: number;
    dataEmissao: string;
    valor: number;
    subTotal: number;
    valorPago: number;
    qtdeTotalItens: number;
    formaPagamento: string;
    taxaImposto: string;
    valorImposto: number;
    tributoFederal: string | null;
    tributoEstadual: string | null;
    nomeEstabelecimento: string;
    fone: string | null;
    cep: string | null;
    cidade: string;
    endereco: string;
    cnpj: string;
    cpf: string | null;
    consumidor: string;
    inscricaoEstadual: string | null;
    chaveAcesso: string;
    itens: TransactionItem[];
}

export type TransactionPage = {
    content?: Transaction[];
    last: boolean;
    totalElements: number;
    totalPages: number;
    size?: number;
    number: number;
    first: boolean;
    numberOfElements?: number;
    empty?: boolean;
} 