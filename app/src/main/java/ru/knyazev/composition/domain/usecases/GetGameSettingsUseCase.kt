package ru.knyazev.composition.domain.usecases

import ru.knyazev.composition.domain.entities.GameSettings
import ru.knyazev.composition.domain.entities.Level
import ru.knyazev.composition.domain.repository.Repository

class GetGameSettingsUseCase(private val repository: Repository) {

    operator fun invoke(level: Level): GameSettings = repository.getGameSettings(level)
}