package com.example.games

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.games.databinding.LayoutMenuActivityBinding



class Menu : AppCompatActivity()
{
    private lateinit var binding: LayoutMenuActivityBinding
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioBtn1: RadioButton
    private lateinit var radioBtn2: RadioButton
    private lateinit var radioBtn3: RadioButton
    private lateinit var radioBtn4: RadioButton
    var escolhaBotao: String? = null
    private lateinit var voltar: Button
    private lateinit var acao: Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = LayoutMenuActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        radioGroup = findViewById(R.id.radioGroup)
        radioBtn1 = binding.radioButton1
        radioBtn2 = binding.radioButton2
        radioBtn3 = binding.radioButton3
        radioBtn4 = binding.radioButton4

        voltar = binding.btnVoltar
        acao = binding.btnACTION


        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            escolhaBotao = when (checkedId) {
                radioBtn1.id -> "Opção1"
                radioBtn2.id -> "Opção2"
                radioBtn3.id -> "Opção3"
                radioBtn4.id -> "Opção4"
                else -> null
            }
        }

        voltar.setOnClickListener()
        {
            val intent = Intent(this@Menu, MainActivity::class.java)
            finish()
            startActivity(intent)
        }

        acao.setOnClickListener()
        {
            when (escolhaBotao)
            {
                "Opção1" ->
                {
                    val intent = Intent(this@Menu, AdicionarTela::class.java)
                    finish()
                    startActivity(intent)
                }

                "Opção2" ->
                {
                    val intent = Intent(this@Menu, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                }

                "Opção3" ->
                {
                    val intent = Intent(this@Menu, AtualizarTela::class.java)
                    finish()
                    startActivity(intent)
                }

                "Opção4" ->
                {
                    val intent = Intent(this@Menu, DeletarTela::class.java)
                    finish()
                    startActivity(intent)
                }

                else ->
                {
                    Log.d("DEBUG", "Escolha inválida")
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("AVISO!")
                    builder.setMessage("SELECIONE UMA DAS OPÇÕES ACIMA E CLIQUE EM " +
                            "CONFIRMAR")
                    builder.setPositiveButton("OK")
                    { _, _ ->
                        // código a ser executado quando o usuário clica em OK
                    }
                    val dialog = builder.create()
                    dialog.show()
                }
            }
        }
    }
}