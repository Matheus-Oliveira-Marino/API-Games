package com.example.games

import retrofit2.Call
import retrofit2.http.*

//  É uma interface Java que declara métodos para cada endpoint da API
interface Service
{
    //get = consultar(select)
    //post = inserir
    //delete
    //put = alterar

    //Incluir um dog
    @POST("/servico/jogos")
    fun incluirJogo(@Body jogo: Jogos): Call<Jogos?>?

    //Alterar um pet
    @PUT("/servico/jogos/{id}")
    fun alterarJogo(@Path("id") id: String, @Body jogo: Jogos): Call<Void>

    // Deletar um jogo
    @DELETE("/servico/{id}") //Remocao()
    fun excluirJogo(@Path("id") id: String): Call<Void>

    @GET("/servico/jogos/{id}") //RecuperacaodeUm()
    fun searchID(@Path("id") id: String): Call<Jogos>

    @GET("/servico/jogos")
    fun getAll(): Call<List<Jogos>>

    @GET("/servico/jogos/nome/{nome}")
    fun getGames(@Path("nome") nome: String): Call<Jogos>

}