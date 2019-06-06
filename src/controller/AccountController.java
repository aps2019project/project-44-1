package controller;

import models.Account;
import models.ArtificialIntelligence;
import models.Battle;
import models.Enums.BattleKind;
import models.Enums.BattleMode;
import models.Enums.ErrorType;
import models.Game;
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
                case SHOW_MATCH_HISTORY:
                    account.showHistory();
            }
        }
        while (!isFinish);
    }

    //------------------------------------------------------------Battle
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
                    view.sout("select \n multiPlayer \n or\nsinglePlayer");
                    break;
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

    private void chooseGameMode(AccountRequest request, Account p1, Account p2) {
        view.showGameModes();
        do {
            request.getNewCommand();
            modeHandler(request.getType(), p1, p2, BattleKind.MULTI_PLAYER);
        } while (!request.getType().equals(RequestType.EXIT));
    }

    private void modeHandler(RequestType type, Account p1, Account p2, BattleKind battleKind) {
        switch (type) {
            case DEATH_MATCH:
                BattleController.getInstance().main(new Battle(battleKind, BattleMode.DEATH_MATCH,
                        p1, p2, 0, 500));
                break;
            case CAPTURE_FLAG1:
                BattleController.getInstance().main(new Battle(battleKind, BattleMode.CAPTURE_FLAG_1,
                        p1, p2, 1, 1000));
                break;
            case CAPTURE_FLAG2:
                multipleFlagMode(p1, p2, battleKind);
        }
    }

    private void chooseGameKind(AccountRequest request) {
        do {
            view.printGameKinds();
            request.getNewCommand();
            switch (request.getType()) {
                case STORY_GAME:
                    storyGame(request);
                    break;
                case CUSTOM_GAME:
                    /*custom game menu*/
            }
        } while (!request.getType().equals(RequestType.EXIT));
    }

    private void storyGame(AccountRequest request) {
        int level = chooseStoryGame(request);
        ArtificialIntelligence artificialIntelligence = new ArtificialIntelligence();
        Account ai_player = artificialIntelligence.getAccount(level);
        RequestType type = artificialIntelligence.getType(level);
        if (!type.equals(RequestType.CAPTURE_FLAG2))
            modeHandler(type, account, ai_player, BattleKind.SINGLE_PLAYER);
        else multipleFlagMode(account, ai_player, BattleKind.SINGLE_PLAYER, 5);     //must ASK
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

    private void multipleFlagMode(Account p1, Account p2, BattleKind battleKind, int... flagNum) {
        if (flagNum == null) {
            int[] ints = new int[1];
            ints[0] = new AccountRequest().getNumberOfFlags();
            flagNum = ints;
        }
        BattleController.getInstance().main(new Battle(battleKind, BattleMode.CAPTURE_FLAG_2,
                p1, p2, flagNum[0], 1500));
    }

    //-----------------------------------------------------------------
    private void save() {
    }

    private void help() {
        view.printAccountMenuHelp(account.toString());
    }

    private void enterCollection() {
        CollectionController.getInstance().main(this.account.getCollection());
    }

    private void enterShop() {
        ShopController.getInstance().main(account);
    }

}