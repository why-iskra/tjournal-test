package ru.unit.tjournaltest.data.converter

import ru.unit.tjournaltest.data.api.dto.LoginResponseDTO
import ru.unit.tjournaltest.domain.auth.entity.AuthEntity

object AuthConverter {
    fun apiResponseToEntity(value: LoginResponseDTO) = AuthEntity(value.message, value.success)
}