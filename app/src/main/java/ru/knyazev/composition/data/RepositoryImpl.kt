package ru.knyazev.composition.data

import ru.knyazev.composition.domain.entities.GameSettings
import ru.knyazev.composition.domain.entities.Level
import ru.knyazev.composition.domain.entities.Question
import ru.knyazev.composition.domain.repository.Repository
import kotlin.random.Random

object RepositoryImpl : Repository {
    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        while (options.size < countOfOptions) {
            options.add(Random.nextInt(MIN_ANSWER_VALUE, sum))
        }
        options.random()
        return Question(sum, visibleNumber, options.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> GameSettings(10, 3, 50, 8)
            Level.EASY -> GameSettings(10, 10, 70, 60)
            Level.NORMAL -> GameSettings(20, 20, 80, 40)
            Level.HARD -> GameSettings(30, 30, 90, 40)
        }
    }
}