package com.example.savitsin_pr_31_pract2

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class LoginActivity : AppCompatActivity() {

    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var pref: SharedPreferences
    lateinit var ed: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Dependencies.init(this)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)

        pref = getSharedPreferences("MyPrefs", MODE_PRIVATE)
    }

    fun Login(view: View)
    {
        val emailText = email.text.toString()
        val passwordText = password.text.toString()

        if (emailText.isNotEmpty() && passwordText.isNotEmpty()) {
            ed = pref.edit()
            ed.putString("email", emailText)
            ed.putString("password", passwordText)
            ed.apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            AlertDialog.Builder(this)
                .setTitle("Ошибка")
                .setMessage("Поля должны быть заполнены")
                .setPositiveButton("OK", null)
                .create()
                .show()
        }
    }
}