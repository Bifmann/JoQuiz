package com.example.joquiz

import android.content.Context
import org.xmlpull.v1.XmlPullParser

class QuizRepository(private val context: Context) {
    private val quizList = mutableListOf<QuizList>()

    init {
        loadQuizzesFromXML()
    }

    fun loadQuizzesFromXML(): List<QuizList> {
        try {
            val parser: XmlPullParser = context.resources.getXml(R.xml.quizzes)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG && parser.name == "quiz") {
                    var title = ""
                    val questions = mutableListOf<Question>()
                    while (!(eventType == XmlPullParser.END_TAG && parser.name == "quiz")) {
                        if (eventType == XmlPullParser.START_TAG && parser.name == "title") {
                            parser.next() // Move to title text
                            title = parser.text
                        } else if (eventType == XmlPullParser.START_TAG && parser.name == "question") {
                            val question = parseQuestion(parser)
                            questions.add(question)
                        }
                        eventType = parser.next()
                    }
                    quizList.add(QuizList(title, questions))
                } else {
                    eventType = parser.next()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return quizList
    }

    private fun parseQuestion(parser: XmlPullParser): Question {
        var text = ""
        var trueAnswer = ""
        var wrongAnswer1 = ""
        var wrongAnswer2 = ""
        while (!(parser.eventType == XmlPullParser.END_TAG && parser.name == "question")) {
            if (parser.eventType == XmlPullParser.START_TAG) {
                when (parser.name) {
                    "text" -> {
                        parser.next() // Move to question text
                        text = parser.text
                    }
                    "trueAnswer" -> {
                        parser.next() // Move to correct answer text
                        trueAnswer = parser.text
                    }
                    "wrongAnswer1" -> {
                        parser.next() // Move to wrong answer 1 text
                        wrongAnswer1 = parser.text
                    }
                    "wrongAnswer2" -> {
                        parser.next() // Move to wrong answer 2 text
                        wrongAnswer2 = parser.text
                    }
                }
            }
            parser.next()
        }
        return Question(text, trueAnswer, wrongAnswer1, wrongAnswer2)
    }


    fun getAllQuizzes(): List<QuizList> {
        return quizList.toList()
    }

    fun addQuiz(quiz: QuizList) {
        quizList.add(quiz)
    }

    //fun removeQuiz(quiz: QuizList) {
    //    quizList.remove(quiz)
    //}
}

