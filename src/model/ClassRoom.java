package model;

import java.util.Date;

import model.enums.ClassRoomType;

/**
 * Created by junior and ramon.
 */
public class ClassRoom extends Entity {

    private int number;
    private ClassRoomType type;

    public ClassRoom(int number, ClassRoomType type) {
        super();
        this.type = type;
        this.number = number;
    }

    public ClassRoom(int id, int number, ClassRoomType type, Date createdOn) {
        super(id, createdOn);
        this.type = type;
        this.number = number;
    }

    public ClassRoomType getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return getType().getLabel() + ": " + number;
    }
}
