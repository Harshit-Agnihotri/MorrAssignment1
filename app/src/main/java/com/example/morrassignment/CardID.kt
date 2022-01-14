package com.example.morrassignment

class CardID() {
    private lateinit var cvvRegex: Regex

    private fun luhn(number: String): Boolean {
        val numbers = number.map { it.toString().toInt() }.toIntArray()
        val sum = evenDigits(numbers) + oddDigits(numbers)
        return sum % 10 == 0
    }

    private fun oddDigits(numbers: IntArray): Int {
        var sum = 0
        for (i in numbers.indices) {
            if (i % 2 == 1) {
                sum += numbers[i]
            }
        }
        return sum
    }

    private fun evenDigits(numbers: IntArray): Int {
        var sum = 0
        for (i in numbers.indices) {
            if (i % 2 == 0) {
                val digit = numbers[i] * 2
                if (digit > 9) {
                    val digits = digit.toString().map { it.toString().toInt() }.toIntArray()
                    for (j in digits.indices) {
                        sum += digits[j]
                    }
                } else {
                    sum += digit
                }
            }
        }
        return sum
    }

    private fun CardType(cardNumber: String): Boolean{
        val visa = "^4[0-9]{12}(?:[0-9]{3})?$".toRegex()
        val masterCard = "^(5[1-5][0-9]{14}|2(22[1-9][0-9]{12}|2[3-9][0-9]{13}|[3-6][0-9]{14}|7[0-1][0-9]{13}|720[0-9]{12}))\$".toRegex()
        val americanExpress = "^3[47][0-9]{13}\$".toRegex()
        val discover = "^65[4-9][0-9]{13}|64[4-9][0-9]{13}|6011[0-9]{12}|(622(?:12[6-9]|1[3-9][0-9]|[2-8][0-9][0-9]|9[01][0-9]|92[0-5])[0-9]{10})\$".toRegex()
        if (visa(cardNumber, visa)) cvvRegex = "\\d{3}".toRegex()
        if (masterCard(cardNumber, masterCard)) cvvRegex = "\\d{3}".toRegex()
        if (americanExpress(cardNumber, americanExpress)) cvvRegex = "\\d{4}".toRegex()
        if (discover(cardNumber, discover)) cvvRegex = "\\d{3}".toRegex()
        return visa(cardNumber, visa) || masterCard(cardNumber, masterCard) || americanExpress(cardNumber, americanExpress)
                || discover(cardNumber, discover)
    }

    private fun visa(cardNumber: String, regex: Regex): Boolean{
        return cardNumber.matches(regex)
    }

    private fun masterCard(cardNumber: String, regex: Regex): Boolean{
        return cardNumber.matches(regex)
    }

    private fun americanExpress(cardNumber: String, regex: Regex): Boolean{
        return cardNumber.matches(regex)
    }

    private fun discover(cardNumber: String, regex: Regex): Boolean{
        return cardNumber.matches(regex)
    }

    fun getRegex():Regex = cvvRegex

    fun validateCard(cardNumber: String): Boolean = luhn(cardNumber) && CardType(cardNumber)
}