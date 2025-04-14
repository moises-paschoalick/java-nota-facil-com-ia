# 📄 Conversão e Extração de Dados de Notas Fiscais

## 📌 Sobre o Projeto

Este projeto tem como objetivo extrair e converter dados de notas fiscais eletrônicas (NFe) para um formato estruturado, facilitando a análise e integração com outros sistemas. A API recebe um JSON com as informações da nota e mapeia os dados para um modelo padronizado.

## 🚀 Funcionalidades
- Recebimento de dados de notas fiscais em JSON
- Conversão de valores monetários para formatos adequados
- Extração e padronização de informações da nota
- Estruturação de itens da compra
- Validação e tratamento de dados inconsistentes

## 🏗️ Estrutura do Projeto

O projeto está organizado em duas partes principais:

### Backend (/backend)
- API REST em Java com Spring Boot
- Processamento e validação dos dados
- Integração com banco de dados
- Endpoints para recebimento e consulta de notas

### Frontend (/frontweb)
- Interface web em React
- Visualização das notas processadas
- Dashboard com análises e estatísticas
- Interface responsiva e moderna

## 📥 Exemplo de Payload de Entrada

```json
{
  "valor": "R$ 7,86",
  "nomeEstabelecimento": "COMERCIO DE ALIMENTOS GOIANEIRO LTDA ME",
  "cep": "Não informado",
  "endereço": "RUA LEBLON. 20 - TABAJARAS, UBERLANDIA - MG",
  "cnpj": "13.598.301/0001-00",
  "inscricaoEstadual": "0017684600079",
  "fone": "Não informado",
  "itens": [
    {
      "descricao": "PÃO DE QUEIJO",
      "quantidade": "0.13 KG",
      "valorUnitario": "R$ 37,90",
      "subtotal": "R$ 4,93"
    },
    {
      "descricao": "PAO FRANCES",
      "quantidade": "0.14 KG",
      "valorUnitario": "R$ 20,90",
      "subtotal": "R$ 2,93"
    }
  ],
  "valorTotal": "R$ 7,86",
  "consumidor": "CONSUMIDOR NÃO IDENTIFICADO",
  "numeroCupom": "000.623.308",
  "dataEmissao": "18/02/2026"
}
```

## 🚀 Como Executar

### Backend
```bash
cd backend
./mvnw spring-boot:run
```

### Frontend
```bash
cd frontweb
yarn install
yarn start
```

O frontend estará disponível em `http://localhost:3000` e o backend em `http://localhost:8080`.
