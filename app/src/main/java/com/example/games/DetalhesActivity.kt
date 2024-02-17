package com.example.games
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.games.databinding.DetailsActivityBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetalhesActivity:AppCompatActivity()
{
    private lateinit var binding: DetailsActivityBinding
    private lateinit var ID: TextView
    private lateinit var nome: TextView
    private lateinit var classificacao: TextView
    private lateinit var descricao: TextView
    private lateinit var imagem: TextView
    private lateinit var voltar: Button
    private var idJogo: Int? = null


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = DetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

       nome = binding.tvNome
       classificacao = binding.tvClass
       descricao = binding.tvDescr
       imagem = binding.tvURL
       ID = binding.tvId

       voltar = binding.tvButtonVoltar

        // Recebe os dados do intent
        val name = intent.getStringExtra("nome")
        Log.d("DetalhesActivity", "nome: $name")

        val retrofitConfig = RetrofitConfig.getRetrofit()
        val service = retrofitConfig.create(Service::class.java)
        val call = service.getGames(name ?: "")

        call.enqueue(object : Callback<Jogos>
        {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Jogos>, response: Response<Jogos>)
            {
                if (response.isSuccessful)
                {
                    val jogos = response.body()
                    if (jogos != null)
                    {

                        nome.text = "Nome: "+ jogos.nome
                        classificacao.text = "Classificação: " + jogos.classificacao
                        descricao.text = "Descrição: " + jogos.descricao
                        imagem.text = "Imagem: " + jogos.imagem
                        ID.text = "ID: " + jogos.id
                        Log.d("DetalhesActivity", "JSON retornado: ${response.body().toString()}")
                    }
                } else
                {
                    Log.e("DetalhesActivity", "Erro ao carregar dados. Código de erro: ${response.code()}")
                    Toast.makeText(this@DetalhesActivity, "Erro ao carregar dados", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Jogos>, t: Throwable)
            {
                Log.e("Jogos", "Erro ao carregar jogos", t)
                Toast.makeText(this@DetalhesActivity, "Erro ao buscar informações do jogo", Toast.LENGTH_SHORT).show()
            }
        })
        
        // Define o listener do botão de voltar para a MainActivity
        voltar.setOnClickListener {
            val intent = Intent(this@DetalhesActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}