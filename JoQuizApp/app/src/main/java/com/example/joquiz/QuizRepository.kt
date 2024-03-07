package com.example.joquiz

import android.content.Context
import org.xmlpull.v1.XmlPullParser

class QuizRepository (private val context: Context){
    private val quizList = mutableListOf<QuizList>()

    init {
        loadQuizzesFromXML()
    }

    private fun loadQuizzesFromXML() {
        try {
            val parser: XmlPullParser = context.resources.getXml(R.xml.quizzes)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG && parser.name == "quiz") {
                    parser.nextTag() // Avance au tag <title>
                    if (parser.name == "title") {
                        parser.next() // Avance au texte du titre
                        val title = parser.text
                        addQuiz(QuizList(title))
                    }
                }
                eventType = parser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
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

