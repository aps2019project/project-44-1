package view;

public class GameRequest extends Request {
    private static final String SIGN_IN = "login \\w+";
    private static final String SIGN_UP = "create account \\w+";
    private static final String SHOW_LEADERBOARD = "Show leaderboard";

    @Override
    public RequestType getType() {
        if (command.matches(SIGN_IN)) {
            return RequestType.LOGIN;
        } else if (command.matches(SIGN_UP)) {
            return RequestType.CREATE_ACCOUNT;
        } else if (command.matches(SHOW_LEADERBOARD)) {
            return RequestType.SHOW_LEADERBOARD;
        } else if (command.equals(HELP)) {
            return RequestType.HELP;
        } else if (command.equals(EXIT)) {
            return RequestType.EXIT;
        } else {
            /** INVALID_COMMAND can be converted to null*/
            return RequestType.INVALID_COMMAND;
        }
    }

    public String getUserName() {
        switch (getType()) {
            case LOGIN:
                return command.split("\\s")[1];
            case CREATE_ACCOUNT:
                return command.split("\\s")[2];
            default:
                // TODO: 15/04/2019 what to do?
                return null;
        }
    }

    public String getPassword(View view) {
        view.printGetPasswordCommand();
        getNewCommand();
        return command.trim();
    }
}
