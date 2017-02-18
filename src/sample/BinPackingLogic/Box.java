package sample.BinPackingLogic;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Xsignati on 24.01.2017.
 */
public class Box extends Cuboid implements Comparable<Box>{
    /**
     * Box parameters
     */

    private SimpleIntegerProperty w;
    private   SimpleIntegerProperty d;
    private SimpleIntegerProperty h;

    public final int INIT_WIDTH;
    public final int INIT_DEPTH;
    public final int INIT_HEIGHT;

    private BoxState boxState;
    private int binId;
    private int weight;

    public Box(int width, int depth, int height){
        super(-1, -1, -1, width, depth, height);
        this.INIT_WIDTH = width;
        this.INIT_DEPTH = depth;
        this.INIT_HEIGHT = height;
        w = new SimpleIntegerProperty(width);
        h = new SimpleIntegerProperty(height);
        d = new SimpleIntegerProperty(depth);
    }

    @Override
    public int compareTo(Box o){
        return o.getVolume() - getVolume();
    }

    public enum BoxState {
        ASSIGNED, AWAITING, NOT_ASSIGNED,
    }

    public BoxState getBoxState() {
        return boxState;
    }

    public void setBoxState(BoxState boxState) {
        this.boxState = boxState;
    }

    public int getBinId() {
        return binId;
    }

    public void setBinId(int binTreeNumber) {
        this.binId = binTreeNumber;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setW(int w) {
        this.w.set(w);
    }

    public void setD(int d) {
        this.d.set(d);
    }

    public void setH(int h) {
        this.h.set(h);
    }

    public int getW() {
        return w.get();
    }

    public SimpleIntegerProperty wProperty() {
        return w;
    }

    public int getD() {
        return d.get();
    }

    public SimpleIntegerProperty dProperty() {
        return d;
    }

    public int getH() {
        return h.get();
    }

    public SimpleIntegerProperty hProperty() {
        return h;
    }
}
