package BinPacking.Logic.PackingStrategy;

import BinPacking.Data.LogicUI.BinTree;
import BinPacking.Data.LogicUI.BinTreeNode;
import javafx.collections.ObservableList;
import BinPacking.Data.LogicUI.Bin;
import BinPacking.Data.LogicUI.Box;

/**
 * Created by Xsignati on 24.01.2017.
 */
public interface PackingStrategy {
    /**
     * A recurrent method that search for a Bin that meet the conditions. The function look through Bin children
     * (the RootBin by default)
     * @param bin
     * @param box
     * @return bin that meet the conditions
     */
    BinTree search(BinTree binTreeNode, Box box);

    default boolean boxFitsToBin(Bin bin, Box box) {
        return (box.getLength() <= bin.getLength() &&
                box.getWidth() <= bin.getWidth() &&
                box.getHeight() <= bin.getHeight());
    }

    void prepareInput(ObservableList<Box> boxList);
}
