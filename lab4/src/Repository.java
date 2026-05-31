

import java.util.List;

public interface Repository {
    List<Vacancy> getAll();
    Vacancy getById(int id);
    boolean addVacancy(Vacancy vacancy);
    boolean deleteVacancy(int id);
    List<Vacancy> getByLanguageAndSalary(String language, float minSalary, float maxSalary);
    List<Vacancy> getByCompany(String companyName);
}