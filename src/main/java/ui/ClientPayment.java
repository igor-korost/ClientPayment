package ui;

import dao.CardDaoImpl;
import dao.ClientDaoImpl;
import dao.PaymentDaoImpl;
import service.Card;
import service.Client;
import service.Payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientPayment {

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            Scanner sc0 = new Scanner(System.in);
            System.out.println("Выберите таблицу с которой вы будете работать");
            System.out.println("Введите 1 для работы с клиентами");
            System.out.println("Введите 2 для работы с картами");
            System.out.println("Введите 3 для работы с платежами ");
            System.out.println("0 - выход ");
            int input = sc0.nextInt();

            switch (input) {
                case (1):
                    selectClients();
                    break;
                case (2):
                    selectCards();
                    break;
                case (3):
                    selectPayments();
                    break;
                case (0):
                    exit = true;
                    break;
            }
            sc0.close();
        }
    }

    public static void selectClients() {
        ClientDaoImpl clientDao = new ClientDaoImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите 1 чтобы ОТОБРАЗИТЬ все записи (select *)");
        System.out.println("Выберите 2 чтобы НАЙТИ пользователя по id");
        System.out.println("Выберите 3 чтобы ОБНОВИТЬ информацию о пользователе");
        System.out.println("Выберите 4 чтобы ВСТАВИТЬ нового пользователя");
        System.out.println("Выберите 5 чтобы УДАЛИТЬ пользователя по id");

        while (scanner.hasNext()) {
            int input = scanner.nextInt();
            switch (input) {
                case (1):
                    // SELECT * FROM client
                    ArrayList<Client> arrayList = clientDao.selectAllUsers();
                    for (Client client : arrayList) {
                        System.out.println("id пользователя: " + client.getId() + "; Имя пользователя: " + client.getName());
                    }
                    break;
                // SELECT client WHERE id = ...
                case (2):
                    Scanner sc2 = new Scanner(System.in);
                    System.out.println("Выберите ID пользователя для отображения");
                    int id2 = sc2.nextInt();
                    String client = clientDao.getClient(id2).getName();
                    System.out.println("Клиент с ID = " + id2 + " это " + client);
                    break;
                //UPDATE by id:
                case (3):
                    Scanner sc3 = new Scanner(System.in);
                    System.out.println("Выберите ID пользователя для обновления данных ");
                    int id3 = sc3.nextInt();
                    System.out.println("Введите имя пользователя:");
                    Scanner sc31 = new Scanner(System.in);
                    String name3 = sc31.nextLine();
                    Client client3 = new Client(id3, name3);
                    clientDao.updateClient(client3);
                    break;
                // Insert client:
                case (4):
                    Scanner sc4 = new Scanner(System.in);
                    System.out.println("Выберите ID пользователя для вставки: ");
                    int id4 = sc4.nextInt();
                    System.out.println("Введите имя пользователя:");
                    Scanner sc41 = new Scanner(System.in);
                    String name4 = sc41.nextLine();
                    Client client4 = new Client(id4, name4);
                    clientDao.insertClient(client4);
                    break;
                //Delete by id:
                case (5):
                    Scanner sc5 = new Scanner(System.in);
                    System.out.println("Выберите ID пользователя для удаления: ");
                    int id5 = sc5.nextInt();
                    clientDao.deleteClient(id5);
                    break;
            }
        }
        scanner.close();
    }


    private static void selectCards() {
        CardDaoImpl cardDao = new CardDaoImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите 1 чтобы ОТОБРАЗИТЬ все записи (select *)");
        System.out.println("Выберите 2 чтобы НАЙТИ карту по id");
        System.out.println("Выберите 3 чтобы ОБНОВИТЬ информацию о карте");
        System.out.println("Выберите 4 чтобы ВСТАВИТЬ новую карту");
        System.out.println("Выберите 5 чтобы УДАЛИТЬ карту по id");

        while (scanner.hasNext()) {
            int input = scanner.nextInt();
            switch (input) {
                case (1):
                    // SELECT * FROM card
                    ArrayList<Card> arrayList = cardDao.selectAllCards();
                    for (Card card : arrayList) {
                        System.out.println("id карты: " + card.getCardId() + " id клиента: " + card.getClientId() + " тип карты: " + card.getCardType());
                    }
                    break;
                // SELECT card WHERE id = ...
                case (2):
                    Scanner sc2 = new Scanner(System.in);
                    System.out.println("Выберите ID карты для отображения");
                    int id2 = sc2.nextInt();
                    String card = cardDao.getCard(id2).getCardType();
                    break;
                //UPDATE by id:
                case (3):
                    Scanner sc3 = new Scanner(System.in);
                    System.out.println("Выберите ID карты для обновления данных ");
                    int id3 = sc3.nextInt();
                    System.out.println("Введите ID клиента для обновления:");
                    Scanner sc31 = new Scanner(System.in);
                    int id31 = sc31.nextInt();
                    System.out.println("Введите тип карты:");
                    Scanner sc32 = new Scanner(System.in);
                    String cardType = sc32.nextLine();
                    Card card3 = new Card(id3, id31, cardType);
                    cardDao.updateCard(card3);
                    break;
                // Insert card:
                case (4):
                    Scanner sc4 = new Scanner(System.in);
                    System.out.println("Выберите ID карты для вставки: ");
                    int id4 = sc4.nextInt();
                    System.out.println("Выберите ID клиента для вставки: ");
                    Scanner sc41 = new Scanner(System.in);
                    int id41 = sc41.nextInt();
                    System.out.println("Введите тип карты");
                    Scanner sc42 = new Scanner(System.in);
                    String name42 = sc42.nextLine();
                    Card card4 = new Card(id4, id41, name42);
                    cardDao.insertCard(card4);
                    break;
                //Delete by id:
                case (5):
                    Scanner sc5 = new Scanner(System.in);
                    System.out.println("Выберите ID пользователя для удаления: ");
                    int id5 = sc5.nextInt();
                    cardDao.deleteCard(id5);
                    break;
            }
        }
    }

    private static void selectPayments() {
        PaymentDaoImpl paymentDao = new PaymentDaoImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите 1 чтобы ОТОБРАЗИТЬ все записи (select *)");
        System.out.println("Выберите 2 чтобы НАЙТИ платеж по id");
        System.out.println("Выберите 3 чтобы ОБНОВИТЬ информацию о платежах");
        System.out.println("Выберите 4 чтобы ВСТАВИТЬ новый платеж");
        System.out.println("Выберите 5 чтобы УДАЛИТЬ платеж id");

        while (scanner.hasNext()) {
            int input = scanner.nextInt();
            switch (input) {
                case (1):
                    // SELECT * FROM payment
                    ArrayList<Payment> arrayList = paymentDao.selectAllPayments();
                    for (Payment payment : arrayList) {
                        String idPayment = "id платежа: " + payment.getPaymentId();
                        String idCard = " id карты: " + payment.getPaymentId();
                        String date = " дата платежа: " + payment.getPaymentDate();
                        String sum = " сумма платежа: " + String.valueOf(payment.getPaymentSum());
                        System.out.println(idPayment + " " + idCard + " " + date + " " + sum);
                    }
                    break;
                // SELECT payment WHERE id = ...
                case (2):
                    Scanner sc2 = new Scanner(System.in);
                    System.out.println("Выберите ID платежа для отображения:");
                    int id2 = sc2.nextInt();
                    String card = paymentDao.getPayment(id2).getPaymentDate();
                    break;
                //UPDATE by id:
                case (3):
                    Scanner sc3 = new Scanner(System.in);
                    System.out.println("Выберите ID платежа для обновления данных:");
                    int id3 = sc3.nextInt();
                    System.out.println("Введите дату платжа:");
                    Scanner sc31 = new Scanner(System.in);
                    String date3 = sc31.nextLine();
                    System.out.println("Введите сумму платжа:");
                    Scanner sc32 = new Scanner(System.in);
                    BigDecimal sum3 = sc32.nextBigDecimal();
                    System.out.println("Введите id клиента платежа:");
                    Scanner sc33 = new Scanner(System.in);
                    int id33 = sc33.nextInt();
                    Payment payment3 = new Payment(id3, id33, date3, sum3);
                    paymentDao.updatePayment(payment3);
                    break;
                // Insert payment:
                case (4):
                    Scanner sc4 = new Scanner(System.in);
                    System.out.println("Выберите ID платежа для вставки:");
                    int id4 = sc4.nextInt();
                    System.out.println("Введите дату платжа:");
                    Scanner sc41 = new Scanner(System.in);
                    String date4 = sc41.nextLine();
                    System.out.println("Введите сумму платжа:");
                    Scanner sc42 = new Scanner(System.in);
                    BigDecimal sum4 = sc42.nextBigDecimal();
                    System.out.println("Введите id карты платежа:");
                    Scanner sc43 = new Scanner(System.in);
                    int id43 = sc43.nextInt();
                    Payment payment4 = new Payment(id4, id43, date4, sum4);
                    paymentDao.insertPayment(payment4);
                    break;
                //Delete by id:
                case (5):
                    Scanner sc5 = new Scanner(System.in);
                    System.out.println("Выберите ID пользователя для удаления: ");
                    int id5 = sc5.nextInt();
                    paymentDao.deletePayment(id5);
                    break;
            }
        }
    }

}







