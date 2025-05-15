package ru.knyazev.composition.domain.repository

import ru.knyazev.composition.domain.entities.GameSettings
import ru.knyazev.composition.domain.entities.Level
import ru.knyazev.composition.domain.entities.Question

interface Repository {

    fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question

    fun getGameSettings(level: Level): GameSettings
}