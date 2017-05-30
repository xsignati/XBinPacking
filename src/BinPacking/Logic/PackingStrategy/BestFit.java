package BinPacking.Logic.PackingStrategy;

import BinPacking.Data.LogicUI.BinTree;
import BinPacking.Data.LogicUI.BinTreeNode;
import javafx.collections.ObservableList;
import BinPacking.Data.LogicUI.Bin;
import BinPacking.Data.LogicUI.Box;

/**
 * Created by Xsignati on 24.01.2017.
 * Find and return the Bin with minimum volume.
 */
public class BestFit implements PackingStrategy {
    @Override
    public BinTree search(BinTree binTreeNode, Box box) {
        BinTree minBinNode;

        if(isBinEmptyAndFitToBox(binTreeNode, box))
            minBinNode = binTreeNode;
        else
            minBinNode = null;

        for (BinTree child : binTreeNode.getChildren())
            minBinNode = min(minBinNode, search(child, box));

        return minBinNode;
    }

    private boolean isBinEmptyAndFitToBox(BinTree binTreeNode, Box box){
        Bin bin = binTreeNode.getData();
        return bin.getState() == Bin.State.EMPTY && boxFitsToBin(bin, box);
    }

    private BinTree min(BinTree firstNode, BinTree secondNode){
        BinTree minimumVolumeNode;
        if(isEmpty(firstNode))
            minimumVolumeNode = secondNode;
        else
            minimumVolumeNode = continueSearch(firstNode, secondNode);

        return minimumVolumeNode;
    }

    private boolean isEmpty(BinTree node){
        return node == null;
    }

    private BinTree continueSearch(BinTree firstNode, BinTree secondNode){
        if(isEmpty(secondNode))
            return firstNode;
        else
            return getSmallerVolumeNode(firstNode, secondNode);
    }

    private BinTree getSmallerVolumeNode(BinTree firstNode, BinTree secondNode){
        Bin first = firstNode.getData();
        Bin second = secondNode.getData();
        if(isFirstGreater(first, second))
            return secondNode;
        else
            return firstNode;
    }

    private boolean isFirstGreater(Bin first, Bin second){
        return second.getVolume() < first.getVolume();
    }

    @Override
    public void prepareInput(ObservableList<Box> boxList){
        boxList.sort((b1,b2) -> b2.getVolume().compareTo(b1.getVolume()));
    }
}

