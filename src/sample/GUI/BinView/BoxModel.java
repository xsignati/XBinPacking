package sample.GUI.BinView;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.Random;

/**
 * Created by Xsignati on 08.02.2017.
 */
public class BoxModel extends Group {
    private final PhongMaterial material = new PhongMaterial();
    private Box box;
    private final static double SHIFT_RATIO = 0.5;

    public BoxModel (double x, double y, double z, double width, double height, double depth ){
        this(x, y, z, width, height, depth, 1);
    }

    public BoxModel (double x, double y, double z, double width, double height, double depth , double scale){
        this(x, y, z, width, height, depth, scale, new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat(), 1));
    }

    public BoxModel (double x, double y, double z, double width, double height, double depth , double scale, Color color){
        box = new Box(scale * width, scale * height, scale * depth);
        box.setTranslateX(scale * (x  + SHIFT_RATIO * width));
        box.setTranslateY(scale * (y  + SHIFT_RATIO * depth));
        box.setTranslateZ(scale * (z  + SHIFT_RATIO * height));
        material.setDiffuseColor(color);
        material.setSpecularColor(Color.DARKGREY);
        box.setMaterial(material);
        getChildren().add(box);
    }

}
