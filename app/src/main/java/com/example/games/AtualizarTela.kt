package com.example.games


import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.webkit.URLUtil
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.games.databinding.AtualizaTelaActivityBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Timer
import java.util.TimerTask


@Suppress("DEPRECATION")
class AtualizarTela : AppCompatActivity()
{
    private lateinit var binding: AtualizaTelaActivityBinding
    private lateinit var id: EditText
    private lateinit var nome: EditText
    private lateinit var classificacao: EditText
    private lateinit var descricao: EditText
    private lateinit var url: EditText
    private lateinit var btnConfirmar: Button
    private lateinit var btnGoogleImages: Button
    private lateinit var btnGetUrl: Button
    private lateinit var btnVoltar: Button

    companion object
    {
        private const val REQUEST_CODE_COPY_LINK = 1
    }

    val retrofitConfig = RetrofitConfig.getRetrofit()
    val service = retrofitConfig.create(Service::class.java)

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = AtualizaTelaActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = binding.tvID
        nome = binding.tvNome
        classificacao = binding.tvclassificacao
        descricao = binding.tvdescricao
        url = binding.tvAdicionarURL
        btnConfirmar = binding.tvButtonConfirmar
        btnVoltar = binding.tvButtonVoltar

        id.addTextChangedListener(object : TextWatcher
        {
            var timer: Timer? = null

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int)
            {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if ((s?.length ?: 0) > 0)
                {
                    timer?.cancel()
                    timer = Timer()
                    timer?.schedule(object : TimerTask()
                    {
                        override fun run() {
                            val retrofitConfig = RetrofitConfig.getRetrofit()
                            val service = retrofitConfig.create(Service::class.java)
                            val call  = service.searchID(id.text.toString())

                            call.enqueue(object : Callback<Jogos?> {
                                override fun onResponse(call: Call<Jogos?>, response: Response<Jogos?>)
                                {
                                    val jogo = response.body()

                                    if (response.isSuccessful)
                                    {
                                            nome.setText(jogo?.nome)
                                            classificacao.setText(jogo?.classificacao)
                                            descricao.setText(jogo?.descricao)
                                            url.setText(jogo?.imagem)
                                    }
                                    else
                                    {
                                        Toast.makeText(this@AtualizarTela, "ID incorreto ou inexistente", Toast.LENGTH_SHORT).show()
                                        nome.setText("")
                                        classificacao.setText("")
                                        descricao.setText("")
                                        url.setText("")
                                    }
                                }

                                override fun onFailure(call: Call<Jogos?>, t: Throwable)
                                {
                                    Log.e("DetalhesActivity", "Erro ao buscar jogo", t)
                                }
                            })
                        }
                    }, 800)
                }
            }


            override fun afterTextChanged(s: Editable?)
            {
                // Nada a fazer aqui
            }
        })

        //scrollView = findViewById(R.id.idScrollView)

        btnConfirmar.setOnClickListener {
            val id2 = id.text.toString()
            val nome2 = nome.text.toString()
            val classificacao2 = classificacao.text.toString()
            val descricao2 = descricao.text.toString()
            val url2 = url.text.toString()

            if (id2.isEmpty() || nome2.isEmpty() || classificacao2.isEmpty() || descricao2.isEmpty() || url2.isEmpty()) {
                val builder = AlertDialog.Builder(this@AtualizarTela)
                builder.setTitle("AVISO!")
                builder.setMessage("PREENCHA TODOS OS DADOS CORRETAMENTE!")
                builder.setPositiveButton("OK")
                { _, _ ->

                }
                val dialog = builder.create()
                dialog.show()
            } else
            {
                atualizarDados(id2)
            }
        }

        btnVoltar.setOnClickListener()
        {
            val intent = Intent(this@AtualizarTela, Menu::class.java)
            finish()
            startActivity(intent)
        }
    }

    private fun atualizarDados(id: String)
    {
        val jogoAtualizado = Jogos(
            nome = nome.text.toString(),
            classificacao = classificacao.text.toString(),
            descricao = descricao.text.toString(),
            imagem = url.text.toString()
        )

        service.alterarJogo(id, jogoAtualizado).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful)
                {
                    val builder = AlertDialog.Builder(this@AtualizarTela)
                    builder.setTitle("AVISO!")
                    builder.setMessage("JOGO ATUALIZADO COM SUCESSO!")
                    builder.setPositiveButton("OK")
                    { _, _ ->

                    }
                    val dialog = builder.create()
                    dialog.show()

                    binding.tvID.setText("")
                    nome.setText("")
                    classificacao.setText("")
                    descricao.setText("")
                    url.setText("")
                }
                else
                {
                    Toast.makeText(this@AtualizarTela, "Erro ao atualizar dados!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@AtualizarTela, "Erro ao atualizar dados!", Toast.LENGTH_SHORT).show()
            }
        })
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_COPY_LINK) {
            // Obtém o gerenciador de área de transferência
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

            // Verifica se há algum item na área de transferência
            if ((clipboard.primaryClip?.itemCount ?: 0) > 0) {
                // Obtém o texto do primeiro item da área de transferência
                val text = clipboard.primaryClip!!.getItemAt(0).text.toString()

                // Define o texto no EditText
                url.setText(text)
            }
        }
    }

}
