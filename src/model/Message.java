package model;

import java.util.Date;

/**
 * Created by junior and ramon.
 */
public class Message extends Entity {

    private String messageText;
    private User student;
    private Course course;

    public Message(String messageText, User student, Course course) {
        super();
        this.messageText = messageText;
        this.student = student;
        this.course = course;
    }

    public Message(int id, String messageText, User student, Course course, Date createdOn) {
        super(id, createdOn);
        this.messageText = messageText;
        this.student = student;
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public String getMessageText() {
        return messageText;
    }

    public User getStudent() {
        return student;
    }
}