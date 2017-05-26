package BinPacking.Logic.PackingStrategy;

import javafx.collections.ObservableList;
import BinPacking.Data.LogicUI.Bin;
import BinPacking.Data.LogicUI.Box;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Xsignati on 24.01.2017.
 * Find and return the Bin with minimum volume.
 */
public class BestFit implements PackingStrategy {
    @Override
    public Bin search(Bin bin, Box box) {
        Bin minBin;

        if(bin.getBinState() == Bin.BinState.EMPTY && boxFitsToBin(bin, box))
            minBin = bin;
        else
            minBin = null;

        for (Bin child : bin.getBinChildren()) {
            minBin = min(minBin, search(child, box));
        }

        return minBin;
    }

    private Bin min(Bin bin1, Bin bin2){
        Bin minNode;
        if(bin1 == null)
            minNode = bin2;
        else{
            if(bin2 == null)
                minNode = bin1;
            else{
                if(bin2.getVolume() < bin1.getVolume())
                    minNode = bin2;
                else
                    minNode = bin1;
            }
        }
        return minNode;
    }

    @Override
    public void prepareInput(ObservableList<Box> boxList){
        boxList.sort((b1,b2) -> b2.getVolume().compareTo(b1.getVolume()));
    }
}

