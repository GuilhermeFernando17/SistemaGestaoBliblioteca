# Sistema de Gerenciamento de Biblioteca

Sistema web completo para gerenciamento de biblioteca, desenvolvido com Java 24 + Spring Boot 3.4.1 + MongoDB.


**Ferramentas Utilitzadas**
- **Java 24** + **Spring Boot 3.4.1**
- **Maven** (gerenciador de dependências)
- **MongoDB** (banco de dados não relacional)
- **Spring Security** + **BCrypt** (autenticação e criptografia)
- **Thymeleaf** (templates HTML)
- **Spring Data JPA** (persistência)

**Pré-requisitos**
- Java 24+ instalado
- Maven 3.9+ instalado

**Funcionalidades**
- **Login seguro** com BCrypt
- **Dashboard** com métricas em tempo real
- **Usuários** – CRUD completo (criar, listar, editar, excluir)
- **Materiais** – CRUD completo do acervo
- **Empréstimos** – controle com abas (Ativos / Atrasados / Devolvidos)
- **Reservas** – gerenciamento de reservas com status

**Como executar**

Passos
- Entre na pasta do projeto
- Compile e execute o comando  "mvn spring-boot:run"


Acesso
- Abra o navegador em: **http://localhost:8080**

**Credenciais padrão**
- Email: admin@gmail.com
- Senha:admin123 


**MongoDB**
Para acessar os dados do sistema entre na collection localhost:27017 do  MongoDB Compass 


