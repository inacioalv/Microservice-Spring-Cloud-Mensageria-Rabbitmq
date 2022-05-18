<h1 align="center">
  Api e-commercer
</h1>


<p align="center">
  <a href="#-tecnologias">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-projeto">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-url">Url</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
</p>


<br>


## 🚀 Tecnologias

Esse projeto foi desenvolvido com as seguintes tecnologias:

- [Spring](https://spring.io/)
- [Jpa](https://spring.io/projects/spring-data-redis)
- [gateway](https://spring.io/projects/spring-cloud-gateway)
- [netflix-eureka](https://spring.io/projects/spring-cloud-sleuth)
- [Mysql](https://www.mysql.com/)
- [lombok](https://projectlombok.org/)
- [swagger](https://swagger.io/)


## 💻 Projeto
Serviços web RESTful estabelecendo comunicação entre microsserviços e implementando autenticação jwt. 
Esse projeto estabelece comunicação entre microsserviços por meio da arquitetura RabbitMQ baseada em mensagens. 
O producer enviam mensagens para um exchange e encaminhar as mensagens para filas as queues,as queues irão armazenar as mensagens e 
encaminham elas para os consumers. Nesse projeto foi criado Api CRUD que tem o papel de producer que irar enviam mensagens para um exchange,
como podem ver a imagem abaixo foi criado a exchange crud.exchange que irar encaminhar as mensagens para filas.

<img alt="Logo do projeto" src="/img/crud_exchange.png" />

Para isso foi criado as queues que e responsável por armazenar as mensagens em memória 
por sequência entregalas para os consumers.

<img alt="Logo do projeto" src="/img/queue.png" />

Como consumidores foi criado Api pagamento que irão receber as mensagems encaminhadas da filas, 
para isso precissa adicionar vinculação desta troca que iar decide como a encaminhar as mensagens, 
seja para uma fila de mensagem ou para outro exchange.

<img alt="Logo do projeto" src="/img/crud_produto_queue.png" />

## :hammer: Para executar o projeto no terminal, digite o seguinte comando:

```shell script
mvn spring-boot:run 
```

## 💻 Url
Após executar o comando acima, basta apenas abrir o seguinte endereço e visualizar a execução do projeto:

```
Produto
http://localhost:8081/crud/produto
http://localhost:8081/crud/produto/{id}

Pagamento
http://localhost:8082/pagamento/venda
```
## Gateway
```
Produto
http://localhost:8765/crud/produto
http://localhost:8765/crud/produto/{id}

Pagamento
http://localhost:8765/pagamento/venda
```




## 📝 Licença

Este projeto esta sobe a licença MIT. Veja a [LICENÇA](https://opensource.org/licenses/MIT) para saber mais.


