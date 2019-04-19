package view;

public class GameRequest extends Request {

    @Override
    public RequestType getType() {
        if (command.matches("create account \\w+"))
            return RequestType.CREATE_ACCOUNT;
        else if (command.matches("login \\w+"))
            return RequestType.LOGIN;
        else if (command.equals("show leaderboard"))
            return RequestType.SHOW_LEADERBOARD;
        else if (command.equals("help"))
            return RequestType.HELP;
        else if (command.equals("exit"))
            return RequestType.EXIT;
        return null;
    }
}
