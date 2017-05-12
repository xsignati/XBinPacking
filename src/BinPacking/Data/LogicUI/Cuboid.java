package BinPacking.Data.LogicUI;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by Xsignati on 24.01.2017.
 * A parent class for cuboid-shaped classes: Bin, Box.
 * Coordinates and measurements:
 * | Z height
 * |        /
 * |      /
 * |    /Y width
 * |  /
 * |/
 * ------------ X length
 **/
public abstract class Cuboid {
    protected final SimpleDoubleProperty length = new SimpleDoubleProperty();
    protected final SimpleDoubleProperty width = new SimpleDoubleProperty();
    protected final SimpleDoubleProperty height = new SimpleDoubleProperty();
    protected final SimpleDoubleProperty x = new SimpleDoubleProperty();
    protected final SimpleDoubleProperty y = new SimpleDoubleProperty();
    protected final SimpleDoubleProperty z = new SimpleDoubleProperty();
    protected final SimpleIntegerProperty id = new SimpleIntegerProperty();
    protected final Double volume;

    /**
     * @param x coordinate of the cuboid.
     * @param y coordinate of the cuboid.
     * @param z coordinate of the cuboid.
     * @param length length of the cuboid.
     * @param width width of the cuboid.
     * @param height height of the cuboid.
     */
    public Cuboid(double x, double y, double z, double length, double width, double height){
        this.x.set(x);
        this.y.set(y);
        this.z.set(z);
        this.length.set(length);
        this.width.set(width);
        this.height.set(height);
        volume = length * width * height;
    }

    public void setLength(double length) {
        this.length.set(length);
    }

    public void setWidth(double width) {
        this.width.set(width);
    }

    public void setHeight(double height) {
        this.height.set(height);
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

    public Double getVolume() {
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

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }
}