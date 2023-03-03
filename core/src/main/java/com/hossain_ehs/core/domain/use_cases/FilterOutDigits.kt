package com.hossain_ehs.core.domain.use_cases

class FilterOutDigits {
        operator fun invoke(text : String) : String{
            return text.filter {
                it.isDigit()
            }
        }
}