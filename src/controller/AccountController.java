package controller;

import models.*;
import models.Enums.BattleKind;
import models.Enums.BattleMode;
import models.Enums.ErrorType;
import view.AccountRequest;
import view.RequestType;
import view.View;

class AccountController {
    private static AccountController accountController = new AccountController();
    private Account account;
    private View view = View.getInstance();

    private AccountController() {
    }

    static AccountController getInstance() {
        return accountController;
    }

    void main(Account account) {
        AccountRequest request;
        this.account = account;
        boolean isFinish = false;
        do {
            view.showMainMenu();
            request = new AccountRequest();
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
                    System.exit(0);
                    break;
                case HELP:
                    help();
                    break;
                case LOGOUT:
                    isFinish = true;
                    break;
                case SAVE:
                    save();
            }
        }
        while (!isFinish);
    }

    private void enterShop() {
        ShopController.getInstance().main(account);
    }

    private void chooseGameKind(AccountRequest request) {
        Account ai_player;
        do {
            view.printGameKinds();
            request.getNewCommand();
            if (request.getType().equals(RequestType.STORY_GAME)) {
                int level = chooseStoryGame(request);
            }
            else if (request.getType().equals(RequestType.CUSTOM_GAME)) {
                /*custom game menu*/
            }
        } while (!request.getType().equals(RequestType.EXIT));
    }

    private void help() {
        view.printAccountMenuHelp(account.toString());
    }

    private void enterCollection() {
        CollectionController.getInstance().main(this.account.getCollection());
    }

    private void enterBattle(AccountRequest request) {
        if (!account.isReadyToPlay()) {
            view.printError(ErrorType.MAIN_DECK_IS_NOT_VALID);
            return;
        }
        view.printSelectSingleOrMulti();
        do {
            request.getNewCommand();
            switch (request.getType()) {
                case MULTI_PLAYER:
                    chooseSecondPlayer(request);
                    break;
                case SINGLE_PLAYER:
                    chooseGameKind(request);
                    break;
                case HELP:
                    view.sout("select \n multiPlayer \n or\nsinglePlayer");     // FIXME: 6/2/2019 bad smell
                    break;
            }
        } while (!request.getType().equals(RequestType.EXIT));
    }

    private void chooseGameMode(AccountRequest request, Account p1, Account p2) {
        view.showGameModes();
        do {
            request.getNewCommand();
            if (request.getType().equals(RequestType.DEATH_MATCH)) {
                BattleController.getInstance().main(new Battle(BattleKind.MULTI_PLAYER, BattleMode.DEATH_MATCH,
                        p1, p2, 0));
            }
            if (request.getType().equals(RequestType.CAPTURE_FLAG1)) {
                BattleController.getInstance().main(new Battle(BattleKind.MULTI_PLAYER, BattleMode.CAPTURE_FLAG_1,
                        p1, p2, 1));
            }
            if (request.getType().equals(RequestType.CAPTURE_FLAG2)) {
                BattleController.getInstance().main(new Battle(BattleKind.MULTI_PLAYER, BattleMode.CAPTURE_FLAG_2,
                        p1, p2, request.getNumberOfFlags()));
            }
        } while (!request.getType().equals(RequestType.EXIT));
    }

    private void chooseSecondPlayer(AccountRequest request) {
        do {
            view.printPlayersList(Game.getAccounts(), account);
            request.getNewCommand();
            if (request.getType().equals(RequestType.SELECT_SECOND_PLAYER)) {
                Account secondPlayer = Game.getAccount(request.getSecondPlayerUsername());
                if (secondPlayer != null && !secondPlayer.equals(account) && secondPlayer.isReadyToPlay()) {
                    chooseGameMode(request, account, secondPlayer);
                } else {
                    view.printSecondPlayerIsNotReady();
                }
            }
        } while (!request.getType().equals(RequestType.EXIT));
    }

    private int chooseStoryGame(AccountRequest request) {
        boolean isFinish = false;
        do {
            view.showStoryGameKinds();
            request.getNewCommand();
            if (request.getStoryModeLevel() != -1)
                return request.getStoryModeLevel();
            if (request.getType() == RequestType.EXIT) {
                isFinish = true;
            }
        }
        while (!isFinish);
        return -1;
    }

    private void save() {
    }
}