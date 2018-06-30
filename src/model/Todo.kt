package model

import kotlin.js.Date

// Não pode ter função dentro de data class
data class Todo(
    val id: Double = Date().getTime(),
    val title: String,
    val completed: Boolean = false
)