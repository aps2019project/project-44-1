package view;

import models.*;
import models.Enums.ErrorType;

import java.util.ArrayList;

public class View {

    public void printError(ErrorType type) {
        if (type == null)
            return;
        System.out.println(type.getMessage());
    }

    public void printLeaderboard(ArrayList<Account> accounts) {
        for (int i = 1; i <= accounts.size(); i++) {
            System.out.println(i + " - Username : " +
                    accounts.get(i - 1).getUsername() + " - Wins : " +
                    accounts.get(i - 1).getWins());
        }
    }

    public void printGameMenuHelp(String string) {
        System.out.println(string);
    }

    public void printAccountMenuHelp(String string) {
        System.out.println(string);

    }

    public void printCollectionMenuHelp(String string) {
        System.out.println(string);
    }

    public void printCollectionItems(ArrayList<Placeable> list, boolean neadToShowCost) {
        printCardsInFormat(list, neadToShowCost);
    }

    public void printShopMenuHelp(String string) {
        sout(string);
    }

    public void printShopCards(ArrayList<Placeable> cards) {
        printCardsInFormat(cards, true);
    }

    public void sout(Object o) {
        System.out.println(o);
    }

    public void showMainMenu() {
        System.out.println("Main menu\n" +
                "-------\n" +
                "1.Collection\n" +
                "2.Shop\n" +
                "3.Battle\n" +
                "4.Exit\n" +
                "5.Help");
    }

    public void printGetPasswordCommand() {
        System.out.println("Enter your password:");
    }

    public void printStartMenu() {
        System.out.println("Start Menu\n" +
                "---------\n" +
                "1.login\n" +
                "2.create account\n" +
                "3.show leaderboard\n" +
                "4.help\n" +
                "5.exit");
    }

    public void printCardsInFormat(ArrayList<Placeable> cards, boolean neadToShowCost) {
        printHeroesInFormat(cards, neadToShowCost);
        printItemsInFormat(cards, neadToShowCost);
        printSpellsAndMinionsInFormat(cards, neadToShowCost);
    }

    private void printHeroesInFormat(ArrayList<Placeable> cards, boolean neadToShowCost) {
        System.out.println("Heroes :");
        int index = 1;
        for (Placeable card : cards) {
            if (card instanceof Hero && card.getName() != null) {
                System.out.print("        " + index + card.toString());
                showCardCost(card, neadToShowCost);
                index++;
            }
        }
    }

    private void printItemsInFormat(ArrayList<Placeable> cards, boolean neadToShowCost) {
        System.out.println("Items :");
        int index = 1;
        for (Placeable card : cards) {
            if (card instanceof Item && card.getName() != null) {
                System.out.print("        " + index + card.toString());
                showCardCost(card, neadToShowCost);
                index++;
            }
        }

    }

    private void printSpellsAndMinionsInFormat(ArrayList<Placeable> cards, boolean neadToShowCost) {
        System.out.println("Cards :");
        int index = 1;
        for (Placeable card : cards) {
            if (card instanceof Minion || card instanceof Spell && card.getName() != null) {
                System.out.print("        " + index + card.toString());
                showCardCost(card, neadToShowCost);
                index++;
            }
        }
    }

    public void printValidatedMessage() {
        System.out.println("deck is validate");
    }

    public void printNotValidate() {
        System.out.println("deck is not validated");
    }

    public void printDecksInFormat(ArrayList<Deck> decks) {
        int index = 1;
        for (Deck deck : decks) {
            System.out.println(index + " : " + deck.getName() + " :");
            printCardsInFormat(deck.getDeckCards(), false);
        }
    }

    public void successfulSellMessage() {
        System.out.println("the card sold successfully !!!");
    }

    public void unSuccessfulSellMessage() {
        System.out.println("some errors occured when selling cards !!!\n" +
                "make sure you have entered card ID truly !!!");
    }

    public void successfulBuyMessage() {
        System.out.println("you have successfully buy the card .");
    }

    public void printNoCardWithThisName(String cardName) {
        System.out.println("no card found with name : " + cardName);
    }

    public void printCardInCollection(String cardName, int cardID) {
        System.out.println("card" + cardName + "found in collection with ID : " + cardID);
    }

    public void printSelectSingleOrMulti() {
        System.out.println("select how do you want ot play:\n" +
                "1.Single player\n" +
                "2.Multi player");
    }

    public void printPlayersList(ArrayList<Account> accounts, Account firstPlayer) {
        int index = 1;
        for (Account secondPlayer : accounts) {
            if (secondPlayer.equals(firstPlayer)) {
                continue;
            }
            System.out.println(index + "." + secondPlayer.getUsername());
            index++;
        }
    }

    public void showGameModes() {
        System.out.println("1.death match\n" +
                "2.capture flag 1\n" +
                "3.capture flag 2");
    }

    public void printSecondPlayerIsNotReady() {
        System.out.println("selected deck for second player is invalid !!!");
    }

    public void printGameKinds() {
        System.out.println("1.story\n" +
                "2.custom game");
    }

    private void showCardCost(Placeable card, boolean neadToShowCost) {
        if (neadToShowCost) {
            System.out.println(" - Buy Cost : " + card.getCost());
        } else {
            System.out.println();
        }
    }

    public void showStoryGameKinds() {
        System.out.println();
        // TODO: 05/05/2019 after adding datas from json
    }

    public void showMyMinions(ArrayList<Card> cards) {
        showMinionsInBattleFormatted(cards);
    }

    public void showOpponentMinions(ArrayList<Card> cards){
        showMinionsInBattleFormatted(cards);
    }

    public void showMinionsInBattleFormatted(ArrayList<Card> cards) {
        for (Card card : cards) {
            System.out.println(card.getInGameID() + " : " + card.getName() + ", health : " + card.getHP() + ", location : (" +
                    card.getCell().getX() + ", " + card.getCell().getY() + "), power : " + card.getAP());
        }
    }

    public void showCardInfo(String string){
        System.out.println();
    }

}