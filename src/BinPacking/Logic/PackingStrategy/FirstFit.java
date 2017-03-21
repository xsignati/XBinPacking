package BinPacking.Logic.PackingStrategy;

import javafx.collections.ObservableList;
import BinPacking.Data.LogicUI.Bin;
import BinPacking.Data.LogicUI.Box;

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
    public void prepareInput(ObservableList<Box> boxList){
        Collections.sort(boxList);
    }
}