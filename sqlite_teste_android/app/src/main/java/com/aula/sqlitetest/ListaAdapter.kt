package com.aula.sqlitetest
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.content_lista.view.*

class ListaAdapter (nameList: List<Pessoa>, private var ctx: Context, private val callbacks: (Int) -> Unit): RecyclerView.Adapter<ListaAdapter.ViewHolder>() {

    private var nameList: List<Pessoa> = ArrayList<Pessoa>()
    init {
        this.nameList = nameList
    }

    // Aqui é onde o viewholder é criado a partir do layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.content_lista, parent, false)
        return ViewHolder(view)
    }

    // Nessa parte é onde se modifica o item do viewholder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = nameList[position]

        holder.name.text = name.nome
        if(position % 2 == 0) holder.name.setBackgroundColor(Color.GRAY)
        else holder.name.setBackgroundColor(Color.WHITE)
        holder.name.setOnClickListener {
            val intent = Intent(ctx, NameActivity::class.java)
            intent.putExtra("edit", true)
            intent.putExtra("position", name.id)
            ctx.startActivity(intent)
        }
        holder.btn.setOnClickListener {
            val databaseHandler = DatabaseHandler(ctx)
            databaseHandler.deletePessoa(name.id)
            callbacks(position)
        }
    }

    // Devolve quantidade de itens do nameList
    override fun getItemCount(): Int {
        return nameList.size
    }

    // Aqui é a criação dos itens do viewholder
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var name: TextView = view.tvAdpNome
        var btn: Button = view.btnAdpDel
    }
}