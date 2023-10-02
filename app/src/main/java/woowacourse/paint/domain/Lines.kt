package woowacourse.paint.domain

data class Lines(val value: List<Line> = emptyList()) {
    fun add(line: Line): Lines = Lines(value + line)
}
