import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double eps = 0.00001;

        System.out.println("Введіть значення x (в межах від -1 до 1): ");
        String input = scanner.next();

        // Замінюємо кому на крапку, щоб уникнути помилок при парсингу
        input = input.replace(",", ".");

        double x;
        // Перевірка на те, чи ввів користувач саме число
        try {
            x = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Помилка: введено некоректне число!");
            return;
        }

        // Перевірка на область збіжності ряду
        if (Math.abs(x) > 1.0) {
            System.out.println("Помилка: x виходить за межі області збіжності (|x| <= 1).");
            return;
        }

        double u = x; // Доданок ряду (починаємо з k = 0)
        double s = 0; // Сума ряду
        int k = 0;    // Лічильник доданків


        while (Math.abs(u) > eps) {
            s += u;
            k++;
            u = -u * x * x * (2.0 * k - 1) / (2.0 * k + 1);
        }

        // Кінцевий розрахунок за формулою arcctg x = pi/2 - arctg x
        double result = Math.PI / 2 - s;
        double expected = Math.PI / 2 - Math.atan(x);

        System.out.printf("Обчислене значення (arcctg x) = %.6f\n", result);
        System.out.printf("Бібліотечне значення          = %.6f\n", expected);
        System.out.println("Кількість доданків: " + k);
    }
}