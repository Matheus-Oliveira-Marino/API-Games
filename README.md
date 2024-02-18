
<h1 align="center">:video_game: API-Games :video_game: </h1>

<br>

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

:rocket: O projeto consiste em uma API CRUD que tem como finalidade o cadastro de jogos digitais, indicando nome do jogo, sua classificação, descrição, e um link url da imagem.Ainda, é possível que o user visualize os jogos cadastrados por ele, atualizar alguma informação, ou ainda deletar algum jogo. Através de um ```floating action button```, pode selecionar a opção que deseja realizar.

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

- Jetpack - LISTE O MÁXIMO DE COMPONENTES DO JETPACK QUE VOCÊ USA
  - Lifecycle: Observe os ciclos de vida do Android e manipule os estados da interface do usuário após as alterações do ciclo de vida.
  - ViewModel: Gerencia o detentor de dados relacionados à interface do usuário e o ciclo de vida. Permite que os dados sobrevivam a alterações de configuração, como rotações de tela.
  - ViewBinding: Liga os componentes do XML no Kotlin através de uma classe que garante segurança de tipo e outras vantagens.
  - Room: Biblioteca de abstração do banco de dados SQLite que garante segurança em tempo de compilação e facilidade de uso.
  - Custom Views: View customizadas feitas do zero usando XML.
  - [...]
  
- Bibliotecas
  - [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Para realizar requisições seguindo o padrão HTTP.
  - [Glide](https://github.com/bumptech/glide): Para carregamento de imagens e cacheamento das mesmas.
  - [Timber](https://github.com/JakeWharton/timber): Para registros de logs mais amigáveis que facilitam o debug.
  - [...]

## Arquitetura
**API Games** utiliza a arquitetura MVVM e o padrão de Repositories, que segue as [recomendações oficiais do Google](https://developer.android.com/topic/architecture).
</br></br>

