//package sample.GUI.BinView;
//
//import javafx.scene.Group;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.PhongMaterial;
//import javafx.scene.shape.Box;
//
//import java.util.Random;
//
///**
// * Created by Xsignati on 08.02.2017.
// */
//public class BoxModel extends Group {
//    private final PhongMaterial material = new PhongMaterial();
//    private Box box;
//    private final static double SHIFT_RATIO = 0.5;
//
//    public BoxModel (double x, double y, double z, double length, double width, double height ){
//        this(x, y, z, length, width, height, 1);
//    }
//
//    public BoxModel (double x, double y, double z, double length, double width, double height , double scale){
//        this(x, y, z, length, width, height, scale, new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat(), 1));
//    }
//
//    public BoxModel (double x, double y, double z, double length, double width, double height , double scale, Color color){
//        box = new Box(scale * length, scale * width, scale * height);
//        box.setTranslateX(scale * (x  + SHIFT_RATIO * length));
//        box.setTranslateY(scale * (y  + SHIFT_RATIO * width));
//        box.setTranslateZ(scale * (z  + SHIFT_RATIO * height));
//        material.setDiffuseColor(color);
//        material.setSpecularColor(Color.DARKGREY);
//        box.setMaterial(material);
//        getChildren().add(box);
//    }
//
//}
