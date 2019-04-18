package view;

public class GameRequest extends Request {

    @Override
    public ViewRequestType getType() {
        if (command.matches("create account \\w+"))
            return ViewRequestType.CREATE_ACCOUNT;
        else if (command.matches("login \\w+"))
            return ViewRequestType.LOGIN;
        else if (command.equals("show leaderboard"))
            return ViewRequestType.SHOW_LEADERBOARD;
        else if (command.equals("help"))
            return ViewRequestType.HELP;
        else if (command.equals("exit"))
            return ViewRequestType.EXIT;
        return null;
    }
}
