package dao;

import service.Client;

import java.sql.*;
import java.util.ArrayList;

public class ClientDaoImpl implements ClientDaoInterface {
    String dbURL = "jdbc:mysql://localhost:3306/client_payment?characterEncoding=UTF-8";
    String login = "root";
    String password = "root";

    String SELECT_CLIENT_BY_ID = "SELECT id_client, client_name FROM client  WHERE id_client = ?;";
    String SELECT_ALL = "SELECT * FROM client";
    String INSERT_CLIENT = "INSERT INTO client (id_client, client_name) VALUES (?,?);";
    String UPDATE_CLIENT = "UPDATE client SET client_name = ? WHERE id_client = ?;";
    String DELETE_CLIENT = "DELETE FROM client WHERE id_client = ?;";


    Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL, login, password);
            System.out.println("Соединение установлено.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public ArrayList<Client> selectAllUsers() {
        ArrayList<Client> clients = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_ALL);) {
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_client");
                String name = resultSet.getString("client_name");
                clients.add(new Client(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Client getClient(int id) {
        Client client = null;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_CLIENT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int ClientId = resultSet.getInt("id_client");
                String ClientName = resultSet.getString("client_name");
                client = new Client(ClientId, ClientName);
                System.out.println("Select by Id: " + ClientId + " " + ClientName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public void insertClient(Client client) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(INSERT_CLIENT)) {
            statement.setInt(1, client.getId());
            statement.setString(2, client.getName());
            statement.executeUpdate();
            System.out.println(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateClient(Client client) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_CLIENT)) {
            statement.setInt(2, client.getId());
            statement.setString(1, client.getName());
            statement.executeUpdate();
            System.out.println(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClient(int id) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_CLIENT);) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
