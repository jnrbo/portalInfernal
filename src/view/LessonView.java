package view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.ClassRoom;
import model.Course;
import model.Lesson;
import model.User;
import repository.ClassRoomRepository;
import repository.LessonRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by junior and ramon.
 */
public class LessonView extends Application {


    public static final Font OPEN_SANS_LABEL = new Font("Open Sans", 11);

    public static void main(String[] args) {
        launch(args);
    }

    private User logged;

    LessonView(User logged) {
        this.logged = logged;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Agendar aula");
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
        courseLabelBox.getChildren().add(ViewUtils.centeredLabel("Sala"));
        grid.add(courseLabelBox, 0, 2);

        ArrayList<ClassRoom> classRoomArrayList = classRoomRepository.findAllBy("", 0);
        ComboBox<ClassRoom> classRoomComboBox = new ComboBox<>(FXCollections.observableArrayList(classRoomArrayList));
        grid.add(classRoomComboBox, 0, 3);


        VBox dateLabellBox = new VBox(10);
        dateLabellBox.getChildren().add(ViewUtils.centeredLabel("Data"));
        grid.add(dateLabellBox, 0, 4);

        DatePicker datePicker = new DatePicker();
        String pattern = "yyyy-MM-dd";
        datePicker.setPromptText(pattern.toLowerCase());
        datePicker.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
        grid.add(datePicker, 0, 5);

        Button save = new Button("Agendar");
        save.setTextAlignment(TextAlignment.CENTER);
        save.autosize();
        VBox saveBox = new VBox(10);
        saveBox.setFillWidth(true);
        saveBox.setAlignment(Pos.CENTER);
        saveBox.getChildren().add(save);
        grid.add(saveBox, 0, 7);

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LocalDate localDate = datePicker.getValue();
                Date date = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                lessonRepository.save(new Lesson(classRoomComboBox.getValue(), date));
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

    }

    private LessonRepository lessonRepository = new LessonRepository();
    private ClassRoomRepository classRoomRepository = new ClassRoomRepository();
}
