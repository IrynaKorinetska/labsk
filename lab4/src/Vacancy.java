

import java.io.Serializable;

public class Vacancy implements Serializable {
    public static final long serialVersionUID = 1L;

    private int id;
    private String companyName;
    private String position;
    private String language;
    private String requirements;
    private float salary;

    public Vacancy() {}

    public Vacancy(String companyName, String position, String language, String requirements, float salary) {
        this.id = 0;
        this.companyName = companyName;
        this.position = position;
        this.language = language;
        this.requirements = requirements;
        this.salary = salary;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getRequirements() { return requirements; }
    public void setRequirements(String requirements) { this.requirements = requirements; }

    public float getSalary() { return salary; }
    public void setSalary(float salary) { this.salary = salary; }

    @Override
    public String toString() {
        return String.format("%d | %-12s | %-15s | %-10s | ЗП: $%.2f | Вимоги: %s",
                id, companyName, position, language, salary, requirements);
    }
}