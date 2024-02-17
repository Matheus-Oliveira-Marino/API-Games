package com.example.games

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import android.content.Intent


class JogosAdapter(private val context: Context, private var jogos: List<Jogos>) : BaseAdapter() {

    var onItemClickListener: ((jogo: Jogos) -> Unit)? = null

    override fun getCount(): Int
    {
        return jogos.size
    }

    override fun getItem(position: Int): Any
    {
        return jogos[position]
    }

    override fun getItemId(position: Int): Long
    {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
    {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.card_dados, parent, false)
        val jogo = jogos[position]

        // Carregar a imagem do jogo
        val imageView = view.findViewById<ImageView>(R.id.tv_Imagem)

        if (imageView != null)
        {
            Picasso.get().load(jogo.imagem).into(imageView)
        }

        // Configurar os outros campos
        view.findViewById<TextView>(R.id.tv_Nome).text = jogo.nome
        view.findViewById<TextView>(R.id.tv_Classificacao).text = jogo.classificacao

        view.setOnClickListener {
            onItemClickListener?.invoke(jogo)
            val intent = Intent(context, DetalhesActivity::class.java)
            intent.putExtra("nome", jogo.nome)
            intent.putExtra("imagem", jogo.imagem)
            context.startActivity(intent)
        }
        return view


    }

    fun updateData(jogos: List<Jogos>)
    {
        this.jogos = jogos
        notifyDataSetChanged()
    }
}

