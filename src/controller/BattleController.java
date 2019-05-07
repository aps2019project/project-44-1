package controller;

import models.*;
import models.Enums.ErrorType;
import view.BattleRequest;
import view.View;

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
        do {
            BattleRequest request = new BattleRequest();
            request.getNewCommand();
            switch (request.getType()) {
                case EXIT:
                    //battle.getWinner().increaseMoney(1000);        //#TODO exceptions
                    isFinish = true;
                    break;
                case HELP:          //#TODO eazzz
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
                    showHand(request);
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
                    break;
                case END_GAME:
                    endGame();
                    break;
            }
        }
        while (!isFinish && battle.finishChecker(battle));
    }

    private void showGameInfo() {
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
            view.showCardInfo(battle.getCardInfo(cardID));
        else
            view.printError(ErrorType.CARD_NOT_FOUND_IN_BATTLE);
    }

    private void selectCard(BattleRequest request) {
        String cardID = request.getCardID();
        Placeable selectedCard = battle.getCard(cardID);
        if (selectedCard == null) {
            view.printError(ErrorType.INVALID_CARD_ID);
            return;
        }
        if (selectedCard instanceof Card) {
            controlSoldier(request, (Card) selectedCard);
        }
        if (selectedCard instanceof Item) {

        }
        if (battle.getCurrentPlayer().select(request.getCardID()))
            view.printError(ErrorType.INVALID_CARD_ID);
    }

    private void moveCard(BattleRequest request) {
        if (battle.getCurrentPlayer().getSelectedCard() == null)
            view.printError(ErrorType.NO_CARD_SELECTED);
        else view.sout(battle.getCurrentPlayer().move(request.getLocationX(),
                request.getLocationY()));
    }

    private void ordinaryAttack(Card src, BattleRequest request) {
        Placeable dest = battle.getCard(request.getCardID());
        if (!src.isAttackAvailable()) {
            view.usedAttackBefore(src.getInGameID());
        }
        if (battle.getOpponentCardsInMap().contains((dest))) {
            if (battle.castAttack(src, (Card) dest) == ErrorType.DEST_IS_UNAVAILABLE_FOR_ATTACK) {
                view.printError(ErrorType.DEST_IS_UNAVAILABLE_FOR_ATTACK);
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

    private void showHand(BattleRequest request) {
        String cardID = request.getCardID();
    }

    private void endTurn() {
        battle.turnHandler();
    }

    private void showCollectables() {

    }

    private void showNextCard() {

    }

    private void endGame() {
        addThisBattleToBattleHistory();
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

    private void selectCollectable(BattleRequest request) {
        while (true) {
            request.getNewCommand();
            if (request.getType().equals("show item info")) {
                showCollectableInfo(request);
            }
            if (request.getType().equals("use item")) {
                useCollectable(request);
            }
            if (request.getType().equals("exit")) {
                break;
            }
            if (request.getType().equals("show menu in collectable item menu")) {
                showMenuInSelectCollectable();
            }
        }
        //not sure about this
    }

    private void helpInBattle() {

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

    private void addThisBattleToBattleHistory() {
        if (battle.isFirstPlayerWon()) {
            battle.getFirst().addMatchHistory(new MatchHistory
                    (battle.getFirst().getUsername(), true));
            battle.getSecond().addMatchHistory(new MatchHistory
                    (battle.getSecond().getUsername(), false));
        } else {
            battle.getFirst().addMatchHistory(new MatchHistory
                    (battle.getFirst().getUsername(), false));
            battle.getSecond().addMatchHistory(new MatchHistory
                    (battle.getSecond().getUsername(), true));
        }
    }

    private void controlSoldier(BattleRequest request, Card soldier) {
        boolean isFinish = false;
        do {
            request.getNewCommand();
            switch (request.getType()) {
                case MOVE_CARD:
                    battle.getCurrentPlayer().select(request.getCardID());
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

}