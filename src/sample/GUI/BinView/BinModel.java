package sample.GUI.BinView;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Xsignati on 11.02.2017.
 */
public class BinModel extends Group {
    private final static int  thickness = 10;
    private final static double[][] EDGES_SIZES = {{0,0,1}, {0,0,1}, {0,0,1}, {0,0,1}, {1,0,0}, {1,0,0}, {1,0,0}, {1,0,0}, {0,1,0}, {0,1,0}, {0,1,0}, {0,1,0}};
    private final static double[][] EDGES_POSITIONS = {{0,0,0}, {1,0,0}, {0,1,0}, {1,1,0}, {0,0,0}, {0,1,0}, {0,0,1}, {0,1,1}, {0,0,0}, {1,0,0}, {0,0,1}, {1,0,1}};
    private final static double[][] EDGES_SHIFTS = {{-1,-1,-1},{1,-1,-1},{-1,1,-1},{1,1,-1},{-1,-1,-1},{-1,1,-1},{-1,-1,1},{-1,1,1},{-1,-1,-1},{1,-1,-1},{-1,-1,1},{1,-1,1}};
    private final PhongMaterial material = new PhongMaterial();
    private Box[] edges = new Box[12];
    private final static double SHIFT_RATIO = 0.5;

    public BinModel (double length, double width, double height ){
        this(length, width, height, 1);
    }

    public BinModel (double length, double width, double height, double scale){
        this(length, width, height, scale, Color.GREY);
    }

    public BinModel (double length, double width, double height, double scale, Color color){
        material.setSpecularColor(Color.DARKGREY);
        material.setDiffuseColor(color);
        for(int i = 0 ; i < EDGES_SHIFTS.length; i++) {
            edges[i] = new Box(EDGES_SIZES[i][0] * (scale * length + thickness) + thickness, EDGES_SIZES[i][1] * (scale * width + thickness) + thickness, EDGES_SIZES[i][2] * (scale * height  + thickness) + thickness);
            edges[i].setTranslateX(scale * EDGES_POSITIONS[i][0] * length  + SHIFT_RATIO * edges[i].getWidth() + EDGES_SHIFTS[i][0] * thickness);
            edges[i].setTranslateY(scale * EDGES_POSITIONS[i][1] * width  + SHIFT_RATIO * edges[i].getHeight() + EDGES_SHIFTS[i][1] * thickness);
            edges[i].setTranslateZ(scale * EDGES_POSITIONS[i][2] * height + SHIFT_RATIO * edges[i].getDepth() + EDGES_SHIFTS[i][2] * thickness);
            edges[i].setMaterial(material);
            getChildren().add(edges[i]);
        }
    }

}
