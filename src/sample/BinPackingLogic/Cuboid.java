package sample.BinPackingLogic;

/**
 * Created by Xsignati on 24.01.2017.
 */
public abstract class Cuboid{
    /**
     * Coordinates and measurements
     * | Z height
     * |        /
     * |      /
     * |    /Y width
     * |  /
     * |/
     * ------------ X length
     * */
    protected double x;
    protected double y;
    protected double z;
    protected double length;
    protected double width;
    protected double height;
    protected double volume;

    public Cuboid(double x, double y, double z, double length, double width, double height){
        this.x = x;
        this.y = y;
        this.z = z;
        this.length = length;
        this.width = width;
        this.height = height;
        volume = length * width * height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getVolume() {
        return volume;
    }

    public void setCoordinates(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public void setDimensions(double length, double width, double height){
        this.length = length;
        this.width = width;
        this.height = height;
    }

}