package com.example.mentalhealth.Fragment

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mentalhealth.activities.ResultActivity
import com.example.mentalhealth.constants.Constants
import kotlinx.android.synthetic.main.activity_qusestions.*
import mentalhealth.R

class QusestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private  var mCorrectAnswers: Int = 0
    private var mUserName: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qusestions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionList = Constants.getQuestionts()

        setQuestion()

        tv_Option_one.setOnClickListener(this)
        tv_Option_two.setOnClickListener(this)
        tv_Option_Three.setOnClickListener(this)
        tv_Option_Four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)


    }
    private fun setQuestion(){

//        mCurrentPosition = 1
        val question = mQuestionList!![mCurrentPosition - 1]


        defaultOptionsView()
        if (mCurrentPosition == mQuestionList!!.size){
            btn_submit.text = "FINISH"
        }else{
            btn_submit.text = " SUBMIT"
        }

        progress_bar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition" + "/" + progress_bar.max
        tvQuestionInd.text = question!!.question
        ivimage.setImageResource(question.image)
        tv_Option_one.text = question.OptionOne
        tv_Option_two.text = question.OptionTwo
        tv_Option_Three.text = question.OptionThree
        tv_Option_Four.text = question.OptionFour
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0,tv_Option_one)
        options.add(1,tv_Option_two)
        options.add(2,tv_Option_Three)
        options.add(3,tv_Option_Four)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.answer_bg
            )
        }

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_Option_one ->{
                selectedOptionView(tv_Option_one,1)
            }
            R.id.tv_Option_two ->{
                selectedOptionView(tv_Option_two,2)
            }
            R.id.tv_Option_Three ->{
                selectedOptionView(tv_Option_Three,3)
            }
            R.id.tv_Option_Four ->{
                selectedOptionView(tv_Option_Four,4)
            }
            R.id.btn_submit ->{
                if (mSelectedOptionPosition == 0){
                    mCurrentPosition ++

                    when{
                        mCurrentPosition <= mQuestionList!!.size ->{
                            setQuestion()
                        }else ->{
                            val intent = Intent(this, ResultActivity::class.java)
                             intent.putExtra(Constants.USER_NAME, mUserName)
                             intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
//
//                        if (mCorrectAnswers < 3){
//                            tv_score.setText("You have failed")
//                        }

                             intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)
                             startActivity(intent)
                              finish()
                        }
                    }
                }else{
                    val question = mQuestionList?.get(mCurrentPosition - 1)
                    if(question!!.correctAnswer != mSelectedOptionPosition){
//                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border)
                    }else{
                        mCorrectAnswers++
                    }
//                    answerView(question.correctAnswer, R.drawable.correct_option_border)

                    if (mCurrentPosition == mQuestionList!!.size){
                        btn_submit.text = "FINISH"
                    }else{
                        btn_submit.text = "Go to next question"
                    }
                    mSelectedOptionPosition = 0
                }

            }
        }
    }
    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 -> {
                tv_Option_one.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                tv_Option_two.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            1 -> {
                tv_Option_Three.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            1 -> {
                tv_Option_Four.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.edittext_background
        )
    }
}