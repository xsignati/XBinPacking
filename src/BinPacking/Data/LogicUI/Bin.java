package BinPacking.Data.LogicUI;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import BinPacking.Logic.PackingStrategy.PackingStrategy;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by Xsignati on 24.01.2017.
 * The bin is a tree-like structure. Every bin contains a list of its bin children.
 */
public class Bin extends BinSpace implements SceneModel {
    public enum State {EMPTY, FULL}
    public enum Type {ROOT,A,B,C,D}
    private State state = State.EMPTY;
    private Type type;
    private Bin parent;
    private final List<Bin> children;
    //private static int rootBinCounter; //< Each box gets this id. The id indicates the bin assigned to box.
    private final BinModel model = new BinModel();

    /**
     * A public constructor used to construct a new ROOT Bin.
     * @param length length of the bin.
     * @param width width of the bin.
     * @param height height of the bin.
     */
    public Bin(Dimensions dimensions){
        this(dimensions, Color.GREY);
    }

    /**
     * A Public constructor used to construct a new ROOT Bin.
     * @param length length of the bin.
     * @param width width of the bin.
     * @param height height of the bin.
     * @param color bin frame color.
     */
    public Bin(Dimensions dimensions, Color color){
        this(new Point(0,0,0),dimensions, Type.ROOT);
        //rootBinCounter++;
        model.createGraphicModel(dimensions.getLength(), dimensions.getWidth(), dimensions.getHeight(), color); //< Appearance part of code
    }

    /**
     * A private constructor used to construct a new ROOT Bin or CHILD bin
     * @param x x coordinate of the bin.
     * @param y y coordinate of the bin.
     * @param z z coordinate of the bin.
     * @param length length of the bin.
     * @param width width of the bin.
     * @param height height of the bin.
     * @param type type id needed to perform box insertion with alternate configurations.
     */
    public Bin(Point point, Dimensions dimensions, Type type) {
        super(point, new Dimensions(dimensions.getLength(), dimensions.getWidth(), dimensions.getHeight()));
        this.type = type;
        //setId(rootBinCounter);
        children = new LinkedList<>();
    }

    /**
     * @param bin a subspace of its parent bin.
     */
    private void addChild(Bin bin) {
        bin.parent = this;
        bin.setId(getId());
        children.add(bin);
    }

    /**
     * Every box insertion creates 12 new bins (3 subspaces in XYZ direction in 4 alternate versions). Each triplet has its BinType (A,B,C or D) exclusive to rest.
     * If one BinType is chosen the rest must be removed. The BinTypes represent new spaces created after box insertion.
     * @param box Box object.
     */
//    public void addPossibleSubspacesFor(Box box) {
//        if (getLength() - box.getLength() > 0) {
//            addChild(new Bin(getX() + box.getLength(), getY(), getZ(), getLength() - box.getLength(), getWidth(), getHeight(), Type.A));
//            addChild(new Bin(getX() + box.getLength(), getY(), getZ(), getLength()- box.getLength(), box.getWidth(), getHeight(), Type.B));
//            addChild(new Bin(getX() + box.getLength(), getY(), getZ(), getLength() - box.getLength(), getWidth(), box.getHeight(), Type.C));
//            addChild(new Bin(getX() + box.getLength(), getY(), getZ(), getLength()- box.getLength(), box.getWidth(), box.getHeight(), Type.D));
//        }
//        if (getWidth() - box.getWidth() > 0) {
//            addChild(new Bin(getX(), getY() + box.getWidth(), getZ(), box.getLength(), getWidth() - box.getWidth(), getHeight(), Type.A));
//            addChild(new Bin(getX(), getY()+ box.getWidth(), getZ(), getLength(), getWidth() - box.getWidth(), getHeight(), Type.B));
//            addChild(new Bin(getX(), getY() + box.getWidth(), getZ(), box.getLength(), getWidth() - box.getWidth(), box.getHeight(), Type.C));
//            addChild(new Bin(getX(), getY() + box.getWidth(), getZ(), getLength(), getWidth() - box.getWidth(), box.getHeight(), Type.D));
//        }
//        if (getHeight() - box.getHeight() > 0) {
//            addChild(new Bin(getX(), getY(), getZ() + box.getHeight(), box.getLength(), box.getWidth(), getHeight() - box.getHeight(), Type.A));
//            addChild(new Bin(getX(), getY(), getZ() + box.getHeight(), box.getLength(), box.getWidth(), getHeight() - box.getHeight(), Type.B));
//            addChild(new Bin(getX(), getY(), getZ() + box.getHeight(), getLength(), getWidth(), getHeight() - box.getHeight(), Type.C));
//            addChild(new Bin(getX(), getY(), getZ() + box.getHeight(), getLength(), getWidth(), getHeight() - box.getHeight(), Type.D));
//        }
//    }

    /**
     * If the box fits to the one of 3 subspaces forming a BinType, eg. A rest of them will be removed (B,C,D)
     */
    public  void removeNotSelectedSubspaces() {
        if (parent != null)
            parent.children.removeIf(bin -> bin.type != type);
    }

    /**
     * Assign the Bin to the Box. The bin will be no longer available to assignation.
     * @param box Box Object.
     */
    public void reserveBin(Box box){
        box.setCoordinates(getX(), getY(), getZ());
        box.setId(getId());
        setState(State.FULL);
    }

    /**
     * The method search the ROOT bin to find a suitable subspace.
     * @param packingStrategy the bin search method is dependent of used packing strategy.
     * @param box Box Object.
     * @return found bin or null.
     */
//    public BinTree search(PackingStrategy packingStrategy, Box box) {
//        return packingStrategy.search(this, box);
//    }

    public List<Bin> getBinChildren() {
        return children;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

//    public static void resetRootBinCounter(){
//        Bin.rootBinCounter = 0;
//    }

    /**
     * A Graphic model used to display a bin in the GUI.
     */
    public class BinModel{
        //Appearance
        private final Group modelGroup = new Group();
        private final int thickness = 10;
        private final double[][] EDGES_SIZES = {{0, 0, 1}, {0, 0, 1}, {0, 0, 1}, {0, 0, 1}, {1, 0, 0}, {1, 0, 0}, {1, 0, 0}, {1, 0, 0}, {0, 1, 0}, {0, 1, 0}, {0, 1, 0}, {0, 1, 0}};
        private final double[][] EDGES_POSITIONS = {{0, 0, 0}, {1, 0, 0}, {0, 1, 0}, {1, 1, 0}, {0, 0, 0}, {0, 1, 0}, {0, 0, 1}, {0, 1, 1}, {0, 0, 0}, {1, 0, 0}, {0, 0, 1}, {1, 0, 1}};
        private final double[][] EDGES_SHIFTS = {{1, 1, 0}, {0, 1, 0}, {1, 0, 0}, {0, 0, 0}, {0, 1, 1}, {0, 0, 1}, {0, 1, 0}, {0, 0, 0}, {1, 0, 1}, {0, 0, 1}, {1, 0, 0}, {0, 0, 0}};
        private final PhongMaterial material = new PhongMaterial();
        private final javafx.scene.shape.Box[] edges = new javafx.scene.shape.Box[12];
        private final double SHIFT_RATIO = 0.5;

        //Only the outer class should be able to create its BoxModel
        private BinModel(){}

        public void scale(double scale) {
            Arrays.stream(edges).forEach(i -> {
                i.setWidth(i.getWidth() * scale);
                i.setHeight(i.getHeight() * scale);
                i.setDepth(i.getDepth() * scale);
                i.setTranslateX(i.getTranslateX() * scale);
                i.setTranslateY(i.getTranslateY() * scale);
                i.setTranslateZ(i.getTranslateZ() * scale);
            });
        }

        public void createGraphicModel(double length, double width, double height, Color color) {
            material.setSpecularColor(Color.DARKGREY);
            material.setDiffuseColor(color);
            IntStream.range(0, edges.length).forEach( i -> {
                edges[i] = new javafx.scene.shape.Box(EDGES_SIZES[i][0] * length + thickness, EDGES_SIZES[i][1] * (width) + thickness, EDGES_SIZES[i][2] * (height) + thickness);
                edges[i].setTranslateX(EDGES_POSITIONS[i][0] * length + SHIFT_RATIO * edges[i].getWidth() - EDGES_SHIFTS[i][0] * thickness);
                edges[i].setTranslateY(EDGES_POSITIONS[i][1] * width + SHIFT_RATIO * edges[i].getHeight() - EDGES_SHIFTS[i][1] * thickness);
                edges[i].setTranslateZ(EDGES_POSITIONS[i][2] * height + SHIFT_RATIO * edges[i].getDepth() - EDGES_SHIFTS[i][2] * thickness);
                edges[i].setMaterial(material);
                modelGroup.getChildren().add(edges[i]);
            }
            );
        }
    }

    @Override
    public void scale(double scale){
        model.scale(scale);
    }

    @Override
    public void addModel(Group binSceneElements){
        binSceneElements.getChildren().add(model.modelGroup);
    }

    @Override
    public void addModel(Group binSceneElements, int id){
        if(getId() == id)
            binSceneElements.getChildren().add(model.modelGroup);
    }
}