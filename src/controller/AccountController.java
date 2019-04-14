package controller;

import models.*;
import view.AccountRequest;
import view.AccountView;


public class AccountController {
// TODO: 10/04/2019 if this class extends Controller i can not over load main why?

    private Account account;
    private AccountView view = new AccountView();

    public void main(Account account) {
        this.account = account;
        boolean isFinish = false;
        do {
            AccountRequest request = new AccountRequest();
            request.getNewCommand();
            if (request.getType().equals("enter collection")) {
                enterCollection();
            }
            if (request.getType().equals("enter shop")) {
                enterShop();
            }
            if (request.getType().equals("enter battle")) {
                enterBattle(view, request);
            }
            if (request.getType().equals("exit")) {
                isFinish = true;
            }
            if (request.getType().equals("help")) {
                help();
            }
            if (request.getType().equals("log out")) {
                logout();
            }
            if (request.getType().equals("save")) {
                save();
            }
        }
        while (!isFinish);
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

    private void enterShop() {
        ShopController shopController = new ShopController();
        shopController.main();
    }

    private void enterBattle(AccountView view, AccountRequest request) {
        BattleKind battleKind;
        while (true) {
            request.getNewCommand();
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

    private void chooseGameMode(AccountView view, AccountRequest request, Account p1, Account p2, BattleKind battleKind) {
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

    private void chooseSecondPlayer(AccountView view, AccountRequest request, BattleKind battleKind) {
        while (true) {
            request.getNewCommand();
            if (request.getType().equals("select player 2")) {
                Account player2 = request.getSecondPlayer();
                chooseGameMode(view, request, this.account, player2, battleKind);
            }
            if (request.getType().equals("exit")) {
                break;
            }
        }


    }

    private void chooseGameKind(AccountView view, AccountRequest request, BattleKind battleKind) {
        Account ai_player;
        while (true) {
            request.getNewCommand();
            if (request.getType().equals("story game")) {

            }
            if (request.getType().equals("multi player")) {

            }
            if (request.getType().equals("exit")) {
                break;
            }
        }

    }

}


