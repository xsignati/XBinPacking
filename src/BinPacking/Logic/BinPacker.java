package BinPacking.Logic;

import BinPacking.Data.LogicUI.*;

/**
 * Created by Xsignati on 24.01.2017.
 * Main logic class responsible for assigning boxes to binsTrees.
 */
public class BinPacker implements Packer {

    public void pack(InputData inputData) {
        prepareInputAndAddBin(inputData);
        for(Box box: inputData.getBoxList()) {
            int binListSize = inputData.getBinList().size();
            fitBoxOrCreateBinTree(inputData, box, binListSize);
        }
    }

    private void prepareInputAndAddBin(InputData inputData){
        inputData.getPackingStrategy().prepareInput(inputData.getBoxList());
        inputData.getBinList().add(BinTreeNode.rootNode((new Bin(new Dimensions(inputData.getBinLength(), inputData.getBinWidth(), inputData.getBinHeight())))));
    }

    private void fitBoxOrCreateBinTree(InputData inputData, Box box, int binListSize){
        for(int currentNode = 0 ; currentNode <= binListSize; currentNode++){
            if(BoxFitsToBin(inputData, box, currentNode))
                return;
            else if (currentNode == binListSize - 1)
                createNewBinTree(inputData);

        }
    }

    private boolean BoxFitsToBin(InputData inputData, Box box, int currentNode){
        for (int currentRotation = 0; currentRotation < BoxRotator.ROTATIONS_NUM; currentRotation++) {
            BinTree foundNode = inputData.getBinList().get(currentNode).search(inputData.getPackingStrategy(), box);
            if (nodeExists(foundNode)) {
                foundNode.reserveBinFor(box);
                foundNode.tryToAddSubspacesFor(box);
                foundNode.removeNotSelectedSubspaces();
                return true;
            }
            else
                box.rotate();
        }
        return false;
    }

    private void createNewBinTree(InputData inputData){
        inputData.getBinList().add(BinTreeNode.rootNode(new Bin(new Dimensions(inputData.getBinLength(), inputData.getBinWidth(), inputData.getBinHeight()))));
    }

    private boolean nodeExists(BinTree foundNode){
        return foundNode != null;
    }
}

