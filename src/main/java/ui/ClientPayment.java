package ui;

import dao.ClientDaoImpl;
import service.Client;

import java.util.ArrayList;
import java.util.Scanner;

public class ClientPayment {

    public static void main(String[] args) {
        ClientDaoImpl clientDao = new ClientDaoImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Что вы хотите делать с базой данных?");
        System.out.println("Выберите 1 чтобы ОТОБРАЗИТЬ все записи (select *)");
        System.out.println("Выберите 2 чтобы НАЙТИ пользователя по id");
        System.out.println("Выберите 3 чтобы ОБНОВИТЬ информацию о пользователе по id");
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
                    sc2.close();
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
                    sc31.close();
                    sc3.close();
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
                    sc41.close();
                    sc4.close();
                    break;
                //Delete by id:
                case (5):
                    Scanner sc5 = new Scanner(System.in);
                    System.out.println("Выберите ID пользователя для удаления: ");
                    int id5 = sc5.nextInt();
                    clientDao.deleteClient(id5);
                    sc5.close();
                    break;
            }
        }
        scanner.close();
    }
}