package BinPacking.Data.Logic.Bin;

import BinPacking.Data.Logic.BinSpace.BinSpace;
import BinPacking.Data.Logic.BinSpace.Dimensions;
import BinPacking.Data.Logic.BinSpace.Point;
import BinPacking.Data.UI.SceneModels.BinModel;
import BinPacking.Data.UI.SceneModels.GraphicsModel;
import BinPacking.Data.UI.SceneModels.SceneModel;

/**
 * Created by Xsignati on 24.01.2017.
 * The bin is a tree-like structure. Every bin contains a list of its bin children.
 */
public class Bin extends BinSpace implements GraphicsModel {
    public enum State {EMPTY, FULL}
    public enum Type {ROOT,A,B,C,D}
    private State state = State.EMPTY;
    private Type type;
    private SceneModel binModel;

    public Bin(Point point, Dimensions dimensions, Type type) {
        super(point, new Dimensions(dimensions.getLength(), dimensions.getWidth(), dimensions.getHeight()));
        this.type = type;
        createModel();
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

    public SceneModel getBinModel() {
        return binModel;
    }

    public void createModel(){
        binModel = new BinModel(this);
    }

    public void updateModel(){
        binModel = new BinModel(this);
    }

}