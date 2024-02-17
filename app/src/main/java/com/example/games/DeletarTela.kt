package com.example.games

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.games.databinding.DeletaTelaActivityBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.DriverManager
import kotlin.math.log

class DeletarTela : AppCompatActivity()
{
    private lateinit var binding: DeletaTelaActivityBinding
    private lateinit var id: EditText
    private lateinit var btnConfirmar: Button
    private lateinit var btnVoltar: Button

    val retrofitconfig = RetrofitConfig.getRetrofit()
    val service = retrofitconfig.create(Service::class.java)


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = DeletaTelaActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = binding.tvIdDeletar
        btnConfirmar = binding.tvBtnDeletar
        btnVoltar = binding.tvBtnVoltar

        btnVoltar.setOnClickListener()
        {
            val intent = Intent(this@DeletarTela, Menu::class.java)
            finish()
            startActivity(intent)
        }

        btnConfirmar.setOnClickListener() {
            val id2 = id.text.toString()

            if (id2.isBlank())
            {
                // exibe mensagem de erro se o campo do ID estiver em branco
                val builder = AlertDialog.Builder(this)
                builder.setTitle("AVISO!")
                builder.setMessage("DIGITE UM ID VÁLIDO!")
                builder.setPositiveButton("OK") { _, _ -> }
                val dialog = builder.create()
                dialog.show()
            }
            else
            {
                // faz a chamada à API para verificar se o ID existe
                verificarIdExistente(id2)
            }

            }
        }
    private fun verificarIdExistente(id: String)
    {
        service.searchID(id).enqueue(object : Callback<Jogos>
        {

            override fun onResponse(call: Call<Jogos>, response: Response<Jogos>)
            {
                atualizarDados(id)

                val jogos = response.body()
                if (response.isSuccessful)
                {

                }
                else
                {
                    Log.e("Em Deletar Tela", "Erro na requisição. Código de erro: ${response.code()}")
                    val builder = AlertDialog.Builder(this@DeletarTela)
                    builder.setTitle("AVISO!")
                    builder.setMessage("ID NÃO EXISTE NA TABELA!")
                    builder.setPositiveButton("OK")
                    { _, _ ->

                    }
                    val dialog = builder.create()
                    dialog.show()
                }
            }

            override fun onFailure(call: Call<Jogos>, t: Throwable)
            {
                Log.e("Em Deletar Tela", "Erro ao buscar jogo com ID $id: ${t.message}")
                Toast.makeText(this@DeletarTela, "Erro ao Deletar ID", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun atualizarDados(id3: String)
    {
        service.excluirJogo(id3).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful)
                {
                    val builder = AlertDialog.Builder(this@DeletarTela)
                    builder.setTitle("AVISO!")
                    builder.setMessage("ID ENCONTRADO E DELETADO COM SUCESSO!")
                    builder.setPositiveButton("OK")
                    { _, _ ->

                    }
                    val dialog = builder.create()
                    dialog.show()

                    binding.tvIdDeletar.setText("")
                }
                else
                {
                    val builder = AlertDialog.Builder(this@DeletarTela)
                    builder.setTitle("AVISO!")
                    builder.setMessage("ERRO AO DELETAR ID!")
                    builder.setPositiveButton("OK")
                    { _, _ ->

                    }
                    val dialog = builder.create()
                    dialog.show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@DeletarTela, "Erro ao deletar dados!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}


