package com.example.games


import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.games.databinding.AdicionaTelaActivityBinding
import android.content.Intent
import android.net.Uri
import android.webkit.URLUtil
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("DEPRECATION")
class AdicionarTela : AppCompatActivity() {
    private lateinit var binding: AdicionaTelaActivityBinding
    private lateinit var nome: EditText
    private lateinit var classificacao: EditText
    private lateinit var descricao: EditText
    private lateinit var imagem: EditText
    private lateinit var btnConfirmar: Button
    private lateinit var btnVoltar: Button
    private lateinit var btnGoogle: Button
    private lateinit var btnVerifyUrl: Button

    companion object {
        private const val REQUEST_CODE_COPY_LINK = 1
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = AdicionaTelaActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nome = binding.tvAdicionarNome
        classificacao = binding.tvAdicionarClassificacao
        descricao = binding.tvAdicionarDescricao
        imagem = binding.tvAdicionarURL
        btnConfirmar = binding.tvButtonConfirmar
        btnVoltar = binding.tvButtonVoltar
        btnGoogle = binding.tvButtonGoogle
        btnVerifyUrl = binding.tvButtonAtualizarURL

        imagem.isFocusable = false
        imagem.isClickable = false


        btnVerifyUrl.setOnClickListener()
        {
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager


            if (clipboardManager.hasPrimaryClip() && clipboardManager.primaryClipDescription!!.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                val text = clipboardManager.primaryClip?.getItemAt(0)?.text.toString()
                // Verifica se o texto copiado é uma URL de imagem

                if (text.isEmpty())
                {
                    Toast.makeText(this, "Nenhuma URL encontrada na área de transferência", Toast.LENGTH_SHORT).show()
                }
                else if (URLUtil.isValidUrl(text))
                {
                    if (text.endsWith(".jpg") || text.endsWith(".png"))
                    {
                        imagem.setText(text)
                        Toast.makeText(this, "Link é uma imagem!", Toast.LENGTH_SHORT).show()
                    } else
                    {
                        Toast.makeText(this, "A URL copiada não é uma imagem válida", Toast.LENGTH_SHORT).show()
                    }
                }
                else
                {
                    Toast.makeText(this, "A URL copiada não é válida", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnConfirmar.setOnClickListener {

            val nome2 = nome.text.toString()
            val classificacao2 = classificacao.text.toString()
            val descricao2 = descricao.text.toString()
            val imagem2 = imagem.text.toString()

            if (nome2.isEmpty() || classificacao2.isEmpty() || descricao2.isEmpty() || imagem2.isEmpty())
            {
                Toast.makeText(this, "Preencha todos os dados corretamente!", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val retrofitConfig = RetrofitConfig.getRetrofit() // Conexão com o servidor Node.Js na porta 3000

                // Cria uma implementação da interface Service. Permite usar os métodos de endPoint
                val service = retrofitConfig.create(Service::class.java)

                // Cria um objeto Jogos com os dados preenchidos pelo usuário
                val jogo = Jogos(nome2, classificacao2, descricao2, imagem2)// O ID será gerado automaticamente pelo banco de dados

                // Chama o método incluirJogo da interface Service para fazer o POST
                val callback = service.incluirJogo(jogo)

                // Trata a resposta da requisição
                callback?.enqueue(object : Callback<Jogos?> {
                    override fun onResponse(call: Call<Jogos?>, response: Response<Jogos?>) {
                        if (response.isSuccessful)
                        {
                            Toast.makeText(this@AdicionarTela, "Jogo adicionado com sucesso!", Toast.LENGTH_SHORT).show()
                            nome.setText("")
                            classificacao.setText("")
                            descricao.setText("")
                            imagem.setText("")
                        } else
                        {
                            Toast.makeText(this@AdicionarTela, "Erro ao adicionar jogo", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Jogos?>, t: Throwable) {
                        Toast.makeText(this@AdicionarTela, "Erro ao adicionar jogo", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }

        btnGoogle.setOnClickListener()
        {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/imghp"))
            startActivity(intent)

            startActivityForResult(intent, REQUEST_CODE_COPY_LINK)

        }

            btnVoltar.setOnClickListener {
                val intent = Intent(this@AdicionarTela, Menu::class.java)
                finish()
                startActivity(intent)
            }

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
                imagem.setText(text)
            }
        }
    }
}
