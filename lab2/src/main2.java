import java.util.Scanner;

public class main2 {

    public static double readDouble(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                System.out.println("Помилка! Потрібно ввести число.");
                scanner.next();
            }
        }
    }

    public static void main(String[] args) {
        double a, b, c, d;

        System.out.println("Розв'язання нерівності (x - a) / (x^2 + b*x + c) < 0");
        Scanner scanner = new Scanner(System.in);

        a = readDouble(scanner, "Введіть a: ");
        b = readDouble(scanner, "Введіть b: ");
        c = readDouble(scanner, "Введіть c: ");

        d = b * b - 4 * c;

        if (d < 0) {
            System.out.printf("x Є (-inf; %.3f)%n", a);
        } else {
            if (d == 0) {
                double x0 = -b / 2.0;

                if (a < x0) {
                    System.out.printf("x Є (-inf; %.3f)%n", a);
                } else {
                    if (a == x0) {
                        System.out.printf("x Є (-inf; %.3f)%n", a);
                    } else {
                        System.out.printf("x Є (-inf; %.3f) U (%.3f; %.3f)%n", x0, x0, a);
                    }
                }
            } else {
                double x1 = (-b - Math.sqrt(d)) / 2.0;
                double x2 = (-b + Math.sqrt(d)) / 2.0;

                if (a < x1) {
                    System.out.printf("x Є (-inf; %.3f) U (%.3f; %.3f)%n", a, x1, x2);
                } else {
                    if (a == x1) {
                        System.out.printf("x Є (-inf; %.3f) U (%.3f; %.3f)%n", a, a, x2);
                    } else {
                        if (a < x2) {
                            System.out.printf("x Є (-inf; %.3f) U (%.3f; %.3f)%n", x1, a, x2);
                        } else {
                            if (a == x2) {
                                System.out.printf("x Є (-inf; %.3f)%n", x1);
                            } else {
                                System.out.printf("x Є (-inf; %.3f) U (%.3f; %.3f)%n", x1, x2, a);
                            }
                        }
                    }
                }
            }
        }

        scanner.close();
    }
}