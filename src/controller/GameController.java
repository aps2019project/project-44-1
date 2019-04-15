package controller;

import models.ErrorType;
import models.Game;
import models.RequestType;
import view.GameRequest;
import view.GameView;

public class GameController extends Controller {
    private Game game = new Game();
    private GameView view = new GameView();

    @Override
    public void main() {
        boolean isFinish = false;
        do {
            GameRequest request = new GameRequest();
            request.getNewCommand();
            if (request.getType().equals(RequestType.SIGN_UP)) {
                login(view, request);
            }
            if (request.getType().equals(RequestType.SIGN_UP)) {
                createAccount(view, request);
            }
            if (request.getType().equals(RequestType.HELP)) {
                help(view);
            }
            if (request.getType().equals(RequestType.SHOW_LEADERBOARD)) {
                showLeaderboard(view);
            }
            if (request.getType().equals(RequestType.EXIT)) {
                isFinish = true;
            }

        }
        while (!isFinish);
    }

    private void login(GameView view, GameRequest request) {
        AccountController accountController = new AccountController();
        accountController.main(Game.getAccount("username"));
    }

    private void createAccount(GameView view, GameRequest request) {
        if (!game.isUsedUsername(request.getUserName())){
            game.createAccount(request.getUserName(),request.getPassword(view));
        }else {
            request.setError(ErrorType.USED_BEFORE_USERNAME);
            view.printError(request.getError());
        }
    }

    private void showLeaderboard(GameView view) {

    }

    private void help(GameView view) {

    }
}
