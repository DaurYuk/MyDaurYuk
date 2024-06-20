package com.example.mycapstone.register_login.input

class CvName {
    fun isValidName(name: String): Boolean {
        return name.isNotEmpty() && name.length > 2
    }
}