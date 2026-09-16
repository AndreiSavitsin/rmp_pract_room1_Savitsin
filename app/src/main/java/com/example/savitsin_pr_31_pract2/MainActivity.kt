package com.example.savitsin_pr_31_pract2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var resultSpinner: Spinner
    lateinit var diffSpinner: Spinner
    lateinit var countError: EditText
    lateinit var countPoint: EditText

    var resultList = listOf(
        ResultsDbEntity(1, "Победа"),
        ResultsDbEntity(2, "Ничья"),
        ResultsDbEntity(3, "Поражение")
    )
    var diffList = listOf(
        DifficultyLevelsDbEntity(1, "Лёгкая"),
        DifficultyLevelsDbEntity(2, "Средняя"),
        DifficultyLevelsDbEntity(3, "Сложная")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultSpinner = findViewById(R.id.result)
        diffSpinner = findViewById(R.id.diff)
        countError = findViewById(R.id.countError)
        countPoint = findViewById(R.id.countPoint)

        val resultAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            resultList.map { it.resultName }
        )
        resultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        resultSpinner.adapter = resultAdapter

        val diffAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            diffList.map { it.difficultyName }
        )
        diffAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        diffSpinner.adapter = diffAdapter
    }

    fun Add(view: View) {
        val errorText = countError.text.toString()
        val pointText = countPoint.text.toString()

        if (errorText.isNotEmpty() && pointText.isNotEmpty()) {
            val resultId = resultList[resultSpinner.selectedItemPosition].id
            val diffId = diffList[diffSpinner.selectedItemPosition].id

            lifecycleScope.launch {
                val statistic = Statistic(
                    resultId = resultId,
                    difficultId = diffId,
                    mistakes = errorText.toLong(),
                    points = pointText.toLong()
                )
                Dependencies.statisticRepository.insertNewStatisticData(statistic.toStatisticDbEntity())


                Toast.makeText(this@MainActivity, "Данные сохранены!", Toast.LENGTH_SHORT).show()

                countError.text.clear()
                countPoint.text.clear()
            }
        } else {
            AlertDialog.Builder(this)
                .setTitle("Ошибка")
                .setMessage("Заполните все поля")
                .setPositiveButton("OK", null)
                .create()
                .show()
        }
    }

    fun AllStat(view: View) {
        val intent = Intent(this, StatActivity::class.java)
        startActivity(intent)
    }

    fun Back(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}