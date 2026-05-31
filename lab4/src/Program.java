

import java.util.List;
import java.util.Scanner;

public class Program {
    private static Repository repository;

    public static void main(String[] args) {
        repository = new FileRepository("vacancies.dat");
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nОберіть потрібну дію:");
            System.out.println("1 - Переглянути всі вакансії");
            System.out.println("2 - Додати вакансію");
            System.out.println("3 - Шукати за мовою та зарплатою");
            System.out.println("4 - Шукати за компанією");
            System.out.println("5 - Вилучити вакансію");
            System.out.println("0 - Завершити роботу");

            int choice = Integer.parseInt(input.nextLine());
            if (choice == 0) break;

            switch (choice) {
                case 1:
                    List<Vacancy> all = repository.getAll();
                    if(all.isEmpty()) System.out.println("Список порожній.");
                    else all.forEach(System.out::println);
                    break;
                case 2:
                    repository.addVacancy(inputNewVacancy(input));
                    System.out.println("Вакансію додано!");
                    break;
                case 3:
                    System.out.println("Мова програмування:");
                    String lang = input.nextLine();
                    System.out.println("Мінімальна ЗП:");
                    float minS = Float.parseFloat(input.nextLine());
                    System.out.println("Максимальна ЗП:");
                    float maxS = Float.parseFloat(input.nextLine());
                    repository.getByLanguageAndSalary(lang, minS, maxS).forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("Назва компанії:");
                    String comp = input.nextLine();
                    repository.getByCompany(comp).forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("Вкажіть ID вакансії для вилучення:");
                    int id = Integer.parseInt(input.nextLine());
                    if (repository.deleteVacancy(id)) System.out.println("Вилучено успішно.");
                    else System.out.println("Вакансію не знайдено.");
                    break;
                default:
                    System.out.println("Невідома команда.");
            }
        }
    }

    private static Vacancy inputNewVacancy(Scanner scanner) {
        System.out.println("Назва компанії:");
        String company = scanner.nextLine();
        System.out.println("Назва позиції:");
        String pos = scanner.nextLine();
        System.out.println("Мова/технологія:");
        String lang = scanner.nextLine();
        System.out.println("Вимоги:");
        String req = scanner.nextLine();
        System.out.println("Орієнтовна ЗП:");
        float salary = Float.parseFloat(scanner.nextLine());
        return new Vacancy(company, pos, lang, req, salary);
    }
}