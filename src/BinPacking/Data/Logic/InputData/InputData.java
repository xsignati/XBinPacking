package BinPacking.Data.Logic.InputData;
import BinPacking.Data.Logic.BinTree.BinTree;
import BinPacking.Data.Logic.Box.Box;
import BinPacking.Logic.PackingStrategy.PackingStrategy;
import javafx.collections.ObservableList;

/**
 * Created by Xsignati on 21.03.2017.
 * Structure that is being passed from Controller (View) to BinPacker (logic).
 */
public class InputData {
    private final ObservableList<BinTree> binList;
    private final PackingStrategy packingStrategy;
    private final ObservableList<Box> boxList;
    private final double binLength;
    private final double binWidth;
    private final double binHeight;

    public InputData(double binLength, double binWidth, double binHeight, ObservableList<BinTree> binList, PackingStrategy packingStrategy, ObservableList<Box> boxList){
        this.binLength = binLength;
        this.binWidth = binWidth;
        this.binHeight = binHeight;
        this.binList = binList;
        this.packingStrategy = packingStrategy;
        this.boxList = boxList;
    }

    public ObservableList<BinTree> getBinList() {
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