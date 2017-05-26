package BinPacking.Data.LogicUI;

/**
 * Created by Xsignati on 24.05.2017.
 */
public interface Rotator {
    /**
     * Rotate the cuboid.
     * @param cuboid rotated cuboid.
     */
    void rotate(BinSpace cuboid);
    /**
     * Get a number of available rotations
     * @return number of rotations
     */
    int limit();
}
