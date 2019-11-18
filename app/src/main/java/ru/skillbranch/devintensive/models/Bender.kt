package ru.skillbranch.devintensive.models;

public class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String =
        when (question) {
            Question.NAME -> Question.NAME.question
            Question.PROFESSION -> Question.PROFESSION.question
            Question.MATERIAL -> Question.MATERIAL.question
            Question.BDAY -> Question.BDAY.question
            Question.SERIAL -> Question.SERIAL.question
            Question.IDLE -> Question.IDLE.question
        }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        val (valid, s) = question.validate(answer)
        if (!valid) return "$s\n${question.question}" to status.color
        return if (question.answers.contains(answer.toLowerCase())) {
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        } else {
            status = status.nextStatus()
            when {
                status.equals(Status.CRITICAL) -> "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
                else -> "Это неправильный ответ\n${question.question}" to status.color
            }
        }
    }


    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION
            override fun validate(s: String): Pair<Boolean, String> = when {
                "^[A-Z]{1}\\w{1,}".toRegex().matches(s) -> true to ""
                else -> false to "Имя должно начинаться с заглавной буквы"
            }
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question = MATERIAL
            override fun validate(s: String): Pair<Boolean, String> = when {
                "^[a-z]{1}\\w{1,}".toRegex().matches(s) -> true to ""
                else -> false to "Профессия должна начинаться со строчной буквы"
            }
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY
            override fun validate(s: String): Pair<Boolean, String> = when {
                "\\D{1,}".toRegex().matches(s) -> true to ""
                else -> false to "Материал не должен содержать цифр"
            }
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
            override fun validate(s: String): Pair<Boolean, String> = when {
                "\\d{1,}".toRegex().matches(s) -> true to ""
                else -> false to "Год моего рождения должен содержать только цифры"
            }
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
            override fun validate(s: String): Pair<Boolean, String> = when {
                "\\d{7}".toRegex().matches(s) -> true to ""
                else -> false to "Серийный номер содержит только цифры, и их 7"
            }
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question = IDLE
            override fun validate(s: String) = true to ""
        };

        abstract fun nextQuestion(): Question
        abstract fun validate(s: String): Pair<Boolean, String>
    }
}