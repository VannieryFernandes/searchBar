package com.projeto.bar.searchBar

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.projeto.bar.searchBar.MainActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {
    //activity de login
    val progress: ProgressDialog by lazy { ProgressDialog(this) }
    var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        checkloguin()

        // Action Button Send Loguin
        btSend.setOnClickListener {view ->
            signIn(view,etUser.text.toString(), etPassword.text.toString())
        }

    }

    //método de login
    fun signIn(view: View, usuario: String, senha: String){
        //verifica se o login e senha é igual ao banco do firebase
        if(etUser.text.toString().isNotEmpty() && etPassword.text.toString().isNotEmpty()){
            fbAuth.signInWithEmailAndPassword(usuario, senha).addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                if(task.isSuccessful){
                    progress.setCancelable(false)
                    progress.setMessage("Aguarde ...")
                    progress.show()
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    sharedPref()
                    finish()
                }else{
                    Toast.makeText(this,"Email e/ou senha incorreto(s)", Toast.LENGTH_SHORT).show()
                }
            })
        }else{
            Toast.makeText(this,"Preencha os campos!", Toast.LENGTH_SHORT).show()
        }

    }


    fun checkloguin(){
        FirebaseAuth.getInstance()
        // other setup code
        val mPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        if (mPrefs.getBoolean("conectado", false)) {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        } else {
        }
    }

    //shared preference
    fun sharedPref(){

        if(cbCheck.isChecked == true){
            var pref = PreferenceManager.getDefaultSharedPreferences(this)
            var editor = pref.edit()

            //save infos
            editor.putBoolean("conectado", true)
            editor.commit()
        }else{
        }
    }





}
