package sample.BinPackingLogic;

import javafx.collections.ObservableList;
import sample.GUI.BinView.Bin;
import sample.GUI.BinView.Box;

import java.util.Collections;

/**
 * Created by Xsignati on 24.01.2017.
 */
public class FirstFit extends PackingStrategy {
    @Override
    public Bin search(Bin bin, Box box) {
        if(bin.getBinState() == Bin.BinState.FULL) {
            for (Bin subBin : bin.getBinChildren()) {
                Bin foundNode;
                if((foundNode = search(subBin, box)) != null) return foundNode;
            }
        }
        else if (boxFitsToBin(bin, box))
            return bin;
        return null;
    }

    @Override
    protected void prepareInput(ObservableList<Box> boxList){
        Collections.sort(boxList);
    }
}