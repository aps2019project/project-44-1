package view;

import models.ErrorType;

public class GameView {

    public void print(ErrorType errorType) {
        System.out.println(errorType.getMessage());
    }
}
