package controller;

import models.Battle;
import models.Game;
import models.MatchHistory;
import view.BattleRequest;
import view.RequestType;
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
            if (request.getType() == RequestType.EXIT) {
                //battle.getWinner().increaseMoney(1000);        //#TODO exceptions
                isFinish = true;
            }
            if (request.getType() == RequestType.HELP) {         //#TODO eazzz
                helpInBattle();
            }
            if (request.getType() == RequestType.SHOW_GAME_INFO) {        //#TODO eazzz
                showGameInfo();
            }
            if (request.getType() == RequestType.SHOW_MY_MINIONS) {
                showMyMinions();
            }
            if (request.getType() == RequestType.SHOW_OPPONENT_MINIONS) {
                showOpponentMinions();
            }
            if (request.getType() == RequestType.SHOW_CARD_INFO) {
                showCardInfo(request);
            }
            if (request.getType() == RequestType.SELECT_CARD) {
                selectCard(request);
            }
            if (request.getType() == RequestType.MOVE_CARD) {
                moveCard(request);
            }
            if (request.getType() == RequestType.ATTACK_TO_OPPONENT) {
                ordinaryAttack(request);
            }
            if (request.getType() == RequestType.COMBO_ATTACK) {
                comboAttack(request);
            }
            if (request.getType() == RequestType.USE_SPECIAL_POWER) {
                useSpecialPower(request);
            }
            if (request.getType() == RequestType.SHOW_MY_HAND) {
                showHand(request);
            }
            if (request.getType() == RequestType.INSERT_CARD_FROM_HAND_TO_MAP) {
                insertCard(request);
            }
            if (request.getType() == RequestType.END_TURN) {
                endTurn();
            }
            if (request.getType() == RequestType.SHOW_COLLECTABLES) {
                showCollectables();
            }
            if (request.getType() == RequestType.SHOW_NEXT_CARD) {
                showNextCard();
            }
            if (request.getType() == RequestType.ENTER_GRAVEYARD) {
                enterGraveyard(request);
            }
            if (request.getType() == RequestType.END_GAME) {
                endGame();
            }
            if (request.getType() == RequestType.SELECT_COLECTABLE) {
                selectCollectable(request);
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
