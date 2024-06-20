package com.example.mycapstone.register_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mycapstone.MainActivity
import com.example.mycapstone.R
import com.example.mycapstone.databinding.ActivityLoginBinding
import com.example.mycapstone.register_login.viewmodel.LoginViewModel
import com.example.mycapstone.register_login.viewmodel.ResponseLogin

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        val text = "Belum punya akun? Daftar"
        val spannableString = SpannableString(text)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }

        val start = text.indexOf("Daftar")
        val end = start + "Daftar".length

        spannableString.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val linkColor = resources.getColor(R.color.selected_bottom_nav_color, null)
        spannableString.setSpan(ForegroundColorSpan(linkColor), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.registerDesc.text = spannableString
        binding.registerDesc.movementMethod = LinkMovementMethod.getInstance()

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            loginViewModel.login(email, password) { response ->
                if (response != null && !response.error) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Login failed: ${response?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
