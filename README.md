# Documentação do Sistema de Gerenciamento de Usuários

## Arquitetura do Sistema

O Sistema de Gerenciamento de Usuários é uma aplicação desenvolvida com uma arquitetura baseada em microserviços, utilizando tecnologias modernas para o backend. A arquitetura geral do sistema é composta por:

### Backend (Java/Spring Boot)

- Desenvolvido em Java 17 utilizando o framework Spring Boot.
- Utiliza Spring Data JPA com Hibernate para mapeamento objeto-relacional e acesso ao banco de dados PostgreSQL.
- Implementa um sistema de segurança com Spring Security e JWT (JSON Web Token) para autenticação e controle de acesso às APIs.
- Integração com o Flyway para controle de versionamento e migração do banco de dados.

### Banco de Dados

- PostgreSQL é utilizado como banco de dados principal para armazenamento dos dados de usuários e departamentos.
- O esquema do banco de dados é gerenciado e versionado através do Flyway, garantindo consistência com as alterações no código.

## Tecnologias Utilizadas

- **Backend:**
  - Java 17
  - Spring Boot 2.6.x
  - Spring Data JPA com Hibernate
  - Spring Security com JWT
  - Flyway para migração do banco de dados

## Como Executar o Projeto Localmente

Para executar o projeto localmente, siga os passos abaixo:

1. **Configuração do Ambiente:**
   - Instale o JDK Java 17 ou superior.
   - Configure o PostgreSQL e crie um banco de dados vazio.

2. **Configuração do Projeto:**
   - Clone o repositório do projeto do GitHub:
     ```
     git clone https://github.com/seu-usuario/seu-projeto.git
     ```
   - Navegue até o diretório do projeto backend.

3. **Configuração do Banco de Dados:**
   - Configure as propriedades de conexão com o banco de dados no arquivo `application.properties` ou `application.yml` do Spring Boot.

4. **Execução do Backend:**
   - Compile e execute o projeto Spring Boot utilizando o Gradle ou Maven:
     ```
     ./gradlew bootRun
     ```
     ou
     ```
     mvn spring-boot:run
     ```

5. **Teste das APIs:**
   - Use ferramentas como Postman ou curl para enviar requisições HTTP para as APIs implementadas no backend.

Este guia fornece uma visão geral da arquitetura, tecnologias utilizadas e passos básicos para configurar e executar o Sistema de Gerenciamento de Usuários localmente.
