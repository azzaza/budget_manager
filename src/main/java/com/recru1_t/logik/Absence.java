package com.recru1_t.logik;

public enum Absence {
    HOLIDAY("Feiertag"),
    VACATION("Urlaub"),
    SICKNES("Krank"),
    SKIP("Tag verpassen"),
    NONE("");

    private String description;

    Absence(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
