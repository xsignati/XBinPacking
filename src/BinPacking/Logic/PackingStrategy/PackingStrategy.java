package BinPacking.Logic.PackingStrategy;

import javafx.collections.ObservableList;
import BinPacking.Data.LogicUI.Bin;
import BinPacking.Data.LogicUI.Box;

/**
 * Created by Xsignati on 24.01.2017.
 */
public abstract class PackingStrategy {
    public abstract Bin search(Bin bin, Box box);

    protected boolean boxFitsToBin(Bin bin, Box box) {
        return (box.getLength() <= bin.getLength() &&
                box.getWidth() <= bin.getWidth() &&
                box.getHeight() <= bin.getHeight());
    }

    public abstract void prepareInput(ObservableList<Box> boxList);
}
