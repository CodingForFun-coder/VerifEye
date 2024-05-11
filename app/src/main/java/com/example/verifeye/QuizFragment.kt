package com.example.verifeye

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

class QuizFragment : Fragment(), View.OnClickListener {

    private var selectedButton: Button? = null

    private var totalQuestionsTextView: TextView? = null
    private var questionTextView: TextView? = null
    private var ansA: Button? = null
    private var ansB: Button? = null
    private var ansC: Button? = null
    private var ansD: Button? = null
    private var submitBtn: Button? = null

    private var score = 0
    private var totalQuestion = QuestionAnswer.question.size
    private var currentQuestionIndex = 0
    private var selectedAnswer = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)

        totalQuestionsTextView = view.findViewById(R.id.totalQuestions)
        questionTextView = view.findViewById(R.id.question)
        ansA = view.findViewById(R.id.ansA)
        ansB = view.findViewById(R.id.ansB)
        ansC = view.findViewById(R.id.ansC)
        ansD = view.findViewById(R.id.ansD)
        submitBtn = view.findViewById(R.id.submit_btn)

        ansA?.setOnClickListener(this)
        ansB?.setOnClickListener(this)
        ansC?.setOnClickListener(this)
        ansD?.setOnClickListener(this)
        submitBtn?.setOnClickListener(this)

        val questionsText = getString(R.string.total_questions, totalQuestion)
        totalQuestionsTextView?.text = questionsText
        loadNewQuestion()

        return view
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.ansA, R.id.ansB, R.id.ansC, R.id.ansD -> {
                selectedButton?.setBackgroundColor(Color.WHITE)
                selectedButton = view as Button
                selectedAnswer = selectedButton?.text.toString()
                selectedButton?.setBackgroundColor(Color.MAGENTA)
            }
            R.id.submit_btn -> {
                if (selectedAnswer == QuestionAnswer.correctAnswers[currentQuestionIndex]) {
                    score++
                }
                currentQuestionIndex++
                selectedButton?.setBackgroundColor(Color.WHITE)
                selectedButton = null
                selectedAnswer = ""

                if (currentQuestionIndex < totalQuestion) {
                    loadNewQuestion()
                } else {
                    finishQuiz()
                }
            }
        }
    }

    private fun loadNewQuestion() {
        questionTextView?.text = QuestionAnswer.question[currentQuestionIndex]
        val choices = QuestionAnswer.choices[currentQuestionIndex]
        ansA?.text = choices[0]
        ansB?.text = choices[1]
        ansC?.text = choices[2]
        ansD?.text = choices[3]
        ansA?.setBackgroundColor(Color.WHITE)
        ansB?.setBackgroundColor(Color.WHITE)
        ansC?.setBackgroundColor(Color.WHITE)
        ansD?.setBackgroundColor(Color.WHITE)
    }

    private fun finishQuiz() {
        val passStatus = if (score > totalQuestion * 0.60) "Passed" else "Failed"
        val message = getString(R.string.score_message, score, totalQuestion)

        AlertDialog.Builder(requireContext())
            .setTitle(passStatus)
            .setMessage(message)
            .setPositiveButton(R.string.restart) { _, _ -> restartQuiz() }
            .setCancelable(false)
            .show()
    }

    private fun restartQuiz() {
        score = 0
        currentQuestionIndex = 0
        selectedButton = null
        loadNewQuestion()

        ansA?.setBackgroundColor(Color.WHITE)
        ansB?.setBackgroundColor(Color.WHITE)
        ansC?.setBackgroundColor(Color.WHITE)
        ansD?.setBackgroundColor(Color.WHITE)

        totalQuestionsTextView?.text = getString(R.string.total_questions, totalQuestion)

    }
}