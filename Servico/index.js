const express  = require ('express');
const bd       = require ('./bd.js');
const rotas    = require ('./rotas.js');

function middleWareGlobal (req, res, next)
{
    console.time('Duração'); // marca o início da requisição
    console.log('Iniciou  o processamento da requisição '+req.method+' em '+req.url); // indica o início do processamento da url, indicando também o método

    next(); // função que chama o processamento, propriamente dito, da requisição
               
    console.log('Terminou o processamento da requisição '+req.method+' em '+req.url); // indica o término do processamento da url, indicando também o método
    console.timeEnd('Duração'); // informa duração do processamento da requisição
}

async function ativacaoDoServidor ()
{
    const ret = await bd.estrutureSe();

    if (ret===null)
    {
        console.log ('Não foi possível estabelecer conexão com o BD!');
        process.exit(1);
    }

    if (ret===false)
    {
        console.log ('Não foi possível estruturar o BD!');
        process.exit(1);
    }

    const app  = express();
    
    app.use(express.json());   // faz com que o express consiga processar JSON
    app.use(middleWareGlobal); // app.use cria o middleware global

    app.post  ('/servico/jogos' , rotas.inclusao);
    app.put   ('/servico/jogos/:id', rotas.atualizacao);
    app.delete('/servico/:id', rotas.remocao);
    app.get   ('/servico/jogos/:id', rotas.recuperacaoDeUm);
    app.get   ('/servico/jogos', rotas.recuperacaoDeTodos);
    app.get   ('/servico/jogos/nome/:nome', rotas.recuperaNome);

    console.log ('Servidor ativo na porta 3000...');
    app.listen(3000);
}
ativacaoDoServidor();
