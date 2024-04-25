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
import com.kanaye.kanayecinema.databinding.ActivityMainBinding
import com.kanaye.kanayecinema.databinding.ActivityRegisterBinding
import com.kanaye.kanayecinema.models.User

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val userUsername: EditText = findViewById(R.id.username)
        val userEmail: EditText = findViewById(R.id.user_email)
        val userPassword: EditText = findViewById(R.id.user_password)
        val button: Button = findViewById(R.id.button_auth)
        val linkToAuth: TextView = findViewById(R.id.tvLogin)

        linkToAuth.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val username = userUsername.text.toString().trim()
            val email = userEmail.text.toString().trim()
            val password = userPassword.text.toString().trim()

            if (username == "" || email == "" || password == "" )
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show()
            else {
                val user = User(username, email, password)

                val db = DbHelper(this, null)
                db.addUser(user)
                Toast.makeText(this, "Пользователь $username добавлен", Toast.LENGTH_SHORT).show()
                userUsername.text.clear()
                userEmail.text.clear()
                userPassword.text.clear()

            }
        }
    }
}