package ru.unit.tjournaltest.domain.user.pojo

data class UserPOJO(
    val message: String,
    val success: Boolean,
    val result: UserResultPOJO?
)