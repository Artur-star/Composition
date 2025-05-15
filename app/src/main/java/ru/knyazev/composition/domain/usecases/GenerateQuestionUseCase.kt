package ru.knyazev.composition.domain.usecases

import ru.knyazev.composition.domain.entities.Question
import ru.knyazev.composition.domain.repository.Repository

class GenerateQuestionUseCase(private val repository: Repository) {

    operator fun invoke(maxSumValue: Int): Question =
        repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)

    companion object {
        private const val COUNT_OF_OPTIONS = 6
    }
}