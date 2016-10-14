package model.enums;

/**
* Created by junior and ramon.
 */
public enum ClassRoomType implements LabeledEnum{

    ROOM("Sala de aula"),
    LABORATORY("Laboratório");

    private String label;

    ClassRoomType(String label) {
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
