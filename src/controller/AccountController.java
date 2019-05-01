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
            }
        }
        while (!isFinish);
    }


    private void enterShop() {
        ShopController shopController = new ShopController();
        shopController.main(account);
    }

    private void chooseGameKind(AccountRequest request, BattleKind battleKind) {
        Account ai_player;
        do {
            request.getNewCommand();
            if (request.getType().equals(RequestType.STORY_GAME)) {

            }
            if (request.getType().equals(RequestType.MULTI_PLAYER)) {

            }
        } while (!request.getType().equals(RequestType.EXIT));

    }


    private void save() {

    }

    private void logout() {

    }

    private void help() {
        view.printAccountMenuHelp(account.toString());
    }


    private void enterCollection() {
        CollectionController collectionController = new CollectionController();
        collectionController.main(this.account.getCollection());
    }

    private void enterBattle(AccountRequest request) {
        BattleKind battleKind;
        do {
            request.getNewCommand();
            if (request.getType().equals(RequestType.MULTI_PLAYER)) {
                battleKind = BattleKind.MULTI_PLAYER;
                chooseSecondPlayer(request, battleKind);
            }
            if (request.getType().equals(RequestType.SINGLE_PLAYER)) {
                battleKind = BattleKind.SINGLE_PLAYER;
                chooseGameKind(request, battleKind);
            }
        } while (!request.getType().equals(RequestType.EXIT));


    }

    private void chooseGameMode(AccountRequest request, Account p1, Account p2, BattleKind battleKind) {
        BattleController battleController = new BattleController();
        do {
            request.getNewCommand();
            if (request.getType().equals(RequestType.DEATH_MATCH)) {
                battleController.main(new Battle(battleKind, BattleMode.DEATH_MATCH, p1, p2));
            }
            if (request.getType().equals(RequestType.CAPTURE_FLAG1)) {
                battleController.main(new CaptureFlag1(battleKind, p1, p2));
            }
            if (request.getType().equals(RequestType.CAPTURE_FLAG2)) {
                battleController.main(new CaptureFlag2(battleKind, p1, p2, 4));
                // TODO: 11/04/2019 problem in this case about transfering number of flags
            }
        } while (!request.getType().equals(RequestType.EXIT));
    }


    private void chooseSecondPlayer(AccountRequest request, BattleKind battleKind) {
        do {
            request.getNewCommand();
            if (request.getType().equals(RequestType.SELECT_SECOND_PLAYER)) {
                Account player2 = request.getSecondPlayer();
                chooseGameMode(request, this.account, player2, battleKind);
            }
        } while (!request.getType().equals(RequestType.EXIT));


    }


}