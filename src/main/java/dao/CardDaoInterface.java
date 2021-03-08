package dao;

import service.Card;

import java.util.ArrayList;

public interface CardDaoInterface {
    public ArrayList<Card> selectAllCards();

    public Card getCard(int id);

    public void insertCard(Card card);

    public void updateCard(Card card);

    public void deleteCard(int id);

}
