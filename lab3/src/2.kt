import kotlin.math.pow

fun main() {
    println("Введіть межі відрізка для x (a та b, кожне з нового рядка):")
    val a = readlnOrNull()?.replace(",", ".")?.toDoubleOrNull()
    val b = readlnOrNull()?.replace(",", ".")?.toDoubleOrNull()

    println("Введіть межі відрізка для y (c та d, кожне з нового рядка):")
    val c = readlnOrNull()?.replace(",", ".")?.toDoubleOrNull()
    val d = readlnOrNull()?.replace(",", ".")?.toDoubleOrNull()

    // Перевірка на коректність введених даних
    if (a == null || b == null || c == null || d == null) {
        println("Помилка: введено некоректні дані! Програму зупинено.")
        return
    }

    // Обчислюємо крок
    val hx = (b - a) / 7.0
    val hy = (d - c) / 7.0

    // Виведення "шапки" таблиці (значення x)
    print("  y\\x |")
    for (i in 0..7) {
        print("%8.2f".format(a + hx * i))
    }
    println("\n" + "-".repeat(70)) // Лінія розділення

    // Вкладені цикли для обчислення таблиці
    var y = c
    for (i in 0..7) {
        print("%5.2f |".format(y)) // Виводимо y зліва
        var x = a
        for (j in 0..7) {
            // Наша формула: u = x^2 + y^4 * x
            val u = x * x + y.pow(4) * x
            print("%8.2f".format(u))
            x += hx
        }
        println() // Перехід на новий рядок
        y += hy
    }
}