package view.request;

import models.Enums.ErrorType;
import view.View;

public class AccountRequest extends Request {

    private View view = View.getInstance();

    public RequestType getType() {
        if (command.matches("select user \\w+")) {
            return RequestType.SELECT_SECOND_PLAYER;
        } else if (command.matches("Start multiplayer game captureflag2 \\d+")) {
            return RequestType.CAPTURE_FLAG2;
        }
        switch (command) {
            case "enter battle":
                return RequestType.ENTER_BATTLE;
            case "enter collection":
                return RequestType.ENTER_COLLECTION;
            case "enter shop":
                return RequestType.ENTER_SHOP;
            case "help":
                return RequestType.HELP;
            case "exit":
                return RequestType.EXIT;
            case "save":
                return RequestType.SAVE;
            case "logout":
                return RequestType.LOGOUT;
            case "Start multiplayer game deathmatch":
                return RequestType.DEATH_MATCH;
            case "Start multiplayer game captureflag1":
                return RequestType.CAPTURE_FLAG1;
            case "Start multiplayer game captureflag2":
                return RequestType.CAPTURE_FLAG2;
            case "single player":           //CHANGED!!!
                return RequestType.SINGLE_PLAYER;
            case "multi player":            //CHANGED!!!
                return RequestType.MULTI_PLAYER;
            case "story mode":
                return RequestType.STORY_GAME;
            case "custom game":
                return RequestType.CUSTOM_GAME;
            case "show match histories":
                return RequestType.SHOW_MATCH_HISTORY;
            default:
                return RequestType.INVALID_COMMAND;
        }
    }

    public String getSecondPlayerUsername() {
        return command.split(" ")[2];
    }

    public int getNumberOfFlags() {
        return Integer.parseInt(command.split(" ")[4]);
    }

    public int getStoryModeLevel() {
        switch (command) {
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            default:
                view.printError(ErrorType.INVALID_COMMAND);
                return -1;
        }
    }

}