fun main() {
    print("Enter an integer greater than 999: ")
    val input = readLine()

    val n = input?.toIntOrNull()

    if (n == null) {
        println("Error: You must enter an integer.")
    } else if (n <= 999) {
        println("Error: Number must be greater than 999.")
    } else {
        val digit = (n / 100) % 10
        println("The hundreds digit is: $digit")
    }
}