package controller;

import models.ErrorType;
import models.Game;
import view.GameRequest;
import view.View;

public class GameController extends Controller {
    private String command;
    private String username;
    private String password;
    private Game game = new Game();
    private View gameView = new View();

    @Override
    public void main() {
        boolean isFinish = false;
        do {
            GameRequest request = new GameRequest();
            request.setNewCommand();
            command = request.getCommand();
            switch (request.getType()) {
                case LOGIN:
                    loginOrCreate(request, 1);
                    game.login(username, password);
                    break;
                case CREATE_ACCOUNT:
                    loginOrCreate(request, 2);
                    game.createAccount(username, password);
                    break;
                case HELP:
                    game.help();
                    break;
                case SHOW_LEADERBOARD:
                    game.showLeaderboard();
                    break;
                case EXIT:
                    isFinish = true;
                    break;
                default:
                    gameView.printError(ErrorType.GENERAL_ERROR);
            }
        }
        while (!isFinish);
    }

    private void loginOrCreate(GameRequest request, int index) {        //set username and password from input String
        username = command.split("\\s")[index];
        gameView.printGetPasswordCommand();
        request.setNewCommand();
        password = request.getCommand();
        if (!password.matches("\\w+"))          //typically passwords are like this '_'
            gameView.printError(ErrorType.INVALID_PASSWORD);
    }

}
