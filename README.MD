## Passo a passo para executar todo o projeto backend e front usando docker-compose

### Requisitos para fazer os passos abaixo: ter o maven e java instalados

#### 1) Acesse a pasta do projeto backend: ``` /backend ``` e execute o comando: ``` mvn install -DskipTests=true ```
#### 2) Depois volte para a pasta raiz do projeto, onde se encontra o arquivo docker-compose.yml e execute: ``` docker-compose build ```
#### 3) Por último para subir o projeto digite o comando: ``` docker-compose up ```
#### 4) O servidor front end sobe na porta ``` http://localhost:8000/ ```

## Rodar os projetos localmente

## Caso não queira acessar o projeto usando docker entre em cada pasta ./backend e ./frontend abra em um editor de sua preferência e rode o projeto. No ./backend terá que configurar o ``` application-dev.properties  ``` o banco de dados para de acordo com suas configuracoes de url de conexão, usuário e senha.


## Para acessar documentação dos end-points acesse a url abaixo:
``` http://localhost:8080/api/swagger-ui/index.html ``` 
