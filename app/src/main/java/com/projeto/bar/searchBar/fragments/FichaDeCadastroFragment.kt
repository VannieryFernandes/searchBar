package com.projeto.bar.searchBar.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projeto.bar.searchBar.R

/**
 * A placeholder fragment containing a simple view.
 */
class FichaDeCadastroFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ficha_de_cadastro, container, false)
    }
}
