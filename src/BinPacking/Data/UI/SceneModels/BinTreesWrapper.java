package BinPacking.Data.UI.SceneModels;

import BinPacking.Data.Logic.BinTree.BinTree;
import javafx.collections.ObservableList;
import javafx.scene.Group;

/**
 * Created by Xsignati on 31.05.2017.
 */
public class BinTreesWrapper implements SceneModel, Wrapper{
    private final ObservableList<BinTree> binList;

    public BinTreesWrapper(ObservableList<BinTree> binList){
        this.binList = binList;
    }

    public synchronized ObservableList<BinTree> get() {
        return binList;
    }
    public synchronized void clear(){
        binList.clear();
    }

    @Override
    public void addModel(Group binSceneModels){
        binList.forEach(i -> i.getData().getBinModel().addModel(binSceneModels));
    }
    @Override
    public void addModel(Group binSceneModels, int id){
        binList.forEach(i -> i.getData().getBinModel().addModel(binSceneModels, id));
    }
    @Override
    public void scale(double scale) {
        binList.forEach(i -> i.getData().getBinModel().scale(scale));
    }
}
