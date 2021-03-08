package service;

public class Client {
    Integer clientId;
    String name;

    public Client(int clientId, String clientName) {
        this.clientId = clientId;
        this.name = clientName;
    }


    public int getId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}