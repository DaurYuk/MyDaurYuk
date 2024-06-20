package com.example.mycapstone.register_login.input

class CvIsPasswordSame {
    fun isPasswordSame(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
}