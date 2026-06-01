
fun main() {
    print("Enter temperature in Fahrenheit: ")
    val TF = readLine()!!.toDouble()

    val TC = (TF - 32) * 5 / 9

    println("Temperature in Celsius: $TC")
}