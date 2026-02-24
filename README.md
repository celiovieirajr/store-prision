# store-prision

**Store Prison** — Aplicação monolítica Java Spring Boot para compra de produtos e geração de template de mensagem para WhatsApp.

## Sumário
- [Stack e versões](#stack-e-versões)
- [Visão geral da aplicação](#visão-geral-da-aplicação)
- [Estrutura do projeto](#estrutura-do-projeto)
- [Como rodar localmente](#como-rodar-localmente)
- [Testes e cobertura](#testes-e-cobertura)
- [Docker](#docker)
- [CI/CD Azure DevOps](#cicd-azure-devops)
- [Swagger API](#swagger-api)
- [Frontend](#frontend)
- [Geração de template WhatsApp](#geração-de-template-whatsapp)
- [Boas práticas e checklist](#boas-práticas-e-checklist)

## Stack e versões
- Java 17
- Spring Boot 3.x
- Maven 3.9+
- JaCoCo para coverage
- Docker
- Azure DevOps para CI/CD
- Springdoc OpenAPI (Swagger) para documentação

## Visão geral da aplicação
Aplicação monolítica que expõe APIs REST para:
- Autenticação de usuário
- Listagem e seleção de produtos
- Criação de pedido / cotação
- Geração de template de mensagem para WhatsApp com resumo do pedido
  Possui também uma página web (HTML/CSS/JS) para fluxo de compra.

## Estrutura do projeto
```text
com.example.prison
├── exceptions
│     └── RequiredField
│
├── modules
│     ├── dto
│     ├── mapper
│     ├── model
│     ├── repository
│     └── service
│
├── web
│     ├── CategoryController
│     ├── ItemSaleController
│     ├── PenitentiaryController
│     └── ProductController
│
└── PrisonApplication
```


