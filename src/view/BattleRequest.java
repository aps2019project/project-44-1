package view;


public class BattleRequest extends Request {

    @Override
    public RequestType getType() {
        if (command.equals("game info"))
            return RequestType.SHOW_GAME_INFO;
        else if (command.equals("show my minions"))
            return RequestType.SHOW_MY_MINIONS;
        else if (command.equals("show opponent minions"))
            return RequestType.SHOW_OPPONENT_MINIONS;
        else if (command.matches("show card info \\d+"))
            return RequestType.SHOW_CARD_INFO;
        else if (command.matches("select \\d+"))
        /** this condition is checked for selecting normal cards and items*/
            return RequestType.SELECT_CARD;
        else if (command.matches("move to \\(\\[\\d+],\\[\\d+]\\)"))
            return RequestType.MOVE_CARD;
        else if (command.matches("attack \\d+"))
            return RequestType.ATTACK_TO_OPPONENT;
        else if (command.matches("attack combo \\d+ \\d+ (\\d+)+"))
            return RequestType.COMBO_ATTACK;
        else if (command.matches("use special power \\(\\d+,\\d+\\)"))
            return RequestType.USE_SPECIAL_POWER;
        else if (command.equals("show hand"))
            return RequestType.SHOW_MY_HAND;
        else if (command.matches("insert \\w+ in \\(\\d+,\\d+\\)"))
            return RequestType.INSERT_CARD_FROM_HAND_TO_MAP;
        else if (command.equals("end turn"))
            return RequestType.END_TURN;
        else if (command.equals("show collectables"))
            return RequestType.SHOW_COLLECTABLES;
        else if (command.equals("show info"))
            return RequestType.SHOW_SELECTED_ITEM_INFO;
        else if (command.matches("use \\[location \\d+,\\d+]"))
            return RequestType.USE_COllectable_ITEM;
        else if (command.equals("show next card"))
            return RequestType.SHOW_NEXT_CARD;
        else if (command.equals("enter graveyard"))
            return RequestType.ENTER_GRAVEYARD;
        else if (command.matches("show info \\d+"))
            return RequestType.SHOW_CARD_INFO_IN_GRAVEYARD;
        else if (command.equals("show cards"))
            return RequestType.SHOW_ALL_CARDS_IN_GRAVEYARD;
        else if (command.equals("help"))
            return RequestType.HELP;
        else if (command.equals("end game"))
            return RequestType.END_GAME;
        else if (command.equals("exit"))
            return RequestType.EXIT;
        else if (command.equals("show menu"))
            return RequestType.SHOW_MENU;
        else {
            return RequestType.INVALID_COMMAND;
        }
    }

    public int getCardID() {
        switch (getType()) {
            case SHOW_CARD_INFO:
                return Integer.parseInt(command.split(" ")[3]);
            case SELECT_CARD:
                return Integer.parseInt(command.split(" ")[1]);
            case ATTACK_TO_OPPONENT:
                return Integer.parseInt(command.split("")[1]);
            case COMBO_ATTACK:
                // TODO: 05/05/2019
            case SHOW_CARD_INFO_IN_GRAVEYARD:
                return Integer.parseInt(command.split(" ")[2]);
            default:
                return -1;
        }
    }

    public int getLocationX() {
        switch (getType()) {
            case MOVE_CARD:
                return Integer.parseInt(command.replace("move to ([", "").
                        replace("], [", " ").
                        replace("])", "").split(" ")[0]);
            case USE_SPECIAL_POWER:
                return Integer.parseInt(command.replace("use special power (", "").
                        replace(",", " ").
                        replace(")", "").split(" ")[0]);
            case INSERT_CARD_FROM_HAND_TO_MAP:
                return Integer.parseInt(command.replace("insert \\w+ in (", "").
                        replace(",", " ").
                        replace(")", "").split(" ")[0]);
            case USE_COllectable_ITEM:
                return Integer.parseInt(command.replace("use [location ", "").
                        replace(",", " ").
                        replace("]", "").split(" ")[0]);
            default:
                return -1;

        }
    }

    public int getLocationY() {
        switch (getType()) {
            case MOVE_CARD:
                return Integer.parseInt(command.replace("move to ([", "")
                        .replace("], [", " ")
                        .replace("])", "").split(" ")[1]);
            case USE_SPECIAL_POWER:
                return Integer.parseInt(command.replace("use special power (", "").
                        replace(",", " ").
                        replace(")", "").split(" ")[1]);
            case INSERT_CARD_FROM_HAND_TO_MAP:
                return Integer.parseInt(command.replace("insert \\w+ in (", "").
                        replace(",", " ").
                        replace(")", "").split(" ")[1]);

            case USE_COllectable_ITEM:
                return Integer.parseInt(command.replace("use [location ", "").
                        replace(",", " ").
                        replace("]", "").split(" ")[1]);
            default:
                return -1;
        }
    }

    public String getCardName() {
        if (getType().equals(RequestType.INSERT_CARD_FROM_HAND_TO_MAP)) {
            return command.split(" ")[1];
        } else {
            return null;
        }
    }
}
