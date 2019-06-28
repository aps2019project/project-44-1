package controller.logicController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;
import javafx.scene.Node;
import models.*;
import models.Enums.BattleKind;
import models.Enums.BattleMode;
import models.Enums.ErrorType;
import view.request.AccountRequest;
import view.request.RequestType;
import view.View;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AccountController extends Thread {
    private static AccountController accountController = new AccountController();
    private Account account;
    private View view = View.getInstance();

    private AccountController() {
    }

    public static AccountController getInstance() {
        accountController.setDaemon(true);
        return accountController;
    }

    //------------------------------------------------------------Battle
    public void enterBattle(AccountRequest request) {
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
                    storyOrCustom(request);
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
                if (secondPlayer != null && !secondPlayer.equals(account) &&
                        secondPlayer.isReadyToPlay()) {
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
            modeHandler(request.getType(), p1, p2);
        } while (!request.getType().equals(RequestType.EXIT));
    }

    private void modeHandler(RequestType type, Account p1, Account p2) {
        switch (type) {
            case DEATH_MATCH:
                BattleController.getInstance().main(new Battle(BattleKind.MULTI_PLAYER,
                        BattleMode.DEATH_MATCH, p1, p2, 0));
                break;
            case CAPTURE_FLAG1:
                BattleController.getInstance().main(new Battle(BattleKind.MULTI_PLAYER,
                        BattleMode.CAPTURE_FLAG_1, p1, p2, 1));
                break;
            case CAPTURE_FLAG2:
                view.sout("enter number of flags");
                AccountRequest ar = new AccountRequest();
                ar.getNewCommand();
                BattleController.getInstance().main(new Battle(BattleKind.MULTI_PLAYER, BattleMode.CAPTURE_FLAG_2,
                        p1, p2, ar.getNumberOfFlags()));
        }
    }

    private void storyOrCustom(AccountRequest request) {
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
        int level = selectStoryModeLevel(request);
        ArtificialIntelligence artificialIntelligence = new ArtificialIntelligence();
        Account ai_player = artificialIntelligence.getAccount(level);
        RequestType type = artificialIntelligence.getType(level);
        switch (type) {
            case CAPTURE_FLAG1:
                BattleController.getInstance().main(new Battle(BattleKind.SINGLE_PLAYER,
                        BattleMode.CAPTURE_FLAG_1, account, ai_player, 1, 1000));
                break;
            case DEATH_MATCH:
                BattleController.getInstance().main(new Battle(BattleKind.SINGLE_PLAYER,
                        BattleMode.DEATH_MATCH, account, ai_player, 0, 500));
                break;
            case CAPTURE_FLAG2:
                BattleController.getInstance().main(new Battle(BattleKind.SINGLE_PLAYER,
                        BattleMode.CAPTURE_FLAG_2, account, ai_player, 5, 500));     //must "ASK"
        }
    }

    private int selectStoryModeLevel(AccountRequest request) {
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

    //-----------------------------------------------------------------
    public void save() {
        Gson gson = new Gson();
        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader("src\\models\\accountSaves.json"));
            JsonArray array = gson.fromJson(reader, JsonArray.class);
            Account[] accounts = gson.fromJson(array, Account[].class);
            write(gson, accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(Gson gson, Account[] accounts) throws IOException {
        FileWriter writer;
        Account[] accounts2;
        writer = new FileWriter("src/models/accountSaves.json");
        accounts2 = getAccounts(accounts);
        writer.write(gson.toJson(accounts2));
        writer.flush();
        writer.close();
    }

    private Account[] getAccounts(Account[] accounts) {
        Account[] accounts2;
        if (accounts != null) {
            accounts2 = new Account[accounts.length + 1];
            System.arraycopy(accounts, 0, accounts2, 0, accounts.length);
            accounts2[accounts2.length - 1] = account;
        } else {
            accounts2 = new Account[1];
            accounts2[0] = account;
        }
        return accounts2;
    }

    public void enterCollection() {
        CollectionController.getInstance().setCollection(this.account.getCollection());
    }

    public void enterShop() {
        ShopController.getInstance().getShop().setAccount(account);
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void logout(Node node) {
        account = null;
        Game.getInstance().loadPage(node, "/view/fxmls/loginPage.fxml");
    }

    public Account getAccount() {
        return this.account;
    }

}
