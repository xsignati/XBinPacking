package BinPacking.Data.Logic.BinSpace;
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
public abstract class BinSpace{
    private final SimpleDoubleProperty length = new SimpleDoubleProperty();
    private final SimpleDoubleProperty width = new SimpleDoubleProperty();
    private final SimpleDoubleProperty height = new SimpleDoubleProperty();
    private final SimpleDoubleProperty x = new SimpleDoubleProperty();
    private final SimpleDoubleProperty y = new SimpleDoubleProperty();
    private final SimpleDoubleProperty z = new SimpleDoubleProperty();
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final Double volume;
    public final double ORIGINAL_LENGTH;
    public final double ORIGINAL_WIDTH;
    public final double ORIGINAL_HEIGHT;

    /**
     * @param point coordinates of a (0,0,0) bin point.
     * @param dimensions dimensions of a bin.
     */
    public BinSpace(Point point, Dimensions dimensions){
        this.x.set(point.getX());
        this.y.set(point.getY());
        this.z.set(point.getZ());
        this.length.set(dimensions.getLength());
        this.width.set(dimensions.getWidth());
        this.height.set(dimensions.getHeight());
        this.ORIGINAL_LENGTH = dimensions.getLength();
        this.ORIGINAL_WIDTH = dimensions.getWidth();
        this.ORIGINAL_HEIGHT = dimensions.getHeight();
        volume = dimensions.getLength() * dimensions.getWidth() * dimensions.getHeight();
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