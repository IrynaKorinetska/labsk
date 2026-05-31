package com.example.vacancies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseRepository {
    private DataBaseConnector dbConnector;

    public DataBaseRepository(DataBaseConnector dbConnector) {
        this.dbConnector = dbConnector;
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS Vacancies (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "Company VARCHAR(100), " +
                "Position VARCHAR(100), " +
                "Language VARCHAR(50), " +
                "Requirements VARCHAR(255), " +
                "Salary DOUBLE, " +
                "PRIMARY KEY (id))";
        try (Connection conn = dbConnector.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Vacancy> getAll() {
        List<Vacancy> list = new ArrayList<>();
        String sql = "SELECT * FROM Vacancies";
        try (Connection conn = dbConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(extractVacancy(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean addVacancy(Vacancy v) {
        String sql = "INSERT INTO Vacancies (Company, Position, Language, Requirements, Salary) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, v.getCompany());
            pstmt.setString(2, v.getPosition());
            pstmt.setString(3, v.getLanguage());
            pstmt.setString(4, v.getRequirements());
            pstmt.setDouble(5, v.getSalary());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public List<Vacancy> filterByLangAndSalary(String lang, double minSal, double maxSal) {
        List<Vacancy> list = new ArrayList<>();
        String sql = "SELECT * FROM Vacancies WHERE Language = ? AND Salary >= ? AND Salary <= ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, lang);
            pstmt.setDouble(2, minSal);
            pstmt.setDouble(3, maxSal);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) { list.add(extractVacancy(rs)); }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public List<Vacancy> getByCompany(String company) {
        List<Vacancy> list = new ArrayList<>();
        String sql = "SELECT * FROM Vacancies WHERE Company = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, company);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) { list.add(extractVacancy(rs)); }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    private Vacancy extractVacancy(ResultSet rs) throws SQLException {
        return new Vacancy(
                rs.getInt("id"),
                rs.getString("Company"),
                rs.getString("Position"),
                rs.getString("Language"),
                rs.getString("Requirements"),
                rs.getDouble("Salary")
        );
    }
}