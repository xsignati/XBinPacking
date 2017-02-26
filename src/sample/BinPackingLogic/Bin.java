package sample.BinPackingLogic;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Flexscan2243 on 24.01.2017.
 */
class Bin extends Cuboid {
    public enum BinState {EMPTY, FULL}
    private BinState binState = BinState.EMPTY;
    public enum BinType {ROOT,A,B,C,D}
    private BinType binType;
    private static int rootBinCounter = 0; //< Each box gets this id. The id indicates the bin assigned to box.
    private int id;
    private Bin parent;
    private List<Bin> children;

    public Bin(double length, double width, double height){
        this(0,0,0,length,width,height, BinType.ROOT);
        rootBinCounter++;
    }

    private Bin(double x, double y, double z, double length, double width, double height, BinType binType) {
        super(x, y, z, length, width, height);
        this.binType = binType;
        id = rootBinCounter;
        children = new LinkedList<Bin>();
    }

    private void addChild(Bin bin) {
        bin.parent = this;
        bin.id = id;
        children.add(bin);
    }


    public  void removeAltChildren() {
        if (parent != null)
            parent.children.removeIf((Bin bin) -> bin.binType != binType);
    }

    /**
     * Each box insertion creates 12 new bins. Each triplet has its class (A,B,C or D) exclusive to rest.
     * If one class is chosen the rest must be removed;
     */
    public void createChildren(Box box) {
        if (getLength() - box.getLength() > 0) {
            addChild(new Bin(getX() + box.getLength(), getY(), getZ(), getLength() - box.getLength(), getWidth(), getHeight(), BinType.A));
            addChild(new Bin(getX() + box.getLength(), getY(), getZ(), getLength()- box.getLength(), box.getWidth(), getHeight(), BinType.B));
            addChild(new Bin(getX() + box.getLength(), getY(), getZ(), getLength() - box.getLength(), getWidth(), box.getHeight(), BinType.C));
            addChild(new Bin(getX() + box.getLength(), getY(), getZ(), getLength()- box.getLength(), box.getWidth(), box.getHeight(), BinType.D));
        }
        if (getWidth() - box.getWidth() > 0) {
            addChild(new Bin(getX(), getY() + box.getWidth(), getZ(), box.getLength(), getWidth() - box.getWidth(), getHeight(), BinType.A));
            addChild(new Bin(getX(), getY()+ box.getWidth(), getZ(), getLength(), getWidth() - box.getWidth(), getHeight(), BinType.B));
            addChild(new Bin(getX(), getY() + box.getWidth(), getZ(), box.getLength(), getWidth() - box.getWidth(), box.getHeight(), BinType.C));
            addChild(new Bin(getX(), getY() + box.getWidth(), getZ(), getLength(), getWidth() - box.getWidth(), box.getHeight(), BinType.D));
        }
        if (getHeight() - box.getHeight() > 0) {
            addChild(new Bin(getX(), getY(), getZ() + box.getHeight(), box.getLength(), box.getWidth(), getHeight() - box.getHeight(), BinType.A));
            addChild(new Bin(getX(), getY(), getZ() + box.getHeight(), box.getLength(), box.getWidth(), getHeight() - box.getHeight(), BinType.B));
            addChild(new Bin(getX(), getY(), getZ() + box.getHeight(), getLength(), getWidth(), getHeight() - box.getHeight(), BinType.C));
            addChild(new Bin(getX(), getY(), getZ() + box.getHeight(), getLength(), getWidth(), getHeight() - box.getHeight(), BinType.D));
        }
    }

    public void reserveBin(Box box){
        box.setCoordinates(getX(), getY(), getZ());
        box.setBinId(id);
        setBinState(BinState.FULL);
    }

    public Bin search(SearchStrategy packingStrategy, Box box) {
        return packingStrategy.search(this, box);
    }

    public List<Bin> getBinChildren() {
        return children;
    }

    public void setBinState(BinState binState) {
        this.binState = binState;
    }

    public BinType getBinType() {
        return binType;
    }

    public BinState getBinState() {
        return binState;
    }

    
}