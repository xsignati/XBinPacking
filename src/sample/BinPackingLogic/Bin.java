package sample.BinPackingLogic;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Flexscan2243 on 24.01.2017.
 */
class Bin extends Cuboid {
    //**********************************************************************************************
    //Binstate
    public enum BinState {EMPTY, FULL}
    private BinState binState = BinState.EMPTY;

    //Each box insertion creates 12 new bins. Each triplet has its class (A,B,C or D) exclusive to rest.
    //If one class is chosen the rest must be removed;
    public enum BinType {ROOT,A,B,C,D}
    public BinType binType;

    public BinState getBinState() {
        return binState;
    }

    public void setBinState(BinState binState) {
        this.binState = binState;
    }

    public BinType getBinType() {
        return binType;
    }
    //**********************************************************************************************
    private static int rootBinCounter = 0;
    public Bin parent;
    private List<Bin> children;
    private int id;

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
        if (parent != null) {
//            for (Iterator<Bin> iterator = parent.children.iterator(); iterator.hasNext(); ) {
//                Bin binSpace = iterator.next();
//                if (binSpace.binType != binType) {
//                    iterator.remove();
//                }
//            }
            parent.children.removeIf((Bin bin) -> bin.binType != binType);
        }

    }

    public void createChildren(Box box) {
        if (length - box.getLength() > 0) {
            addChild(new Bin(x + box.getLength(), y, z, length - box.getLength(), width, height, BinType.A));
            addChild(new Bin(x + box.getLength(), y, z, length - box.getLength(), box.getWidth(), height, BinType.B));
            addChild(new Bin(x + box.getLength(), y, z, length - box.getLength(), width, box.getHeight(), BinType.C));
            addChild(new Bin(x + box.getLength(), y, z, length - box.getLength(), box.getWidth(), box.getHeight(), BinType.D));
        }
        if (width - box.getWidth() > 0) {
            addChild(new Bin(x, y + box.getWidth(), z, box.getLength(), width - box.getWidth(), height, BinType.A));
            addChild(new Bin(x, y + box.getWidth(), z, length, width - box.getWidth(), height, BinType.B));
            addChild(new Bin(x, y + box.getWidth(), z, box.getLength(), width - box.getWidth(), box.getHeight(), BinType.C));
            addChild(new Bin(x, y + box.getWidth(), z, length, width - box.getWidth(), box.getHeight(), BinType.D));
        }
        if (height - box.getHeight() > 0) {
            addChild(new Bin(x, y, z + box.getHeight(), box.getLength(), box.getWidth(), height - box.getHeight(), BinType.A));
            addChild(new Bin(x, y, z + box.getHeight(), box.getLength(), box.getWidth(), height - box.getHeight(), BinType.B));
            addChild(new Bin(x, y, z + box.getHeight(), length, width, height - box.getHeight(), BinType.C));
            addChild(new Bin(x, y, z + box.getHeight(), length, width, height - box.getHeight(), BinType.D));
        }
    }

    public void reserveBin(Box box){
        box.setCoordinates(x, y, z);
        box.setBinId(id);
        setBinState(BinState.FULL);
    }

    public Bin search(SearchStrategy packingStrategy, Box box) {
        return packingStrategy.search(this, box);
    }

    public List<Bin> getChildren() {
        return children;
    }
}