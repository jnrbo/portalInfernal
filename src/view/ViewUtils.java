package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * Created by junior and ramon.
 */
public class ViewUtils {

    public static final Font OPEN_SANS_LABEL = new Font("Open Sans", 11);

    public static Label centeredLabel(String text) {
        Label label = new Label(text);
        label.setFont(OPEN_SANS_LABEL);
        label.setTextAlignment(TextAlignment.CENTER);
        return label;
    }

    public static Button centeredButton(String text) {
        Button button = new Button(text);
        button.setTextAlignment(TextAlignment.CENTER);
        button.autosize();
        return button;
    }
}
