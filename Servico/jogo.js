class Jogo
{
    //#id
    #nome
    #classificacao
    #descricao
    #imagem

    constructor (nome, classificacao, descricao, imagem)
    {
        // this.id=id;
        this.nome=nome;
        this.classificacao=classificacao;
        this.descricao=descricao;
        this.imagem=imagem;
    }

    // get id ()
    // {
    //     return this.#id
    // }

    get nomeJogo ()
    {
        return this.#nome
    }

    get classificacaoJogo ()
    {
        return this.#classificacao
    }

    get descricaoJogo ()
    {
        return this.#descricao
    }

    get imagemJogo ()
    {
        return this.#imagem
    }

    // set id (id)
    // {
    //     if (id===undefined || typeof id !== 'number' || isNaN(id) || id!==parseInt(id) || id<=0)
    //         throw ('Código inválido');

    //     this.#id = id;
    // }

    set _nome (nome)
    {
        if (nome===undefined || typeof nome !== 'string' || nome==="")
            throw ('Nome inválido');

        this.#nome = nome;
    }

    set _classificacao (classificacao)
    {
        if (classificacao===undefined || typeof classificacao !== 'string' || classificacao==="")
            throw ('Classificação inválida');

        this.#classificacao = classificacao;
    }

    set _descricao (descricao)
    {
        if (descricao===undefined || typeof descricao !== 'string' || descricao==="")
            throw ('Descrição inválida');

        this.#descricao = descricao;
    }

    set _imagem (imagem)
    {
        if (imagem===undefined || typeof imagem !== 'string' || imagem==="")
            throw ('Imagem inválida');

        this.#imagem = imagem;
    }
}

function novo (nome, classificacao, descricao, imagem)
{
    return new Jogo (nome, classificacao, descricao, imagem);
}

module.exports = {novo}
