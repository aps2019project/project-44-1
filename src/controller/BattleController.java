package controller;

import models.Battle;
import view.BattleRequest;
import view.BattleView;


public class BattleController {

    private Battle battle;
    private BattleView view = new BattleView();

    public void main(Battle battle) {
        this.battle = battle;
        boolean isFinish = false;
        do {
            BattleRequest request = new BattleRequest();
            request.setNewCommand();
            if (request.getType().equals("exit")) {
                isFinish = true;
            }
            if (request.getType().equals("help")) {
                helpInBattle(view);
            }

            if (request.getType().equals("show game info")) {
                showGameInfo(view);
            }
            if (request.getType().equals("show my minions")) {
                showMyMinions(view);
            }
            if (request.getType().equals("show opponent minions")) {
                showOpponentMinions(view);
            }
            if (request.getType().equals("show card info")) {
                showCardInfo(view, request);
            }
            if (request.getType().equals("select card")) {
                selectCard(view, request);
            }
            if (request.getType().equals("move card")) {
                moveCard(view, request);
            }
            if (request.getType().equals("ordinary attack")) {
                ordinaryAttack(view, request);
            }
            if (request.getType().equals("combo attack")) {
                comboAttack(view, request);
            }
            if (request.getType().equals("use special power")) {
                useSpecialPower(view, request);
            }
            if (request.getType().equals("show hand")) {
                showHand(view, request);
            }
            if (request.getType().equals("insert card")) {
                insertCard(view, request);
            }
            if (request.getType().equals("end turn")) {
                endTurn(view);
            }
            if (request.getType().equals("show collectable items")) {
                showCollectables(view);
            }
            if (request.getType().equals("show next card")) {
                showNextCard(view);
            }
            if (request.getType().equals("enter graveyard")) {
                enterGraveyard(view, request);
            }
            if (request.getType().equals("end game")) {
                endGame(view);
            }
            if (request.getType().equals("select a collectable item")) {
                selectCollectable(view, request);
            }


        }
        while (!isFinish);
    }

    private void showGameInfo(BattleView view) {

    }

    private void showMyMinions(BattleView view) {

    }

    private void showOpponentMinions(BattleView view) {

    }

    private void showCardInfo(BattleView view, BattleRequest request) {

    }

    private void selectCard(BattleView view, BattleRequest request) {

    }

    private void moveCard(BattleView view, BattleRequest request) {

    }

    private void ordinaryAttack(BattleView view, BattleRequest request) {

    }

    private void comboAttack(BattleView view, BattleRequest request) {

    }

    private void useSpecialPower(BattleView view, BattleRequest request) {

    }

    private void insertCard(BattleView view, BattleRequest request) {

    }

    private void showHand(BattleView view, BattleRequest request) {

    }

    private void endTurn(BattleView view) {

    }

    private void showCollectables(BattleView view) {

    }

    private void showNextCard(BattleView view) {

    }

    private void endGame(BattleView view) {

    }

    private void enterGraveyard(BattleView view, BattleRequest request) {
        while (true) {
            request.setNewCommand();
            if (request.getType().equals("show card info in graveyard")) {
                showCardInfo(view, request);
            }
            if (request.getType().equals("show cards in graveyard")) {
                showCardsInGraveyard(view);
            }
            if (request.getType().equals("exit")) {
                break;
            }
            if (request.getType().equals("show menu")) {
                showMenuInGraveyard(view);
            }
        }
        // not sure about this
    }

    private void selectCollectable(BattleView view, BattleRequest request) {
        while (true) {
            request.setNewCommand();
            if (request.getType().equals("show item info")) {
                showCollectableInfo(view, request);
            }
            if (request.getType().equals("use item")) {
                useCollectable(view, request);
            }
            if (request.getType().equals("exit")) {
                break;
            }
            if (request.getType().equals("show menu in collectable item menu")) {
                showMenuInSelectCollectable(view);
            }
        }
        //not sure about this
    }

    private void helpInBattle(BattleView view) {

    }

    private void showMenuInGraveyard(BattleView view) {

    }

    private void showMenuInSelectCollectable(BattleView view) {

    }

    private void showCardsInGraveyard(BattleView view) {

    }

    private void showCollectableInfo(BattleView view, BattleRequest request) {

    }

    private void useCollectable(BattleView view, BattleRequest request) {

    }


}
