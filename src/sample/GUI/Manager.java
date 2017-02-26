package sample.GUI;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;

import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Created by Xsignati on 02.02.2017.
 */
public class Manager {
    private Controller controller;

    public Manager(Controller controller) {
        this.controller = controller;
    }

    public void run(){
        //Get the display subscene
        SubScene subscene = controller.getBinScene();


        //Bind display subscene size to main scene width and height

//        subscene.heightProperty().bind(controller.getBsPane().heightProperty());
//        subscene.widthProperty().bind(controller.getBsPane().widthProperty());

        Camera camera = new PerspectiveCamera();
        subscene.setCamera(camera);
//        controller.getBox().setTranslateX(160);
//        controller.getBox().setTranslateY(-50);
//        controller.getBox().setTranslateZ(30);


//        controller.getBsPane().requestFocus();

        final Group axisGroup = new Group();
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final Box xAxis = new Box(240.0, 1, 1);
        final Box yAxis = new Box(1, 240.0, 1);
        final Box zAxis = new Box(1, 1, 240.0);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
//        controller.getBsPane().getBinChildren().addAll(axisGroup);




        subscene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    System.out.println("UP"); break;
                }
            }
        });
    }

}


