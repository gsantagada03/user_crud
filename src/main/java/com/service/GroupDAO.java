package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.models.Group;

public class GroupDAO {
    private String url = "jdbc:postgresql://localhost:5432/crud";
    private String user = "postgres";
    private String password = "root";

    // metodo per creare un group
    private void createGroup(Group group) {
        String query = "insert into group (id, name, password, isprivate) values = ?, ?, ?, ?";
        // istanziazione dell'oggetto Connection
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, group.getId());
            ps.setString(2, group.getName());
            ps.setString(3, group.getPassword());
            ps.setBoolean(4, group.isPrivate());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    // metodo per aggiornare un gruppo
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

    // metodo per leggere un group
    private Group readGroup(int groupId) {
        String query = "select * from groups where id = ?";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, groupId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Group(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getBoolean("isPrivate"));
            }

        } catch (Exception e) {
        }
        return null;
    }

    // metodo per cancellare un gruppo

    public void deleteGroup(Group group) {
        String query = "DELETE from groups WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, this.user, this.password);
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, group.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
