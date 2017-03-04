//package sample.GUI.BinView;
//
//import javafx.scene.Group;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.PhongMaterial;
//import javafx.scene.shape.Box;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
///**
// * Created by Xsignati on 11.02.2017.
// */
//public class BinModel extends Group {
//    private final static int  thickness = 10;
//    private final static double[][] EDGES_SIZES = {{0,0,1}, {0,0,1}, {0,0,1}, {0,0,1}, {1,0,0}, {1,0,0}, {1,0,0}, {1,0,0}, {0,1,0}, {0,1,0}, {0,1,0}, {0,1,0}};
//    private final static double[][] EDGES_POSITIONS = {{0,0,0}, {1,0,0}, {0,1,0}, {1,1,0}, {0,0,0}, {0,1,0}, {0,0,1}, {0,1,1}, {0,0,0}, {1,0,0}, {0,0,1}, {1,0,1}};
//    private final static double[][] EDGES_SHIFTS = {{1,1,0},{0,1,0},{1,0,0},{0,0,0},{0,1,1},{0,0,1},{0,1,0},{0,0,0},{1,0,1},{0,0,1},{1,0,0},{0,0,0}};
//    private final PhongMaterial material = new PhongMaterial();
//    private Box[] edges = new Box[12];
//    private final static double SHIFT_RATIO = 0.5;
//
//    public BinModel (double length, double width, double height){
//        this(length, width, height, Color.GREY);
//    }
//
//    public BinModel (double length, double width, double height, Color color){
//        material.setSpecularColor(Color.DARKGREY);
//        material.setDiffuseColor(color);
//        for(int i = 0 ; i < EDGES_SHIFTS.length; i++) {
//            edges[i] = new Box(EDGES_SIZES[i][0] * (length ) + thickness, EDGES_SIZES[i][1] * (width ) + thickness, EDGES_SIZES[i][2] * (height ) + thickness);
//            edges[i].setTranslateX(EDGES_POSITIONS[i][0] * length  + SHIFT_RATIO * edges[i].getWidth() - EDGES_SHIFTS[i][0] * thickness);
//            edges[i].setTranslateY(EDGES_POSITIONS[i][1] * width  + SHIFT_RATIO * edges[i].getHeight() - EDGES_SHIFTS[i][1] * thickness);
//            edges[i].setTranslateZ(EDGES_POSITIONS[i][2] * height + SHIFT_RATIO * edges[i].getDepth() - EDGES_SHIFTS[i][2] * thickness);
//            edges[i].setMaterial(material);
//            getChildren().add(edges[i]);
//        }
//
//    }
//
//    public void scale(double scale){
//        for(int i = 0 ; i < EDGES_SHIFTS.length; i++) {
//            edges[i].setWidth(edges[i].getWidth() * scale);
//            edges[i].setHeight(edges[i].getHeight() * scale);
//            edges[i].setDepth(edges[i].getDepth() * scale);
//            edges[i].setTranslateX(edges[i].getTranslateX() * scale);
//            edges[i].setTranslateY(edges[i].getTranslateY() * scale);
//            edges[i].setTranslateZ(edges[i].getTranslateZ() * scale);
//        }
//    }
//
//}
