package controller.logicController;

import models.Account;
import models.Enums.ErrorType;
import models.Game;

public class GameController extends Thread {
    private static GameController gameController = new GameController();
    private Game game = Game.getInstance();
    private String username;
    private String password;
    private boolean isLoginRequest;
    private String labelText = null;

    private GameController() {
    }

    public static GameController getInstance() {
        return gameController;
    }

    private boolean login() {
        Account account = Game.getAccount(username);
        if (account != null) {
            if (game.isValidPassword(account, password)) {
                showMessage(ErrorType.NO_ERROR);
                AccountController.getInstance().setAccount(account);
            } else {
                showMessage(ErrorType.INVALID_PASSWORD);
            }
        } else {
            showMessage(ErrorType.INVALID_USERNAME);
        }
        return false;
    }

    private void createAccount() {
        if (username.equals("")) {
            showMessage(ErrorType.INVALID_USERNAME);
            return;
        }
        if (password.equals("")) {
            showMessage(ErrorType.INVALID_PASSWORD);
            return;
        }
        if (!game.isUsedUsername(username)) {
            game.createAccount(username, password);
            showMessage(ErrorType.ACCOUNT_CREATED);
        } else {
            showMessage(ErrorType.USED_BEFORE_USERNAME);
        }
    }

    private void showMessage(ErrorType errorType) {
        labelText = errorType.getMessage();
    }

    @Override
    public void run() {
        decide();
    }

    private synchronized void decide() {
        while (true) {
            try {
                if (isLoginRequest) {
                    if (login())
                        return;
                } else
                    createAccount();
                if (Thread.holdsLock(this)) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void getGraphicState(String username, String password, boolean isLoginRequest) {
        this.password = password;
        this.username = username;
        this.isLoginRequest = isLoginRequest;
    }

    public String getLabelText() {
        return labelText;
    }

}
