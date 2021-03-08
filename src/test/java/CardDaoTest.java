import dao.CardDaoImpl;
import dao.PaymentDaoImpl;
import dao.ClientDaoImpl;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.Card;
import service.Client;
import  service.Payment;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CardDaoTest {
    CardDaoImpl cardDao = new CardDaoImpl();

    @Test
    public void testGetCardById() {
        String cardType = cardDao.getCard(1).getCardType();
        Assert.assertEquals(cardType, "Visa", "Тип карты не соответсвует ожидаемому");
    }

    @Test
    public void testSelectAll() {
        ArrayList<Card> arrayList = cardDao.selectAllCards();
        int arraySize = arrayList.size();
        Assert.assertEquals(arraySize, 5, "Количество элементов в базе не соответсвует ожидаемому");
    }

    @Test
    public void testInsertCard() {
        ClientDaoImpl clientDao = new ClientDaoImpl();
        PaymentDaoImpl paymentDao = new PaymentDaoImpl();

        // создание таблицы client и заполнение её значениями:
        Client client2 = new Client(1000, "testName");
        clientDao.insertClient(client2);

        // создание таблицы payment и заполнение её значениями:
        BigDecimal sum = new BigDecimal(5000);
        Payment payment = new Payment(1000,1000,"2020-01-10 10:00:00",sum);
        paymentDao.insertPayment(payment);

        // создание таблицы card заполнение её значениями, выполнение теста:
        Card card2 = new Card(1000,1000 ,"Мир");
        cardDao.insertCard(card2);
        String testName = cardDao.getCard(1000).getCardType();
        Assert.assertEquals(testName, "Мир", "Вставка новой карты завершилась неудачно");
    }

    @Test
    public void testUpdateCard() {

        Card card3 = new Card(2, 2,"Тест Update2");
        cardDao.updateCard(card3);
        String testName = cardDao.getCard(2).getCardType();
        Assert.assertEquals(testName, "Тест Update2", "Обновление информации о карте завершилось неудачно");
    }

    @Test
    public void testDeleteCard() {

        ArrayList<Card> arrayList2 = cardDao.selectAllCards();
        int arraySizeBefore = arrayList2.size();

        cardDao.deleteCard(5);

        ArrayList<Card> arrayList3 = cardDao.selectAllCards();
        int arraySizeAfter = arrayList3.size();
        Assert.assertEquals(arraySizeAfter, arraySizeBefore-1, "Удаления записи не произошло");
    }
}