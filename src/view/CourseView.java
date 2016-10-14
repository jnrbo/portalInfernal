package view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Course;
import model.CourseStudent;
import model.User;
import repository.CourseRepository;
import repository.CourseStudentRepository;

import java.util.ArrayList;

/**
 * Created by junior and ramon.
 */
public class CourseView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    User logged;

    CourseView(User logged) {
        this.logged = logged;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Estudante");
        primaryStage.show();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 150, 25));

        Scene scene = new Scene(grid, 1200, 720);
        primaryStage.setScene(scene);

        Text scenetitle = new Text("Bem vindo, " + logged.getType().getLabel() + " " + logged.getName());
        scenetitle.setTextAlignment(TextAlignment.CENTER);
        scenetitle.setFont(Font.font("Open Sans", FontWeight.NORMAL, 16));
        grid.add(scenetitle, 0, 1);


        ArrayList<CourseStudent> courseStudents =  courseStudentRepository.findAllBy("student_id", logged.getId());
        ArrayList<Course> courses = new ArrayList<>();
        for (CourseStudent courseStudent : courseStudents) {
            courses.add(courseStudent.getCourse());
        }

        ListView<Course> courseListView = new ListView<>();
        courseListView.setItems(FXCollections.observableArrayList(courses));
        grid.add(courseListView, 0, 2);


        Button back = new Button("Loggout");
        back.setTextAlignment(TextAlignment.CENTER);
        back.autosize();
        VBox backBox = new VBox(10);
        backBox.setFillWidth(true);
        backBox.setAlignment(Pos.CENTER);
        backBox.getChildren().add(back);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new LoginView().start(primaryStage);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        grid.add(backBox, 0, 12);
    }

    CourseStudentRepository courseStudentRepository = new CourseStudentRepository();

    private CourseRepository courseRepository = new CourseRepository();
}
