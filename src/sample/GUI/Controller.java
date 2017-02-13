package sample.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;
import sample.GUI.BinView.BinModel;
import sample.GUI.BinView.BoxModel;
import sample.GUI.BinView.CameraModel;

import java.util.concurrent.SynchronousQueue;

public class Controller {
    @FXML
    private Text title;
    @FXML
    public SubScene binScene;
    @FXML
    public Pane bsPane;
    @FXML
    public Group  binsView;
    @FXML
    public Box box;
    @FXML
    private CameraModel cameraModel;
    @FXML
    private Group bin;
    @FXML
    SceneAntialiasing ssaa = SceneAntialiasing.BALANCED;

    public Group getBin() {
        return bin;
    }

    public CameraModel getCameraModel() {return cameraModel;}

    public Group getBinsView() {
        return binsView;
    }

    public Box getBox() {
        return box;
    }

    public Text getTitle() {
        return title;
    }

    public SubScene getBinScene() {
        return binScene;
    }

    public Pane getBsPane() {
        return bsPane;
    }

    SubScene getDrawArea() { return binScene; }

    @FXML
    public void initialize(){
        binScene.heightProperty().bind(bsPane.heightProperty());
        binScene.widthProperty().bind(bsPane.widthProperty());

        binScene.setCamera(cameraModel.getCamera());
        binScene.setOnMousePressed(pressEvent);
        binScene.setOnMouseDragged(dragEvent);
        binScene.setOnScroll(scrollEvent);

    }

    /**
     * TESTER
     */

    public void test() {
        System.out.println(binScene.getAntiAliasing());
        double scale = subSceneScale();

        BinModel binModel = new BinModel(getBinWidth(),getBinHeight(),getBinDepth(),scale);
        bin.getChildren().addAll(binModel);

        BoxModel box1 = new BoxModel(0,0,0,50,50,50, scale);
        BoxModel box2 = new BoxModel(50,0,0,50,50,50, scale);
        BoxModel box3 = new BoxModel(0,50,0,50,50,50, scale);
        BoxModel box4 = new BoxModel(50,0,50,50,50,50, scale);

        bin.getChildren().add(box1);
        bin.getChildren().add(box2);
        bin.getChildren().add(box3);
        bin.getChildren().add(box4);

    }

    private int getBinWidth(){
        return 500;
    }
    private int getBinHeight(){ return 300; }
    private int getBinDepth(){ return 400;
    }

    private double subSceneScale(){

        System.out.print(binScene.getWidth());
        return getMin(binScene.getWidth()/getBinWidth(), binScene.getHeight()/getBinDepth(), binScene.getWidth()/getBinHeight());
    }

    private double getMin(double r1, double r2, double r3) {
        return Math.min(r1, Math.min(r2, r3));
    }


    /**
     * *******************************************************
     */

    /**
     * GUI Event handlers
     */
    private double rotationSpeed = 0.2;
    private double scrollSpeed = 50;
    private double mousePosX;
    private double mousePosY;
    private double mouseOldX;
    private double mouseOldY;
    private double mouseDeltaX;
    private double mouseDeltaY;
    private double scrollDelta;
    private double scrollPosZ;

    @FXML
    EventHandler<MouseEvent> pressEvent = new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent me) {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseOldX = me.getSceneX();
            mouseOldY = me.getSceneY();

        }
    };

    @FXML
    EventHandler<MouseEvent> dragEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent me) {
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseDeltaX = (mousePosX - mouseOldX);
            mouseDeltaY = (mousePosY - mouseOldY);

            if (me.isPrimaryButtonDown()) {
                    cameraModel.getRz().setAngle(cameraModel.getRz().getAngle() - mouseDeltaX * rotationSpeed);
                    cameraModel.getRx().setAngle(cameraModel.getRx().getAngle() + mouseDeltaY * rotationSpeed);
            }
        }
    };

    EventHandler<ScrollEvent> scrollEvent = new EventHandler<ScrollEvent>(){
        @Override
        public void handle(ScrollEvent se) {
            scrollDelta = se.getDeltaY() > 0 ?  scrollSpeed : -scrollSpeed;
            scrollPosZ = cameraModel.getCamera().getTranslateZ();
            cameraModel.getCamera().setTranslateZ(scrollPosZ + scrollDelta);
        }
    };

}

