package view;

import java.util.Scanner;

public abstract class Request {
    private Scanner scanner = new Scanner(System.in);
    private String command;


    public void getNewCommand() {
        this.command = scanner.nextLine();
    }

    public String getType(){
        return command;
    }
}
