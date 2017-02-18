package sample.GUI;
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
    private FXMLLoader loader;
    private GridPane root;
    private static Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Load the fxml and controller
        loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        root = loader.load();
        controller = loader.<Controller>getController();

        //Create a main scene
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenBounds.getWidth() * MAIN_SCREEN_H_SCALE, screenBounds.getHeight() * MAIN_SCREEN_W_SCALE, true, SceneAntialiasing.BALANCED);
        scene.getStylesheets().add(getClass().getResource("css/main.css").toExternalForm());

        //Show a primary stage
        primaryStage.setTitle("Xbinpacking");
        primaryStage.setScene(scene);
        primaryStage.show();

        controller.test();
        //Manager manager = new Manager(controller);
        //manager.run();
        //MoleculeSampleApp msa = new MoleculeSampleApp(controller);
        //msa.start(controller);
    }

    public static void main(String[] args) {
        launch(args);

        // run();
    }



}


