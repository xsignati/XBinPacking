package BinPacking.Data.UI.SceneModels;

import BinPacking.Data.Logic.Box.Box;
import javafx.collections.ObservableList;
import javafx.scene.Group;

/**
 * Created by Xsignati on 31.05.2017.
 */
public class BoxesWrapper implements SceneModel, Wrapper {
    private final ObservableList<Box> boxList;

    public BoxesWrapper(ObservableList<Box> binList){
        this.boxList = binList;
    }

    public synchronized ObservableList<Box> get() {
        return boxList;
    }
    public synchronized void clear(){
        boxList.clear();
    }
    @Override
    public void addModel(Group binSceneModels){
        boxList.stream().forEach(i -> i.getBoxModel().addModel(binSceneModels));
    }
    @Override
    public void addModel(Group binSceneModels, int id){
        boxList.stream().forEach(i -> i.getBoxModel().addModel(binSceneModels, id));
    }
    @Override
    public void scale(double scale) {
        boxList.stream().forEach(i -> i.getBoxModel().scale(scale));
    }
}
