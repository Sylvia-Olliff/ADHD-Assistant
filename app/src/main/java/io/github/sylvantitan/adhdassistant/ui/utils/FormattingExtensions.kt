package io.github.sylvantitan.adhdassistant.ui.utils

import java.time.LocalDateTime

fun LocalDateTime.toFormattedString(): String {
    return "${this.monthValue}/${this.dayOfMonth}/${this.year - 2000} - ${this.hour}:${this.minute}"
}