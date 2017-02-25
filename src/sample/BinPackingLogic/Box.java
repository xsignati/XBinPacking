package sample.BinPackingLogic;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

import java.util.Random;

/**
 * Created by Xsignati on 24.01.2017.
 */
public class Box extends Group implements Comparable<Box>{
    /**
     * Box parameters
     */
    //Dimensions and size
    public final double ORIGINAL_LENGTH;
    public final double ORIGINAL_WIDTH;
    public final double INIT_HEIGHT;
    private SimpleDoubleProperty length = new SimpleDoubleProperty();
    private SimpleDoubleProperty width = new SimpleDoubleProperty();
    private SimpleDoubleProperty height = new SimpleDoubleProperty();
    private SimpleDoubleProperty x = new SimpleDoubleProperty();
    private SimpleDoubleProperty y = new SimpleDoubleProperty();
    private SimpleDoubleProperty z = new SimpleDoubleProperty();
    private double volume;

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
        //measurements
        this.ORIGINAL_LENGTH = length;
        this.ORIGINAL_WIDTH = width;
        this.INIT_HEIGHT = height;
        this.length.set(length);
        this.width.set(width);
        this.height.set(height);
        this.x.set(-1);
        this.y.set(-1);
        this.z.set(-1);
        volume = length * width * height;

        //appearance
        this.scale = scale;
        box = new javafx.scene.shape.Box(scale * length, scale * width, scale * height);
        box.setTranslateX(scale * (getX()  + SHIFT_RATIO * length));
        box.setTranslateY(scale * (getY()  + SHIFT_RATIO * width));
        box.setTranslateZ(scale * (getZ()  + SHIFT_RATIO * height));
        material.setDiffuseColor(color);
        material.setSpecularColor(Color.DARKGREY);
        box.setMaterial(material);
        getChildren().add(box);
    }

    public void setBoxes(){
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
                setDimensions(ORIGINAL_WIDTH, ORIGINAL_LENGTH, INIT_HEIGHT);
                break;
            case LHW:
                setDimensions(ORIGINAL_LENGTH, INIT_HEIGHT, ORIGINAL_WIDTH);
                break;
            case HLW:
                setDimensions(INIT_HEIGHT, ORIGINAL_LENGTH, ORIGINAL_WIDTH);
                break;
            case WHL:
                setDimensions(ORIGINAL_WIDTH, INIT_HEIGHT, ORIGINAL_LENGTH);
                break;
            case HWL:
                setDimensions(INIT_HEIGHT, ORIGINAL_WIDTH, ORIGINAL_LENGTH);
                break;
            case LWH:
                setDimensions(ORIGINAL_LENGTH, ORIGINAL_WIDTH, INIT_HEIGHT);
                break;
            default:
                setDimensions(ORIGINAL_LENGTH, ORIGINAL_WIDTH, INIT_HEIGHT);
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

    public double getX() {
        return x.get();
    }

    public SimpleDoubleProperty xProperty() {
        return x;
    }

    public void setX(double x) {
        this.x.set(x);
    }

    public double getY() {
        return y.get();
    }

    public SimpleDoubleProperty yProperty() {
        return y;
    }

    public void setY(double y) {
        this.y.set(y);
    }

    public double getZ() {
        return z.get();
    }

    public SimpleDoubleProperty zProperty() {
        return z;
    }

    public void setZ(double z) {
        this.z.set(z);
    }

    public double getVolume() {
        return volume;
    }

    public void setDimensions(double length, double width, double height){
        this.length.set(length);
        this.width.set(width);
        this.height.set(height);
    }

    public void setCoordinates(double x, double y, double z){
        this.x.set(x);
        this.y.set(y);
        this.z.set(z);
    }
}
