package com.projeto.bar.searchBar.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.projeto.bar.searchBar.Informacao
import com.projeto.bar.searchBar.models.Bar
import com.projeto.bar.searchBar.R
import com.squareup.picasso.Picasso


class MyAdapter(val mCtx : Context , val layoutId:Int , val barList:List<Bar>)
    : ArrayAdapter<Bar>(mCtx,layoutId,barList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val convertView = layoutInflater.inflate(layoutId, null)

        val img = convertView.findViewById<ImageView>(R.id.img)
        val nome = convertView.findViewById<TextView>(R.id.tvNome)
        val endereco = convertView.findViewById<TextView>(R.id.tvEndereco)
        val cidade = convertView.findViewById<TextView>(R.id.tvCidade)


        val bar = barList[position]


        nome.text = bar.name
        endereco.text = bar.street
        cidade.text = bar.city

        Picasso.with(mCtx).load(bar.img).into(img)

        convertView.setOnClickListener(object:View.OnClickListener {


            override fun onClick(v: View) {
                openDetailActivity(bar.name, bar.city, bar.street, bar.lat, bar.lng,bar.email,bar.phone,bar.img)
            }
        })

        return convertView
    }
    //responsável por enviar informações para outra tela
    private fun openDetailActivity(vararg details:String) {
        val i = Intent(mCtx, Informacao::class.java)
        i.putExtra("NAME_BAR", details[0])
        i.putExtra("CITY", details[1])
        i.putExtra("STREET", details[2])
        i.putExtra("LAT", details[3])
        i.putExtra("LNG", details[4])
        i.putExtra("EMAIL",details[5])
        i.putExtra("PHONE",details[6])
        i.putExtra("IMG",details[7])
        mCtx.startActivity(i)
    }


}