package view;

import java.util.Scanner;

public abstract class Request {
    private Scanner scanner = new Scanner(System.in);
    String command;

    public  String getType(){return "ok";}

    public void setNewCommand() {
        this.command = scanner.nextLine();
    }

    public String getCommand() {
        return command;
    }
}
