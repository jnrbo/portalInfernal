package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.AttrsValues;
import model.Course;
import model.Message;
import model.Student;

/**
 * Created by junior and ramon.
 */

public class MessageRepository extends AbstractRepository<Message> {

    @Override
    public void save(Message message) {
        AttrsValues data = new AttrsValues();
        data.add("message_text", message.getMessageText());
        data.add("course_id", message.getCourse().getId());
        data.add("student_id", message.getStudent().getId());
        create("Message", data);
    }

    @Override
    public Message find(int id) {
        ResultSet result = select("Message", "id = ?", id);
        try {
            if (result.next()) {
                Course course = courseRepository.find(result.getInt("course_id"));
                Student student = new Student(userRepository.find(result.getInt("student_id")));
                return new Message(id(result), result.getString("message_text"), student, course, createdOn(result));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Message> findAllBy(String attributeName, int attributeValue) {
        ArrayList<Message> messages = new ArrayList<>();
        ResultSet result = select("Message", attributeName + " = ?", attributeValue);
        try {
            while (result.next()) {
                Course course = courseRepository.find(result.getInt("course_id"));
                Student student = new Student(userRepository.find(result.getInt("student_id")));
                messages.add(new Message(id(result), result.getString("message_text"), student, course, createdOn(result)));
            }
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    private CourseRepository courseRepository = new CourseRepository();
    private UserRepository userRepository = new UserRepository();
}
