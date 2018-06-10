package com.projeto.bar.searchBar

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.projeto.bar.searchBar.models.Bar
import com.google.firebase.database.FirebaseDatabase

import kotlinx.android.synthetic.main.activity_ficha_de_cadastro.*

class FichaDeCadastro : AppCompatActivity() {
    //desativado esta opção de cadastrar o bar
    lateinit var editText : EditText
    lateinit var editText1 : EditText
    lateinit var editText2 : EditText


    lateinit var btn : Button
    lateinit var btn1 : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ficha_de_cadastro)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }


        editText    = findViewById(R.id.bar)
        editText1   = findViewById(R.id.endereco)
        editText2   = findViewById(R.id.cidade)


        btn   = findViewById(R.id.savebtn)
        btn1   = findViewById(R.id.databaseBtn)

        btn.setOnClickListener {
            saveData()
        }

        btn1.setOnClickListener {
            val intent = Intent(this@FichaDeCadastro,MainActivity::class.java)
            startActivity(intent)
        }

    }


    private fun saveData(){
        val nome    = editText.text.toString().trim()
        val endereco     = editText1.text.toString().trim()
        val cidade     = editText2.text.toString().trim()


        if (nome.isEmpty()){
            editText.error = "Please enter your bar"
            return
        }
        if (endereco.isEmpty()){
            editText.error = "Please enter your endereço"
            return
        }
        if (cidade.isEmpty()){
            editText.error = "Please enter your cidade"
            return
        }

        val myDataBase = FirebaseDatabase.getInstance().getReference("Bares")
        val idbar = myDataBase.push().key


    }



}


