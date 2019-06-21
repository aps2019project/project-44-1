package controller;

import Main.Main;
import controller.fxmlControllers.MainMenuController;
import javafx.fxml.FXMLLoader;
import models.Account;
import models.Enums.ErrorType;
import models.Game;

import java.io.IOException;

public class GameController {
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
//    public void main() {
//        GameRequest request;
//        boolean isFinish = false;
//        do {
//            view.printStartMenu();          /*show menu*/
//            request = new GameRequest();
//            request.getNewCommand();
//            switch (request.getType()) {
//                case LOGIN:
//                    login(request);
//                    break;
//                case CREATE_ACCOUNT:
//                    createAccount(request);
//                    break;
//                case HELP:
//                    help();
//                    break;
//                case SHOW_LEADERBOARD:
//                    showLeaderboard();
//                    break;
//                case EXIT:
//                    isFinish = true;
//            }
//        }
//        while (!isFinish);
//    }

    public void login(String username,String password) {
        Account account = Game.getAccount(username);
        if (account != null) {
            if (game.isValidPassword(account, password)) {
//                AccountController.getInstance().main(account);
                Main.getStage().getScene().setRoot(Main.getMainMenu());
                MainMenuController.setAccount(account);// FIXME: 6/13/2019 her
            } else {
                showMessage(ErrorType.INVALID_PASSWORD);
            }
        } else {
            showMessage(ErrorType.INVALID_USERNAME);
        }
    }

    public void createAccount(String username,String password) {
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
        System.out.println(errorType.getMessage());
    }
//
//    private void showLeaderboard() {
//        view.printLeaderboard(game.getSortedAccounts());
//    }
//
//    private void help() {
//        view.printGameMenuHelp(game.toString());
//    }



    public String getLabelText() {
        return labelText;
    }

}