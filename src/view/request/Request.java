package view.request;

import models.Enums.ErrorType;
import view.View;

import java.util.Scanner;

public abstract class Request {
    private Scanner scanner = new Scanner(System.in);
    String command;
    private View view = View.getInstance();

    /**
     * empty line command error handled here
     */
    public void getNewCommand() {
        this.command = scanner.nextLine();
        if (command.equals("\n"))
            view.printError(ErrorType.ERROR_WITHOUT_MESSAGE);
    }

    public abstract RequestType getType();

}