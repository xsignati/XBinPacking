package BinPacking.Logic.PackingStrategy;

import javafx.collections.ObservableList;
import BinPacking.Data.LogicUI.Bin;
import BinPacking.Data.LogicUI.Box;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Xsignati on 24.01.2017.
 */
public class AreaBestFit extends PackingStrategy {
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
        Bin minBin;
        if(bin1 == null)
            minBin = bin2;
        else{
            if(bin2 == null)
                minBin = bin1;
            else{
                if(bin2.getZ() == bin1.getZ()){
                    if (bin2.getVolume() < bin1.getVolume())
                        minBin = bin2;
                    else
                        minBin = bin1;
                }
                else if(bin2.getZ() < bin1.getZ()){
                    minBin = bin2;
                }
                else
                    minBin = bin1;
            }
        }
        return minBin;
    }

    @Override
    public void prepareInput(ObservableList<Box> boxList){
        boxList.sort((b1,b2) -> b2.getVolume().compareTo(b1.getVolume()));
    }

}
