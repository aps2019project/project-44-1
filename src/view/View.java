package view;

import models.Enums.ErrorType;

public class View {

    private static View view = new View();

    private View() {
    }

    public static View getInstance() {
        return view;
    }

    public void printError(ErrorType type) {
        if (type == null)
            return;
        System.out.println(type.getMessage());
    }

    public void sout(Object o) {
        System.out.println(o);
    }

    public void usedAttackBefore(String cardID) {
        System.out.println("Card with " + cardID +
                " can′t attack ");
    }

}
