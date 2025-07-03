# desafio-backend-nubank

# Desafio

Cosntrua uma API REST para gerenciamento de clientes e seus contatos. Cada cliente pode ter um ou mais contatos associados.

Requisitos Técnicos

Sua aplicação deve conter:

- Cadastro de Cliente [POST /clientes]
- Cadastro de Contato [POST /contatos] associado a um cliente existente
- Listagem de todos os clientes com seus contatos [GET /clietes]
- Listagem de contatos de um cliente específico [GET /clientes/{id}/contatos]
- Uso do Spring Boot + Spring Data JPA
- Banco de Dados PostgreSQL
- Entidades Cliente e Contato com realcionamento OneToMany / ManyToOne

## Requisitos de Código

Esperamos que seu código siga boas práticas de desenvolvimento, incluindo:

- Separação de responsabilidades [camadas: controller, service, repository]
- Uso de DTOs para entrada e saída de dados
- Tratamento adequado de erros
- Usar Lombok

## Diferenciais (Não Obrigatórios)
- Uso de Docker para subir o PostgreSQL
- Testes automatizados
- Documentação com Swagger