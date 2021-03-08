package dao;

import service.Payment;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class PaymentDaoImpl implements PaymentDaoInterface {


    String dbURL = "jdbc:mysql://localhost:3306/client_payment?characterEncoding=UTF-8";
    String login = "root";
    String password = "root";

    String SELECT_PAYMENT_BY_ID = "SELECT id_payment, payment_date, payment_sum FROM payment  WHERE id_payment = ?;";
    String SELECT_ALL = "SELECT * FROM payment";
    String INSERT_PAYMENT = "INSERT INTO payment (id_payment, id_card, payment_date, payment_sum) VALUES (?,?,?,?);";
    String UPDATE_PAYMENT = "UPDATE payment SET id_card = ?, payment_date = ?, payment_sum = ? WHERE id_payment = ?;";
    String DELETE_PAYMENT = "DELETE FROM payment WHERE id_payment = ?;";

    Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL, login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public ArrayList<Payment> selectAllPayments() {
        ArrayList<Payment> payments = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_ALL);) {
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int paymentId = resultSet.getInt("id_payment");
                int cardId = resultSet.getInt("id_card");
                String paymentDate = resultSet.getString("payment_date");
                BigDecimal paymentSum = resultSet.getBigDecimal("payment_sum");
                payments.add(new Payment(paymentId, cardId, paymentDate, paymentSum));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public Payment getPayment(int id) {
        Payment Payment = null;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_PAYMENT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int paymentId = resultSet.getInt("id_payment");
                int cardId = resultSet.getInt("id_payment");
                String paymentDate = resultSet.getString("payment_date");
                BigDecimal paymentSum = resultSet.getBigDecimal("payment_sum");
                Payment = new Payment(paymentId, cardId, paymentDate, paymentSum);
                System.out.println("id платежа: " + paymentId + " дата платежа: " + paymentDate + " сумма платежа: " + paymentSum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Payment;
    }

    @Override
    public void insertPayment(Payment payment) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(INSERT_PAYMENT)) {
            statement.setInt(1, payment.getPaymentId());
            statement.setInt(2,payment.getCardId());
            statement.setString(3, payment.getPaymentDate());
            statement.setBigDecimal(4, payment.getPaymentSum());
            statement.executeUpdate();
            System.out.println(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updatePayment(Payment payment) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PAYMENT)) {
            statement.setInt(4, payment.getPaymentId());
            statement.setInt(1,payment.getCardId());
            statement.setString(2, payment.getPaymentDate());
            statement.setBigDecimal(3, payment.getPaymentSum());
            statement.executeUpdate();
            System.out.println(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deletePayment(int id) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PAYMENT);) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}