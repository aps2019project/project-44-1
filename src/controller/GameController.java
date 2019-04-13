package controller;

import models.Game;
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
            if (request.getType().equals("sign in")) {
                login(view,request);
            }
            if (request.getType().equals("sign up")){
                createAccount(view,request);
            }
            if (request.getType().equals("help")){
                help(view);
            }
            if (request.getType().equals("show leaderboard")){
                showLeaderboard(view);
            }
            if (request.getType().equals("exit")){
                isFinish = true;
            }

        }
        while (!isFinish);
    }

    private void login(GameView view,GameRequest request) {
        AccountController accountController = new AccountController();
        accountController.main(Game.getAccount("username"));
    }

    private void createAccount(GameView view,GameRequest request) {

    }

    private void showLeaderboard(GameView view){

    }

    private void help(GameView view){

    }
}
