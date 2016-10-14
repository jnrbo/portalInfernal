package model;

public class PermissionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PermissionException() {
        super("Permissão negada!");
    }
}
