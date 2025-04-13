# 📄 Conversão e Extração de Dados de Notas Fiscais

## 📌 Sobre o Projeto

Este projeto tem como objetivo extrair e converter dados de notas fiscais eletrônicas (NF-e) para um formato estruturado, facilitando a análise e integração com outros sistemas. A API recebe um JSON com as informações da nota e mapeia os dados para um modelo padronizado.

## 🚀 Funcionalidades
- Recebimento de dados de notas fiscais em JSON
- Conversão de valores monetários para formatos adequados
- Extração e padronização de informações da nota
- Estruturação de itens da compra
- Validação e tratamento de dados inconsistentes

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
  "dataEmissao": "18/02/2026",
  "tributos": "Federal R$:1,76, Estadual R$:0,33"
}
```

## 🔧 Tecnologias Utilizadas
- **Java** com **Spring Boot**
- **Lombok** para simplificação do código
- **JPA/Hibernate** para persistência de dados
- **BigDecimal** para manipulação de valores monetários
- **Git** para versionamento

## 📌 Como Rodar o Projeto
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```
2. Entre no diretório do projeto:
   ```bash
   cd seu-repositorio
   ```
3. Execute o projeto via Maven ou Gradle:
   ```bash
   ./mvnw spring-boot:run
   ```
   ou
   ```bash
   ./gradlew bootRun
   ```

## 📝 Licença
Este projeto está sob a licença MIT - sinta-se livre para usá-lo e melhorá-lo! 😊
