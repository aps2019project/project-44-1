package view;

import models.ErrorType;

import java.util.Scanner;

public abstract class Request {
    private Scanner scanner = new Scanner(System.in);
    String command;
    private View view = new View();
    protected static final String EXIT = "Exit";
    protected static final String HELP = "Help";
    protected ErrorType error = null;

    public void getNewCommand() {
        this.command = scanner.nextLine();
        command = command.toLowerCase();
        if (command.equals("\n"))
            view.printError(ErrorType.ERROR_WITHOUT_MESSAGE);
    }       /**  empty line command error handled here*/

    public String getCommand() {
        return command;
    }

    public abstract RequestType getType();

    public String getName(int index) {
        return command.split("\\s")[index].trim();
    }

    public int getID(int index) {
        return Integer.parseInt(command.split("\\s")[index].trim());
    }

    public ErrorType getError() {
        return error;
    }

    public void setError(ErrorType error) {
        this.error = error;
    }


}
