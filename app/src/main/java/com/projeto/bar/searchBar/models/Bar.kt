package com.projeto.bar.searchBar.models



class Bar(val idBar: String, val name: String, val city: String, val street: String, val email: String
               , val img: String,val phone: String, val lat:String,val lng:String) {
    constructor() : this("", "", "", "", "", "", "", "", "") {}

}
