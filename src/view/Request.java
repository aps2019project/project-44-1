package view;

import models.Enums.ErrorType;

import java.util.Scanner;

public abstract class Request {
    private Scanner scanner = new Scanner(System.in);
    String command;
    private View view = View.getInstance();
    protected static final String EXIT = "Exit";
    protected static final String HELP = "Help";

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