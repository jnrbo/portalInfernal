package model;

import java.util.Date;

/**
 * Created by junior and ramon.
 */
public abstract class Entity {

    private Integer id;
    private Date createdOn;

    protected Entity() {
        this.createdOn = new Date();
    }

    protected Entity(int id, Date createdOn) {
        this.id = id;
        this.createdOn = createdOn;
    }

    public int getId() {
        return id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public boolean hasId() {
        return id != null;
    }
}