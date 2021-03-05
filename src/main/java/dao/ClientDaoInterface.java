package dao;

import service.Client;

import java.util.ArrayList;

public interface ClientDaoInterface {

    public ArrayList<Client> selectAllUsers();

    public Client getClient(int id);

    public void insertClient(Client client);

    public void updateClient(Client client);

    public void deleteClient(int id);

}
