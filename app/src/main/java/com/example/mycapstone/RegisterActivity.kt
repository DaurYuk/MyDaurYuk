package com.example.mycapstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mycapstone.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            val gmail = binding.gmailEditText.text.toString()
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            // Implementasi logika register disini
            if (registerAuthentication(gmail, username, password)){
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
    private fun registerAuthentication(gmail:String, username:String, password:String): Boolean{
        // logika register akun
        return gmail == "admin@gmail.com" && username == "user" && password == "password"
    }
}