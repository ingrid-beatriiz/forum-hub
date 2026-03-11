#  ForumHub - Challenge Back-End (Alura/ONE)

Este projeto é uma API REST para um fórum de discussões, desenvolvido como parte do desafio final do módulo de Back-End da formação Oracle Next Education (ONE) em parceria com a Alura. O foco principal é o gerenciamento de tópicos (CRUD) e a segurança de acesso.

## 🚀 Funcionalidades

A API realiza o gerenciamento completo de tópicos com as seguintes rotas:

* **POST /topicos**: Cadastra um novo tópico. (Bloqueia duplicados com mesmo título e mensagem).
* **GET /topicos**: Lista todos os tópicos (Com paginação de 10 itens e ordenação por data).
* **GET /topicos/{id}**: Detalha um tópico específico.
* **PUT /topicos/{id}**: Atualiza as informações de um tópico.
* **DELETE /topicos/{id}**: Remove um tópico do banco de dados.

## 🔐 Segurança e Autenticação

Implementamos um controle de acesso rigoroso seguindo as melhores práticas do modelo REST:
* **Spring Security**: Configurado para autenticação Stateless.
* **JSON Web Token (JWT)**: Todas as requisições (exceto o login) exigem um token válido no cabeçalho `Authorization`.

## 🛠️ Tecnologias Utilizadas

* **Java 17** & **Spring Boot 3**
* **Maven** (Gerenciador de dependências)
* **MySQL** (Banco de dados relacional)
* **Spring Data JPA** & **Hibernate**
* **Flyway** (Migrações do banco de dados)
* **Auth0 JWT** (Geração e validação de tokens)
* **Lombok** (Produtividade no código)

## 📸 Demonstração dos Testes

### Autenticação (Login)
Para acessar a API, primeiro deve-se obter o token através do endpoint `/login`.

![Login no Insomnia](![alt text](image.png))

*O token retornado deve ser inserido como **Bearer Token** nas próximas requisições.*

## 📖 Como Rodar o Projeto

1.  Clone este repositório.
2.  Certifique-se de ter o **MySQL** rodando.
3.  Configure as credenciais do seu banco no arquivo `src/main/resources/application.properties`.
4.  Execute a aplicação (as tabelas e o usuário de teste serão criados automaticamente pelo Flyway).

### 🔑 Usuário de Teste (Padrão)
Para facilitar a avaliação, o sistema já conta com um usuário pré-cadastrado via migration:
* **Login:** `ingrid@alura.com`
* **Senha:** `123456`

---