const bdConfig   = require ('./bdconfig.js');
const bd         = require('./bd.js') 
const Livros     = require ('./livros.js');
const Livro      = require ('./livro.js');
const Comunicado = require ('./comunicado.js');


// para a rota de CREATE
async function inclusao (req, res)
{
    if (Object.values(req.body).length!=4 || !req.body.nome || !req.body.classificacao || !req.body.descricao || !req.body.imagem)
    {
        const erro = Comunicado.novo('DdI','Dados inesperados','Não foram fornecidos exatamente as 3 informações esperadas de um livro (codigo, nome e preço)').object;
        return res.status(422).json(erro);
    }
    
    let game;
    try
    {
        game = Livro.novo (req.body.nome, req.body.classificacao, req.body.descricao, req.body.imagem);
    }
    catch (excecao)
    {
        const erro = Comunicado.novo('TDE','Dados de tipos errados','Codigo deve ser um numero natural positivo, nome deve ser um texto não vazio e preço deve ser um número real positivo').object;
        return res.status(422).json(erro);
    }

    const ret = await Livros.inclua(game);

    if (ret===null)
    {
        const  erro = Comunicado.novo('CBD','Sem conexão com o BD','Não foi possível estabelecer conexão com o banco de dados').object;
        return res.status(500).json(erro);
    }

    if (ret===false)
    {
        const  erro = Comunicado.novo('LJE','Livro já existe','Já há livro cadastrado com o código informado').object;
        return res.status(409).json(erro);
    }

        const  sucesso = Comunicado.novo('IBS','Inclusão bem sucedida','O livro foi incluído com sucesso').object;
        return res.status(201).json(sucesso);

}

// para a rota de UPDATE
async function atualizacao (req, res)
{
    if (Object.values(req.body).length!=4 || !req.body.nome || !req.body.classificacao || !req.body.descricao || !req.body.imagem)
    {
        const erro = Comunicado.novo('DdI','Dados inesperados','Não foram fornecidos exatamente as 3 informações esperadas de um livro (codigo atual, novo nome e novo preço)').object;
        return res.status(422).json(erro);
    }
    
    let game;
    try
    {
        game = Livro.novo (req.body.nome,req.body.classificacao,req.body.descricao,req.body.imagem);
    }
    catch (excecao)
    {
        const erro = Comunicado.novo('TDE','Dados de tipos errados','Codigo deve ser um numero natural positivo, nome deve ser um texto não vazio e preço deve ser um número real positivo').object;
        return res.status(422).json(erro);
    }

    const id = req.params.id;
    console.log(id);
    
//     if (id!=req.params.id)
//     {
//         const erro = Comunicado.novo('TMC','Mudança de código','Tentativa de mudar o código do livro').object;
//         return res.status(400).json(erro);
//     }
    
    let ret = await Livros.recupereUm(id);
    console.log(ret);

    if (ret===null)
    {
        const  erro = Comunicado.novo('CBD','Sem conexão com o BD','Não foi possível estabelecer conexão com o banco de dados').object;
        return res.status(500).json(erro);
    }

    if (ret===false)
    {
        const  erro = Comunicado.novo('FNC','Falha no comando SQL','O comando SQL apresenta algum erro').object;
        return res.status(409).json(erro);
    }

    if (ret.length==0)
    {
        const erro = Comunicado.novo('LNE','Livro inexistente','Não há livro cadastrado com o código informado').object;
        return res.status(404).json(erro);
    }

    ret = await Livros.atualize(game, id);

    if (ret===null)
    {
        const  erro = Comunicado.novo('CBD','Sem conexão com o BD','Não foi possível estabelecer conexão com o banco de dados').object;
        return res.status(500).json(erro);
    }

    if (ret===false)
    {
        const  erro = Comunicado.novo('FNC','Falha no comando SQL','O comando SQL apresenta algum erro').object;
        return res.status(409).json(erro);
    }

  //if (ret===true)
  //{
        const sucesso = Comunicado.novo('ABS','Alteração bem sucedida','O livro foi atualizado com sucesso').object;
        return res.status(201).json(sucesso);
  //}
}

// para a rota de DELETE
async function remocao (req, res)
{
    if (Object.values(req.body).length!=0) //Há dados no corpo  da requisição.Ao remover, não é necessário ter corpo
    {
        const erro = Comunicado.novo('DSP','Fornecimento de dados sem propósito','Foram fornecidos dados sem necessidade no corpo da requisição').object;
        return res.status(422).json(erro);
    }

    const id = req.params.id;
    console.log(id);
    let ret = await Livros.recupereUm(id);


    if (ret===null)
    {
        const  erro = Comunicado.novo('CBD','Sem conexão com o BD','Não foi possível estabelecer conexão com o banco de dados').object;
        return res.status(500).json(erro);
    }

    if (ret===false)
    {
        const  erro = Comunicado.novo('FNC','Falha no comando SQL','O comando SQL apresenta algum erro').object;
        return res.status(409).json(erro);
    }

    if (ret.length==0)
    {
        const erro = Comunicado.novo('LNE','Livro inexistente','Não há livro cadastrado com o código informado').object;
        return res.status(404).json(erro);
    }

    ret = await Livros.remova(id);


    if (ret===null)
    {
        const  erro = Comunicado.novo('CBD','Sem conexão com o BD','Não foi possível estabelecer conexão com o banco de dados').object;
        return res.status(500).json(erro);
    }

    if (ret===false)
    {
        const  erro = Comunicado.novo('FNC','Falha no comando SQL','O comando SQL apresenta algum erro').object;
        return res.status(409).json(erro);
    }

  if (ret===true)
  {
        const sucesso = Comunicado.novo('RBS','Remoção bem sucedida','O livro foi removido com sucesso').object;
        return res.status(200).json(sucesso);
  }
}

// para a segunda rota de READ (um)
async function recuperacaoDeUm (req, res)
{
    if (Object.values(req.body).length!=0)
    {
        const erro = Comunicado.novo('DSP','Fornecimento de dados sem propósito','Foram fornecidos dados sem necessidade no corpo da requisição').object;
        return res.status(422).json(erro);
    }

    const id = req.params.id;
    console.log(id)

    const ret = await Livros.recupereUm(id);
//    console.log("Antes:");
//    console.log(Livros.recupereUm(id));


    if (ret===null)
    {
        const  erro = Comunicado.novo('CBD','Sem conexão com o BD','Não foi possível estabelecer conexão com o banco de dados').object;
        console.log(erro);
        return res.status(500).json(erro);
    }

    if (ret===false)
    {
        const  erro = Comunicado.novo('FNC','Falha no comando SQL','O comando SQL apresenta algum erro').object;
        console.log(erro);
        return res.status(409).json(erro);
    }

    if (ret.length==0)
    {
        const erro = Comunicado.novo('LNE','Livro inexistente','Não há livro cadastrado com o código informado').object;
        console.log(erro);
        return res.status(404).json(erro);
    }

    return res.status(200).json(ret[0]);
}

// para a primeira rota de READ (todos)
async function recuperacaoDeTodos (req, res)
{
    if (Object.values(req.body).length!=0)
    {
        const erro = Comunicado.novo('DSP','Fornecimento de dados sem propósito','Foram fornecidos dados sem necessidade no corpo da requisição').object;
        return res.status(422).json(erro);
    }

    const ret = await Livros.recupereTodos();

    if (ret===null)
    {
        const  erro = Comunicado.novo('CBD','Sem conexão com o BD','Não foi possível estabelecer conexão com o banco de dados').object;
        return res.status(500).json(erro);
    }

    if (ret===false)
    {
        const  erro = Comunicado.novo('FNC','Falha no comando SQL','O comando SQL apresenta algum erro').object;
        return res.status(409).json(erro);
    }

    return res.status(200).json(ret);
}

// para a segunda rota de READ (um)
async function recuperaNome (req, res)
{
    if (Object.values(req.body).length!=0)
    {
        const erro = Comunicado.novo('DSP','Fornecimento de dados sem propósito','Foram fornecidos dados sem necessidade no corpo da requisição').object;
        return res.status(422).json(erro);
    }

    const nome = req.params.nome;
    console.log(nome)

    const ret = await Livros.recupereUmNome(nome);
//    console.log("Antes:");
//    console.log(Livros.recupereUm(id));

    if (ret===null)
    {
        const  erro = Comunicado.novo('CBD','Sem conexão com o BD','Não foi possível estabelecer conexão com o banco de dados').object;
        console.log(erro);
        return res.status(500).json(erro);
    }

    if (ret===false)
    {
        const  erro = Comunicado.novo('FNC','Falha no comando SQL','O comando SQL apresenta algum erro').object;
        console.log(erro);
        return res.status(409).json(erro);
    }

//    if (ret.length==0)
//    {
//        const erro = Comunicado.novo('LNE','Livro inexistente','Não há livro cadastrado com o código informado').object;
//        console.log(erro);
//        return res.status(404).json(erro);
//    }

    return res.status(200).json(ret[0]);
}

module.exports = {inclusao, atualizacao, remocao, recuperacaoDeUm, recuperacaoDeTodos, recuperaNome}