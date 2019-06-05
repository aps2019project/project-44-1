package controller;

import models.*;
import models.Enums.BattleKind;
import models.Enums.ErrorType;
import view.BattleRequest;
import view.View;

import static models.Enums.ErrorType.DEST_IS_UNAVAILABLE_FOR_ATTACK;

class BattleController {
    private static BattleController battleController = new BattleController();
    private Battle battle;
    private View view = View.getInstance();

    private BattleController() {
    }

    static BattleController getInstance() {
        return battleController;
    }

    void main(Battle battle) {
        this.battle = battle;
        boolean isFinish = false;
        battle.getCurrentPlayer().setMana(Player.turnBeginMana[0]);
        do {
            View.getInstance().showMap(this.battle);
            BattleRequest request = new BattleRequest();
            request.getNewCommand();
            switch (request.getType()) {
                case EXIT:
                    isFinish = true;
                    break;
                case HELP:                   //#TODO eazzz
                    helpInBattle();
                    break;
                case SHOW_GAME_INFO:         //#TODO eazzz
                    showGameInfo();
                    break;
                case SHOW_MY_MINIONS:
                    showMyMinions();
                    break;
                case SHOW_OPPONENT_MINIONS:
                    showOpponentMinions();
                    break;
                case SHOW_CARD_INFO:
                    showCardInfo(request);
                    break;
                case SELECT_CARD:
                    selectCard(request);
                    break;
                case SHOW_MY_HAND:
                    showHand();
                    break;
                case INSERT_CARD_FROM_HAND_TO_MAP:
                    insertCard(request);
                    break;
                case END_TURN:
                    endTurn();
                    break;
                case SHOW_COLLECTABLES:
                    showCollectables();
                    break;
                case SHOW_NEXT_CARD:
                    showNextCard();
                    break;
                case ENTER_GRAVEYARD:
                    enterGraveyard(request);
            }
            if (battle.finishChecker(battle)) {
                isFinish = true;
                gameHistory();
            }
        }
        while (!isFinish);
    }

    private void gameHistory() {
        if (battle.isFirstPlayerWon()) {
            battle.getFirst().increaseWins();
            battle.getFirst().increaseMoney(battle.getPrize());
            MatchHistory matchHistory = new MatchHistory(battle.getSecond().getUsername(), true);
            battle.getFirst().addMatchHistory(matchHistory);
            matchHistory = new MatchHistory(battle.getFirst().getUsername(), false);
            battle.getSecond().addMatchHistory(matchHistory);
        } else {
            battle.getSecond().increaseWins();
            battle.getSecond().increaseMoney(battle.getPrize());
            MatchHistory matchHistory = new MatchHistory(battle.getFirst().getUsername(), true);
            battle.getSecond().addMatchHistory(matchHistory);
            matchHistory = new MatchHistory(battle.getSecond().getUsername(), false);
            battle.getFirst().addMatchHistory(matchHistory);
        }
    }

    private void showGameInfo() {
        view.sout("player1 Mana : " + battle.getFirstPlayer().getMana() +
                "\nplayer2 Mana : " + battle.getSecondPlayer().getMana());
        view.sout(battle.toString());
    }

    private void showMyMinions() {
        view.showMyMinions(battle.getMyCardsInMap());
    }

    private void showOpponentMinions() {
        view.showOpponentMinions(battle.getOpponentCardsInMap());
    }

    private void showCardInfo(BattleRequest request) {
        String cardID = request.getCardID();
        if (battle.getCard(cardID) != null)
            view.sout(battle.getCardInfo(cardID));
        else
            view.printError(ErrorType.CARD_NOT_FOUND_IN_BATTLE);
    }

    private void selectCard(BattleRequest request) {
        String cardID = request.getCardID();
        Placeable selectedCard = battle.getCard(cardID);
        if (selectedCard == null || !battle.getCurrentPlayer().select(request.getCardID())) {
            view.printError(ErrorType.INVALID_CARD_ID);
            return;
        }
        if (selectedCard instanceof Card) {
            controlSoldier(request, (Card) selectedCard);
        } else if (selectedCard instanceof Item) {
            controlItem(request, (Item) selectedCard);
        }
    }

    private void moveCard(BattleRequest request) {
        if (battle.getCurrentPlayer().getSelectedCard() == null)
            view.printError(ErrorType.NO_CARD_SELECTED);
        else view.sout(battle.getCurrentPlayer().move(request.getLocationX(),
                request.getLocationY()));
    }

    private void ordinaryAttack(Card src, BattleRequest request) {
        Placeable dest = battle.getCard(request.getCardID());
        if (dest == null || dest instanceof Spell) {
            view.printError(ErrorType.CARD_NOT_FOUND_IN_BATTLE);
            return;
        } else if (!src.isAttackAvailable()) {
            view.usedAttackBefore(src.getInGameID());
            return;
        }
        if (battle.getOpponentCardsInMap().contains((dest))) {
            if (battle.castAttack(src, (Card) dest) == DEST_IS_UNAVAILABLE_FOR_ATTACK) {
                view.printError(DEST_IS_UNAVAILABLE_FOR_ATTACK);
            }
        } else {
            view.printError(ErrorType.INVALID_DEST_ID);
        }

    }

    private void comboAttack(BattleRequest request) {

    }

    private void useSpecialPower(BattleRequest request) {

    }

    private void insertCard(BattleRequest request) {
        battle.getCurrentPlayer().insert(request.getCardName(),
                request.getLocationX(), request.getLocationY());
    }

    private void showHand() {
        for (Card c : battle.getCurrentPlayer().getHand()) {
            view.sout(c.toString() + "\n");
        }
        showNextCard();
    }

    private void endTurn() {
        if (battle.getBattleKind().equals(BattleKind.SINGLE_PLAYER)) {
            battle.turnHandler();
            ArtificialIntelligence.aiAction(this.battle);
        }
        battle.turnHandler();
    }

    private void showCollectables() {

    }

    private void showNextCard() {
        view.sout("next card in hand will be : " + battle.getCurrentPlayer().getNextCardInHand());
    }

    private void enterGraveyard(BattleRequest request) {
        while (true) {
            request.getNewCommand();
            if (request.getType().equals("show card info in graveyard")) {
                showCardInfo(request);
            }
            if (request.getType().equals("show cards in graveyard")) {
                showCardsInGraveyard();
            }
            if (request.getType().equals("exit")) {
                break;
            }
            if (request.getType().equals("show menu")) {
                showMenuInGraveyard();
            }
        }
        // not sure about this
    }

    private void helpInBattle() {
        view.sout("1.Game Info  \n2.show my minions \nshow opponent minions \n show card inf");
    }       //#TODO

    private void showMenuInGraveyard() {

    }

    private void showMenuInSelectCollectable() {

    }

    private void showCardsInGraveyard() {

    }

    private void showCollectableInfo(BattleRequest request) {

    }

    private void useCollectable(BattleRequest request) {

    }

    private void controlSoldier(BattleRequest request, Card soldier) {
        boolean isFinish = false;
        do {
            request.getNewCommand();
            switch (request.getType()) {
                case MOVE_CARD:
                    moveCard(request);
                    break;
                case ATTACK_TO_OPPONENT:
                    ordinaryAttack(soldier, request);
                    break;
                case COMBO_ATTACK:
                    comboAttack(request);
                    break;
                case USE_SPECIAL_POWER:
                    useSpecialPower(request);
                    break;
                case EXIT:
                    isFinish = true;
            }
        }
        while (!isFinish && battle.finishChecker(battle));
    }

    private void controlItem(BattleRequest request, Item item) {
        boolean isFinish = false;
        do {
            request.getNewCommand();
            switch (request.getType()) {
                case SHOW_SELECTED_ITEM_INFO:
                    break;
                case USE_COllectable_ITEM:
                    break;
                case EXIT:
                    isFinish = true;

            }
        }
        while (!isFinish && battle.finishChecker(battle));
    }

}