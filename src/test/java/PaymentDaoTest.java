import dao.CardDaoImpl;
import dao.PaymentDaoImpl;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.Card;
import service.Payment;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PaymentDaoTest {

    PaymentDaoImpl paymentDao = new PaymentDaoImpl();

    @Test
    public void testGetCardById() {
        String paymentDate = paymentDao.getPayment(1).getPaymentDate();
        Assert.assertEquals(paymentDate, "2020-01-10 10:00:00", "дата платежа не соответсвует ожидаемому значению");
    }

    @Test
    public void testSelectAll() {
        ArrayList<Payment> arrayList = paymentDao.selectAllPayments();
        int arraySize = arrayList.size();
        Assert.assertEquals(arraySize, 5, "Количество элементов в базе не соответсвует ожидаемому");
    }

    @Test
    public void testInsertCard() {
        BigDecimal sum = new BigDecimal(5000);
        Payment payment = new Payment(10,10,"2020-01-10 10:00:00",sum);
        paymentDao.insertPayment(payment);
        String testDate = paymentDao.getPayment(10).getPaymentDate();
        Assert.assertEquals(testDate, "2020-01-10 10:00:00", "Вставка нового платежа завершилась неудачно");
    }

    @Test
    public void testUpdateCard() {
        BigDecimal sum = new BigDecimal(2000);
        Payment payment = new Payment(2,2,"2022-02-20 20:00:00",sum);
        paymentDao.updatePayment(payment);
        String testDate = paymentDao.getPayment(2).getPaymentDate();
        Assert.assertEquals(testDate, "2022-02-20 20:00:00", "Обновление информации о платеже завершилось неудачно");
    }

    @Test
    public void testDeleteCard() {

        ArrayList<Payment> arrayList2 = paymentDao.selectAllPayments();
        int arraySizeBefore = arrayList2.size();

        paymentDao.deletePayment(5);

        ArrayList<Payment> arrayList3 = paymentDao.selectAllPayments();
        int arraySizeAfter = arrayList3.size();
        Assert.assertEquals(arraySizeAfter, arraySizeBefore - 1, "Удаления записи не произошло");
    }
}

