package com.givechuckajob.geoquiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class QuizActivity : AppCompatActivity() {

    lateinit private var mQuestionTextView:TextView
    lateinit private var mNextButton:Button
    lateinit private var mTrueButton:Button
    lateinit private var mFalseButton:Button

    private val mQuestionBank = arrayOf(Question(R.string.question_australia, true), Question(R.string.question_oceans, true), Question(R.string.question_mideast, false), Question(R.string.question_africa, false), Question(R.string.question_americas, true), Question(R.string.question_asia, true))
    private var mCurrentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        mQuestionTextView = findViewById(R.id.question_text_view)
        mNextButton = findViewById(R.id.next_button)
        mTrueButton = findViewById(R.id.true_button)
        mFalseButton = findViewById(R.id.false_button)


        mNextButton.setOnClickListener {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size
            updateQuestion()
        }
        mTrueButton.setOnClickListener { checkAnswer(true) }
        mFalseButton.setOnClickListener { checkAnswer(false) }
        updateQuestion()
    }
    private fun updateQuestion() {
        val question = mQuestionBank[mCurrentIndex].textResId
        mQuestionTextView.setText(question)
    }
    private fun checkAnswer(userPressedTrue: Boolean) {
        val answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue

        var messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast
        }
        else {
            messageResId = R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}


