package BinPacking.Data.LogicUI;

import BinPacking.Data.LogicUI.Box;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Xsignati on 08.04.2017.
 */
public class BoxList {
    private final ObservableList<Box> boxList = FXCollections.observableArrayList();
    public synchronized void add(Box box){
        boxList.add(box);
    }
    public synchronized ObservableList<Box> get() {
        return boxList;
    }
    public synchronized void clear(){
        boxList.clear();
    }
}