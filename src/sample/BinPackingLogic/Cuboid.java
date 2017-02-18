package sample.BinPackingLogic;

/**
 * Created by Xsignati on 24.01.2017.
 */
abstract class Cuboid{
    /**
     * Coordinates and measurements
     * | Z height
     * |        /
     * |      /
     * |    /Y depth
     * |  /
     * |/
     * ------------ X width
     * */
    private int x;
    private int y;
    private int z;
    private int width;
    private int depth;
    private int height;
    private int volume;

    public Cuboid(int x, int y, int z, int width, int depth, int height){
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.depth = depth;
        this.height = height;
        volume = width * depth * height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getWidth() {
        return width;
    }

    public int getDepth() {
        return depth;
    }

    public int getHeight() {
        return height;
    }

    public int getVolume() {
        return volume;
    }

    public void setCoordinates(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public void setDimensions(int width, int depth, int height){
        this.width = width;
        this.depth = depth;
        this.height = height;
    }

}