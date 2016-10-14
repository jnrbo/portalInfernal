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
import javafx.scene.control.TextField;
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
import model.enums.*;
import repository.*;
import repository.LessonRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static view.ViewUtils.centeredButton;

/**
 * Created by junior and ramon.
 */
public class AddUserView extends Application {


    public static final Font OPEN_SANS_LABEL = new Font("Open Sans", 11);

    public static void main(String[] args) {
        launch(args);
    }

    private User logged;

    AddUserView(User logged) {
        this.logged = logged;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Cadastrar Usuario");
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


        VBox nameLabelBox = new VBox(10);
        nameLabelBox.getChildren().add(ViewUtils.centeredLabel("Nome:"));
        grid.add(nameLabelBox, 0, 2);

        TextField nameTextField = new TextField();
        nameTextField.setAlignment(Pos.CENTER);
        grid.add(nameTextField, 0, 3);


        VBox passwordLabelBox = new VBox(10);
        passwordLabelBox.getChildren().add(ViewUtils.centeredLabel("Senha:"));
        grid.add(passwordLabelBox, 0, 4);

        TextField passwordField = new TextField();
        passwordField.setAlignment(Pos.CENTER);
        grid.add(passwordField, 0, 5);


        VBox typeLabelBox = new VBox(10);
        typeLabelBox.getChildren().add(ViewUtils.centeredLabel("Tipo:"));
        grid.add(typeLabelBox, 0, 6);

        List<UserType> types = new ArrayList<>();
        types.add(UserType.ADMIN);
        types.add(UserType.STUDENT);
        types.add(UserType.TEACHER);

        ComboBox<UserType> userTypeComboBox = new ComboBox<>(FXCollections.observableArrayList(types));
        grid.add(userTypeComboBox, 0, 7);


        Button save = centeredButton("Salvar");
        VBox saveBox = new VBox(10);
        saveBox.setFillWidth(true);
        saveBox.setAlignment(Pos.CENTER);
        saveBox.getChildren().add(save);
        grid.add(saveBox, 0, 9);

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                userRepository.save(new User(nameTextField.getText(), passwordField.getText(), userTypeComboBox.getValue()));
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

    private UserRepository userRepository = new UserRepository();
}
