package dao;

import service.Payment;

import java.util.ArrayList;

public interface PaymentDaoInterface {
    public ArrayList<Payment> selectAllPayments();

    public Payment getPayment(int id);

    public void insertPayment(Payment payment);

    public void updatePayment(Payment payment);

    public void deletePayment(int id);

}
