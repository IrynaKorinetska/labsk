import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;

        System.out.print("Enter an integer greater than 999: ");

        if (scanner.hasNextInt()) {
            n = scanner.nextInt();

            if (n > 999) {
                int digit = (n / 100) % 10;
                System.out.println("The hundreds digit is: " + digit);
            } else {
                System.out.println("Error: Number must be greater than 999.");
            }
        } else {
            System.out.println("Error: You must enter an integer.");
        }

        scanner.close();
    }
}