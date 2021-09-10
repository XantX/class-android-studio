package com.example.app_geoquizz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var questions: ArrayList<Question>
    var position = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadQuestions()
        setupViews()
    }

    private fun loadQuestions() {
        questions = ArrayList()
        var question = Question("Es lima la capital de chile?", false)
        questions.add(question)
        questions.add(Question("Es lima la capital de peru?", true))
        questions.add(Question("Es el pollo a la brasa peruano?", true))
        questions.add(Question("Es Santiago la capital de ecuador", false))
        questions.add(Question("Es Buenos Aires la capital de Colombia", false))
        questions.add(Question("Es el callao la capital de chile", false))
    }

    private fun setupViews() {
        // primera forma
        val btYes = findViewById<Button>(R.id.btYes)
        val btNo = findViewById<Button>(R.id.btNo)
        val btNext = findViewById<Button>(R.id.btNext)
        // Add cantidad de preguntas

        //Cargar las preguntas

        showSentence()
        btNext.setOnClickListener {
            position++
            if (position >= questions.size){
               position = 0
            }
            showSentence()
        }

        btYes.setOnClickListener {
            if (questions[position].answer){
                showSuccessQuestion()
            }else{
                showFailQuestion()
            }
        }
        btNo.setOnClickListener {
            if (!questions[position].answer){
                showSuccessQuestion()
            }else{
                showFailQuestion()
            }
        }
    }

    private fun showFailQuestion() {
        Toast.makeText(this,"Incorrecto",Toast.LENGTH_SHORT).show()
    }

    private fun showSuccessQuestion() {
        Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show()
    }

    private fun showSentence() {
        val tvSentence = findViewById<TextView>(R.id.tvSentence)
        tvSentence.text = questions[position].sentence
    }
}