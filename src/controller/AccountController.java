package controller;

import models.*;
import models.Enums.BattleKind;
import models.Enums.BattleMode;
import models.Enums.ErrorType;
import view.AccountRequest;
import view.RequestType;
import view.View;

class AccountController {

    private Account account;
    private View view = new View();

    void main(Account account) {
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
            view.printGameKinds();
            request.getNewCommand();
            if (request.getType().equals(RequestType.STORY_GAME)) {
                chooseStoryGame(request,battleKind);
                // TODO: 04/05/2019 list story game haro chejoori namayesh beadam? 
            }
            if (request.getType().equals(RequestType.CUSTOM_GAME)) {
                // TODO: 04/05/2019
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
        if (!account.isReadyToPlay()) {
            view.printError(ErrorType.MAIN_DECK_IS_NOT_VALID);
            return;
        }
        view.printSelectSingleOrMulti();
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
        view.showGameModes();
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
                battleController.main(new CaptureFlag2(battleKind, p1, p2, request.getNumberOfFlags()));
                // TODO: 11/04/2019 problem in this case about transfering number of flags
            }
        } while (!request.getType().equals(RequestType.EXIT));
    }


    private void chooseSecondPlayer(AccountRequest request, BattleKind battleKind) {
        do {
            request.getNewCommand();
            view.printPlayersList(Game.getAccounts(), account);
            if (request.getType().equals(RequestType.SELECT_SECOND_PLAYER)) {
                Account secondPlayer = Game.getAccount(request.getSecondPlayerUsername());
                if (secondPlayer.isReadyToPlay()) {
                    chooseGameMode(request, this.account, secondPlayer, battleKind);
                } else {
                    view.printSecondPlayerIsNotReady();
                }
            }
        } while (!request.getType().equals(RequestType.EXIT));
    }

    public void chooseStoryGame(AccountRequest request, BattleKind battleKind) {
    }


}