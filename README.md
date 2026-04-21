# Sistema de Gerenciamento de Biblioteca

Sistema web completo para gerenciamento de biblioteca, desenvolvido com Java 24 + Spring Boot 3.4.1 + SQLite.

## 🚀 Tecnologias

- **Java 24** + **Spring Boot 3.4.1**
- **Maven** (gerenciador de dependências)
- **SQLite** (banco de dados embutido)
- **Spring Security** + **BCrypt** (autenticação e criptografia)
- **Thymeleaf** (templates HTML)
- **Spring Data JPA** (persistência)

## 📋 Funcionalidades

- **Login seguro** com BCrypt
- **Dashboard** com métricas em tempo real
- **Usuários** – CRUD completo (criar, listar, editar, excluir)
- **Materiais** – CRUD completo do acervo
- **Empréstimos** – controle com abas (Ativos / Atrasados / Devolvidos)
- **Reservas** – gerenciamento de reservas com status
- Detecção automática de empréstimos **atrasados**

## ▶️ Como executar

### Pré-requisitos
- Java 24+ instalado
- Maven 3.9+ instalado

### Passos

```bash
# 1. Entre na pasta do projeto
cd biblioteca

# 2. Compile e execute
mvn spring-boot:run
```

### Acesso
Abra o navegador em: **http://localhost:8080**

### Credenciais padrão
| Campo  | Valor             |
|--------|-------------------|
| Email  | admin@gmail.com   |
| Senha  | admin123          |

## 🗄️ Banco de Dados

O arquivo `biblioteca.db` é criado automaticamente na pasta raiz do projeto na primeira execução, já com dados de exemplo:

- 2 usuários (Guilherme F e Maikon H)
- 2 materiais (O Senhor dos Anéis e Turma da Mônica)
- 2 empréstimos ativos
- 1 reserva pendente

## 📁 Estrutura do projeto

```
biblioteca/
├── pom.xml
└── src/main/
    ├── java/com/biblioteca/
    │   ├── config/          # Segurança e inicialização de dados
    │   ├── controller/      # Controllers MVC
    │   ├── model/           # Entidades JPA
    │   ├── repository/      # Repositórios Spring Data
    │   └── service/         # Lógica de negócio
    └── resources/
        ├── application.properties
        ├── static/css/      # Estilos CSS
        └── templates/       # Templates Thymeleaf
```
