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
import model.*;
import repository.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by junior and ramon.
 */
public class StudentAttendanceView extends Application {

    public static final Font OPEN_SANS_LABEL = new Font("Open Sans", 11);
    private User logged;
    private Course course;

    public static void main(String[] args) {
        launch(args);
    }


    StudentAttendanceView(User logged, Course course) {
        this.logged = logged;
        this.course = course;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Faltas");
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

        ArrayList<Attendance> attendanceArrayList = attendanceRepository.findAllBy("user_id", logged.getId());

        int absent = 0;
        for (Attendance attendance : attendanceArrayList) {
            if (attendance.isAttended()) {
                absent++;
            }
        }

        VBox grade1LabelBox = new VBox(10);
        grade1LabelBox.getChildren().add(ViewUtils.centeredLabel("Faltas: " + absent));
        grid.add(grade1LabelBox, 0, 2);

        Button back = new Button("Voltar");
        back.setTextAlignment(TextAlignment.CENTER);
        back.autosize();
        VBox backBox = new VBox(10);
        backBox.setFillWidth(true);
        backBox.setAlignment(Pos.CENTER);
        backBox.getChildren().add(back);
        grid.add(backBox, 0, 8);

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
    }

    private AttendanceRepository attendanceRepository = new AttendanceRepository();
}
