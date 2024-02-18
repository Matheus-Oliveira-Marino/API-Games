const bd = require ('./bd');

async function inclua (game)
{
    const conexao = await bd.getConexao ();
    if (conexao==null) return null;

    try
    {
        const sql     = 'INSERT INTO games (nome, classificacao, descricao, imagem) VALUES (?, ?, ?, ?)';
        const dados   = [game.nome,game.classificacao,game.descricao,game.imagem];
        await conexao.query (sql, dados);
        return true;
    }
    catch (excecao)
    {
        return false;
    }
}

async function atualize (game, id)
{
    const conexao = await bd.getConexao ();
    if (conexao==null) return null;

    try
    {
        const sql   = 'UPDATE games SET nome = ?, classificacao = ?, descricao = ?, imagem = ? WHERE ID = ?';
        const dados = [game.nome,game.classificacao,game.descricao,game.imagem, [id]];
        await conexao.query (sql,dados);
        return true;
    }
    catch (excecao)
    {
        return false;
    }
}
    
async function remova (id)
{
    const conexao = await bd.getConexao ();
    if (conexao==null) return null;

    try
    {
        const sql   = 'DELETE FROM games WHERE ID = ?';
        const dados = [id];
        await conexao.execute (sql,dados);
        return true;
    }
    catch (excecao)
    {
        return false;
    }
}

async function recupereUm (id)
{
    const conexao = await bd.getConexao();
    if (conexao==null) return null;

    try
    {
        const  sql     = 'SELECT * FROM games WHERE ID = ?';
        const  dados   = [id];
        const [linhas] = await conexao.execute(sql,dados);
        console.log([linhas]);
        return linhas;
    }
    catch (excecao)
    {
        return false;
    }
}

async function recupereTodos ()
{
    const conexao = await bd.getConexao();
    if (conexao==null) return null;

    try
    {
        const  sql     = 'SELECT * FROM games';
        const [linhas] = await conexao.query(sql);
        console.log(linhas);
        return linhas;
    }
    catch (excecao)
    {
        return false;
    }
}

async function recupereUmNome (nome)
{
    const conexao = await bd.getConexao();
    if (conexao==null) return null;

    try
    {
        const  sql     = 'SELECT * FROM games WHERE nome = ?';
        const  dados   = [nome];
        const [linhas] = await conexao.execute(sql,dados);
        console.log(linhas);
        return linhas;
    }
    catch (excecao)
    {
        return false;
    }
}

module.exports = {inclua, atualize, remova, recupereUm, recupereTodos, recupereUmNome}



