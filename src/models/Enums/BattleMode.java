package models.Enums;

public enum BattleMode {
    DEATH_MATCH("DEATH MATCH"),
    CAPTURE_FLAG_1("SAVE FLAG"),
    CAPTURE_FLAG_2("CAPTURE FLAG");

    BattleMode(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }

}
