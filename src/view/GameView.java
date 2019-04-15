package view;

import models.ErrorType;

public class GameView {

    public GameView(ErrorType errorType) {
        print(errorType);
    }

    public void print(ErrorType errorType) {
        System.out.println(errorType.getMessage());
    }
}
