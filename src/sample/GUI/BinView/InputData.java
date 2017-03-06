package sample.GUI.BinView;

import javafx.collections.ObservableList;
import sample.BinPackingLogic.PackingStrategy;

/**
 * Created by Xsignati on 01.03.2017.
 */
public class InputData{
    private ObservableList<Bin> binList;
    private PackingStrategy packingStrategy;
    private ObservableList<Box> boxList;
    private double binLength;
    private double binWidth;
    private double binHeight;

    public InputData(double binLength, double binWidth, double binHeight, ObservableList<Bin> binList, PackingStrategy packingStrategy, ObservableList<Box> boxList){
        this.binLength = binLength;
        this.binWidth = binWidth;
        this.binHeight = binHeight;
        this.binList = binList;
        this.packingStrategy = packingStrategy;
        this.boxList = boxList;
    }

    public ObservableList<Bin> getBinList() {
        return binList;
    }
    public PackingStrategy getPackingStrategy() {
        return packingStrategy;
    }
    public ObservableList<Box> getBoxList() {
        return boxList;
    }
    public double getBinLength() {return binLength;}
    public double getBinWidth() {return binWidth;}
    public double getBinHeight() {return binHeight;}

}