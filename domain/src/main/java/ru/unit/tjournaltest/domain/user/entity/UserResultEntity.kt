package ru.unit.tjournaltest.domain.user.entity

data class UserResultEntity(
    val name: String,
    val karma: Int,
    val avatar: UserAvatarEntity
)
