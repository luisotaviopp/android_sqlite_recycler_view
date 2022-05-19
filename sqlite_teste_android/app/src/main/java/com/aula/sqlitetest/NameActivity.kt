package com.aula.sqlitetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_name.*

class NameActivity : AppCompatActivity() {

    // Database
    val databaseHandler = DatabaseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        val edit = intent.getBooleanExtra("edit", false)
        val position = intent.getIntExtra("position", 0)
        if(edit){
            val pessoa = databaseHandler.getPessoa(position)
            etNome.setText(pessoa.nome)
            btnInsertNome.setText("Editar")
        }
        btnInsertNome.setOnClickListener {
            if(etNome.text.toString() == ""){
                Toast.makeText(this,"Nome está vazio.",Toast.LENGTH_SHORT).show()
            }
            else {
                if(edit){
                    val pessoa = Pessoa(position, etNome.text.toString())
                    databaseHandler.updatePessoa(pessoa)
                    finish()
                }
                else {
                    val pessoa = Pessoa(0, etNome.text.toString())
                    databaseHandler.addPessoa(pessoa)
                    finish()
                }
            }
        }
        btnCancel.setOnClickListener {
            finish()
        }
    }
}