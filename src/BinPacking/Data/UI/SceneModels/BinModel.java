package BinPacking.Data.UI.SceneModels;

/**
 * Created by Xsignati on 30.05.2017.
 */

import BinPacking.Data.Logic.Bin.Bin;
import BinPacking.Data.Logic.BinSpace.BinSpace;
import BinPacking.Data.Logic.BinSpace.Dimensions;
import BinPacking.Data.Logic.BinSpace.Point;
import BinPacking.Data.UI.SceneModels.SceneModel;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * A Graphic model used to display a bin in the GUI.
 */
public class BinModel extends BinSpace implements SceneModel {
    private final Group modelGroup = new Group();
    private final int thickness = 10;
    private final double[][] EDGES_SIZES = {{0, 0, 1}, {0, 0, 1}, {0, 0, 1}, {0, 0, 1}, {1, 0, 0}, {1, 0, 0}, {1, 0, 0}, {1, 0, 0}, {0, 1, 0}, {0, 1, 0}, {0, 1, 0}, {0, 1, 0}};
    private final double[][] EDGES_POSITIONS = {{0, 0, 0}, {1, 0, 0}, {0, 1, 0}, {1, 1, 0}, {0, 0, 0}, {0, 1, 0}, {0, 0, 1}, {0, 1, 1}, {0, 0, 0}, {1, 0, 0}, {0, 0, 1}, {1, 0, 1}};
    private final double[][] EDGES_SHIFTS = {{1, 1, 0}, {0, 1, 0}, {1, 0, 0}, {0, 0, 0}, {0, 1, 1}, {0, 0, 1}, {0, 1, 0}, {0, 0, 0}, {1, 0, 1}, {0, 0, 1}, {1, 0, 0}, {0, 0, 0}};
    private final PhongMaterial material = new PhongMaterial();
    private final javafx.scene.shape.Box[] edges = new javafx.scene.shape.Box[12];
    private final double SHIFT_RATIO = 0.5;

    public BinModel(Bin bin){
        super(new Point(bin.getX(), bin.getY(), bin.getZ()), new Dimensions(bin.getLength(), bin.getWidth(), bin.getHeight()));
        setId(bin.getId());
        Color color = Color.GRAY;
        material.setSpecularColor(Color.DARKGREY);
        material.setDiffuseColor(color);
        IntStream.range(0, edges.length).forEach(i -> {
                    edges[i] = new javafx.scene.shape.Box(EDGES_SIZES[i][0] * getLength() + thickness, EDGES_SIZES[i][1] * (getWidth()) + thickness, EDGES_SIZES[i][2] * (getHeight()) + thickness);
                    edges[i].setTranslateX(EDGES_POSITIONS[i][0] * getLength() + SHIFT_RATIO * edges[i].getWidth() - EDGES_SHIFTS[i][0] * thickness);
                    edges[i].setTranslateY(EDGES_POSITIONS[i][1] * getWidth() + SHIFT_RATIO * edges[i].getHeight() - EDGES_SHIFTS[i][1] * thickness);
                    edges[i].setTranslateZ(EDGES_POSITIONS[i][2] * getHeight() + SHIFT_RATIO * edges[i].getDepth() - EDGES_SHIFTS[i][2] * thickness);
                    edges[i].setMaterial(material);
                    modelGroup.getChildren().add(edges[i]);
                }
        );
    }

    public void scale(double scale) {
        Arrays.stream(edges).forEach(i -> {
            i.setWidth(i.getWidth() * scale);
            i.setHeight(i.getHeight() * scale);
            i.setDepth(i.getDepth() * scale);
            i.setTranslateX(i.getTranslateX() * scale);
            i.setTranslateY(i.getTranslateY() * scale);
            i.setTranslateZ(i.getTranslateZ() * scale);
        });
    }

    @Override
    public void addModel(Group binSceneModels) {
        binSceneModels.getChildren().add(modelGroup);
    }

    @Override
    public void addModel(Group binSceneModels, int id) {
        if(getId() == id)
            binSceneModels.getChildren().add(modelGroup);
    }
}