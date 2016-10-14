import model.*;
import model.enums.Bimester;
import model.enums.ClassRoomType;
import model.enums.UserType;
import repository.*;
import service.UserService;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by junior on 26/09/16.
 */
public class Tester {

    public static void main(String[] args) {
        clear();
        testUser();
        testCourse();
        testCourseStudent();
        testClassRoom();
        testLesson();
        testMessage();
        testGrade();
        testAttendance();
    }

    private static void testUser() {
        User admin = new User("Junior", "senha", UserType.ADMIN);
        userRepository.save(admin);
        User savedAdmin = userRepository.find(1);

        User teacher = new User("Ramon", "senha", UserType.TEACHER);
        userRepository.save(teacher);
        User savedTeacher = userRepository.find(2);

        User student = new User("Jean", "senha", UserType.STUDENT);
        userRepository.save(student);
        User savedStudent = userRepository.find(3);

        if (savedAdmin == null || savedTeacher == null || savedStudent == null) {
            throw new RuntimeException("usuario null");
        }
    }

    private static void testCourse() {
        Course math = new Course("Matematica", new Teacher(userRepository.find(2)));
        courseRepository.save(math);
        Course savedMath = courseRepository.find(1);

        Course bale = new Course("Balé Classico", new Teacher(userRepository.find(2)));
        courseRepository.save(bale);
        Course savedBale = courseRepository.find(2);

        if (savedMath == null || savedBale == null) {
            throw new RuntimeException("Curso null");
        }
    }

    private static void testCourseStudent() {
        Course course = courseRepository.find(1);
        Student student = new Student(userRepository.find(3));

        courseStudentRepository.save(new CourseStudent(course, student));

        CourseStudent saved = courseStudentRepository.find(1);

        if (saved == null) {
            throw new RuntimeException("Curso student null");
        }

    }

    private static void testClassRoom() {
        ClassRoom classRoom1 = new ClassRoom(102, ClassRoomType.LABORATORY);
        classRoomRepository.save(classRoom1);
        ClassRoom saved1 = classRoomRepository.find(1);

        ClassRoom classRoom2 = new ClassRoom(105, ClassRoomType.ROOM);
        classRoomRepository.save(classRoom2);
        ClassRoom saved2 = classRoomRepository.find(2);

        if (saved1 == null || saved2 == null) {
            throw new RuntimeException("Sala null");
        }
    }

    private static void testLesson() {
        ClassRoom classRoom = classRoomRepository.find(1);
        Lesson lesson = new Lesson(classRoom, new Date());

        lessonRepository.save(lesson);

        Lesson saved = lessonRepository.find(1);

        if (saved == null) {
            throw new RuntimeException("Aula null");
        }
    }

    private static void testMessage() {
        Course course = courseRepository.find(1);
        Student student = new Student(userRepository.find(1));

        Message message = new Message("FODEU", student, course);
        messageRepository.save(message);

        Message saved = messageRepository.find(1);

        if (saved == null) {
            throw new RuntimeException("Mensagem null");
        }
    }

    private static void testGrade() {
        Course course = courseRepository.find(1);
        Student student = new Student(userRepository.find(1));

        Grade grade = new Grade(75, student, course, Bimester.FIRST);
        gradeRepository.save(grade);

        Grade saved = gradeRepository.find(1);


        if (saved == null) {
            throw new RuntimeException("Nota null");
        }
    }

    private static void testAttendance() {

        Course course = courseRepository.find(1);
        Student student = new Student(userRepository.find(1));

        Attendance attendance = new Attendance(true, student, course);


        attendanceRepository.save(attendance);


        Attendance saved = attendanceRepository.find(1);

        if (saved == null) {
            throw new RuntimeException("Presenca null");
        }
    }

    private static void clear() {
        String queries[] = {
                "SET FOREIGN_KEY_CHECKS = 0;",
                "DROP DATABASE `portal_infernal`",
                "CREATE SCHEMA `portal_infernal` ;",
                CREATE_USER,
                CREATE_COURSE,
                CREATE_ATTENDANCE,
                CREATE_CLASS_ROOM,
                CREATE_GRADE,
                CREATE_LESSON,
                CREATE_MESSAGE,
                CREATE_COURSE_LESSON,
                CREATE_LESSON_STUDENT,
                CREATE_COURSE_STUDENT,
                "SET FOREIGN_KEY_CHECKS = 0;"
        };

        try {
            Connection connection = ConnectionFactory.connect();
            for (String query : queries) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.execute(query);
                statement.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static CourseStudentRepository courseStudentRepository = new CourseStudentRepository();
    private static AttendanceRepository attendanceRepository = new AttendanceRepository();
    private static GradeRepository gradeRepository = new GradeRepository();
    private static MessageRepository messageRepository = new MessageRepository();
    private static LessonRepository lessonRepository = new LessonRepository();
    private static ClassRoomRepository classRoomRepository = new ClassRoomRepository();
    private static UserRepository userRepository = new UserRepository();
    private static CourseRepository courseRepository = new CourseRepository();


    private static final String CREATE_USER = "CREATE TABLE `portal_infernal`.`User` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `created_on` DATETIME NOT NULL,\n" +
            "  `name` VARCHAR(45) NOT NULL,\n" +
            "  `password` VARCHAR(45) NOT NULL,\n" +
            "  `type` VARCHAR(45) NOT NULL,\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  UNIQUE INDEX `id_UNIQUE` (`id` ASC));\n";

    private static final String CREATE_COURSE = "CREATE TABLE `portal_infernal`.`Course` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `created_on` DATETIME NOT NULL,\n" +
            "  `name` VARCHAR(45) NOT NULL,\n" +
            "  `teacher_id` INT NOT NULL,\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  UNIQUE INDEX `id_UNIQUE` (`id` ASC),\n" +
            "  CONSTRAINT `teacher`\n" +
            "    FOREIGN KEY (`id`)\n" +
            "    REFERENCES `portal_infernal`.`User` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION);\n";

    private static final String CREATE_ATTENDANCE = "CREATE TABLE `portal_infernal`.`Attendance` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `created_on` DATETIME NOT NULL,\n" +
            "  `attended` VARCHAR(45) NOT NULL,\n" +
            "  `course_id` INT NOT NULL,\n" +
            "  `student_id` INT NOT NULL,\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  UNIQUE INDEX `id_UNIQUE` (`id` ASC),\n" +
            "  CONSTRAINT `Student`\n" +
            "    FOREIGN KEY (`id`)\n" +
            "    REFERENCES `portal_infernal`.`User` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT `Course`\n" +
            "    FOREIGN KEY (`id`)\n" +
            "    REFERENCES `portal_infernal`.`Course` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION);";

    private static final String CREATE_CLASS_ROOM = "CREATE TABLE `portal_infernal`.`Classroom` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `created_on` DATETIME NOT NULL,\n" +
            "  `number` INT NOT NULL,\n" +
            "  `type` VARCHAR(45) NOT NULL,\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  UNIQUE INDEX `id_UNIQUE` (`id` ASC));\n";

    private static final String CREATE_GRADE = "CREATE TABLE `portal_infernal`.`Grade` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `created_on` DATETIME NOT NULL,\n" +
            "  `student_id` INT NOT NULL,\n" +
            "  `course_id` INT NOT NULL,\n" +
            "  `score` INT NOT NULL,\n" +
            "  `bimester` VARCHAR(45) NOT NULL,\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  UNIQUE INDEX `id_UNIQUE` (`id` ASC),\n" +
            "  CONSTRAINT `Course_Grade`\n" +
            "    FOREIGN KEY (`id`)\n" +
            "    REFERENCES `portal_infernal`.`Course` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT `Student_Grade`\n" +
            "    FOREIGN KEY (`id`)\n" +
            "    REFERENCES `portal_infernal`.`User` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION);\n";

    private static final String CREATE_LESSON = "CREATE TABLE `portal_infernal`.`Lesson` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `created_on` DATETIME NOT NULL,\n" +
            "  `classroom_id` INT NOT NULL,\n" +
            "  `lesson_date` DATETIME NOT NULL,\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  UNIQUE INDEX `id_UNIQUE` (`id` ASC),\n" +
            "  CONSTRAINT `ClassRoom_Lesson`\n" +
            "    FOREIGN KEY (`id`)\n" +
            "    REFERENCES `portal_infernal`.`Classroom` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION);\n";

    private static final String CREATE_MESSAGE = "CREATE TABLE `portal_infernal`.`Message` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `created_on` DATETIME NOT NULL,\n" +
            "  `message_text` VARCHAR(255) NOT NULL,\n" +
            "  `student_id` INT NOT NULL,\n" +
            "  `course_id` INT NOT NULL,\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  UNIQUE INDEX `id_UNIQUE` (`id` ASC),\n" +
            "  CONSTRAINT `Student_Message`\n" +
            "    FOREIGN KEY (`id`)\n" +
            "    REFERENCES `portal_infernal`.`User` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT `Course_Message`\n" +
            "    FOREIGN KEY (`id`)\n" +
            "    REFERENCES `portal_infernal`.`Course` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION);\n";

    private static final String CREATE_COURSE_LESSON = "CREATE TABLE `portal_infernal`.`Course_Lesson` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `course_id` INT NOT NULL,\n" +
            "  `lesson_id` INT NOT NULL,\n" +
            "  `created_on` DATETIME NOT NULL,\n" +
            "  PRIMARY KEY (`course_id`, `lesson_id`, `id`),\n" +
            "  INDEX `CourseLesson_Lesson_idx` (`lesson_id` ASC),\n" +
            "  UNIQUE INDEX `id_UNIQUE` (`id` ASC),\n" +
            "  CONSTRAINT `CourseLesson_Course`\n" +
            "    FOREIGN KEY (`course_id`)\n" +
            "    REFERENCES `portal_infernal`.`Course` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT `CourseLesson_Lesson`\n" +
            "    FOREIGN KEY (`lesson_id`)\n" +
            "    REFERENCES `portal_infernal`.`Lesson` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION);\n";


    private static final String CREATE_LESSON_STUDENT = "CREATE TABLE `portal_infernal`.`Lesson_Student` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `student_id` INT NOT NULL,\n" +
            "  `lesson_id` INT NOT NULL,\n" +
            "  `created_on` DATETIME NOT NULL,\n" +
            "  PRIMARY KEY (`student_id`, `lesson_id`, `id`),\n" +
            "  INDEX `LessonStudent_Lesson_idx` (`lesson_id` ASC),\n" +
            "  UNIQUE INDEX `id_UNIQUE` (`id` ASC),\n" +
            "  CONSTRAINT `LessonStudent_Student`\n" +
            "    FOREIGN KEY (`student_id`)\n" +
            "    REFERENCES `portal_infernal`.`User` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT `LessonStudent_Lesson`\n" +
            "    FOREIGN KEY (`lesson_id`)\n" +
            "    REFERENCES `portal_infernal`.`Lesson` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION);\n";

    private static final String CREATE_COURSE_STUDENT = "CREATE TABLE `portal_infernal`.`Course_Student` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `student_id` INT NOT NULL,\n" +
            "  `course_id` INT NOT NULL,\n" +
            "  `created_on` DATETIME NOT NULL,\n" +
            "  PRIMARY KEY (`student_id`, `course_id`, `id`),\n" +
            "  INDEX `CourseStudent_Course_idx` (`course_id` ASC),\n" +
            "  INDEX `CourseStudent_Student_idx` (`student_id` ASC),\n" +
            "  UNIQUE INDEX `id_UNIQUE` (`id` ASC),\n" +
            "  CONSTRAINT `CourseStudent_Student`\n" +
            "    FOREIGN KEY (`student_id`)\n" +
            "    REFERENCES `portal_infernal`.`User` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT `CourseStudent_Course`\n" +
            "    FOREIGN KEY (`course_id`)\n" +
            "    REFERENCES `portal_infernal`.`Course` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION);\n";

}
