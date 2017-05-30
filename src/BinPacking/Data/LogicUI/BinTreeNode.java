package BinPacking.Data.LogicUI;

import BinPacking.Logic.PackingStrategy.PackingStrategy;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Xsignati on 27.05.2017.
 */
public class BinTreeNode implements BinTree{
    private static int rootNodesCounter;
    private BinTree parent;
    private List<BinTree> children;
    private Bin bin;
    private int id;

    private BinTreeNode(Bin bin) {
        id = rootNodesCounter;
        this.bin = bin;
        bin.setId(id);
        this.children = new LinkedList<>();
    }

    @Override
    public void addChildWith(Bin child){
        BinTree childNode = new BinTreeNode(child);
        childNode.setParent(this);
        childNode.setId(id);
        children.add(childNode);
    }

    @Override
    public BinTree getParent(){
        return parent;
    }

    @Override
    public void setParent(BinTree binTree){
        parent = binTree;
    }

    @Override
    public List<BinTree> getChildren(){
        return children;
    }

    @Override
    public Bin getData(){
        return bin;
    }

    @Override
    public void setId(int id){
        this.id = id;
    }

    @Override
    public int getId(){
        return id;
    }

    public static BinTree rootNode(Bin bin){
        rootNodesCounter++;
        return new BinTreeNode(bin);
    }

    /**
     * Every box insertion creates 12 new bins (3 subspaces in XYZ direction in 4 alternate versions). Each triplet has its BinType (A,B,C or D) exclusive to rest.
     * If one BinType is chosen the rest must be removed. The BinTypes represent new spaces created after box insertion.
     * @param box Box object.
     */
    public void tryToAddSubspacesFor(Box box) {
        if (isFirstArgGreater(bin.getLength(), box.getLength())) {
            addChildWith(new Bin(new Point(bin.getX() + box.getLength(), bin.getY(), bin.getZ()), new Dimensions(bin.getLength() - box.getLength(), bin.getWidth(), bin.getHeight()), Bin.Type.A));
            addChildWith(new Bin(new Point(bin.getX() + box.getLength(), bin.getY(), bin.getZ()), new Dimensions(bin.getLength()- box.getLength(), box.getWidth(), bin.getHeight()), Bin.Type.B));
            addChildWith(new Bin(new Point(bin.getX() + box.getLength(), bin.getY(), bin.getZ()), new Dimensions(bin.getLength() - box.getLength(), bin.getWidth(), box.getHeight()), Bin.Type.C));
            addChildWith(new Bin(new Point(bin.getX() + box.getLength(), bin.getY(), bin.getZ()), new Dimensions(bin.getLength()- box.getLength(), box.getWidth(), box.getHeight()), Bin.Type.D));
        }
        if (isFirstArgGreater(bin.getWidth(), box.getWidth())) {
            addChildWith(new Bin(new Point(bin.getX(), bin.getY() + box.getWidth(), bin.getZ()), new Dimensions(box.getLength(), bin.getWidth() - box.getWidth(), bin.getHeight()), Bin.Type.A));
            addChildWith(new Bin(new Point(bin.getX(), bin.getY() + box.getWidth(), bin.getZ()), new Dimensions(bin.getLength(), bin.getWidth() - box.getWidth(), bin.getHeight()), Bin.Type.B));
            addChildWith(new Bin(new Point(bin.getX(), bin.getY() + box.getWidth(), bin.getZ()), new Dimensions(box.getLength(), bin.getWidth() - box.getWidth(), box.getHeight()), Bin.Type.C));
            addChildWith(new Bin(new Point(bin.getX(), bin.getY() + box.getWidth(), bin.getZ()), new Dimensions(bin.getLength(), bin.getWidth() - box.getWidth(), box.getHeight()), Bin.Type.D));
        }
        if (isFirstArgGreater(bin.getHeight(), box.getHeight())) {
            addChildWith(new Bin(new Point(bin.getX(), bin.getY(), bin.getZ() + box.getHeight()), new Dimensions(box.getLength(), box.getWidth(), bin.getHeight() - box.getHeight()), Bin.Type.A));
            addChildWith(new Bin(new Point(bin.getX(), bin.getY(), bin.getZ() + box.getHeight()), new Dimensions(box.getLength(), box.getWidth(), bin.getHeight() - box.getHeight()), Bin.Type.B));
            addChildWith(new Bin(new Point(bin.getX(), bin.getY(), bin.getZ() + box.getHeight()), new Dimensions(bin.getLength(), bin.getWidth(), bin.getHeight() - box.getHeight()), Bin.Type.C));
            addChildWith(new Bin(new Point(bin.getX(), bin.getY(), bin.getZ() + box.getHeight()), new Dimensions(bin.getLength(), bin.getWidth(), bin.getHeight() - box.getHeight()), Bin.Type.D));
        }
    }

    private boolean isFirstArgGreater(double first, double second){
        return first > second;
    }

    public void removeNotSelectedSubspaces() {
        if (parentExists())
            parent.getChildren().removeIf(node -> node.getData().getType() != bin.getType());
    }

    private boolean parentExists(){
        return parent != null;
    }

    public void reserveBinFor(Box box){
        giveCoordinatesAndIdTo(box);
        changeStateToFull();
    }

    private void giveCoordinatesAndIdTo(Box box){
        box.setCoordinates(bin.getX(), bin.getY(), bin.getZ());
        box.setId(id);
    }

    private void changeStateToFull(){
        bin.setState(Bin.State.FULL);
    }

    public BinTree search(PackingStrategy packingStrategy, Box box) {
        return packingStrategy.search(this, box);
    }
}