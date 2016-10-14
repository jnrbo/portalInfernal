package view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Course;
import model.User;
import repository.CourseRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static view.ViewUtils.centeredButton;

/**
 * Created by junior and ramon.
 */
public class TeacherView extends Application {

    public static final Font OPEN_SANS_LABEL = new Font("Open Sans", 11);
    private User logged;

    public static void main(String[] args) {
        launch(args);
    }


    TeacherView(User logged) {
        this.logged = logged;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Professor");
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

        VBox courseLabelBox = new VBox(10);
        courseLabelBox.getChildren().add(ViewUtils.centeredLabel("Disciplina"));
        grid.add(courseLabelBox, 0, 2);

        ArrayList<Course> coursesArrayList = courseRepository.findAllBy("teacher_id", 1);
        ComboBox<Course> courseComboBox = new ComboBox<>(FXCollections.observableArrayList(coursesArrayList));
        grid.add(courseComboBox, 0, 3);


        Button lesson = centeredButton("Cadastrar Aula");
        VBox courseClassBox = new VBox(10);
        courseClassBox.setFillWidth(true);
        courseClassBox.setAlignment(Pos.CENTER);
        courseClassBox.getChildren().add(lesson);
        grid.add(courseClassBox, 0, 5);

        Button sendMessage = centeredButton("Enviar recado");
        VBox sendMessageVBox = new VBox(10);
        sendMessageVBox.setFillWidth(true);
        sendMessageVBox.setAlignment(Pos.CENTER);
        sendMessageVBox.getChildren().add(sendMessage);
        grid.add(sendMessageVBox, 0, 6);

        Button attendance = centeredButton("Realizar chamada");
        VBox attendanceBox = new VBox(10);
        attendanceBox.setFillWidth(true);
        attendanceBox.setAlignment(Pos.CENTER);
        attendanceBox.getChildren().add(attendance);
        grid.add(attendanceBox, 0, 7);

        Button grade = centeredButton("Cadastrar notas");
        VBox gradeBox = new VBox(10);
        gradeBox.setFillWidth(true);
        gradeBox.setAlignment(Pos.CENTER);
        gradeBox.getChildren().add(grade);
        grid.add(gradeBox, 0, 8);


        lesson.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new LessonView(logged).start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        sendMessage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        attendance.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        grade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

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


//        ListView<Course> courseListView = new ListView<>();
//        courseListView.setItems(FXCollections.observableArrayList(coursesArrayList));
//        grid.add(courseListView, 0, 2);


    }

    CourseRepository courseRepository = new CourseRepository();
}
