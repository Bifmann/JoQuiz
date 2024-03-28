package com.example.joquiz

import android.content.Context
import org.xmlpull.v1.XmlPullParser

// Définition de la classe QuizRepository pour charger et stocker les quiz.
class QuizRepository(private val context: Context) {
    // Liste pour stocker les quiz chargés.
    private val quizList = mutableListOf<QuizList>()

    // Bloc d'initialisation pour charger les quiz dès la création de l'instance.
    init {
        loadQuizzesFromXML()
    }

    // Fonction pour charger les quiz à partir d'un fichier XML.
    private fun loadQuizzesFromXML(): List<QuizList> {
        try {
            // Initialisation du parseur XML.
            val parser: XmlPullParser = context.resources.getXml(R.xml.quizzes)
            var eventType = parser.eventType

            // Parcourir le document XML.
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG && parser.name == "quiz") {
                    // Initialisation des variables pour stocker les données d'un quiz.
                    var title = ""
                    val questions = mutableListOf<Question>()

                    // Boucle pour traiter chaque balise à l'intérieur d'un élément quiz.
                    while (!(eventType == XmlPullParser.END_TAG && parser.name == "quiz")) {
                        when {
                            // Traitement de la balise titre.
                            eventType == XmlPullParser.START_TAG && parser.name == "title" -> {
                                parser.next() // Passage au texte du titre.
                                title = parser.text
                            }
                            // Traitement des questions.
                            eventType == XmlPullParser.START_TAG && parser.name == "question" -> {
                                val question = parseQuestion(parser)
                                questions.add(question)
                            }
                        }
                        eventType = parser.next()
                    }
                    // Ajout du quiz à la liste.
                    quizList.add(QuizList(title, questions))
                } else {
                    eventType = parser.next()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace() // Gestion des erreurs.
        }
        return quizList
    }

    // Fonction pour analyser une question et ses réponses à partir du fichier XML.
    private fun parseQuestion(parser: XmlPullParser): Question {
        var text = ""
        var trueAnswer = ""
        var wrongAnswer1 = ""
        var wrongAnswer2 = ""

        // Boucle pour traiter chaque balise à l'intérieur d'une question.
        while (!(parser.eventType == XmlPullParser.END_TAG && parser.name == "question")) {
            if (parser.eventType == XmlPullParser.START_TAG) {
                when (parser.name) {
                    "text" -> {
                        parser.next() // Passage au texte de la question.
                        text = parser.text
                    }
                    "trueAnswer" -> {
                        parser.next() // Passage à la bonne réponse.
                        trueAnswer = parser.text
                    }
                    "wrongAnswer1" -> {
                        parser.next() // Passage à la première mauvaise réponse.
                        wrongAnswer1 = parser.text
                    }
                    "wrongAnswer2" -> {
                        parser.next() // Passage à la seconde mauvaise réponse.
                        wrongAnswer2 = parser.text
                    }
                }
            }
            parser.next()
        }
        // Création et retour de l'objet Question.
        return Question(text, trueAnswer, wrongAnswer1, wrongAnswer2)
    }

    // Fonction pour obtenir tous les quiz chargés.
    fun getAllQuizzes(): List<QuizList> {
        return quizList.toList()
    }
}
