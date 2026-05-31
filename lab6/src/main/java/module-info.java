module com.example.vacancies {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.h2database;

    opens com.example.vacancies.data to javafx.base;

    opens com.example.vacancies to javafx.fxml;
    exports com.example.vacancies;
}