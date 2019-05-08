package models;

public interface Fight {

    static void increaseHP(int num, Card card) {
        card.setHP(card.getHP() + num);
    }

    static void increaseAP(int num, Card card) {
        card.setAP(card.getAP() + num);
    }

    static void decreaseAP(int num, Card card) {
        card.setHP(card.getHP() - num);
        if (card.getAP() < 0)
            card.setAP(0);
    }

    static void decreaseHP(int num, boolean isAttack, Card card) {
        card.setHP(card.getHP() - num);
        // holy buff will work here if isAttack == true
    }

}
