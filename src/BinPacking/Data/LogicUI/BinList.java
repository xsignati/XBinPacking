package BinPacking.Data.LogicUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;

/**
 * Created by Xsignati on 08.04.2017.
 * A wrapper for list of bins.
 */
public class BinList implements SceneModel{
    private final ObservableList<BinTree> binList = FXCollections.observableArrayList();
    public synchronized void add(BinTree bin){
        binList.add(bin);
    }
    public synchronized ObservableList<BinTree> get() {
        return binList;
    }
    public synchronized void clear(){
        binList.clear();
    }
    @Override
    public void addModel(Group binSceneModels){
        binList.stream().forEach(i -> i.getData().addModel(binSceneModels));
    }
    @Override
    public void addModel(Group binSceneModels, int id){
        binList.forEach(i -> i.getData().addModel(binSceneModels, id));
    }
    @Override
    public void scale(double scale) {
        binList.stream().forEach(i -> i.getData().scale(scale));
    }
}