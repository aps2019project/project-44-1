package controller;

import models.*;
import view.AccountRequest;
import view.View;

public class AccountController {

    private Account account;
    private View view = new View();

    public void main(Account account) {
        this.account = account;
        boolean isFinish = false;
        do {
            AccountRequest request = new AccountRequest();
            request.setNewCommand();
            switch (request.getType()) {
                case COLLECTION:
                    enterCollection();
                    break;
                case SHOP:
                    enterShop();
                    break;
                case BATTLE:
                    enterBattle(request);
                    break;
                case EXIT:
                    isFinish = true;
                    break;
                case HELP:
                    view.printAccountMenuHelp();
                    break;
                case LOGOUT:
                    isFinish = true;
                    break;
                case SAVE:
                    save();
                    break;
            }
        }
        while (!isFinish);
    }

    private void save() {
    }

    private void enterCollection() {
        CollectionController collectionController = new CollectionController();
        collectionController.main(account);
    }

    private void enterShop() {
        ShopController shopController = new ShopController();
        shopController.main();
    }

    private void enterBattle(AccountRequest request) {
        BattleKind battleKind;
        while (true) {
            request.setNewCommand();
            if (request.getType().equals("multi player")) {
                battleKind = BattleKind.MULTI_PLAYER;
                chooseSecondPlayer(view, request, battleKind);
            }
            if (request.getType().equals("single player")) {
                battleKind = BattleKind.SINGLE_PLAYER;
                chooseGameKind(view, request, battleKind);
            }
            if (request.getType().equals("exit")) {
                break;
            }
        }


    }

    private void chooseGameMode(View view, AccountRequest request, Account p1, Account p2, BattleKind battleKind) {
        BattleController battleController = new BattleController();
        while (true) {
            request.setNewCommand();
            if (request.getType().equals("death match")) {
                battleController.main(new Battle(battleKind, p1, p2));
            }
            if (request.getType().equals("capture flag 1")) {
                battleController.main(new CaptureFlag1(battleKind, p1, p2));
            }
            if (request.getType().equals("capture flag 2")) {
                battleController.main(new CaptureFlag2(battleKind, p1, p2, 4));
                // TODO: 11/04/2019 problem in this case about transfering number of flags
            }
            if (request.getType().equals("exit")) {
                break;
            }
        }
    }

    private void chooseSecondPlayer(View view, AccountRequest request, BattleKind battleKind) {
        do {
            request.setNewCommand();
            if (request.getType().equals("select player 2")) {
                Account player2 = request.getSecondPlayer();
                chooseGameMode(view, request, this.account, player2, battleKind);
            }
        } while (!request.getType().equals("exit"));


    }

    private void chooseGameKind(View view, AccountRequest request, BattleKind battleKind) {
        Account ai_player;
        do {
            request.setNewCommand();
            if (request.getType().equals("story game")) {

            }
            if (request.getType().equals("multi player")) {

            }
        } while (!request.getType().equals("exit"));

    }

}