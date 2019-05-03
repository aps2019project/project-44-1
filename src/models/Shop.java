package models;

import models.Enums.ErrorType;

import java.util.ArrayList;

public class Shop {
    private static Shop shop = new Shop();
    ArrayList<String> cardNames = new ArrayList<>();
    private ArrayList<Placeable> cards = new ArrayList<>();
    private Account account;


    private Shop() {

    }

    public static Shop getInstance() {
        return shop;
    }


    public void help() {
    }

    public void showShopCards() {
    }


    /**
     * sell() returns true when the selling is done completely and return false in the other case
     */
    public boolean sell(int cardID) {
        Placeable card = account.getCollection().getCard(cardID);
        if (card != null) {
            account.getCollection().deleteCardFromCollection(cardID);
            account.increaseMoney(card.getCost());
            return true;
        }
        return false;
    }

    /**
     * it's better to throw execptions
     */
    public ErrorType buy(String cardName) {
        Placeable card = getCard(cardName);
        if (haveEnoughMoneyToBuyCard(card, account)) {
            if (card instanceof Item && !account.getCollection().canBuyItem()) {
                return ErrorType.MAX_ITEMS_IN_COLLECTION_REACHED;
            } else {
                account.getCollection().addCardToCollection(card);
                account.decreaseMoney(card.getCost());
                return ErrorType.NO_ERROR;
            }
        } else {
            return ErrorType.NOT_ENOUGH_MONEY_TO_BUY_CARD;
        }
    }

    public void searchInShop(String cardName) {
    }

    public int searchInCollection(String cardName) {
        Collection collection = account.getCollection();
        return collection.searchInCollection(cardName);
    }

    @Override
    public String toString() {
        return "1.show collection\n" +
                "2.search [item name | card name]\n" +
                "3.search collection [item name | card name]\n" +
                "4.buy [card name | item name]\n" +
                "5.sell [card id | card id]\n" +
                "6.show\n" +
                "7.help\n" +
                "8.exit";
    }

    public ArrayList<Placeable> getCards() {
        return cards;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Placeable getCard(String cardName) {
        for (Placeable card : cards) {
            if (card.getName().equals(cardName)) {
                return card;
            }
        }
        return null;
    }

    public boolean haveEnoughMoneyToBuyCard(Placeable card, Account account) {
        return account.getMoney() >= card.getCost();
    }

    public boolean canBuyItem() {
        return account.getCollection().canBuyItem();
    }


}