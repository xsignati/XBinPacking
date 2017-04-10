package BinPacking.Data.LogicUI;

import BinPacking.Data.LogicUI.Bin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Xsignati on 08.04.2017.
 */
public class BinList {
    private final ObservableList<Bin> binList = FXCollections.observableArrayList();
    public synchronized void add(Bin bin){
        binList.add(bin);
    }
    public synchronized ObservableList<Bin> get() {
        return binList;
    }
    public synchronized void clear(){
        binList.clear();
    }
}