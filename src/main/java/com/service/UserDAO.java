package com.service;

import com.models.User;
import java.sql.*;

public class UserDAO {
    private String url = "jdbc:postgresql://localhost:5432/crud";
    private String user = "postgres";
    private String password = "root";

    // Metodo per creare un utente
    public void createUser(User user) {
        String temp_email = user.getEmail();
        if (IsEmailPresent(temp_email)) {
            System.out.println("Email già presente");
            return;
        }

        String query = "INSERT INTO Utente (nome, cognome, email, password, data_di_registrazione) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, this.user, this.password);
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, user.getNome());
            pstmt.setString(2, user.getCognome());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setDate(5, Date.valueOf(user.getDataDiRegistrazione()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Nella classe UserDAO

    public void printAllUsers() {
        String query = "SELECT * FROM Utente";
        try (Connection conn = DriverManager.getConnection(url, this.user, this.password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nome: " + rs.getString("nome") +
                        ", Cognome: " + rs.getString("cognome") + ", Email: " + rs.getString("email") +
                        ", Data di Registrazione: " + rs.getDate("data_di_registrazione").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean IsEmailPresent(String email) {
        String query = "SELECT * FROM Utente WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(url, this.user, this.password);
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Metodo per leggere un utente
    public User readUser(int userId) {
        String query = "SELECT * FROM Utente WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, this.user, this.password);
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getDate("data_di_registrazione").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Nessun utente trovato
    }

    // Metodo per aggiornare un utente
    public void updateUser(User user) {
        String query = "UPDATE Utente SET nome = ?, cognome = ?, email = ?, password = ?, data_di_registrazione = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, this.user, this.password);
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, user.getNome());
            pstmt.setString(2, user.getCognome());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setDate(5, Date.valueOf(user.getDataDiRegistrazione()));
            pstmt.setInt(6, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo per cancellare un utente
    public void deleteUser(int userId) {
        String query = "DELETE FROM Utente WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, this.user, this.password);
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
