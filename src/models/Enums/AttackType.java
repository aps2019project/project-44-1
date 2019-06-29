package models.Enums;

public enum AttackType {
    MELEE,
    RANGED,
    HYBRID;

    public String getNameType() {
        if (this == MELEE) {
            return "Melee";
        } else if (this == HYBRID) {
            return "Hybrid";
        } else {
            return "Ranged";
        }
    }

}
