package dao;

import service.Card;

import java.sql.*;
import java.util.ArrayList;

public class CardDaoImpl implements CardDaoInterface {
    String dbURL = "jdbc:mysql://localhost:3306/client_payment?characterEncoding=UTF-8";
    String login = "root";
    String password = "root";

    String SELECT_CARD_BY_ID = "SELECT id_card, id_client, card_type FROM card  WHERE id_card = ?;";
    String SELECT_ALL = "SELECT * FROM card";
    String INSERT_CARD = "INSERT INTO card (id_card, id_client, card_type) VALUES (?,?,?);";
    String UPDATE_CARD = "UPDATE card SET id_client = ?, card_type = ? WHERE id_card = ?;";
    String DELETE_CARD = "DELETE FROM card WHERE id_card = ?;";


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
    public ArrayList<Card> selectAllCards() {
        ArrayList<Card> cards = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_ALL);) {
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int cardId = resultSet.getInt("id_card");
                int clientId = resultSet.getInt("id_client");
                String cardType = resultSet.getString("card_type");
                cards.add(new Card(cardId, clientId, cardType));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }

    @Override
    public Card getCard(int id) {
        Card card = null;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_CARD_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int cardId = resultSet.getInt("id_card");
                int clientId = resultSet.getInt("id_client");
                String cardType = resultSet.getString("card_type");
                card = new Card(cardId, clientId, cardType);
                String cardStr = "id карты: " + cardId;
                String clientStr = "id клиента: " + clientId;
                String typeStr = "тип карты: " + cardType;
                System.out.println(cardStr + " " + clientStr + " " + typeStr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return card;
    }
    @Override
    public void insertCard(Card card) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(INSERT_CARD)) {
            statement.setInt(1, card.getCardId());
            statement.setInt(2, card.getClientId());
            statement.setString(3, card.getCardType());
            statement.executeUpdate();
            System.out.println(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCard(Card card) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_CARD)) {
            statement.setInt(3, card.getCardId());
            statement.setInt(1, card.getClientId());
            statement.setString(2, card.getCardType());
            statement.executeUpdate();
            System.out.println(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCard(int id) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_CARD);) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
