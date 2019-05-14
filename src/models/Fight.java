package models;

import models.Enums.ErrorType;

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

    default ErrorType castAttack(Card src, Card dest) {
        if (src.isInAttackRange(src.getMyCell(), dest.getMyCell())) {
            decreaseHP(src.getAP(), true, dest);
            src.setAttackAvailable(false);
            if (dest.isInAttackRange(dest.getMyCell(), src.getMyCell())) {
                src.setAttackAvailable(false);
                decreaseHP(dest.getAP(), true, src);
            }
            return ErrorType.NO_ERROR;
        }
        return ErrorType.DEST_IS_UNAVAILABLE_FOR_ATTACK;
    }

}
