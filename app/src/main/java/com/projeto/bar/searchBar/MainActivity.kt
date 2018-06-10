package com.projeto.bar.searchBar

import android.app.ProgressDialog
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView

import com.projeto.bar.searchBar.adapters.MyAdapter
import com.google.firebase.auth.FirebaseAuth
import com.projeto.bar.searchBar.models.Bar
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val progress: ProgressDialog by lazy { ProgressDialog(this) }

    var br = ConnectivityReceiver()
    lateinit var ref: DatabaseReference
    lateinit var barList: MutableList<Bar>
    lateinit var rvBars: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        registerReceiver(br, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))

        barList = mutableListOf()


        rvBars = findViewById(R.id.rvBares)
        progress.setCancelable(false)
        progress.setMessage("carregando dados ...")
        progress.show()

        ref = FirebaseDatabase.getInstance().getReference("Bares")


        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                if (p0!!.exists()) {
                    barList.clear()

                    for (e in p0.children) {
                        val bar = e.getValue(Bar::class.java)
                        barList.add(bar!!)

                    }
                    progress.dismiss()
                    val adapter = MyAdapter(this@MainActivity, R.layout.item_list, barList)
                    rvBars.adapter = adapter

                }
            }

        })


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_ficha_de_cadastro) {//abre a ficha de cadastro(nao cadastra)
            var i = Intent(this@MainActivity, FichaDeCadastro::class.java)
            startActivity(i)
            // Handle the camera action
        } else if (id == R.id.nav_perfil) {
            var i = Intent(this@MainActivity, Cadastro::class.java)
            startActivity(i)

        } else if (id == R.id.nav_ajuda) {//tratamento de abrir o navegador e ir para a página
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://searchabar.wixsite.com/searchabar")))

        } else if (id == R.id.nav_pagamento) {
            var i = Intent(this@MainActivity, Informacao::class.java)
            startActivity(i)

        } else if (id == R.id.nav_share) {//tratamento de signout do app

            try {
                FirebaseAuth.getInstance().signOut()
                val preferences = PreferenceManager.getDefaultSharedPreferences(this)
                val editor = preferences.edit()
                editor.clear()
                editor.commit()
                val changePage = Intent(this, Main2Activity::class.java)
                startActivity(changePage)
            } catch (e: Exception) {
            }


        } else if (id == R.id.nav_send) {//tratamento de envio por email
            var intentEmail: Intent = Intent(Intent.ACTION_SENDTO,
                    Uri.fromParts("Search Bar", "reclamacao@search.com", null))
            intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Reclamaçao")
            intentEmail.putExtra(Intent.EXTRA_TEXT, "Sua reclamação é:")
            startActivity(Intent.createChooser(intentEmail, "Enviar email..."))

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true


    }

}