

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class FileRepository implements Repository {
    private String fileName;
    private List<Vacancy> vacancies;

    public FileRepository(String fileName) {
        this.fileName = fileName;
        this.vacancies = new ArrayList<>();
        if (new File(fileName).exists()) {
            reloadData();
        }
    }

    @SuppressWarnings("unchecked")
    private void reloadData() {
        if (new File(fileName).exists()) {
            try (FileInputStream fis = new FileInputStream(fileName);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                vacancies = (List<Vacancy>) ois.readObject();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void save() throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this.vacancies);
        }
    }

    @Override
    public List<Vacancy> getAll() {
        reloadData();
        return vacancies;
    }

    @Override
    public Vacancy getById(int id) {
        reloadData();
        return vacancies.stream().filter(v -> v.getId() == id).findFirst().orElse(null);
    }

    @Override
    public boolean addVacancy(Vacancy vacancy) {
        reloadData();
        int id = 1;
        if (!vacancies.isEmpty()) {
            OptionalInt maxId = vacancies.stream().mapToInt(Vacancy::getId).max();
            if (maxId.isPresent()) {
                id = maxId.getAsInt() + 1;
            }
        }
        vacancy.setId(id);
        vacancies.add(vacancy);
        try {
            save();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean deleteVacancy(int id) {
        reloadData();
        Optional<Vacancy> found = vacancies.stream().filter(v -> v.getId() == id).findFirst();
        if (found.isPresent()) {
            vacancies.remove(found.get());
            try {
                save();
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    @Override
    public List<Vacancy> getByLanguageAndSalary(String language, float minSalary, float maxSalary) {
        reloadData();
        return vacancies.stream()
                .filter(v -> v.getLanguage().equalsIgnoreCase(language)
                        && v.getSalary() >= minSalary
                        && v.getSalary() <= maxSalary)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vacancy> getByCompany(String companyName) {
        reloadData();
        return vacancies.stream()
                .filter(v -> v.getCompanyName().equalsIgnoreCase(companyName))
                .collect(Collectors.toList());
    }
}