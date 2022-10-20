package utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun String.toFormattedDate(pattern: String) : String {
    val inputFormatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH)
    val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH)
    val dateParser = LocalDate.parse(this, inputFormatter)
    return dateParser.format(outputFormatter)
}