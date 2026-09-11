# ୨୧ Maintenance API

API REST desenvolvida como projeto final do **Bootcamp de Java da DIO em parceria com o Itaú**.

O projeto foi criado do zero a partir da proposta de aplicar os conceitos de **Design Patterns** estudados durante a formação, utilizando um contexto diferente dos exemplos apresentados no bootcamp.

⊹ ˚₊ ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯ ₊˚ ⊹

## ⌁ Sobre o projeto

A **Maintenance API** simula o gerenciamento de serviços de manutenção técnica.

A aplicação permite cadastrar, consultar, atualizar e excluir manutenções. O valor do serviço é calculado automaticamente de acordo com a prioridade informada.

Além da aplicação dos padrões de projeto, o projeto trabalha com:

* API REST
* Spring Boot
* MongoDB
* DTOs e validação
* tratamento global de exceções
* OpenAPI e Swagger UI
* injeção de dependências

## ୨୧ Tecnologias

`Java 17` · `Spring Boot 4.1.1` · `Spring Web MVC`
`Spring Data MongoDB` · `MongoDB Atlas`
`Bean Validation` · `OpenAPI 3.1` · `Swagger UI`
`Maven` · `Git` · `GitHub`

## ⊹ Design Patterns

O foco principal do desafio foi utilizar padrões de projeto para organizar responsabilidades dentro da aplicação.

### Strategy

Utilizado no cálculo do valor da manutenção.

Cada prioridade possui uma estratégia própria:

| Prioridade |     Valor |
| ---------- | --------: |
| `BAIXA`    |  R$ 50,00 |
| `MEDIA`    |  R$ 80,00 |
| `ALTA`     | R$ 120,00 |

A interface `CalculoValorStrategy` define o contrato das estratégias, enquanto `CalculoOrcamentoService` identifica qual implementação deve ser utilizada.

```text
CalculoValorStrategy
├── CalculoValorBaixaStrategy
├── CalculoValorMediaStrategy
└── CalculoValorAltaStrategy
```

A estrutura permite adicionar novas regras de cálculo sem concentrar todas as possibilidades em uma única classe.

### Facade

A `ManutencaoFacade` organiza o fluxo de criação e atualização de uma manutenção.

Ela coordena o cálculo do orçamento e a persistência do registro, mantendo o controller mais simples.

```text
Controller
    ↓
ManutencaoFacade
    ↓
CalculoOrcamentoService
    ↓
Strategy
    ↓
Repository
    ↓
MongoDB
```

### Singleton

O Singleton é utilizado através do comportamento padrão do container do Spring.

Os beans gerenciados por `@Service` e `@Component` possuem, por padrão, escopo `singleton`.

Por isso, não foi necessário criar manualmente uma implementação utilizando construtor privado e instância estática.

## ⌁ Estrutura

```text
maintenance-api/
├── src/
│   └── main/
│       └── java/
│           └── com/peche/maintenance_api/
│               ├── config/
│               │   └── OpenAPIConfig.java
│               │
│               ├── controller/
│               │   ├── GlobalExceptionHandler.java
│               │   └── ManutencaoController.java
│               │
│               ├── dto/
│               │   └── ManutencaoRequestDTO.java
│               │
│               ├── model/
│               │   └── Manutencao.java
│               │
│               ├── repository/
│               │   └── ManutencaoRepository.java
│               │
│               └── service/
│                   ├── ManutencaoService.java
│                   │
│                   ├── facade/
│                   │   └── ManutencaoFacade.java
│                   │
│                   └── strategy/
│                       ├── CalculoValorStrategy.java
│                       ├── CalculoValorBaixaStrategy.java
│                       ├── CalculoValorMediaStrategy.java
│                       ├── CalculoValorAltaStrategy.java
│                       └── CalculoOrcamentoService.java
│
├── .gitignore
├── pom.xml
└── README.md
```

O `.env` é utilizado apenas na configuração local e permanece fora do versionamento.

## ୨୧ Endpoints

| Método   | Endpoint            | Função                  |
| -------- | ------------------- | ----------------------- |
| `GET`    | `/manutencoes`      | Lista as manutenções    |
| `GET`    | `/manutencoes/{id}` | Busca uma manutenção    |
| `POST`   | `/manutencoes`      | Cria uma manutenção     |
| `PUT`    | `/manutencoes/{id}` | Atualiza uma manutenção |
| `DELETE` | `/manutencoes/{id}` | Exclui uma manutenção   |

## ⌁ Exemplo

### `POST /manutencoes`

```json
{
  "cliente": "Cliente Teste",
  "equipamento": "Notebook",
  "problema": "Sem retorno de video",
  "prioridade": "MEDIA",
  "status": "ABERTA"
}
```

O `valor` não é enviado pelo cliente.

A prioridade `MEDIA` seleciona a estratégia correspondente e resulta em um orçamento de **R$ 80,00**.

```json
{
  "cliente": "Cliente Teste",
  "equipamento": "Notebook",
  "id": "id-gerado-pelo-mongodb",
  "prioridade": "MEDIA",
  "problema": "Sem retorno de video",
  "status": "ABERTA",
  "valor": 80.0
}
```

## ୨୧ Validação

As requisições utilizam Bean Validation através de `@Valid`.

Exemplo:

```java
@NotBlank(message = "Cliente é obrigatório")
private String cliente;
```

As exceções de validação são tratadas pelo `GlobalExceptionHandler`.

Exemplo de resposta:

```json
{
  "message": "Dados inválidos",
  "errors": {
    "cliente": "Cliente é obrigatório"
  },
  "status": 400
}
```

## ⌁ Banco de dados

A aplicação utiliza **MongoDB Atlas** para persistência.

As manutenções são armazenadas na coleção:

```text
manutencoes
```

A conexão é configurada por variável de ambiente. O arquivo `.env` não é versionado.

## ⊹ OpenAPI

A API utiliza **OpenAPI 3.1** para especificação e **Swagger UI** para documentação e testes dos endpoints.

A configuração da documentação está em:

```text
src/main/java/com/peche/maintenance_api/config/OpenAPIConfig.java
```

A aplicação disponibiliza a documentação através do Swagger UI quando executada localmente.

## ୨୧ Executando o projeto

Clone o repositório:

```bash
git clone https://github.com/Pechebiloski/Projeto-dio
```

Entre na pasta:

```bash
cd maintenance-api
```

Configure a conexão com o MongoDB no `.env`.

Execute com o Maven Wrapper:

```bash
mvnw.cmd spring-boot:run
```

Ou:

```bash
./mvnw spring-boot:run
```

## ⌁ Aprendizados

Este projeto foi uma oportunidade de sair de exemplos isolados de Design Patterns e utilizá-los dentro de uma aplicação própria.

Durante o desenvolvimento, pratiquei:

`Spring Boot` · `MongoDB` · `REST` · `DTO` · `Validation`
`Dependency Injection` · `Exception Handling` · `OpenAPI`
`Strategy` · `Facade` · `Singleton` · `Git & GitHub`

Também foi uma prática de organização de responsabilidades entre controller, service, facade, strategy e repository.

## ୨୧ Sobre o desafio

Projeto desenvolvido para o desafio final de **Padrões de Projeto** do **Bootcamp de Java da DIO em parceria com o Itaú**.

A proposta escolhida foi **Criar do Zero**, desenvolvendo uma aplicação própria para colocar os conceitos estudados em prática.

⊹ ˚₊ ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯ ₊˚ ⊹

*projeto desenvolvido para estudo e portfólio.*
