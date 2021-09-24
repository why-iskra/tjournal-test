package ru.unit.tjournaltest.data.transformer

interface BaseTransformer<T, R> {
    fun transform(value: T): R
}