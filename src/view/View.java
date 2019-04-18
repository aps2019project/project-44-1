package view;

import models.ErrorType;

public class View {

    public void printError(ErrorType type) {        //after each error get new command
        if (type == null)
            return;
        System.out.println(type.getMessage());
        Request request = new GameRequest();
        request.setNewCommand();
    }

    public void printGetPasswordCommand() {
        System.out.println("Enter your password");
    }
}
