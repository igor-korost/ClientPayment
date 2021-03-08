package service;

public class Card {

    Integer cardId;
    Integer clientId;
    String cardType;

    public Card(Integer cardId, Integer clientId, String cardType) {
        this.cardId = cardId;
        this.clientId = clientId;
        this.cardType = cardType;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }


}
