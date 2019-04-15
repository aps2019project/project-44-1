package view;

import models.ErrorType;
import models.RequestType;

import java.util.Scanner;

public abstract class Request {
    protected Scanner scanner = new Scanner(System.in);
    protected String command;
    protected static final String EXIT = "Exit";
    protected static final String HELP = "Help";
    protected ErrorType error = null;


    public void getNewCommand() {
        this.command = scanner.nextLine();
    }

    public abstract RequestType getType();

    public ErrorType getError() {
        return error;
    }

    public void setError(ErrorType error) {
        this.error = error;
    }
}
