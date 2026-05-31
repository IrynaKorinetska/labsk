package com.example.vacancies;

import com.example.vacancies.data.DataBaseConnector;
import com.example.vacancies.data.DataBaseRepository;
import com.example.vacancies.data.Vacancy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private TableView<Vacancy> tableVacancies;
    @FXML private TableColumn<Vacancy, String> colCompany;
    @FXML private TableColumn<Vacancy, String> colPosition;
    @FXML private TableColumn<Vacancy, String> colLanguage;
    @FXML private TableColumn<Vacancy, String> colRequirements;
    @FXML private TableColumn<Vacancy, Double> colSalary;

    @FXML private TextField txtCompanyAdd, txtPositionAdd, txtLanguageAdd, txtRequirementsAdd, txtSalaryAdd;
    @FXML private TextField txtFilterLang, txtFilterMinSalary, txtFilterMaxSalary, txtFilterCompany;

    private DataBaseRepository repository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Ініціалізація бази даних H2
        DataBaseConnector connector = new DataBaseConnector("ITVacanciesDB");
        repository = new DataBaseRepository(connector);

        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colLanguage.setCellValueFactory(new PropertyValueFactory<>("language"));
        colRequirements.setCellValueFactory(new PropertyValueFactory<>("requirements"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        updateTable(repository.getAll());
    }

    private void updateTable(List<Vacancy> data) {
        ObservableList<Vacancy> list = FXCollections.observableArrayList(data);
        tableVacancies.setItems(list);
    }

    @FXML
    public void addVacancyAction(ActionEvent event) {
        try {
            Vacancy v = new Vacancy(
                    txtCompanyAdd.getText(),
                    txtPositionAdd.getText(),
                    txtLanguageAdd.getText(),
                    txtRequirementsAdd.getText(),
                    Double.parseDouble(txtSalaryAdd.getText())
            );
            repository.addVacancy(v);

            txtCompanyAdd.clear(); txtPositionAdd.clear(); txtLanguageAdd.clear();
            txtRequirementsAdd.clear(); txtSalaryAdd.clear();

            updateTable(repository.getAll());
        } catch (NumberFormatException e) {
            System.out.println("Помилка формату зарплати!");
        }
    }

    @FXML
    public void filterLangSalaryAction(ActionEvent event) {
        try {
            String lang = txtFilterLang.getText();
            double min = Double.parseDouble(txtFilterMinSalary.getText());
            double max = Double.parseDouble(txtFilterMaxSalary.getText());
            updateTable(repository.filterByLangAndSalary(lang, min, max));
        } catch (NumberFormatException e) {
            System.out.println("Помилка фільтрів!");
        }
    }

    @FXML
    public void filterCompanyAction(ActionEvent event) {
        updateTable(repository.getByCompany(txtFilterCompany.getText()));
    }

    @FXML
    public void showAllAction(ActionEvent event) {
        updateTable(repository.getAll());
    }
}