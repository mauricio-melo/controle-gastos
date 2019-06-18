# controle-gastos
### Esse é um serviço que tem como objetivo controlar gastos de usuarios

#### Rodando a aplicação localmente:

1.Inicie a imagem do Mysql utilizando o docker-compose:
```docker-compose up```
        

2.Construa o projeto:
``` mvn clean package install``` 
        
3.Navegue até o jar:
```cd target```
        
4.Execute o jar:
 ```java -jar controle-gastos-0.0.1-SNAPSHOT.jar``` 
 
5.Acesse o Swagger:
  ```http://localhost:8080/swagger-ui.html``` 