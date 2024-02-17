package com.example.games

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig //Classe para se comunicar com o node.js
{
    /* companion object é uma construção da linguagem Kotlin que permite criar objetos estáticos em
    uma classe, similar aos membros estáticos em Java.*/
    companion object
    {
        fun getRetrofit(): Retrofit
        {
            return Retrofit.Builder().baseUrl("http://192.168.15.63:3000").addConverterFactory(
                GsonConverterFactory.create()).build()
        }
    }
}