package controller;

import models.Battle;
import models.Game;
import models.MatchHistory;
import view.BattleRequest;
import view.View;

class BattleController {

    private Battle battle;
    private View view = new View();

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
                case MOVE_CARD:
                    moveCard(request);
                    break;
                case ATTACK_TO_OPPONENT:
                    ordinaryAttack(request);
                    break;
                case COMBO_ATTACK:
                    comboAttack(request);
                    break;
                case USE_SPECIAL_POWER:
                    useSpecialPower(request);
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
                case SELECT_COLECTABLE:
                    selectCollectable(request);
                    break;
            }
        }
        while (!isFinish && battle.finishChecker(battle));
    }

    private void showGameInfo() {

    }

    private void showMyMinions() {

    }

    private void showOpponentMinions() {

    }

    private void showCardInfo(BattleRequest request) {

    }

    private void selectCard(BattleRequest request) {

    }

    private void moveCard(BattleRequest request) {

    }

    private void ordinaryAttack(BattleRequest request) {

    }

    private void comboAttack(BattleRequest request) {

    }

    private void useSpecialPower(BattleRequest request) {

    }

    private void insertCard(BattleRequest request) {

    }

    private void showHand(BattleRequest request) {

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
        if (battle.isPlayer1Won()) {
            Game.getInstance().getAccount1().addMatchHistory(new MatchHistory
                    (Game.getInstance().getAccount1().getUsername(), true));
            Game.getInstance().getAccount2().addMatchHistory(new MatchHistory
                    (Game.getInstance().getAccount2().getUsername(), false));
        } else {
            Game.getInstance().getAccount1().addMatchHistory(new MatchHistory
                    (Game.getInstance().getAccount1().getUsername(), false));
            Game.getInstance().getAccount2().addMatchHistory(new MatchHistory
                    (Game.getInstance().getAccount2().getUsername(), true));
        }
    }

}