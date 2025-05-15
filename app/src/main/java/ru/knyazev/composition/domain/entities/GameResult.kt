package ru.knyazev.composition.domain.entities

data class GameResult(
    val winner: Boolean,
    val countOfQuestions: Int,
    val countOfRightAnswer: Int,
    val gameSettings: GameSettings,
)