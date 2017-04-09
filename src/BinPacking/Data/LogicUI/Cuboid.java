package BinPacking.Data.LogicUI;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;

/**
 * Created by Xsignati on 24.01.2017.
 * Parent class for cuboid-shape classes: Bin, Box.
 * Coordinates and measurements :
 * | Z height
 * |        /
 * |      /
 * |    /Y width
 * |  /
 * |/
 * ------------ X length
 **/
public abstract class Cuboid extends Group implements Comparable<Cuboid>{
    protected SimpleDoubleProperty length = new SimpleDoubleProperty();
    protected SimpleDoubleProperty width = new SimpleDoubleProperty();
    protected SimpleDoubleProperty height = new SimpleDoubleProperty();
    protected SimpleDoubleProperty x = new SimpleDoubleProperty();
    protected SimpleDoubleProperty y = new SimpleDoubleProperty();
    protected SimpleDoubleProperty z = new SimpleDoubleProperty();
    protected SimpleIntegerProperty cid = new SimpleIntegerProperty();
    protected double volume;

    public Cuboid(double x, double y, double z, double length, double width, double height){
        this.x.set(x);
        this.y.set(y);
        this.z.set(z);
        this.length.set(length);
        this.width.set(width);
        this.height.set(height);
        volume = length * width * height;
    }

    public double getLength() {
        return length.get();
    }

    public double getWidth() {
        return width.get();
    }

    public double getHeight() {
        return height.get();
    }

    public double getX() {
        return x.get();
    }

    public double getY() {
        return y.get();
    }

    public double getZ() {
        return z.get();
    }

    public double getVolume() {
        return volume;
    }

    public void setCoordinates(double x, double y, double z){
        this.x.set(x);
        this.y.set(y);
        this.z.set(z);
    }
    public void setSize(double length, double width, double height){
        this.length.set(length);
        this.width.set(width);
        this.height.set(height);
    }

    public int getCid() {
        return cid.get();
    }

    public SimpleIntegerProperty cidProperty() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid.set(cid);
    }

    //Appearance

    /**
     * Scale method used to fit a model to the SubScene size
     * @param scale
     */
    public abstract void scale(double scale);

    /**
     * Model's appearance creation
     * @param length
     * @param width
     * @param height
     * @param color
     */
    public abstract void createGraphicModel(double length, double width, double height, Color color);

    @Override
    public int compareTo(Cuboid o){
        return (int)(o.getVolume() - getVolume());
    }
}