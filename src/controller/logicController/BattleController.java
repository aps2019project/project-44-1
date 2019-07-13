package controller.logicController;

import models.*;
import models.Enums.BattleKind;
import models.Enums.ErrorType;
import view.request.BattleRequest;
import view.View;

import static models.Enums.ErrorType.DEST_IS_UNAVAILABLE_FOR_ATTACK;

public class BattleController {
    private Battle battle;
    private View view = View.getInstance();

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    public Battle getBattle() {
        return battle;
    }

    public void gameHistory() {
        if (battle.isFirstPlayerWon()) {
            battle.getFirst().increaseWins();
            battle.getFirst().increaseMoney(battle.getPrize());
            view.sout("PLAYER \t" + battle.getFirst().getUsername() +
                    "\nWON!!!" + "PRIZE : \t" + battle.getPrize());
            MatchHistory matchHistory = new MatchHistory(battle.getSecond().getUsername(),
                    true);
            battle.getFirst().addMatchHistory(matchHistory);
            matchHistory = new MatchHistory(battle.getFirst().getUsername(),
                    false);
            battle.getSecond().addMatchHistory(matchHistory);
        } else {
            battle.getSecond().increaseWins();
            battle.getSecond().increaseMoney(battle.getPrize());
            view.sout("PLAYER \t" + battle.getSecond().getUsername() +
                    "\nWON!!!" + "PRIZE : \t" + battle.getPrize());
            MatchHistory matchHistory = new MatchHistory(battle.getFirst().getUsername(),
                    true);
            battle.getSecond().addMatchHistory(matchHistory);
            matchHistory = new MatchHistory(battle.getSecond().getUsername(),
                    false);
            battle.getFirst().addMatchHistory(matchHistory);
        }
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

    public void endTurn() {
        if (battle.getBattleKind().equals(BattleKind.SINGLE_PLAYER)) {
            battle.turnHandler();
            ArtificialIntelligence.aiAction(this.battle);
        }
        battle.turnHandler();
    }

    public void enterGraveyard() {

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

    public void save() {

    }

}