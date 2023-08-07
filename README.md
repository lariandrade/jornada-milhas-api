<div align="center">
    <img src="https://github.com/lariandrade/jornada-milhas-api/assets/44838761/6b603719-cda2-4b05-be45-6518a9d404ca"/>
    <h1 align="center">API REST</h1>
</div>
<div align="center">
    <img src="http://img.shields.io/static/v1?label=STATUS&message=FINALIZADO&color=GREEN&style=for-the-badge"/>
</div>

## Descrição do Projeto
O desafio proposto pela Alura na 7ª Edição do Challenge Backend é desenvolver em 4 semanas a estrutura do backend para um site de viagens. A API REST terá recursos para gerenciar depoimentos, destinos de viagens e seus respectivos valores. O projeto visa proporcionar uma experiência completa e interativa aos usuários, permitindo que compartilhem suas experiências de viagem e descubram novos destinos emocionantes. 

## Layout

O layout da aplicação está disponível neste link: <a href="https://www.figma.com/proto/1qD4hmpnvxoeHRC1cbWKgR/Challenge-Escola-de-Programa%C3%A7%C3%A3o?type=design&node-id=4-6408&scaling=min-zoom&page-id=0%3A1">Figma</a>

## Funcionalidades
- ✅ CRUD de Depoimentos
- ✅ CRUD de Destinos
- ✅ Integração com ChatGPT

## Endpoints

### Depoimentos
![image](https://github.com/lariandrade/jornada-milhas-api/assets/44838761/84f58eb3-6097-4fe0-ad9b-25c91af0299e)

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
Lista todos os depoimentos cadastrados ou, se preferir, pode filtar usando os parametros `page` e `size`.
> Exemplo: /depoimentos?page=0&size=2

```
[
  {
    "id": 1,
    "foto": "https://url_da_imagem.jpg",
    "depoimento": "Plataforma confiável.",
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

### GET /depoimentos/depoimentos-home
Lista três depoimentos aleatorios para exibir na home.
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
  },
  {
    "id": 10,
    "foto": "https://url_da_imagem.jpg",
    "depoimento": "Recomendo fortemente!",
    "autor": "Talita Matos"
  }
]
```

### Destinos
![image](https://github.com/lariandrade/jornada-milhas-api/assets/44838761/33c26ed8-d97c-4ff3-94e6-33b0f86e121b)

### POST /destinos
Recebe o nome, preço e foto.
> Exemplo de corpo da requisição:

```
{
    "foto1": "https://url_da_imagem_japao1.jpg",
    "foto2": "https://url_da_imagem_japao2.jpg",
    "nome": "Japao",
    "preco": 10350,
    "meta": "Terceira maior economia do mundo!",
    "textoDescritivo": ""
}
```
**Obs.: se o atributo "textoDescritivo" estiver em branco, o texto será gerado automaticamente pelo ChatGPT.**

```java
if (dados.textoDescritivo().isBlank()) {
    String textoGerado = chatService.gerarTexto(dados.nome());
    destino.setTextoDescritivo(textoGerado);
}
```

### GET /destinos/todos
Lista todos os destinos cadastrados ou, se preferir, pode filtar usando os parametros `page` e `size`.
> Exemplo: /destinos/todos?page=0&size=2

```
[
  {
    "id": 1,
    "foto1": "url_da_imagem_paris1.jpg",
    "foto2": "url_da_imagem_paris2.jpg",
    "nome": "Paris",
    "preco": 9500.5,
    "meta": "Um dos principais centros de finanças!",
    "textoDescritivo": "Paris é uma das cidades mais incríveis do mundo. Uma mistura única de cultura, moda, gastronomia e beleza. É uma cidade cintilante e cheia de história que vale a pena ser explorada."
  },
  {
    "id": 2,
    "foto1": "https://url_da_imagem_japao1.jpg",
    "foto2": "https://url_da_imagem_japao2.jpg",
    "nome": "Japao",
    "preco": 10350,
    "meta": "Terceira maior economia do mundo!",
    "textoDescritivo": "O Japão é um país surpreendente! O povo japonês coloca uma ênfase incrível na cultura tradicional e na tecnologia moderna, que encantam todos os visitantes.
  }
]
```
### GET /destinos/
Lista destino referente ao nome informado.
> Exemplo: destinos?nome=Paris

```
{
    "id": 1,
    "foto1": "url_da_imagem_paris1.jpg",
    "foto2": "url_da_imagem_paris2.jpg",
    "nome": "Paris",
    "preco": 9500.5,
    "meta": "Um dos principais centros de finanças!",
    "textoDescritivo": "Paris é uma das cidades mais incríveis do mundo. Uma mistura única de cultura, moda, gastronomia e beleza. É uma cidade cintilante e cheia de história que vale a pena ser explorada."
}
```
### GET /destinos/{id}
Lista destino referente ao id informado.
> Exemplo: destinos/2

```
{
    "id": 2,
    "foto1": "https://url_da_imagem_japao1.jpg",
    "foto2": "https://url_da_imagem_japao2.jpg",
    "nome": "Japao",
    "preco": 10350,
    "meta": "Terceira maior economia do mundo!",
    "textoDescritivo": "O Japão é um país surpreendente! O povo japonês coloca uma ênfase incrível na cultura tradicional e na tecnologia moderna, que encantam todos os visitantes.
}
```
### PUT /destinos/{id}
Atualiza destino referente ao id informado.
> Exemplo: destinos/1

```
{
    "preco": 9345.30
}
```
> Retorno da requisição:
```
{
    "id": 1,
    "foto1": "url_da_imagem_paris1.jpg",
    "foto2": "url_da_imagem_paris2.jpg",
    "nome": "Paris",
    "preco": 9345.30,
    "meta": "Um dos principais centros de finanças!",
    "textoDescritivo": "Paris é uma das cidades mais incríveis do mundo. Uma mistura única de cultura, moda, gastronomia e beleza. É uma cidade cintilante e cheia de história que vale a pena ser explorada."
}
```
### DELETE /destinos/{id}
Deleta destino referente ao id informado.

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
3. Configurando Token OpenAI

O token OpenAI é essencial para possibilitar a utilização do ChatGPT e deve ser configurado no mesmo arquivo em que o banco de dados foi definido. 
Para garantir a segurança, é recomendavél o uso de variáveis de ambiente.

```
openai.api.key=${TOKEN_GPT}
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
