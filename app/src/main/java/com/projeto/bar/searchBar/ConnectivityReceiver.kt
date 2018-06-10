package com.projeto.bar.searchBar

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast

class ConnectivityReceiver : BroadcastReceiver() {
    //broadcast receiver
    override fun onReceive(context: Context, intent: Intent) {
        var cm:ConnectivityManager =context.getSystemService(Context.CONNECTIVITY_SERVICE)as ConnectivityManager

        var netInfo = cm.activeNetworkInfo
        //verifica se a conexao do celular está ok
        if(netInfo != null && netInfo.isConnectedOrConnecting){


        }else{//senão avisa ao usuário
            val toast = Toast.makeText(context, "Sem internet você não bebe!", Toast.LENGTH_SHORT)
            toast.show()
        }

    }
}
