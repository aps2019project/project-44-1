package view;

import models.ErrorType;

public abstract class View {
    public void printError(ErrorType type) {
        if (type == null)
            return;
        if (type != ErrorType.ERROR_WITHOUT_MESSAGE)
            System.out.println(type.getMessage());
    }
}
