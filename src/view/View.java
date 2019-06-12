package view;

import models.*;
import models.Enums.ErrorType;
import models.Enums.ItemType;

import java.util.ArrayList;

public class View {

    private static View view = new View();

    private View() {
    }

    public static View getInstance() {
        return view;
    }

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
                "5.Help\n" + "6.show match histories\n");
    }

    void printGetPasswordCommand() {
        System.out.println("Enter your password:");
    }

    public void printStartMenu() {
        System.out.println("Start Menu\n" +
                "---------\n" +
                "1.login\n" +
                "2.create account\n" +
                "3.show leaderboard\n" +
                "4.Help\n" +
                "5.Exit\n");
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
        System.out.println("card " + cardName + " found in collection with ID : " + cardID);
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
        System.out.println("1.story mode\n" +
                "2.custom game\n");
    }

    private void showCardCost(Placeable card, boolean neadToShowCost) {
        if (neadToShowCost) {
            System.out.println(" - Buy Cost : " + card.getCost());
        } else {
            System.out.println();
        }
    }

    public void showStoryGameKinds() {
        System.out.println("choose level between:\n" +
                "1.death match\t\tOPPONENT : divsefid" +
                "\n2.save flag\t\tOPPONENT : zahhak\n" +
                "3.capture multiple flags\t\tOPPONENT : arash");
    }

    public void showMyMinions(ArrayList<Card> cards) {
        showMinionsInBattleFormatted(cards);
    }

    public void showOpponentMinions(ArrayList<Card> cards) {
        showMinionsInBattleFormatted(cards);
    }

    private void showMinionsInBattleFormatted(ArrayList<Card> cards) {
        for (Card card : cards) {
            System.out.println(card.getInGameID() + " : " + card.getName() +
                    ", health : " + card.getHP() + ", location : (" + card.getMyCell().getX()
                    + ", " + card.getMyCell().getY() + "), power : " + card.getAP());
        }
    }

    public void printCardWasFound() {
        System.out.println("card was found ins shop");
    }

    public void usedAttackBefore(String cardID) {
        System.out.println("Card with " + cardID +
                " can′t attack ");
    }

    public void showMap(Battle battle) {
        Cell[][] cells = battle.getMap().getCells();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    if (cells[i][j].getItem() != null &&
                            cells[i][j].getItem().getItemType().equals(ItemType.FLAG)) {
                        System.out.print(" f |");
                        continue;
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    System.out.println(i + "\t" + j);
                }
                if (cells[i][j].getCard() instanceof Hero && cells[i][j].getCard().
                        getOwner().equals(battle.getFirstPlayer())) {
                    System.out.print(" O |");
                } else if (cells[i][j].getCard() instanceof Minion && cells[i][j].
                        getCard().getOwner().equals(battle.getFirstPlayer())) {
                    System.out.print(" o |");
                } else if (cells[i][j].getCard() instanceof Hero && cells[i][j].
                        getCard().getOwner().equals(battle.getSecondPlayer())) {
                    System.out.print(" X |");
                } else if (cells[i][j].getCard() instanceof Minion && cells[i][j].
                        getCard().getOwner().equals(battle.getSecondPlayer())) {
                    System.out.print(" x |");
                } else {
                    System.out.print(" __|");
                }

            }

            System.out.println();
        }
    }

}