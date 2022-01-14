package com.example.morrassignment

class CardOwner {
    fun checkChar(name: String): Boolean = name.matches("^[a-zA-Z]*$".toRegex())
}