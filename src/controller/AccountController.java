package controller;

import models.*;
import view.AccountRequest;
import view.RequestType;
import view.View;

public class AccountController {

    private Account account;
    private View view = new View();

    public void main(Account account) {
        this.account = account;
        boolean isFinish = false;
        do {
            view.showMainMenu();
            AccountRequest request = new AccountRequest();
            request.getNewCommand();
            switch (request.getType()) {
                case ENTER_COLLECTION:
                    enterCollection();
                    break;
                case ENTER_SHOP:
                    enterShop();
                    break;
                case ENTER_BATTLE:
                    enterBattle(request);
                    break;
                case EXIT:
                    isFinish = true;
                    break;
                case HELP:
                    help();
                    break;
                case LOGOUT:
                    logout();
                    break;
                case SAVE:
                    save();
                    break;
                case LOGIN:
            }
        }
        while (!isFinish);
    }


    private void enterShop() {
        ShopController shopController = new ShopController();
        shopController.main();
    }

    private void chooseGameMode(View view, AccountRequest request, Account p1, Account p2, BattleKind battleKind) {
        BattleController battleController = new BattleController();
        while (true) {
            request.getNewCommand();
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
            request.getNewCommand();
            if (request.getType().equals("select player 2")) {
                Account player2 = request.getSecondPlayer();
                chooseGameMode(view, request, this.account, player2, battleKind);
            }
        } while (!request.getType().equals("exit"));


    }

    private void chooseGameKind(View view, AccountRequest request, BattleKind battleKind) {
        Account ai_player;
        do {
            request.getNewCommand();
            if (request.getType().equals("story game")) {

            }
            if (request.getType().equals("multi player")) {

            }
        } while (!request.getType().equals("exit"));

    }
    private void save() {

    }

    private void logout() {

    }

    private void help() {

    }


    private void enterCollection() {
        CollectionController collectionController = new CollectionController();
        collectionController.main(this.account.getCollection());
    }

    private void enterBattle(AccountRequest request) {
        BattleKind battleKind;
        while (true) {
            request.getNewCommand();
            if (request.getType().equals(RequestType.MULTI_PLAYER)) {
                battleKind = BattleKind.MULTI_PLAYER;
                chooseSecondPlayer(request, battleKind);
            }
            if (request.getType().equals(RequestType.SINGLE_PLAYER)) {
                battleKind = BattleKind.SINGLE_PLAYER;
                chooseGameKind(request, battleKind);
            }
            if (request.getType().equals(RequestType.EXIT)) {
                break;
            }
        }


    }

    private void chooseGameMode(AccountRequest request, Account p1, Account p2, BattleKind battleKind) {
        BattleController battleController = new BattleController();
        while (true) {
            request.getNewCommand();
            if (request.getType().equals(RequestType.DEATH_MATCH)) {
                battleController.main(new Battle(battleKind, p1, p2));
            }
            if (request.getType().equals(RequestType.CAPTURE_FLAG1)) {
                battleController.main(new CaptureFlag1(battleKind, p1, p2));
            }
            if (request.getType().equals(RequestType.CAPTURE_FLAG2)) {
                battleController.main(new CaptureFlag2(battleKind, p1, p2, 4));
                // TODO: 11/04/2019 problem in this case about transfering number of flags
            }
            if (request.getType().equals(RequestType.EXIT)) {
                break;
            }
        }
    }

    private void chooseSecondPlayer(AccountRequest request, BattleKind battleKind) {
        while (true) {
            request.getNewCommand();
            if (request.getType().equals(RequestType.SELECT_SECOND_PLAYER)) {
                Account player2 = request.getSecondPlayer();
                chooseGameMode(request, this.account, player2, battleKind);
            }
            if (request.getType().equals(RequestType.EXIT)) {
                break;
            }
        }


    }

    private void chooseGameKind(AccountRequest request, BattleKind battleKind) {
        Account ai_player;
        while (true) {
            request.getNewCommand();
            if (request.getType().equals(RequestType.STORY_GAME)) {

            }
            if (request.getType().equals(RequestType.MULTI_PLAYER)) {

            }
            if (request.getType().equals(RequestType.EXIT)) {
                break;
            }
        }

    }

}