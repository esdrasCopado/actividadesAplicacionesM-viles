
// Nombre: Esdras Jahir Copado Martinez
// ID: 173089

data class Evaluacion(val posicionExacta: Int, val posicionIncorrecta: Int)

fun evaluar(secret: String, guess: String): Evaluacion {
    var posicionExacta = 0
    var posicionIncorrecta = 0
    val secretCount = mutableMapOf<Char, Int>()
    val guessCount = mutableMapOf<Char, Int>()

    for (i in secret.indices) {
        if (secret[i] == guess[i]) {
            posicionExacta++
        } else {
            secretCount[secret[i]] = secretCount.getOrDefault(secret[i], 0) + 1
            guessCount[guess[i]] = guessCount.getOrDefault(guess[i], 0) + 1
        }
    }

    for ((char, count) in guessCount) {
        if (char in secretCount) {
            posicionIncorrecta += minOf(secretCount[char]!!, count)
        }
    }

    return Evaluacion(posicionExacta, posicionIncorrecta)
}

fun main() {
    println(evaluar("ABCD", "ABCD")) // Evaluacion(4, 0)
    println(evaluar("ABCD", "CDBA")) // Evaluacion(0, 4)
    println(evaluar("ABCD", "ABDC")) // Evaluacion(2, 2)
    println(evaluar("AABC", "ADFE")) // Evaluacion(1, 0)
    println(evaluar("AABC", "DEAA")) // Evaluacion(0, 2)
}