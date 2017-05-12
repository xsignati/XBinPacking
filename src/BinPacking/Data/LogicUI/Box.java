package BinPacking.Data.LogicUI;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import java.util.Random;

/**
 * Created by Xsignati on 24.01.2017.
 */
public class Box extends Cuboid implements SceneModel{
    public static final int ROTATIONS_NUM = Box.Rotations.values().length;
    public enum Rotations {WLH, LHW, HLW, WHL, HWL, LWH }
    private final double ORIGINAL_LENGTH;
    private final double ORIGINAL_WIDTH;
    private final double ORIGINAL_HEIGHT;
    private final BoxModel model = new BoxModel();

    /**
     * Default color parameter constructor.
     * @param length
     * @param width
     * @param height
     */
    public Box(double length, double width, double height){
        this(length, width, height, new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat(), 1));
    }

    /**
     * User defined color constructor.
     * @param length
     * @param width
     * @param height
     * @param color
     */
    public Box(double length, double width, double height, Color color){
        super(0, 0, 0, length, width, height);
        this.ORIGINAL_LENGTH = length;
        this.ORIGINAL_WIDTH = width;
        this.ORIGINAL_HEIGHT = height;
        model.createGraphicModel(length, width, height, color);
    }

    /**
     * Rotate the box.
     * @param rotation
     */
    public void rotate(Rotations rotation) {
        switch (rotation) {
            case WLH:
                setSize(ORIGINAL_WIDTH, ORIGINAL_LENGTH, ORIGINAL_HEIGHT);
                break;
            case LHW:
                setSize(ORIGINAL_LENGTH, ORIGINAL_HEIGHT, ORIGINAL_WIDTH);
                break;
            case HLW:
                setSize(ORIGINAL_HEIGHT, ORIGINAL_LENGTH, ORIGINAL_WIDTH);
                break;
            case WHL:
                setSize(ORIGINAL_WIDTH, ORIGINAL_HEIGHT, ORIGINAL_LENGTH);
                break;
            case HWL:
                setSize(ORIGINAL_HEIGHT, ORIGINAL_WIDTH, ORIGINAL_LENGTH);
                break;
            case LWH:
                setSize(ORIGINAL_LENGTH, ORIGINAL_WIDTH, ORIGINAL_HEIGHT);
                break;
            default:
                setSize(ORIGINAL_LENGTH, ORIGINAL_WIDTH, ORIGINAL_HEIGHT);
                break;
        }
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
