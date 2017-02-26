package sample.BinPackingLogic;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

import java.util.Random;

/**
 * Created by Xsignati on 24.01.2017.
 */
public class Box extends Cuboid implements Comparable<Box>{
    /**
     * Box parameters
     */
    //Dimensions and size
    public final double ORIGINAL_LENGTH;
    public final double ORIGINAL_WIDTH;
    public final double ORIGINAL_HEIGHT;
    //Appearance
    private final PhongMaterial material = new PhongMaterial();
    private javafx.scene.shape.Box box;
    private final static double SHIFT_RATIO = 0.5;

    //Id and others
    private int binId;
    private int weight;

    private double scale;

    public Box(double length, double width, double height , double scale){
        this(length, width, height, scale, new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat(), 1));
    }

    public Box(double length, double width, double height , double scale, Color color){
        super(-1, -1, -1, length, width, height);
        //measurements
        this.ORIGINAL_LENGTH = length;
        this.ORIGINAL_WIDTH = width;
        this.ORIGINAL_HEIGHT = height;

        //appearance
        this.scale = scale;
        box = new javafx.scene.shape.Box(scale * length, scale * width, scale * height);
        material.setDiffuseColor(color);
        material.setSpecularColor(Color.DARKGREY);
        box.setMaterial(material);
        getChildren().add(box);
    }

    public void setBoxes(){
        box.setWidth(scale * getLength());
        box.setHeight(scale * getWidth());
        box.setDepth(scale * getHeight());
        box.setTranslateX(scale * (getX()  + SHIFT_RATIO * getLength()));
        box.setTranslateY(scale * (getY()  + SHIFT_RATIO * getWidth()));
        box.setTranslateZ(scale * (getZ()  + SHIFT_RATIO * getHeight()));
    }

    @Override
    public int compareTo(Box o){
        return (int)(o.getVolume() - getVolume());
    }

    public enum Rotations {WLH, LHW, HLW, WHL, HWL, LWH }

    public void rotate(Rotations rotation) {
        switch (rotation) {
            case WLH:
                setDimensions(ORIGINAL_WIDTH, ORIGINAL_LENGTH, ORIGINAL_HEIGHT);
                break;
            case LHW:
                setDimensions(ORIGINAL_LENGTH, ORIGINAL_HEIGHT, ORIGINAL_WIDTH);
                break;
            case HLW:
                setDimensions(ORIGINAL_HEIGHT, ORIGINAL_LENGTH, ORIGINAL_WIDTH);
                break;
            case WHL:
                setDimensions(ORIGINAL_WIDTH, ORIGINAL_HEIGHT, ORIGINAL_LENGTH);
                break;
            case HWL:
                setDimensions(ORIGINAL_HEIGHT, ORIGINAL_WIDTH, ORIGINAL_LENGTH);
                break;
            case LWH:
                setDimensions(ORIGINAL_LENGTH, ORIGINAL_WIDTH, ORIGINAL_HEIGHT);
                break;
            default:
                setDimensions(ORIGINAL_LENGTH, ORIGINAL_WIDTH, ORIGINAL_HEIGHT);
                break;
        }
    }

    //Setters and getters

    public double getBinId() {
        return binId;
    }

    public void setBinId(int binTreeNumber) {
        this.binId = binTreeNumber;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length.get();
    }

    public SimpleDoubleProperty lengthProperty() {
        return length;
    }

    public void setLength(double length) {
        this.length.set(length);
        box.setWidth(scale * length);
        box.setTranslateX(scale * (getX()  + SHIFT_RATIO * length));
    }

    public double getWidth() {
        return width.get();
    }

    public SimpleDoubleProperty widthProperty() {
        return width;
    }

    public void setWidth(double width) {
        this.width.set(width);
        box.setHeight(scale * width);
        box.setTranslateY(scale * (getY()  + SHIFT_RATIO * width));

    }

    public double getHeight() {
        return height.get();
    }

    public SimpleDoubleProperty heightProperty() {
        return height;
    }

    public void setHeight(double height) {
        this.height.set(height);
        box.setDepth(scale * height);
        box.setTranslateZ(scale * (getZ()  + SHIFT_RATIO * height));
    }

}
