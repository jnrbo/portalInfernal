package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;
import javafx.stage.Stage;
import model.User;
import service.UserService;


/**
 * Created by junior and ramon.
 */
public class LoginView extends Application {


    private static final Font OPEN_SANS_LABEL = new Font("Open Sans", 11);

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Login");
        primaryStage.show();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 150, 25));

        Scene scene = new Scene(grid, 1200, 720);
        primaryStage.setScene(scene);

        Text scenetitle = new Text("Bem vindo");
        scenetitle.setTextAlignment(TextAlignment.CENTER);
        scenetitle.setFont(Font.font("Open Sans", FontWeight.NORMAL, 20));

        VBox sceneTitleBox = new VBox(10);
        sceneTitleBox.setAlignment(Pos.CENTER);
        sceneTitleBox.getChildren().add(scenetitle);
        sceneTitleBox.setPadding(new Insets(0, 0, 30, 0));
        grid.add(sceneTitleBox, 0, 0);


        VBox userNameBox = new VBox(10);
        userNameBox.getChildren().add(ViewUtils.centeredLabel("Usuario"));
        grid.add(userNameBox, 0, 1);

        TextField userTextField = new TextField();
        userTextField.setAlignment(Pos.CENTER);
        grid.add(userTextField, 0, 2);

        VBox passwordBox = new VBox(10);
        passwordBox.setPadding(new Insets(10, 0, 0, 0));
        passwordBox.getChildren().add(ViewUtils.centeredLabel("Senha"));
        grid.add(passwordBox, 0, 3);

        PasswordField passwordField = new PasswordField();
        passwordField.setAlignment(Pos.CENTER);
        grid.add(passwordField, 0, 4);

        Button login = new Button("Acessar");
        login.setTextAlignment(TextAlignment.CENTER);
        login.autosize();
        VBox loginVBox = new VBox(10);
        loginVBox.setFillWidth(true);
        loginVBox.setAlignment(Pos.CENTER);
        loginVBox.getChildren().add(login);
        grid.add(loginVBox, 0, 6);

        Text result = new Text();
        result.setTextAlignment(TextAlignment.CENTER);
        VBox resultBox = new VBox();
        resultBox.setPadding(new Insets(20, 0, 20, 0));
        resultBox.setAlignment(Pos.CENTER);
        resultBox.getChildren().add(result);
        grid.add(resultBox, 0, 8);


        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String userId = userTextField.getText();
                String password = passwordField.getText();
                System.out.println("user:" + userId + " | pass:" + password);
                if (userId.isEmpty() || password.isEmpty()) {
                    error(result);
                    return;
                } else {

                    User logged = userService.login(Integer.valueOf(userId), password);
                    if (logged != null) {
                        success(result);
                        System.out.println("SUCESSO");
                        try {
                            switch (logged.getType()) {
                                case ADMIN:
                                    new AdminView(logged).start(primaryStage);
                                    break;
                                case TEACHER:
                                    new TeacherView(logged).start(primaryStage);
                                    break;
                                case STUDENT:
                                    new StudentView(logged).start(primaryStage);
                                    break;
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        error(result);
                    }
                }
            }
        });
    }

    private void success(Text text) {
        text.setFill(Paint.valueOf("#14dd0e"));
        text.setText("Carregando...");
    }

    private void error(Text text) {
        text.setFill(Paint.valueOf("#c13414"));
        text.setText("Dados inválidos!");
    }

    private UserService userService = new UserService();
}