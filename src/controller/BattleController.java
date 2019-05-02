package controller;

import models.Battle;
import view.BattleRequest;
import view.View;

public class BattleController {

    private Battle battle;
    private View view = new View();

    public void main(Battle battle) {
        this.battle = battle;
        boolean isFinish = false;
        do {
            BattleRequest request = new BattleRequest();
            request.getNewCommand();
            if (request.getType().equals("exit")) {
                isFinish = true;
            }
            if (request.getType().equals("help")) {
                helpInBattle();
            }

            if (request.getType().equals("show game info")) {
                showGameInfo();
            }
            if (request.getType().equals("show my minions")) {
                showMyMinions();
            }
            if (request.getType().equals("show opponent minions")) {
                showOpponentMinions();
            }
            if (request.getType().equals("show card info")) {
                showCardInfo(request);
            }
            if (request.getType().equals("select card")) {
                selectCard(request);
            }
            if (request.getType().equals("move card")) {
                moveCard(request);
            }
            if (request.getType().equals("ordinary attack")) {
                ordinaryAttack(request);
            }
            if (request.getType().equals("combo attack")) {
                comboAttack(request);
            }
            if (request.getType().equals("use special power")) {
                useSpecialPower(request);
            }
            if (request.getType().equals("show hand")) {
                showHand(request);
            }
            if (request.getType().equals("insert card")) {
                insertCard(request);
            }
            if (request.getType().equals("end turn")) {
                endTurn();
            }
            if (request.getType().equals("show collectable items")) {
                showCollectables();
            }
            if (request.getType().equals("show next card")) {
                showNextCard();
            }
            if (request.getType().equals("enter graveyard")) {
                enterGraveyard(request);
            }
            if (request.getType().equals("end game")) {
                endGame();
            }
            if (request.getType().equals("select a collectable item")) {
                selectCollectable(request);
            }


        }
        while (!isFinish);
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

    }

    private void showCollectables() {

    }

    private void showNextCard() {

    }

    private void endGame() {

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

    }

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


}
