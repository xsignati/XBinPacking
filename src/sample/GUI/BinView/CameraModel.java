package sample.GUI.BinView;

import javafx.beans.NamedArg;
import javafx.beans.property.DoubleProperty;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

/**
 * Created by Xsignati on 10.02.2017.
 */
public class CameraModel extends Group {
    public final static int Y_UP_ROTATION = 180;
    private int xRotation;
    private int yRotation;
    private int zRotation;
    private double distance;
    private Rotate rx = new Rotate();
    private Rotate ry = new Rotate();
    private Rotate rz = new Rotate();
    Camera camera = new PerspectiveCamera(true);


    public CameraModel(@NamedArg("xRotation") int xRotation, @NamedArg("yRotation") int yRotation, @NamedArg("zRotation") int zRotation, @NamedArg("distance") int distance){
        this.xRotation = xRotation;
        this.yRotation = yRotation;
        this.zRotation = zRotation;
        this.distance = distance;

        reset();
        rx.setAxis(Rotate.X_AXIS);
        ry.setAxis(Rotate.Y_AXIS);
        rz.setAxis(Rotate.Z_AXIS);
        getTransforms().addAll(rz, ry, rx);

        getChildren().add(camera);
        camera.getTransforms().add(new Rotate(Y_UP_ROTATION, Rotate.Z_AXIS));
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-distance); //150 200

        Color lightColor = Color.rgb(240, 240, 240);
        PointLight headLight;
        headLight = new PointLight(lightColor);
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

    public void setDistance(double distance) {
        this.distance = distance;
    }

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
