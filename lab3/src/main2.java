import java.util.Scanner;

public class main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть межі відрізка для x (a та b):");
        double a = readDoubleSafely(scanner);
        double b = readDoubleSafely(scanner);

        System.out.println("Введіть межі відрізка для y (c та d):");
        double c = readDoubleSafely(scanner);
        double d = readDoubleSafely(scanner);

        // Перевірка, чи не виникла помилка під час введення
        if (Double.isNaN(a) || Double.isNaN(b) || Double.isNaN(c) || Double.isNaN(d)) {
            System.out.println("Помилка: введено некоректні дані! Програму зупинено.");
            return;
        }

        // Обчислюємо крок (ділимо на 7, щоб отримати 8 точок)
        double hx = (b - a) / 7.0;
        double hy = (d - c) / 7.0;

        // Виведення "шапки" таблиці (значення x)
        System.out.print("  y\\x |");
        for (int i = 0; i < 8; i++) {
            System.out.printf("%8.2f", a + hx * i);
        }
        System.out.println("\n" + "-".repeat(70)); // Лінія розділення

        // Вкладені цикли для обчислення таблиці
        double y = c;
        for (int i = 0; i < 8; i++) {
            System.out.printf("%5.2f |", y); // Виводимо y зліва
            double x = a;
            for (int j = 0; j < 8; j++) {
                // Наша формула: u = x^2 + y^4 * x
                double u = x * x + Math.pow(y, 4) * x;
                System.out.printf("%8.2f", u);
                x += hx;
            }
            System.out.println(); // Перехід на новий рядок
            y += hy;
        }
    }

    // Допоміжний метод для безпечного зчитування чисел (з крапкою або комою)
    private static double readDoubleSafely(Scanner scanner) {
        if (scanner.hasNext()) {
            String input = scanner.next().replace(",", ".");
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                return Double.NaN; // Повертаємо NaN як індикатор помилки
            }
        }
        return Double.NaN;
    }
}