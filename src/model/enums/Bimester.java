package model.enums;

/**
* Created by junior and ramon.
 */
public enum Bimester implements LabeledEnum{

    FIRST("Primeiro bimestre"),
    SECOND("Segundo bimestre"),
    THIRD("Terceiro bimestre");

    private String label;

    Bimester(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
