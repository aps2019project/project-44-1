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
                int level = chooseStoryGame(request, battleKind);
                /* level will be -1 if player don't choose any of story games and come back here*/

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
                try {
                    chooseSecondPlayer(request, battleKind);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
            if (request.getType().equals(RequestType.SINGLE_PLAYER)) {
                battleKind = BattleKind.SINGLE_PLAYER;
                chooseGameKind(request, battleKind);
            }
        } while (!request.getType().equals(RequestType.EXIT));

    }

    private void chooseGameMode(AccountRequest request, Player p1, Player p2,
                                BattleKind battleKind) {
        view.showGameModes();
        BattleController battleController = new BattleController();
        do {
            request.getNewCommand();
            if (request.getType().equals(RequestType.DEATH_MATCH)) {
                battleController.main(new Battle(battleKind, BattleMode.DEATH_MATCH,
                        p1, p2, 0));
            }
            if (request.getType().equals(RequestType.CAPTURE_FLAG1)) {
                battleController.main(new Battle(battleKind, BattleMode.CAPTURE_FLAG_1,
                        p1, p2, 1));
            }
            if (request.getType().equals(RequestType.CAPTURE_FLAG2)) {
                battleController.main(new Battle(battleKind, BattleMode.CAPTURE_FLAG_2,
                        p1, p2, request.getNumberOfFlags()));
            }
        } while (!request.getType().equals(RequestType.EXIT));
    }


    private void chooseSecondPlayer(AccountRequest request, BattleKind battleKind) throws CloneNotSupportedException {
        do {
            request.getNewCommand();
            view.printPlayersList(Game.getAccounts(), account);
            if (request.getType().equals(RequestType.SELECT_SECOND_PLAYER)) {
                Account player2 = request.getSecondPlayer();
                chooseGameMode(request, new Player(account.getMainDeck().clone()),
                        new Player(player2.getMainDeck().clone()), battleKind);
                Account secondPlayer = Game.getAccount(request.getSecondPlayerUsername());
                if (secondPlayer.isReadyToPlay()) {
                    chooseGameMode(request, new Player(this.account.getMainDeck()),
                            new Player(secondPlayer.getMainDeck()), battleKind);
                } else {
                    view.printSecondPlayerIsNotReady();
                }
            }
        } while (!request.getType().equals(RequestType.EXIT));
    }

    public int chooseStoryGame(AccountRequest request, BattleKind battleKind) {
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


}