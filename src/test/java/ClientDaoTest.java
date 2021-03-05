import dao.ClientDaoImpl;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.Client;

import java.util.ArrayList;

public class ClientDaoTest {
    ClientDaoImpl clientDao = new ClientDaoImpl();

    @Test
    public void testGetClientById() {
        String clientName = clientDao.getClient(1).getName();
        Assert.assertEquals(clientName, "Клиент1", "Имя не соответсвует ожидаемому");
    }

    @Test
    public void testSelectAll() {
        ArrayList<Client> arrayList = clientDao.selectAllUsers();
        int arraySize = arrayList.size();
        Assert.assertEquals(arraySize, 5, "Количество элементов в базе не соответсвует ожидаемому");
    }

    @Test
    public void testInsertClient() {
        Client client2 = new Client(5, "test1");
        clientDao.insertClient(client2);
        String testName = clientDao.getClient(5).getName();
        Assert.assertEquals(testName, "test1", "Вставка нового клиента завершилась неудачно");
    }

    @Test
    public void testUpdateClient() {
        Client client3 = new Client(2, "Тест Update2");
        clientDao.updateClient(client3);
        String testName = clientDao.getClient(2).getName();
        Assert.assertEquals(testName, "Тест Update2", "Обновление информации о клиенте завершилось неудачно");
    }

    @Test
    public void testDeleteClient() {

        ArrayList<Client> arrayList2 = clientDao.selectAllUsers();
        int arraySizeBefore = arrayList2.size();

        clientDao.deleteClient(5);

        ArrayList<Client> arrayList3 = clientDao.selectAllUsers();
        int arraySizeAfter = arrayList3.size();
        Assert.assertEquals(arraySizeAfter, arraySizeBefore-1, "Удаления записи не произошло");
    }
}