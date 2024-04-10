package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.models.Group;

public class GroupDAO {
    
    private String url = "jdbc:postgresql://localhost:5432/crud";
    private String user = "postgres";
    private String password = "root";


    //metodo per aggiornare un gruppo
    public void updateGroup(Group group) {
        String query = "UPDATE groups SET name = ?, password = ?, isprivate = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, this.user, this.password);
        PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, group.getName());
            pstmt.setString(2, group.getPassword());
            pstmt.setBoolean(3, group.isPrivate());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //metodo per cancellare un gruppo

    public void deleteGroup(Group group) {
        String query = "DELETE from groups WHERE id = ?";
    }
}
