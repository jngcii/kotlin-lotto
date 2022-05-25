package com.nextstep.jngcii.calculator

object InputParser {
    private val delimiterParseRegex = Regex("//(\\D)\\\\n(.*)")
    private val defaultDelimiterRegex = Regex("[,:]")

    fun parseDelimiter(input: String): Pair<String, String?> {
        delimiterParseRegex.find(input)?.let {
            val (delimiter, expression) = it.destructured
            return Pair(expression, delimiter)
        }
        return Pair(input, null)
    }

    fun parseExpression(input: String, delimiter: String? = null): List<Int> {
        val parsedResults = delimiter?.let { input.split(it) }
            ?: input.split(defaultDelimiterRegex)

        return parsedResults.map { it.toIntOrThrow().onlyPositiveOrThrow() }
    }

    private fun String.toIntOrThrow(): Int {
        return try {
            this.toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("부적절한 계산식입니다.")
        }
    }

    private fun Int.onlyPositiveOrThrow(): Int {
        if (this < 0) {
            throw IllegalArgumentException("양의 정수만 입력 가능합니다.")
        }
        return this
    }
}
