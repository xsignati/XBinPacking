package BinPacking.Data.UI;

import javafx.beans.NamedArg;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

/**
 * Created by Xsignati on 10.02.2017.
 * A camera model with custom rotations and lights.
 */
public class CameraModel extends Group {
    public final static int Y_UP_ROTATION = 180;
    private final int xRotation;
    private final int yRotation;
    private final int zRotation;
    private final Rotate rx = new Rotate();
    private final Rotate ry = new Rotate();
    private final Rotate rz = new Rotate();
    private final Camera camera = new PerspectiveCamera(true);
    private double distance;

    /**
     * @param xRotation rotation of the camera over the X axis.
     * @param yRotation rotation of the camera over the Y axis.
     * @param zRotation rotation of the camera over the Z axis.
     * @param distance distance from (0,0,0).
     */
    public CameraModel(@NamedArg("xRotation") int xRotation, @NamedArg("yRotation") int yRotation, @NamedArg("zRotation") int zRotation, @NamedArg("distance") int distance){
        //Save FXML parameters
        this.xRotation = xRotation;
        this.yRotation = yRotation;
        this.zRotation = zRotation;
        this.distance = distance;

        //Set a camera position
        reset();

        //Add camera rotations
        rx.setAxis(Rotate.X_AXIS);
        ry.setAxis(Rotate.Y_AXIS);
        rz.setAxis(Rotate.Z_AXIS);
        getTransforms().addAll(rz, ry, rx);

        //Add the camera to group
        getChildren().add(camera);
        camera.getTransforms().add(new Rotate(Y_UP_ROTATION, Rotate.Z_AXIS));
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-distance); //150 200

        //There are 2 lights - ambient light illuminating all objects and point light attached to camera (used during manipulation of camera position)
        Color lightColor = Color.rgb(240, 240, 240);
        PointLight headLight = new PointLight(lightColor);
        headLight.translateXProperty().bind(camera.translateXProperty());
        headLight.translateYProperty().bind(camera.translateYProperty());
        headLight.translateZProperty().bind(camera.translateZProperty());
        Color ambientColor = Color.rgb(122, 122, 122, 1);
        AmbientLight ambient = new AmbientLight(ambientColor);
        getChildren().add(headLight);
        getChildren().add(ambient);
    }

    public Rotate getRx() {
        return rx;
    }

    public Rotate getRy() {
        return ry;
    }

    public Rotate getRz() {
        return rz;
    }

    /**
     * @param distance distance of the camera to the (0,0,0) point of the subScene.
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Set the camera position to default value.
     */
    public void reset() {
        rx.setAngle(xRotation);
        ry.setAngle(yRotation);
        rz.setAngle(zRotation);
        camera.setTranslateZ(-distance);
    }

    public Camera getCamera() {
        return camera;
    }
}
