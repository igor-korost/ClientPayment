package service;

import java.math.BigDecimal;

public class Payment {
    Integer paymentId;
    Integer cardId;
    String paymentDate;
    BigDecimal paymentSum;

    public Payment(Integer paymentId, Integer cardId, String paymentDate, BigDecimal paymentSum) {
        this.paymentId = paymentId;
        this.cardId = cardId;
        this.paymentDate = paymentDate;
        this.paymentSum = paymentSum;
    }

    public Payment(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(BigDecimal paymentSum) {
        this.paymentSum = paymentSum;
    }
}
