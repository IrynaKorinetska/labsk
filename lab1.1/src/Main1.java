import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter temperature in Fahrenheit: ");

        if (scanner.hasNextDouble()) {
            double TF = scanner.nextDouble();
            double TC = (TF - 32) * 5 / 9;
            System.out.println("Temperature in Celsius: " + TC);
        } else {
            System.out.println("Error: You must enter a number.");
        }

        scanner.close();
    }
}
