
<h1 align="center">:video_game: API-Games :video_game: </h1>

  <h2 align = "center">Projeto API - CRUD para jogos digitais desenvolvido durante o 3º Semestre do Curso de Desenvolvimento de Sistemas no colégio Cotuca :books: </h2>  

<p align="center">
  <a href="https://opensource.org/licenses/MIT"></a> <a href="https://opensource.org/license/mit/">LICENSE</a>
  
  <br>
  <br>
    <img alt="Static Badge" src="https://img.shields.io/badge/License%20-%20MIT%20-%20green?style=flat&logo=%2334A853&logoColor=%233d85c6&labelColor=(178%2C%20222%2C%2039)&color=%233d85c6">
    <img alt="Static Badge" src="https://img.shields.io/badge/API%20-%2033%2B%20-%20green?style=flat&logo=%2334A853&logoColor=%2334A853&labelColor=(178%2C%20222%2C%2039)&color=(50%2C205%2C50)">
    <img alt="Static Badge" src="https://img.shields.io/badge/Android%20Apache-%2013.0%20-%20green?style=flat&logo=%2334A853&logoColor=%233d85c6&labelColor=(178%2C%20222%2C%2039)&color=(0%2C255%2C0)">

</p>
  
<p align="center">  

⭐ Esse é um projeto para demonstrar meu conhecimento técnico no desenvolvimento Android native com Kotlin. Mais informações técnicas abaixo.

## DESCRIÇÃO

:rocket: O projeto consiste em uma API CRUD que tem como finalidade o cadastro de jogos digitais, indicando nome do jogo, sua classificação, descrição, e um link url da imagem.Ainda, é possível que o usuário visualize os jogos cadastrados por ele, atualizar alguma informação, ou ainda deletar algum jogo. Através de um ```floating action button```, pode selecionar a opção que deseja realizar.

</p>

</br>

<p float="left" align="center">

## TELAS DO APLICATIVO

⭐ O aplicativo permite o cadastro de jogos por solicitar que o usuário adicione um ```nome```, ```classificação```, ```descrição``` e uma ```URL válida```. Caso não seja inserido 
uma url que contenha uma imagem, ou se os outros campos estiverem vazios, o sistema irá reportar um erro.O cadastro de um jogo novo adiciona um novo registro na tabela do banco de dados, contendo cada informação citada anteriormente. O mesmo vale para outras operações.
<div align="center">
  
  https://github.com/Matheus-Oliveira-Marino/API-Games/assets/139178883/f53b8138-6b18-4f8b-98bc-bfd15f61a477
</div>

<br>
<br>

  É possível visualizar as alterações adicionadas através do botão de ```Ver filmes```. Para editar um jogo previamente cadastrado, a ação pode ser feita através do botão ```Atualizar Jogos```.As alteraçõs serão enviadas ao banco de dados, e será modificado o jogo disponível de acordo com seu ```ID``` na tabela. o botão ```Excluir Jogos``` redireciona o usuário para deletar um registro de um jogo, passando como parâmetro o ID existente no database do MySQL.
  
<div align="center">

  https://github.com/Matheus-Oliveira-Marino/API-Games/assets/139178883/f3b8c0f3-c762-4b38-9fb6-617385ecc9fd

</div>

</p>

## Tecnologias usadas 
- Minimum SDK level - 27
- Maximum SDK level - 33
- [Linguagem Kotlin](https://kotlinlang.org/)


## Bibliotecas
  - [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Para realizar requisições seguindo o padrão HTTP.
  - [Picasso](https://github.com/square/picasso): Simplifica o processo de baixar, armazenar em cache e exibir imagens de forma eficiente em aplicativos Android.
  - [Coroutines](https://github.com/Kotlin/kotlinx.coroutines): Permite executar tarefas assíncronas de forma simples e concisa, sem bloquear execução do aplicativo.
  - [GSON](https://github.com/google/gson): Facilita a conversão de objetos Java em JSON e vice-versa  
  - [Glide](https://github.com/bumptech/glide): Para carregamento de imagens e cacheamento das mesmas.
  - [Timber](https://github.com/JakeWharton/timber): Para registros de logs mais amigáveis que facilitam o debug.
  - [Volley](https://google.github.io/volley/): Facilita o envio e o recebimento de solicitações HTTP de um aplicativo para um servidor web.

## Arquitetura
**API Games** utiliza a arquitetura MVVM e o padrão de Repositories, que segue as [recomendações oficiais do Google](https://developer.android.com/topic/architecture).
</br></br>

## Instalação

- __node.js__(windows11): [guide](https://www.youtube.com/watch?v=EIzdQxMXcrc)
- __node.js__(Ubuntu LTS 22.04): [guide](https://www.youtube.com/watch?v=EIzdQxMXcrc)

- Para rodar o servidor localmente, é necessário abrir o `cmd` e entrar na pasta __Servico__ [(link)](https://github.com/Matheus-Oliveira-Marino/API-Games/tree/main/Servico)
- Digitar o comando `npm init -y` para instalação do `package.json`;
- *Instalar o Express*: `npm install express --save`;

Após executar esse comando é criada a pasta `node_modules`, que contém as dependências instaladas no
projeto. Também é criado o arquivo `package_lock.json`, tendo posse das dependências usadas no APP.

- Editar [variáveis de ambiente](https://github.com/Matheus-Oliveira-Marino/API-Games/blob/main/Servico/template.bat) para acesso ao Banco de Dados de sua preferência;

- ainda no `cmd`, navegar até o local do arquivo `.bat` e digitar o comando:
  `node template.bat`

  este retornará uma mensagem de que houve sucesso em inicializar as variáveis de ambiente;

  - entrar na pasta que contem o arquivo `index.js` e subir o servidor pelo comando:
 
    `node index.js`
  
