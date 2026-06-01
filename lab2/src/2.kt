import kotlin.math.sqrt

fun readDouble(message: String): Double {
    while (true) {
        print(message)
        val input = readLine()

        try {
            return input!!.replace(',', '.').toDouble()
        } catch (e: Exception) {
            println("Помилка! Потрібно ввести число.")
        }
    }
}

fun main() {
    println("Розв'язання нерівності (x - a) / (x^2 + b*x + c) < 0")

    val a = readDouble("Введіть a: ")
    val b = readDouble("Введіть b: ")
    val c = readDouble("Введіть c: ")

    val d = b * b - 4 * c

    if (d < 0) {
        println("x Є (-inf; %.3f)".format(a))
    } else if (d == 0.0) {
        val x0 = -b / 2.0

        if (a < x0) {
            println("x Є (-inf; %.3f)".format(a))
        } else {
            if (a == x0) {
                println("x Є (-inf; %.3f)".format(a))
            } else {
                println("x Є (-inf; %.3f) U (%.3f; %.3f)".format(x0, x0, a))
            }
        }
    } else {
        val x1 = (-b - sqrt(d)) / 2.0
        val x2 = (-b + sqrt(d)) / 2.0

        if (a < x1) {
            println("x Є (-inf; %.3f) U (%.3f; %.3f)".format(a, x1, x2))
        } else {
            if (a == x1) {
                println("x Є (-inf; %.3f) U (%.3f; %.3f)".format(a, a, x2))
            } else {
                if (a < x2) {
                    println("x Є (-inf; %.3f) U (%.3f; %.3f)".format(x1, a, x2))
                } else {
                    if (a == x2) {
                        println("x Є (-inf; %.3f)".format(x1))
                    } else {
                        println("x Є (-inf; %.3f) U (%.3f; %.3f)".format(x1, x2, a))
                    }
                }
            }
        }
    }
}