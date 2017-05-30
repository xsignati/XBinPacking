package BinPacking.Data.LogicUI;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import java.util.Random;

/**
 * Created by Xsignati on 24.01.2017.
 */
public class Box extends BinSpace implements Rotatable, SceneModel{
    private BoxRotator boxRotator;
    private final BoxModel model = new BoxModel();

    public void rotate(){
        boxRotator.rotate(this);
    }
    public Box(Dimensions dimensions){
        this(dimensions, new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat(), 1));
        boxRotator = new BoxRotator();
    }

    public Box(Dimensions dimensions, Color color){
        super(new Point(0,0,0), new Dimensions(dimensions.getLength(), dimensions.getWidth(), dimensions.getHeight()));
        model.createGraphicModel(dimensions.getLength(), dimensions.getWidth(), dimensions.getHeight(), color);
    }

    @Override
    public boolean equals(Object o){
        boolean result = false;
        if (o instanceof Box) {
            Box otherBox = (Box)o;
            result = (getX() == otherBox.getX() && getY() == otherBox.getY() && getZ() == otherBox.getZ());
        }
        return result;
    }

    /**
     * A Graphic model used to display a box in the GUI.
     */
    public class BoxModel {
        //Appearance
        private final Group modelGroup = new Group();
        private final PhongMaterial material = new PhongMaterial();
        private javafx.scene.shape.Box box;
        private final static double SHIFT_RATIO = 0.5;

        //Only the outer class should be able to create its BoxModel
        private BoxModel(){}

        public void scale(double scale){
            box.setWidth(getLength() * scale);
            box.setHeight(getWidth() * scale);
            box.setDepth(getHeight() * scale);
            box.setTranslateX(scale * (getX()  + SHIFT_RATIO * getLength()));
            box.setTranslateY(scale * (getY()  + SHIFT_RATIO * getWidth()));
            box.setTranslateZ(scale * (getZ()  + SHIFT_RATIO * getHeight()));
        }

        public void createGraphicModel(double length, double width, double height, Color color){
            box = new javafx.scene.shape.Box(length, width, height);
            material.setDiffuseColor(color);
            material.setSpecularColor(Color.DARKGREY);
            box.setMaterial(material);
            modelGroup.getChildren().add(box);
        }
    }

    @Override
    public void scale(double scale){
        model.scale(scale);
    }

    @Override
    public void addModel(Group binSceneElements){
        binSceneElements.getChildren().add(model.modelGroup);
    }

    @Override
    public void addModel(Group binSceneElements, int id){
        if(getId() == id)
            binSceneElements.getChildren().add(model.modelGroup);
    }

}
