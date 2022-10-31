package com.example.mentalhealth.constants

import com.example.mentalhealth.Fragment.Question
import mentalhealth.R

object Constants{

    const val STORAGE_PATH_UPLOADS = "uploads/"
    const val DATABASE_PATH_UPLOADS = "uploads"
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total questions"
    const val CORRECT_ANSWERS: String = "correct answers"

    fun getQuestionts(): ArrayList<Question>{
        val questionList = ArrayList<Question>()

        val question1 = Question(
            1,
            "Trouble concentrating on things, such as reading the newspaper or watching television ",
            R.drawable.sad,
            "Not at all",
            "Several days",
            "Nearly every day",
            "More than half the days",
            2
        )
        questionList.add(question1)

        val question2 = Question(
            2,
            "Feeling down, depressed, or hopeless",
            R.drawable.sad,
            "Not at all",
            "Several days",
            "Neary every day",
            "More than half the days",
            2
        )
        questionList.add(question2)

        val question3 = Question(
            3,
            "Trouble falling or staying asleep, or sleeping too much ",
            R.drawable.sad,
            "Not at all",
            "Several days",
            "Neary every day",
            "More than half the days",
            2
        )
        questionList.add(question3)

        val question4 = Question(
            4,
            "Feeling tired or having little energy ",
            R.drawable.sad,
            "Not at all",
            "Several days",
            "Neary every day",
            "More than half the days",
            2
        )
        questionList.add(question4)

        val question5 = Question(
            5,
            "Poor appetite or overeating ",
            R.drawable.sad,
            "Not at all",
            "Several days",
            "Neary every day",
            "More than half the days",
            2
        )
        questionList.add(question5)

        val question6 = Question(
            6,
            "Feeling bad about yourself - or that you are a failure or have let yourself or your family down",
            R.drawable.sad,
            "Not at all",
            "Several days",
            "Neary every day",
            "More than half the days",
            1
        )
        questionList.add(question6)

        val question7 = Question(
            7,
            "Little interest or pleasure in doing things",
            R.drawable.sad,
            "Not at all",
            "Several days",
            "Neary every day",
            "More than half the days",
            1
        )
        questionList.add(question7)
        return questionList
    }

}