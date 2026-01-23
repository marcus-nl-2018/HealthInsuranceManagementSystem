package com.health.insurance.util;

import com.health.insurance.exception.NavigationException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import static com.health.insurance.util.AlertUtil.showAlert;

public class NavigationUtil {

    // Standard app window size
    private static final double MIN_WIDTH  = 780;
    private static final double MIN_HEIGHT = 580;
    private static final double MAX_WIDTH  = 780;
    private static final double MAX_HEIGHT = 700;

    // Prevent instantiation
    private NavigationUtil() {}

    public static void goTo(Stage stage, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    NavigationUtil.class.getResource(fxmlPath)
            );

            Scene scene = new Scene(loader.load());

            stage.setScene(scene);

            stage.setMinWidth(MIN_WIDTH);
            stage.setMinHeight(MIN_HEIGHT);
            stage.setMaxWidth(MAX_WIDTH);
            stage.setMaxHeight(MAX_HEIGHT);

            stage.hide();
            stage.show();

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error!", "Unable to load the Dashboard screen.");
            throw new NavigationException(
                    "Failed to navigate to FXML: " + fxmlPath,
                    e
            );
        }
    }
}
