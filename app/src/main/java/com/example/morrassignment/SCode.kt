package com.example.morrassignment

class SCode {
    fun securityCode(cvvNumber: String, cvvRegex: Regex): Boolean = cvvNumber.matches(cvvRegex)
}