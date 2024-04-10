package com.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.postgresql.Driver;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //metodo per leggere un messaggio
    public Message readMessage(int messageId){
        String query = "select * from message where id = ?";
        try(Connection con = DriverManager.getConnection(url, this.user, this.password)){
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, messageId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Message(
                    rs.getInt("id_message"),
                    rs.getInt("sender"),
                    rs.getString("text"),
                    rs.getDate("timestamp").toLocalDate());
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    return null;
}

   //metodo per aggiornare un messaggio
   


}