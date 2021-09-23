package ru.unit.tjournaltest.domain.user.entity

data class UserEntity(
    val message: String,
    val success: Boolean,
    val result: UserResultEntity?
)