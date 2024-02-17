package com.example.games

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridView
import android.widget.ProgressBar
import android.widget.Toast
import com.example.games.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myProgressBar: ProgressBar
    private lateinit var fab: FloatingActionButton
    private lateinit var gridView: GridView
    private lateinit var adapter: JogosAdapter
    private lateinit var jogos: List<Jogos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gridView = binding.tvGridView
        adapter = JogosAdapter(this, emptyList())
        gridView.adapter = adapter

        myProgressBar = binding.myProgressBar
        myProgressBar.isIndeterminate = true
        myProgressBar.visibility = View.VISIBLE

        fab = binding.fab
        fab.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
            finish()
        }

        carregarJogos()

//        gridView.setOnItemClickListener { _, _, position, _ ->
//            val jogo = jogos[position]
//            val intent = Intent(this, DetalhesActivity::class.java)
//            intent.putExtra("nome", jogo.nome)
//            startActivity(intent)
//        }
    }

    private fun carregarJogos() {
        val retrofitConfig = RetrofitConfig.getRetrofit()
        val service = retrofitConfig.create(Service::class.java)
        val callback = service.getAll()

        callback.enqueue(object : Callback<List<Jogos>> {
            override fun onResponse(call: Call<List<Jogos>>, response: Response<List<Jogos>>) {
                myProgressBar.visibility = View.GONE
                jogos = response.body() ?: emptyList()
                Log.d("Jogos", "Número de jogos carregados: ${jogos.size}")
                adapter.updateData(jogos)
                Log.d("JogosAdapter", "Número de jogos: ${adapter.count}")
            }

            override fun onFailure(call: Call<List<Jogos>>, t: Throwable) {
                myProgressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, "Erro ao carregar jogos", Toast.LENGTH_SHORT).show()
            }
        })
    }
}


