package model.enums;

/**
 * Created by junior and ramon.
 */
public enum UserType implements LabeledEnum {

    ADMIN("Administrador"),
    STUDENT("Estudante"),
    TEACHER("Professor");

    private String label;

    UserType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
