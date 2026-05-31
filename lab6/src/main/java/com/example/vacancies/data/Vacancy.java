package com.example.vacancies.data;

public class Vacancy {
    private int id;
    private String company;
    private String position;
    private String language;
    private String requirements;
    private double salary;

    public Vacancy(int id, String company, String position, String language, String requirements, double salary) {
        this.id = id;
        this.company = company;
        this.position = position;
        this.language = language;
        this.requirements = requirements;
        this.salary = salary;
    }

    public Vacancy(String company, String position, String language, String requirements, double salary) {
        this.company = company;
        this.position = position;
        this.language = language;
        this.requirements = requirements;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getCompany() { return company; }
    public String getPosition() { return position; }
    public String getLanguage() { return language; }
    public String getRequirements() { return requirements; }
    public double getSalary() { return salary; }
}