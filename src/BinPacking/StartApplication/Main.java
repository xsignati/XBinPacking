package BinPacking.StartApplication;
import BinPacking.UI.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Created by Xsignati on 02.02.2017.
 */
public class Main extends Application{
    public static final double MAIN_SCREEN_W_SCALE = 0.5;
    public static final double MAIN_SCREEN_H_SCALE = 0.5;
    private static Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Load the fxml and controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/BinPacking/UI/ui.fxml"));
        GridPane root = loader.load();
        controller = loader.getController();

        //Create a main scene
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenBounds.getWidth() * MAIN_SCREEN_H_SCALE, screenBounds.getHeight() * MAIN_SCREEN_W_SCALE, true, SceneAntialiasing.BALANCED);
        scene.getStylesheets().add(getClass().getResource("/BinPacking/UI/css/main.css").toExternalForm());

        //Show a primary stage
        primaryStage.setTitle("XBinPacking");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Shutdown the thread executor in case of closing the application
     */
    @Override
    public void stop() {
        if (controller.getLoaderExecutor() != null) {
            controller.getLoaderExecutor().shutdown();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


