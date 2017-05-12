package BinPacking.Data.LogicUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
/**
 * Created by Xsignati on 08.04.2017.
 * A wrapper for list of boxes.
 */
public class BoxList implements SceneModel{
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
    @Override
    public void addModel(Group binSceneModels){
        boxList.stream().forEach(i -> i.addModel(binSceneModels));
    }
    @Override
    public void addModel(Group binSceneModels, int id){
        boxList.stream().forEach(i -> i.addModel(binSceneModels, id));
    }
    @Override
    public void scale(double scale) {
        boxList.stream().forEach(i -> i.scale(scale));
    }

}