package BinPacking.StartApplication;
import BinPacking.View.Controller;
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
    private static Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/BinPacking/View/ui.fxml"));

        GridPane root = loader.load();
        Scene scene = createScene(root);
        scene.getStylesheets().add(getClass().getResource("/BinPacking/View/css/main.css").toExternalForm());
        setStage(primaryStage,scene);

        controller = loader.getController();
    }

    private Scene createScene(GridPane root){
        final double MAIN_SCREEN_W_SCALE = 0.5;
        final double MAIN_SCREEN_H_SCALE = 0.5;
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        return new Scene(root, screenBounds.getWidth() * MAIN_SCREEN_H_SCALE, screenBounds.getHeight() * MAIN_SCREEN_W_SCALE, true, SceneAntialiasing.BALANCED);
    }

    private void setStage(Stage primaryStage, Scene scene){
        primaryStage.setTitle("XBinPacking");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Shutdown the thread executor in case of closing the application.
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


