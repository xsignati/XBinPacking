package sample.BinPackingLogic;

import javafx.collections.ObservableList;
import sample.GUI.BinView.Bin;
import sample.GUI.BinView.Box;

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

    protected abstract void prepareInput(ObservableList<Box> boxList);
}
