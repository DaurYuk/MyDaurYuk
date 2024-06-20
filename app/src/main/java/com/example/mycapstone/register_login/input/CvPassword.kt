package com.example.mycapstone.register_login.input

class CvPassword {
    fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }
}