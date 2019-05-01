package view;

import models.*;

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

    public void printCollectionItems(ArrayList<Placeable> list) {
        printCardsInFormat(list);
    }

    public void printShopMenuHelp(String string) {
        sout(string);
    }

    public void printShopCards(ArrayList<Placeable> cards) {
        printCardsInFormat(cards);
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

    public void printCardsInFormat(ArrayList<Placeable> cards) {
        printHeroesInFormat(cards);
        printItemsInFormat(cards);
        printSpellsAndMinionsInFormat(cards);
    }

    private void printHeroesInFormat(ArrayList<Placeable> cards) {
        System.out.println("Heroes :");
        int index = 1;
        for (Placeable card : cards) {
            if (card instanceof Hero) {
                System.out.println("        " + index + card.toString());
                index++;
            }
        }
    }

    private void printItemsInFormat(ArrayList<Placeable> cards) {
        System.out.println("Items :");
        int index = 1;
        for (Placeable card : cards) {
            if (card instanceof Item) {
                System.out.println("        " + index + card.toString());
                index++;
            }
        }

    }

    private void printSpellsAndMinionsInFormat(ArrayList<Placeable> cards) {
        System.out.println("Cards :");
        int index = 1;
        for (Placeable card : cards) {
            if (card instanceof Minion || card instanceof Spell) {
                System.out.println("        " + index + card.toString());
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
            printCardsInFormat(deck.getDeckCards());
        }
    }
}