package view;

import models.ErrorType;

import java.util.Scanner;

public abstract class Request {
    private Scanner scanner = new Scanner(System.in);
    String command;
    private View view = new View();

    public void setNewCommand() {
        this.command = scanner.nextLine();
        if (command.equals("\n"))
            view.printError(ErrorType.ERROR_WITHOUT_MESSAGE);
    }       /**  empty line command error handled here*/

    public String getCommand() {
        return command;
    }

    public abstract ViewRequestType getType();
}
