package com.example.loginsignupauth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginsignupauth.databinding.ActivityQuizBinding

class quizActivity : AppCompatActivity() {

    private lateinit var binding : ActivityQuizBinding

    private val questions = arrayOf("Which famous landmark is located in the heart of Paris, France?","In which country can you find Machu Picchu, a 15th-century Inca citadel located in the Andes Mountains?"," The Great Barrier Reef, the world's largest coral reef system, is situated off the coast of which country?")
    private val options = arrayOf(
        arrayOf("Big Ben","Statue of Liberty","Eiffel Tower"),
        arrayOf("Peru","Mexico","Egypt"),
        arrayOf("Australia","Brazil","Canada"))
    private val correctAnswers = arrayOf(2,1,1)

    private var currentQuestionIndex =0
    private var score =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestions()
        binding.option1Button.setOnClickListener {
            checkAnswer(0)
        }
        binding.option2Button.setOnClickListener {
            checkAnswer(1)
        }
        binding.option3Button.setOnClickListener {
            checkAnswer(2)
        }
        binding.restartButton.setOnClickListener {
            restartQuiz()
        }

    }

    private fun correctButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(android.graphics.Color.GREEN)
            1 -> binding.option2Button.setBackgroundColor(android.graphics.Color.GREEN)
            2 -> binding.option3Button.setBackgroundColor(android.graphics.Color.GREEN)
        }
    }

    private fun wrongButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(android.graphics.Color.RED)
            1 -> binding.option2Button.setBackgroundColor(android.graphics.Color.RED)
            2 -> binding.option3Button.setBackgroundColor(android.graphics.Color.RED)
        }
    }

    private fun resetButtonColors(){
        binding.option1Button.setBackgroundColor(android.graphics.Color.rgb(50,59,96))
        binding.option2Button.setBackgroundColor(android.graphics.Color.rgb(50,59,96))
        binding.option3Button.setBackgroundColor(android.graphics.Color.rgb(50,59,96))
    }

    private fun showResults(){
        Toast.makeText(this,"Your Score: $score out of ${questions.size}", Toast.LENGTH_LONG).show()
        binding.restartButton.isEnabled = true
    }

    private fun displayQuestions(){
        binding.questionText.text = questions[currentQuestionIndex]
        binding.option1Button.text = options[currentQuestionIndex][0]
        binding.option2Button.text = options[currentQuestionIndex][1]
        binding.option3Button.text = options[currentQuestionIndex][2]
        resetButtonColors()
    }

    private fun checkAnswer(selectedAnswerIndex:Int){
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]

        if(selectedAnswerIndex == correctAnswerIndex)
        {
            score++
            correctButtonColors(selectedAnswerIndex)
        }else{
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }
        if(currentQuestionIndex < questions.size - 1){
            currentQuestionIndex++
            binding.questionText.postDelayed({displayQuestions()},1000)
        }else{
            showResults()
        }

    }
    private fun restartQuiz(){
        currentQuestionIndex = 0
        score  =0
        displayQuestions()
        binding.restartButton.isEnabled = false
    }
}

