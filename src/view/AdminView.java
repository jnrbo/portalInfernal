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

/**
 * Created by junior and ramon.
 */
public class AdminView extends Application {

    public static final Font OPEN_SANS_LABEL = new Font("Open Sans", 11);
    private User logged;

    public static void main(String[] args) {
        launch(args);
    }


    AdminView(User logged) {
        this.logged = logged;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Admin");
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

        Button addUser = new Button("Cadastrar Usuario");
        addUser.setTextAlignment(TextAlignment.CENTER);
        addUser.autosize();
        VBox addUserBox = new VBox(10);
        addUserBox.setFillWidth(true);
        addUserBox.setAlignment(Pos.CENTER);
        addUserBox.getChildren().add(addUser);
        grid.add(addUserBox, 0, 2);
        
        Button addCourse = new Button("Cadastrar Disciplina");
        addCourse.setTextAlignment(TextAlignment.CENTER);
        addCourse.autosize();
        VBox addCourseBox = new VBox(10);
        addCourseBox.setFillWidth(true);
        addCourseBox.setAlignment(Pos.CENTER);
        addCourseBox.getChildren().add(addCourse);
        grid.add(addCourseBox, 0, 3);
        
        Button addClassroom = new Button("Cadastrar Sala de Aula");
        addClassroom.setTextAlignment(TextAlignment.CENTER);
        addClassroom.autosize();
        VBox addClassroomBox = new VBox(10);
        addClassroomBox.setFillWidth(true);
        addClassroomBox.setAlignment(Pos.CENTER);
        addClassroomBox.getChildren().add(addClassroom);
        grid.add(addClassroomBox, 0, 4);
        
        Button courseReport = new Button("Relatorio disciplina");
        courseReport.setTextAlignment(TextAlignment.CENTER);
        courseReport.autosize();
        VBox courseReportBox = new VBox(10);
        courseReportBox.setFillWidth(true);
        courseReportBox.setAlignment(Pos.CENTER);
        courseReportBox.getChildren().add(courseReport);
        grid.add(courseReportBox, 0, 5);

        addUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new AddUserView(logged).start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        addCourse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
//                    new AddCourseView(logged).start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        addClassroom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new AddClassroomView(logged).start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        courseReport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
 //                   new CourseReportView(logged).start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

//        ListView<Course> courseListView = new ListView<>();
//        courseListView.setItems(FXCollections.observableArrayList(coursesArrayList));
//        grid.add(courseListView, 0, 2);

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

    CourseRepository courseRepository = new CourseRepository();
}
