# iara-api-mongo

Desenvolvimento da **API NoSQL do projeto IARA**, responsável por integrar e gerenciar os dados armazenados no **MongoDB**, servindo como camada de persistência e comunicação entre os módulos do ecossistema IARA.


## 📚 Sumário

* [💡 Sobre o Projeto](#-sobre-o-projeto)
* [⚙️ Tecnologias Utilizadas](#️-tecnologias-utilizadas)
* [🧩 Como Executar](#-como-executar)
* [🧰 Endpoints / Exemplos de Uso](#-endpoints--exemplos-de-uso)
* [👩‍💻 Autor](#-autor)


## 💡 Sobre o Projeto

O **IARA API Mongo** é uma API REST desenvolvida em **Java (Spring Boot)**, com o objetivo de centralizar o gerenciamento de dados **NoSQL** do projeto **IARA**, que visa facilitar o **registro, análise e acompanhamento de ábacos industriais**.

Esta API é responsável por:

* Armazenar dados em um banco **MongoDB**.
* Disponibilizar endpoints para **consultas, cadastros e atualizações** de informações.
* Fornecer integração com os outros módulos do ecossistema **IARA**, como o **painel de BI** e a **API relacional SQL**.


## ⚙️ Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework:** Spring Boot
* **Banco de Dados:** MongoDB
* **Gerenciador de Dependências:** Maven
* **Documentação:** Swagger UI
* **Outros:** Docker, Spring Data MongoDB, Lombok, Spring Web


## 🧩 Como Executar

### 🐳 Usando Docker

```bash
# Cria a imagem
docker build -t iara-api-mongo .

# Executa o container
docker run -p 8080:8080 iara-api-mongo
```

### 🧱 Localmente

```bash
# Clone o repositório
git clone https://github.com/IARA-TECH/iara-api-mongo.git

# Entre na pasta
cd iara-api-mongo

# Compile o projeto
mvn clean install

# Execute a aplicação
mvn spring-boot:run
```

> ⚠️ Certifique-se de ter o **MongoDB** rodando localmente (ou configurado no arquivo `.env`) antes de iniciar a aplicação.

## 🧰 Endpoints / Exemplos de Uso

Principais endpoints da API:

| Método | Endpoint                 | Descrição                                   |
| ------ | ------------------------ | ------------------------------------------- |
| GET    | `/api/abacus`            | Lista todos os ábacos cadastrados           |
| GET    | `/api/abacus/{id}`       | Retorna um ábaco específico                 |
| POST   | `/api/abacus`            | Cria um novo registro de ábaco              |
| PUT    | `/api/abacus/{id}`       | Atualiza as informações de um ábaco         |
| DELETE | `/api/abacus/{id}`       | Remove um registro de ábaco                 |
| GET    | `/api/dashboard`         | Retorna dados agregados para o painel de BI |
| POST   | `/api/sheet/upload`      | Faz upload de planilhas de dados            |
| GET    | `/swagger-ui/index.html` | Acessa a documentação interativa da API     |

Exemplo de requisição:

```bash
curl -X POST http://localhost:8080/api/abacus \
  -H "Content-Type: application/json" \
  -d '{
        "name": "Abaco 1",
        "location": "Linha de Produção A",
        "status": "Ativo"
      }'
```

## 👩‍💻 Autor

**IARA Tech**

Projeto Interdisciplinar desenvolvido por alunos do **1º e 2º ano do Instituto J&F**, com o propósito de **otimizar o registro e monitoramento de ábacos industriais**.

📍 São Paulo, Brasil
📧 [iaratech.oficial@gmail.com](mailto:iaratech.oficial@gmail.com)
🌐 GitHub: [https://github.com/IARA-TECH](https://github.com/IARA-TECH)
