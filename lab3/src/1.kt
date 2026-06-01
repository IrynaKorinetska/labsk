import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan

fun main() {
    println("Введіть значення x (в межах від -1 до 1): ")

    // Зчитуємо та одразу замінюємо можливу кому на крапку
    val input = readlnOrNull()?.replace(",", ".")

    // Безпечне перетворення у число (захист від некоректного вводу)
    val x = input?.toDoubleOrNull() ?: run {
        println("Помилка: введено некоректне число!")
        return
    }

    // Перевірка на область збіжності ряду
    if (abs(x) > 1.0) {
        println("Помилка: x виходить за межі області збіжності (|x| <= 1).")
        return
    }

    val eps = 0.00001
    var u = x
    var s = 0.0
    var k = 0

    // Цикл накопичення суми ряду
    while (abs(u) > eps) {
        s += u
        k++
        u = -u * x * x * (2.0 * k - 1) / (2.0 * k + 1) // Рекурентне обчислення
    }

    // Кінцевий розрахунок
    val result = PI / 2 - s
    val expected = PI / 2 - atan(x)

    println("Обчислене значення (arcctg x) = %.6f".format(result))
    println("Бібліотечне значення          = %.6f".format(expected))
    println("Кількість доданків: $k")
}