package BinPacking.Data.UI;

import BinPacking.Data.LogicUI.Bin;
import BinPacking.Data.LogicUI.BinList;
import BinPacking.Data.LogicUI.Box;
import BinPacking.Data.LogicUI.BoxList;
import javafx.beans.NamedArg;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

/**
 * Created by Xsignati on 08.04.2017.
 */
public class BinScene extends SubScene {
    private Scale scale;
    private CameraModel camera;
    private Group bins;
    private Group boxes;

    public BinScene(@NamedArg("root") Parent root, @NamedArg("width") double width, @NamedArg("height") double height, @NamedArg("depthBuffer") boolean depthBuffer) {
        super(root, width, height, true, SceneAntialiasing.BALANCED);
        camera = (CameraModel) getRoot().getChildrenUnmodifiable().get(0);
        bins = (Group) getRoot().getChildrenUnmodifiable().get(1);
        boxes = (Group) getRoot().getChildrenUnmodifiable().get(2);

        //Add SubScene event handlers
        setCamera(camera.getCamera());
        setOnMousePressed(pressEvent);
        setOnMouseDragged(dragEvent);
        setOnScroll(scrollEvent);
    }

    /**
     * Add boxes and bins 3D models to the SubScene.
     *
     * @param selectedBin - display boxes belong to the chosen Bin identified by binId
     */
    public void draw(BoxList boxList, BinList binList, int selectedBin) {
        boxes.getChildren().clear();
        bins.getChildren().clear();

        for (Box box : boxList.get()) {
            if (box.getCid() == selectedBin)
                boxes.getChildren().add(box);
        }
        bins.getChildren().add(binList.get().get(selectedBin));
    }

    public void rescale(BoxList boxList, BinList binList, double binLength, double binWidth, double binHeight) {
        camera.setDistance(getWidth() * 4);
        camera.reset();
        scale = new Scale(getWidth() / binLength, getHeight() / binWidth, getWidth() / binHeight);

        for (Box box : boxList.get()) {
            box.scale(scale.get());
        }

        for (Bin bin : binList.get()) {
            bin.scale(scale.get());
        }
    }

    public void rescale(Box box){
        box.scale(scale.get());
    }

    /**
     * Created by Xsignati on 09.04.2017.
     * Computes an appropriate scaling factor. Every box and bin is scaled
     * before drawing to match to the SubScene camera view and avoid too large objects to displaying.
     */
    private class Scale{
        private double scale; //Scale factor
        private Scale(double s1, double s2, double s3){
            scale = getMin(s1, s2, s3);
        }
        private double getMin(double r1, double r2, double r3) {
            return Math.min(r1, Math.min(r2, r3));
        }
        private double get() {
            return scale;
        }
    }

    //Event handlers controlling a rotation of camera and ZOOM.
    private double mousePosX;
    private double mousePosY;
    private double mouseOldX;
    private double mouseOldY;

    @FXML
    private EventHandler<MouseEvent> pressEvent = new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent me) {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseOldX = me.getSceneX();
            mouseOldY = me.getSceneY();
        }
    };

    @FXML
    private EventHandler<MouseEvent> dragEvent = new EventHandler<MouseEvent>() {
        private double rotationSpeed = 0.2;
        private double mouseDeltaX;
        private double mouseDeltaY;
        @Override
        public void handle(MouseEvent me) {
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseDeltaX = (mousePosX - mouseOldX);
            mouseDeltaY = (mousePosY - mouseOldY);

            if (me.isPrimaryButtonDown()) {
                camera.getRz().setAngle(camera.getRz().getAngle() - mouseDeltaX * rotationSpeed);
                camera.getRx().setAngle(camera.getRx().getAngle() + mouseDeltaY * rotationSpeed);
            }
        }
    };

    private EventHandler<ScrollEvent> scrollEvent = new EventHandler<ScrollEvent>(){
        private double scrollSpeed = 50;
        private double scrollDelta;
        private double scrollPosZ;
        @Override
        public void handle(ScrollEvent se) {
            scrollDelta = se.getDeltaY() > 0 ?  scrollSpeed : -scrollSpeed;
            scrollPosZ = camera.getCamera().getTranslateZ();
            camera.getCamera().setTranslateZ(scrollPosZ + scrollDelta);
        }
    };
}

