package com.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import com.models.Message;

public class MessageDAO {
    private String url = "jdbc:postgresql://localhost:5432/crud";
    private String user = "postgres";
    private String password = "root";

    // metodo per creare un messaggio
    public void createMessage(Message message) {

        String query = " insert into message(id, sender_message, text, timestamp ) values = ?, ?, ?, ? ";

        try (Connection con = DriverManager.getConnection(url, this.user, this.password)) {
            // l'oggetto di tipo PreparedStatement serve per effettuare la query nel DB e
            // valorizzare i segnaposto
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, message.getId());
            ps.setInt(2, message.getSenderMessage());
            ps.setString(3, message.getText());
            ps.setDate(4, Date.valueOf(message.getCurrentTimeStamp()));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // metodo per leggere un messaggio
    public Message readMessage(int messageId) {
        String query = "select * from messaggio where id = ?";
        try (Connection con = DriverManager.getConnection(url, this.user, this.password)) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, messageId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Message(
                        rs.getInt("id_message"),
                        rs.getInt("sender"),
                        rs.getString("text"),
                        rs.getDate("timestamp").toLocalDate());
            }
        } catch (Exception e) {
        }
        return null;
    }

    // metodo per aggiornare un messaggio

    public void updateMessage(Message message) {
        String query = "UPDATE messaggio SET text = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, this.user, this.password);
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, message.getText());
            pstmt.setInt(2, message.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Metodo per cancellare un messaggio
    public void deleteMessage(int messageId) {
        String query = "DELETE * from messaggio WHERE id = ?";
        try(Connection conn = DriverManager.getConnection(url, this.user, this.password);
            PreparedStatement pstm = conn.prepareStatement(query)) {
            pstm.setInt(1, messageId);
            pstm.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


}