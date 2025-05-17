package ru.knyazev.composition.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
    val winner: Boolean,
    val countOfQuestions: Int,
    val countOfRightAnswer: Int,
    val gameSettings: GameSettings,
) : Parcelable