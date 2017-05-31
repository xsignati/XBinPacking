package BinPacking.Data.Logic.Box;

import BinPacking.Data.Logic.BinSpace.*;
import BinPacking.Data.Logic.Rotation.*;
import BinPacking.Data.UI.SceneModels.*;

/**
 * Created by Xsignati on 24.01.2017.
 */
public class Box extends BinSpace implements Rotatable, GraphicsModel {
    private Rotator boxRotator;
    private SceneModel boxModel;

    public void rotate(){
        boxRotator.rotate(this);
    }

    public Box(Dimensions dimensions) {
        super(new Point(0,0,0), dimensions);
        boxRotator = new BoxRotator();
        createModel();
    }

    public SceneModel getBoxModel() {
        return boxModel;
    }

    public void createModel(){
        boxModel = new BoxModel(this);
    }

    public void updateModel(){
        boxModel = new BoxModel(this);
    }
}
