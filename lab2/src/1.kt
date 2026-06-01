fun readInt(message: String): Int {

    while (true) {
        print(message)

        val input = readLine()

        try {
            return input!!.toInt()
        } catch (e: Exception) {
            println("Потрібно ввести ціле число.")
        }
    }
}

fun main() {

    val a = readInt("Введіть перше число: ")
    val b = readInt("Введіть друге число: ")
    val c = readInt("Введіть третє число: ")

    var min = a

    if (b < min) {
        min = b
    }

    if (c < min) {
        min = c
    }

    println("Найменше число: $min")
}