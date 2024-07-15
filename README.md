# Projeto API do Fórum Hub

Este projeto é uma API REST para o Fórum Hub, uma aplicação de fórum online.

## Configuração do Ambiente

### Pré-requisitos

- JDK 17 ou superior
- Maven 3.x
- MySQL

### Configuração do Banco de Dados

1. Crie um banco de dados MySQL chamado `forum_hub`.
2. Configure as credenciais do banco de dados em `src/main/resources/application.properties`.

Exemplo de configuração:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/forum_hub
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```
## Execução do Projeto
Para executar o projeto localmente, você pode usar o Maven. Na raiz do projeto, execute:
```properties
mvn spring-boot:run
```
O servidor será iniciado em http://localhost:8080.

## Endpoints Disponíveis
POST /login: Autenticação de usuários. Retorna um token JWT para acesso aos endpoints protegidos.
## Documentação da API
A documentação da API pode ser acessada em /swagger-ui.html, após iniciar o servidor.

##Dependências
Este projeto utiliza as seguintes dependências principais:

- Spring Boot
- Spring Security
- Spring Data JPA
- Lombok
- Flyway (para migrações de banco de dados)
- JWT (JSON Web Token) para autenticação
## Estrutura do Projeto
```properties
src
├── main
│   ├── java
│   │   └── forum
│   │       └── hub
│   │           ├── api
│   │           │   ├── controller       # Controladores da API
│   │           │   ├── domain
│   │           │   │   ├── dto         # DTOs (Data Transfer Objects)
│   │           │   │   ├── model       # Entidades do domínio
│   │           │   │   └── repository  # Repositórios JPA
│   │           │   ├── infra
│   │           │   │   ├── exception   # Tratamento de exceções
│   │           │   │   ├── security    # Configuração de segurança (Spring Security)
│   │           │   │   └── security
│   │           │   └── service         # Serviços da aplicação
│   │           └── Application.java    # Classe principal (ponto de entrada)
│   └── resources
│       ├── application.properties     # Configurações do Spring Boot
│       ├── data-migration             # Migrações de banco de dados (Flyway)
│       └── static                     # Recursos estáticos (opcional)
└── test                               # Testes unitários e de integração
```
## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para fazer um fork deste projeto e enviar um pull request com melhorias.

## Licença
Este projeto está licenciado sob a MIT License.
