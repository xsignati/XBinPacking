package BinPacking.Logic.PackingStrategy;

import javafx.collections.ObservableList;
import BinPacking.Data.LogicUI.Bin;
import BinPacking.Data.LogicUI.Box;

/**
 * Created by Xsignati on 24.01.2017.
 */
public abstract class PackingStrategy {
    /**
     * A recurrent method that search for a Bin that meet the conditions. The function look through Bin children
     * (the RootBin by default)
     * @param bin
     * @param box
     * @return bin that meet the conditions
     */
    public abstract Bin search(Bin bin, Box box);

    boolean boxFitsToBin(Bin bin, Box box) {
        return (box.getLength() <= bin.getLength() &&
                box.getWidth() <= bin.getWidth() &&
                box.getHeight() <= bin.getHeight());
    }

    public abstract void prepareInput(ObservableList<Box> boxList);
}
