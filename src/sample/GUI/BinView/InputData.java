package sample.GUI.BinView;

import javafx.collections.ObservableList;
import sample.BinPackingLogic.Bin;
import sample.BinPackingLogic.Box;
import sample.BinPackingLogic.SearchStrategy;

/**
 * Created by Xsignati on 01.03.2017.
 */
public class InputData{
    private Bin bin;
    private SearchStrategy packingStrategy;
    private ObservableList<Box> boxList;

    public InputData(Bin bin, SearchStrategy packingStrategy, ObservableList<Box> boxList){
        this.bin = bin;
        this.packingStrategy = packingStrategy;
        this.boxList = boxList;
    }

    public Bin getBin() {
        return bin;
    }

    public void setBin(Bin bin) {
        this.bin = bin;
    }

    public SearchStrategy getPackingStrategy() {
        return packingStrategy;
    }

    public ObservableList<Box> getBoxList() {
        return boxList;
    }
}