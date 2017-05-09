package BinPacking.Data.LogicUI;

import BinPacking.Data.LogicUI.Bin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.transform.Scale;

/**
 * Created by Xsignati on 08.04.2017.
 */
public class BinList implements SceneModel{
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
    @Override
    public void addModel(Group binSceneModels){
        binList.stream().forEach(i -> i.addModel(binSceneModels));
    }
    @Override
    public void addModel(Group binSceneModels, int id){
        binList.stream().forEach(i -> i.addModel(binSceneModels, id));
    }
    @Override
    public void scale(double scale) {
        binList.stream().forEach(i -> i.scale(scale));
    }
}