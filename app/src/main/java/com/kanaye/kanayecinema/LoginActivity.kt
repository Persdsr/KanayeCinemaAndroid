package com.kanaye.kanayecinema

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.kotlinapp.DbHelper
import com.kanaye.kanayecinema.databinding.ActivityLoginBinding
import com.kanaye.kanayecinema.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val userUsername: EditText = findViewById(R.id.user_username)
        val userPassword: EditText = findViewById(R.id.user_password)
        val button: Button = findViewById(R.id.button_auth)
        val linkToReg: TextView = findViewById(R.id.tvRegister)

        button.setOnClickListener {
            val username = userUsername.text.toString().trim()
            val password = userPassword.text.toString().trim()

            if (username == ""  || password == "" )
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show()
            else {
                val db = DbHelper(this, null)
                val isAuth: Boolean = db.getUser(username, password)

                if(isAuth) {
                    Toast.makeText(this, "Пользователь $username авторизован", Toast.LENGTH_SHORT)
                        .show()
                    userUsername.text.clear()
                    userPassword.text.clear()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else
                    Toast.makeText(this, "Пользователь $username НЕ авторизован", Toast.LENGTH_SHORT).show()

            }
        }

        linkToReg.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}