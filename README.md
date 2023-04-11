# Sistema Hello

## 📕 Índice
<ul>
  <li>Clonagem do Projeto</li>
  <li>Configuração do application.properties</li>
</ul>

Para clonar o projeto, siga as etapas abaixo:

Crie uma pasta no local de sua preferência, por exemplo: "hello-spring-boot". </br>
Abra o terminal na pasta criada e execute o comando abaixo:


```bash
git clone https://github.com/SamuelGoulart/hello-spring-boot.git hello
```

### Configuração do application.properties


Para que o projeto funcione corretamente, é necessário configurar as suas credenciais de acesso ao banco de dados no arquivo application.properties. 
</br>
#### Siga as etapas abaixo:

Abra o arquivo application.properties localizado no seguinte caminho: src\main\resources\application.properties
Insira as informações de sua conexão no arquivo conforme o exemplo abaixo:

```bash
spring.jpa.database=ORACLE
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

Certifique-se de preencher corretamente as informações de acordo com a sua conexão ao banco de dados.
