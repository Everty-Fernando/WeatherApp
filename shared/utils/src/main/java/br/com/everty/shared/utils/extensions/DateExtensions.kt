package br.com.everty.shared.utils.extensions

import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter


fun Long.convertTimestampToString(format: String): String {
    val instant = Instant.ofEpochSecond(this)
    val localDateTime = LocalDateTime.ofInstant(instant,  ZoneId.systemDefault())
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(format)

    return localDateTime.format(formatter).firstLetterUpperCase()
}

fun Long.convertTimestampToLocalDate(): LocalDate {
    val instant = Instant.ofEpochSecond(this)
    return LocalDateTime.ofInstant(instant, ZoneOffset.UTC).toLocalDate()
}


const val FORMAT_MONTH_YEAR_FULL = "MMMM, dd"
const val FORMAT_HOURS_MINUTES = "HH:MM"
const val FORMAT_HOURS = "HH"