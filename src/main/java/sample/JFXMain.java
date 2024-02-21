package sample;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class JFXMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(new Scene(createRoot(), 300, 250));
        primaryStage.show();
    }

    private static Pane createRoot() {

        Circle circle = new Circle(100, 100, 50, Color.GREEN);
        Group group = new Group(circle);

        TextField fieldR = new TextField("50");

        circle.radiusProperty().bind(bindTextToDouble(fieldR.textProperty()));

        Button button = new Button();
        button.setText("Draw");
        button.setOnAction(e -> {
            System.out.printf("Draw circle!%n");
        });

        return new VBox(30, button, fieldR, group);
    }

    private static DoubleBinding bindTextToDouble(StringProperty stringProperty) {
        return Bindings.createDoubleBinding(() -> {
                    try {
                        return Double.parseDouble(stringProperty.get());
                    } catch (NumberFormatException e) {
                        return 5.0;
                    }
                },
                stringProperty);
    }
}