package service;

import model.Course;
import model.Message;
import model.User;
import repository.CourseRepository;
import repository.MessageRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by ramon on 01/10/16.
 */
public class MessageService {

    public void sendMessage(String text, int courseId, Set<Integer> ids) {
        Course course  = courseRepository.find(courseId);
        for (int id : ids ) {
            User student = userRepository.find(id);
            messageRepository.save(new Message("", student, course));
        }
    }

    public String readMessage(int student, int course) {
        ArrayList<Message> wantedMessages = new ArrayList<>();
        ArrayList<Message> allMessages = messageRepository.findAllBy("student_id", student);
        for (Message message : allMessages) {
            if (message.getCourse().getId() == course) {
                wantedMessages.add(message);
            }
        }
        return wantedMessages.get(wantedMessages.size() -1).getMessageText();
    }

    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private CourseRepository courseRepository;
}
