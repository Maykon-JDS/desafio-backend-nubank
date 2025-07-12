# Desafio Back-End Nubank

![Foto de Capa](assets/imgs/foto-de-capa.png)

[![licence mit](https://img.shields.io/badge/licence-MIT-blue.svg)](./LICENSE)
[![Conventional Commits](https://img.shields.io/badge/Conventional%20Commits-1.0.0-%23FE5196?logo=conventionalcommits&logoColor=white)](https://conventionalcommits.org)

## Sumário
- [Desafio Back-End Nubank](#desafio-back-end-nubank)
  - [Sumário](#sumário)
  - [Introdução](#introdução)
    - [Requisitos Técnicos](#requisitos-técnicos)
    - [Requisitos de Código](#requisitos-de-código)
    - [Diferenciais (Não obrigatórios)](#diferenciais-não-obrigatórios)
  - [Tecnologias Usadas](#tecnologias-usadas)
  - [Licença](#licença)

## Introdução
Construa uma API REST para gerenciamento de clientes e seus contatos. Cada cliente pode ter um ou mais contatos associados.

### Requisitos Técnicos

A aplicação deve conter:

- Cadastro de Cliente: `POST /clientes`
- Cadastro de Contato associado a um cliente existente: `POST /contatos`
- Listagem de todos os clientes com seus contatos: `GET /clientes`
- Listagem de contatos de um cliente específico: `GET /clientes/{id}/contatos`
- Uso do **Spring Boot** + **Spring Data JPA**
- Banco de Dados **PostgreSQL**
- Entidades **Cliente** e **Contato** com relacionamento `@OneToMany` / `@ManyToOne`

### Requisitos de Código

Esperamos que o código siga boas práticas de desenvolvimento, incluindo:

- Separação de responsabilidades (`controller`, `service`, `repository`)
- Uso de **DTOs** para entrada e saída de dados
- Tratamento adequado de erros
- Uso de **Lombok**

### Diferenciais (Não obrigatórios)

- Uso de **Docker** para subir o PostgreSQL
- **Testes automatizados**
- Documentação com **Swagger**

## Tecnologias Usadas
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)&nbsp;
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)&nbsp;
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-007396?style=for-the-badge&logo=hibernate&logoColor=white)&nbsp;
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white)&nbsp;
![Lombok](https://img.shields.io/badge/Lombok-FF0000?style=for-the-badge&logo=lombok&logoColor=white)&nbsp;
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)&nbsp;

<!-- ## Estrutura do Projeto -->

<!-- Para entender a estrutura do projeto, consulte [Estrutura do Projeto](link_para_o_documentacao_estrutura.md).  -->
<!-- TODO: Implement it -->

<!-- ## Como Executar o Projeto -->

<!-- Para obter instruções sobre como executar o projeto, consulte [Como Executar o Projeto](link_para_o_documentacao_execucao.md).  -->
<!-- TODO: Implement it -->

## Licença
Este projeto está sob a [Licença MIT](./LICENSE.md). Consulte o arquivo [LICENSE.md](LICENSE.md) para obter mais detalhes.
