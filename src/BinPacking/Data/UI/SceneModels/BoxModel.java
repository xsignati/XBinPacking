package BinPacking.Data.UI.SceneModels;

import BinPacking.Data.Logic.BinSpace.BinSpace;
import BinPacking.Data.Logic.BinSpace.Dimensions;
import BinPacking.Data.Logic.BinSpace.Point;
import BinPacking.Data.Logic.Box.Box;
import BinPacking.Data.UI.SceneModels.SceneModel;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

import java.util.Random;

/**
 * Created by Xsignati on 30.05.2017.
 */
public class BoxModel extends BinSpace implements SceneModel {
    //Appearance
    private final Group modelGroup = new Group();
    private final PhongMaterial material = new PhongMaterial();
    private javafx.scene.shape.Box shapeBox;
    private final static double SHIFT_RATIO = 0.5;

    //Only the outer class should be able to create its BoxModel
    public BoxModel(Box box){
        super(new Point(box.getX(), box.getY(), box.getZ()), new Dimensions(box.getLength(),box.getWidth(),box.getHeight()));
        setId(box.getId());
        Color color = new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat(), 1);
        shapeBox = new javafx.scene.shape.Box(getLength(), getWidth(), getHeight());
        material.setDiffuseColor(color);
        material.setSpecularColor(Color.DARKGREY);
        shapeBox.setMaterial(material);
        modelGroup.getChildren().add(shapeBox);
    }

    public void scale(double scale) {
        shapeBox.setWidth(getLength() * scale);
        shapeBox.setHeight(getWidth() * scale);
        shapeBox.setDepth(getHeight() * scale);
        shapeBox.setTranslateX(scale * (getX() + SHIFT_RATIO * getLength()));
        shapeBox.setTranslateY(scale * (getY() + SHIFT_RATIO * getWidth()));
        shapeBox.setTranslateZ(scale * (getZ() + SHIFT_RATIO * getHeight()));
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