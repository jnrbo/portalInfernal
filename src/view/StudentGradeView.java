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
public class StudentGradeView extends Application {

    public static final Font OPEN_SANS_LABEL = new Font("Open Sans", 11);
    private User logged;
    private Course course;

    public static void main(String[] args) {
        launch(args);
    }

    StudentGradeView(User logged, Course course) {
        this.logged = logged;
        this.course = course;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Notas");
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

        ArrayList<Grade> gradeArrayList = gradeRepository.findAllBy("user_id", 1);

        if (gradeArrayList != null && !gradeArrayList.isEmpty()) {
            VBox grade1LabelBox = new VBox(10);
            grade1LabelBox.getChildren().add(ViewUtils.centeredLabel("Nota 1 " + gradeArrayList.get(0)));
            grid.add(grade1LabelBox, 0, 2);

            VBox grade2LabelBox = new VBox(10);
            grade2LabelBox.getChildren().add(ViewUtils.centeredLabel("Nota 2 " + gradeArrayList.get(1)));
            grid.add(grade2LabelBox, 0, 3);

            VBox grade3LabelBox = new VBox(10);
            grade3LabelBox.getChildren().add(ViewUtils.centeredLabel("Nota 3 " + gradeArrayList.get(2)));
            grid.add(grade3LabelBox, 0, 4);

            VBox grade4LabelBox = new VBox(10);
            grade4LabelBox.getChildren().add(ViewUtils.centeredLabel("Nota 4 " + gradeArrayList.get(3)));
            grid.add(grade4LabelBox, 0, 5);
        } else {
            grid.add(ViewUtils.centeredLabel("Nenhuma nota cadastrada"), 0, 3);
        }


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

        grid.add(backBox, 0, 8);
    }

    GradeRepository gradeRepository = new GradeRepository();
}
