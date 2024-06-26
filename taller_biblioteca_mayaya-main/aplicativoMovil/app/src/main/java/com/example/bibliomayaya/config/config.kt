package com.example.bibliomayaya.config

class config {

    //se crea una url static, para consultar sin instanciar
    //método companion object sirve para almacenar las variables static

    companion object{
        val urlBase="http://192.168.22.167:8080/api/v1/"
        val urlLibro=urlBase+"libro/"
    }
}