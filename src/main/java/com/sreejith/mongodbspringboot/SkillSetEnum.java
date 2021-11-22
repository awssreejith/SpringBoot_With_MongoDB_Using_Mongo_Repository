package com.sreejith.mongodbspringboot;

public enum SkillSetEnum {

    BAT("Batsman"),
    BOW("Bowler"),
    ALL("AllRounder"),
    WKT("WicketKeeper");

    public final String label;

    private SkillSetEnum(String label) {
        this.label = label;
    }

    public static SkillSetEnum valueOfLabel(String label) {
        for (SkillSetEnum e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }

}
