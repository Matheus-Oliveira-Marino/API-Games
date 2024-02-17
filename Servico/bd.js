const mysql    = require("mysql2/promise");
const bdConfig = require('./bdconfig.js');

async function getConexao ()
{
    if (global.conexao && global.conexao.state !== 'disconnected')
        return global.conexao;

    try
    {
        const conexao = await mysql.createConnection (bdConfig);
        global.conexao = conexao;
        return conexao;
    }
    catch (erro)
    {
        return null;
    }
}

async function estrutureSe ()
{
    const conexao = await getConexao ();
    if (conexao==undefined) return null;

    const sql = 'CREATE TABLE IF NOT EXISTS games (ID TINYINT UNSIGNED AUTO_INCREMENT, nome VARCHAR(80) NOT NULL, classificacao VARCHAR(80) NOT NULL, descricao VARCHAR(300) NOT NULL, imagem VARCHAR(200) NOT NULL,  PRIMARY KEY (ID))';
    
    try
    {
        await conexao.query (sql);
        return true;
    }
    catch (erro)
    {
        return false;
    }
}

module.exports = {getConexao, estrutureSe}
