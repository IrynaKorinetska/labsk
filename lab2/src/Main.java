import java.util.Scanner;

public class Main {

    public static int readInt(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);

            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Потрібно ввести ціле число.");
                scanner.next(); // очищає неправильний ввід
            }
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int a = readInt(scanner, "Введіть перше число: ");
        int b = readInt(scanner, "Введіть друге число: ");
        int c = readInt(scanner, "Введіть третє число: ");

        int min = a;

        if (b < min) {
            min = b;
        }

        if (c < min) {
            min = c;
        }

        System.out.println("Найменше число: " + min);

        scanner.close();
    }
}