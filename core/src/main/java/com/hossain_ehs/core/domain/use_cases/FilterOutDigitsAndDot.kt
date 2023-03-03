package com.hossain_ehs.core.domain.use_cases

class FilterOutDigitsAndDot {
    operator fun invoke(input: String): String {
        // filter out everything except digits and dot
        val filtered = input.filter { it.isDigit() || it == '.' }
        var dotCount = 0
        val result = StringBuilder()
        for (c in filtered) {
            if (c == '.') {
                if (dotCount == 0) { // only allow one dot
                    result.append(c)
                    dotCount++
                }
            } else {
                result.append(c)
            }
        }
        return result.toString()
    }
}