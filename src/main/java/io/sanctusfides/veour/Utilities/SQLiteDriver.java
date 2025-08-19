package io.sanctusfides.veour.Utilities;

import io.sanctusfides.veour.Models.Model;

import java.sql.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class SQLiteDriver {

    public String[] getLatAndLong(String city, String state) {
        String[] arr = new String[2];
        String query = "SELECT lat, long FROM locations WHERE city = ? AND state = ?";
        String url = "jdbc:sqlite:" + getClass().getResource("/Files/localdb.db");

        try (Connection conn = DriverManager.getConnection(url)) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, city);
                preparedStatement.setString(2, state);
                try (ResultSet results = preparedStatement.executeQuery()) {
                    while (results.next()) {
                        arr[0] = String.valueOf(results.getString("lat"));
                        arr[1] = String.valueOf(results.getString("long"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;
    }


    public void deleteDB() {
        String url = "jdbc:sqlite:" + getClass().getResource("/Files/localdb.db");
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            String query = "DELETE FROM locations";
            statement.executeUpdate(query);
            System.out.println("DELETED");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void buildDB() {
        Collection<String> cities = Model.getInstance().getCities();
        String url = "jdbc:sqlite:" + getClass().getResource("/Files/localdb.db");
        AtomicReference<PreparedStatement> preparedStatement = new AtomicReference<>();
        AtomicReference<Connection> conn = new AtomicReference<>();
        AtomicInteger count = new AtomicInteger();
        cities.forEach(local -> {
            System.out.println(count.get());
            count.getAndIncrement();
            String[] arr = local.split(",");
            String city = arr[0].toLowerCase().trim().replace(" ", "+");
            String state = arr[1].toLowerCase().trim().replace(" ", "+");
            String initials = arr[2];
            String lat = arr[3];
            String lon = arr[4];
            String rank = arr[5];
            try {
                conn.set(DriverManager.getConnection(url));

                String query = "INSERT INTO locations (city,state,initials,lat,long,rank) VALUES (?,?,?,?,?,?)";
                preparedStatement.set(conn.get().prepareStatement(query));
                preparedStatement.get().setString(1, city);
                preparedStatement.get().setString(2, state);
                preparedStatement.get().setString(3, initials);
                preparedStatement.get().setString(4, lat);
                preparedStatement.get().setString(5, lon);
                preparedStatement.get().setString(6, rank);
                int res = preparedStatement.get().executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (preparedStatement.get() != null) {
                        preparedStatement.get().close();
                    }
                    if (conn.get() != null) {
                        conn.get().close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("DB Build Finished");
    }

    public void readDB() {
        String url = "jdbc:sqlite:" + getClass().getResource("/Files/localdb.db");
        try {
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            conn = DriverManager.getConnection(url);
            String query = "SELECT * FROM locations";
            preparedStatement = conn.prepareStatement(query);
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                System.out.println(results.getString("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}