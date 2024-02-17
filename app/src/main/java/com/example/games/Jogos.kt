package com.example.games

import com.google.gson.annotations.SerializedName

class Jogos
    (
    @SerializedName("nome")
    val nome: String,

    @SerializedName("classificacao")
    val classificacao: String,

    @SerializedName("descricao")
    val descricao: String,

    @SerializedName("imagem")
    val imagem: String

): java.io.Serializable
{
    @SerializedName("ID")
    var id: String? = null
}
