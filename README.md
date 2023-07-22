<div align="center">
    <img src="https://github.com/lariandrade/jornada-milhas-api/assets/44838761/6b603719-cda2-4b05-be45-6518a9d404ca"/>
    <h1 align="center">API REST</h1>
</div>
<div align="center">
    <img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=yellow&style=for-the-badge"/>
</div>

## Descrição do Projeto
O desafio proposto pela Alura na 7ª Edição do Challenge Backend é desenvolver em 4 semanas a estrutura do backend para um site de viagens. A API REST terá recursos para gerenciar depoimentos, destinos de viagem e seus respectivos valores. O projeto visa proporcionar uma experiência completa e interativa aos usuários, permitindo que compartilhem suas experiências de viagem e descubram novos destinos emocionantes. 

## Layout

O layout da aplicação está disponível neste link: <a href="https://www.figma.com/proto/1qD4hmpnvxoeHRC1cbWKgR/Challenge-Escola-de-Programa%C3%A7%C3%A3o?type=design&node-id=4-6408&scaling=min-zoom&page-id=0%3A1">Figma</a>

## Funcionalidades
- ✅ CRUD de Depoimentos
- 🚧 CRUD de Destinos
- 🚧 Integração com IA

## Endpoints

### Depoimentos
![image](https://github.com/lariandrade/jornada-milhas-api/assets/44838761/6a52ce5f-0675-44d5-a189-8bdc2b5a37fb)

### POST /depoimentos
Recebe o depoimento, nome e foto do autor.
> Exemplo de corpo da requisição:

```
{
    "foto": "https://url_da_imagem.jpg",
    "depoimento": "Execelente plataforma",
    "autor": "Larissa Andrade"
}
```

### GET /depoimentos
Lista todos os depoimentos cadastrados.
> Exemplo de retorno da requisição:

```
[
  {
    "id": 1,
    "foto": "https://url_da_imagem.jpg",
    "depoimento": "Plataforma confiavél.",
    "autor": "Rodrigo Soares"
  },
  {
    "id": 2,
    "foto": "https://url_da_imagem.jpg",
    "depoimento": "Execelente plataforma",
    "autor": "Larissa Andrade"
  }
]
```
### GET /depoimentos/{id}
Lista depoimento referente ao id informado.
> Exemplo: depoimentos/2

```
{
    "id": 2,
    "foto": "https://url_da_imagem.jpg",
    "depoimento": "Execelente plataforma",
    "autor": "Larissa Andrade"
}
```

### PUT /depoimentos/{id}
Atualiza depoimento referente ao id informado.
> Exemplo: depoimentos/2

```
{
    "depoimento": "Execelente plataforma de viagens"
}
```
> Retorno da requisição:
```
{
    "id": 2,
    "foto": "https://url_da_imagem.jpg",
    "depoimento": "Execelente plataforma de viagens",
    "autor": "Larissa Andrade"
}
```
### DELETE /depoimentos/{id}
Deleta depoimento referente ao id informado.

## ▶️ Rodar a aplicação

1. Baixe o projeto:
```
git clone https://github.com/lariandrade/jornada-milhas-api.git
```
Abra o projeto na IDE.

2. Configurando as credenciais de acesso ao banco de dados

No arquivo `application.properties`, localize as configurações de acesso ao banco de dados e insira suas próprias credenciais:
```
spring.datasource.username=<insira seu nome de usuario>
spring.datasource.password=<insira sua senha>
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080)

O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Tecnologias utilizadas

O projeto foi construído utilizando as seguintes ferramentas e tecnologias:

- Java 17
- Maven
- Spring Boot 3
- Spring Data JPA
- MySQL
- Flyway
- SpringDoc OpenAPI 3
