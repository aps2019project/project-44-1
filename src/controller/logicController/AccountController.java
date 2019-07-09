package controller.logicController;

import client.RequestSender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;
import javafx.scene.Node;
import models.*;
import models.Enums.BattleKind;
import models.Enums.BattleMode;
import server.Environment;
import server.Request;
import view.fxmls.wrapperClasses.Serial;
import view.request.RequestType;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AccountController extends Thread {
    private static AccountController accountController = new AccountController();
    private Account account;
    private Account opponent = null;
    private int state;
    public static final int SINGLE1 = 1;
    public static final int SINGLE2 = 2;
    public static final int SINGLE3 = 3;
    public static final int MULTI1 = 20;
    public static final int MULTI2 = 22;
    private boolean regreted = false;

    private AccountController() {
    }

    public static AccountController getInstance() {
        accountController.setDaemon(true);
        return accountController;
    }

    //------------------------------------------------------------Battle
    private void enterBattle() {
        if (state >= MULTI1) {
            modeHandler();
        } else {
            storyGame();
        }
    }

    private void modeHandler() {
        switch (state) {
            case MULTI1:
                BattleController.getInstance().setBattle(new Battle(BattleKind.MULTI_PLAYER,
                        BattleMode.DEATH_MATCH, account, opponent, 0));
                break;
            case MULTI2:
                BattleController.getInstance().setBattle(new Battle(BattleKind.MULTI_PLAYER,
                        BattleMode.CAPTURE_FLAG_1, account, opponent, 1));
                break;
            default:
                BattleController.getInstance().setBattle(new Battle(BattleKind.MULTI_PLAYER,
                        BattleMode.CAPTURE_FLAG_2, account, opponent, state - MULTI1));
        }
    }

    private void storyGame() {
        int level = state;
        ArtificialIntelligence artificialIntelligence = new ArtificialIntelligence();
        Account ai_player = artificialIntelligence.getAccount(level);
        RequestType type = artificialIntelligence.getType(level);
        switch (type) {
            case CAPTURE_FLAG1:
                BattleController.getInstance().setBattle(new Battle(BattleKind.SINGLE_PLAYER,
                        BattleMode.CAPTURE_FLAG_1, account, ai_player, 1, 1000));
                break;
            case DEATH_MATCH:
                BattleController.getInstance().setBattle(new Battle(BattleKind.SINGLE_PLAYER,
                        BattleMode.DEATH_MATCH, account, ai_player, 0, 500));
                break;
            case CAPTURE_FLAG2:
                BattleController.getInstance().setBattle(new Battle(BattleKind.SINGLE_PLAYER,
                        BattleMode.CAPTURE_FLAG_2, account, ai_player, 5, 500));     //must "ASK"
        }
    }

    //-----------------------------------------------------------------save
    public void save() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Account.class, new Serial()).create();
        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader("src\\models\\accountSaves.json"));
            JsonArray array = gson.fromJson(reader, JsonArray.class);
            Account[] accounts = gson.fromJson(array, Account[].class);
            if (!savedBefore(accounts))
                write(gson, accounts, true);
            else
                write(gson, accounts, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(Gson gson, Account[] accounts, boolean containsNew) throws IOException {
        FileWriter writer;
        writer = new FileWriter("src/models/accountSaves.json");
        if (containsNew) {
            Account[] accounts2 = getAccounts(accounts);
            writer.write(gson.toJson(accounts2));
        } else
            writer.write(gson.toJson(accounts));
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

    private boolean savedBefore(Account[] accounts) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getUsername().equals(account.getUsername())) {
                accounts[i] = account;
                return true;
            }
        }
        return false;
    }

    //----------------------------------------------------------------------

    public void setAccount(Account account) {
        this.account = account;
    }

    public void logout(Node node) {
        Request request = new Request(Environment.MAIN_MENU);
        request.setUsername(account.getUsername());
        request.setRequestType(server.RequestType.LOG_OUT);
        RequestSender.getInstance().sendRequest(request);
        account = null;
        Game.getInstance().loadPage(node, "/view/fxmls/loginPage.fxml");
    }

    public Account getAccount() {
        return this.account;
    }

    public void setOpponent(Account opponent) {
        this.opponent = opponent;
    }

    @Override
    public void run() {
        enterBattle();
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isRegreted() {
        return regreted;
    }

    public void setRegreted(boolean regreted) {
        this.regreted = regreted;
    }

}
