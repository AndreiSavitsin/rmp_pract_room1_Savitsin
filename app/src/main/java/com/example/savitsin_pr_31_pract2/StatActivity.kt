package com.example.savitsin_pr_31_pract2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class StatActivity : AppCompatActivity() {

    lateinit var emailUser: TextView
    lateinit var passwordUser: TextView
    lateinit var resultUser: TextView
    lateinit var compUser: TextView
    lateinit var errorsUser: TextView
    lateinit var pointsUser: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stat)

        emailUser = findViewById(R.id.emailUser)
        passwordUser = findViewById(R.id.passwordUser)
        resultUser = findViewById(R.id.resultUser)
        compUser = findViewById(R.id.compUser)
        errorsUser = findViewById(R.id.errorsUser)
        pointsUser = findViewById(R.id.pointsUser)

        val pref = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        emailUser.text = pref.getString("email", "Не указан")
        passwordUser.text = pref.getString("password", "Не указан")

        lifecycleScope.launch {
            val allStats = Dependencies.statisticRepository.getAllStatisticData()
            if (allStats.isNotEmpty()) {
                val last = allStats.last()
                resultUser.text = last.result
                compUser.text = last.difficult
                errorsUser.text = last.mistakes.toString()
                pointsUser.text = last.points.toString()
            } else {
                resultUser.text = "Нет данных"
                compUser.text = "Нет данных"
                errorsUser.text = "0"
                pointsUser.text = "0"
            }
        }
    }

    fun Back(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}