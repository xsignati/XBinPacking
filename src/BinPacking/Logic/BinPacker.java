package BinPacking.Logic;

import BinPacking.Data.LogicUI.*;

/**
 * Created by Xsignati on 24.01.2017.
 * Main logic class. It's responsible for assignation boxes to bins.
 */
public class BinPacker implements Packer {
    Rotator boxRotator = new BoxRotator();

    /**
     * Run the packing process.
     * @param d the input data that contains bin size, boxes list, chose packing strategy and empty bins list.
     */
    public void pack(InputData d) {
        // Prepare the input, e.g sort boxes by their volume values
        d.getPackingStrategy().prepareInput(d.getBoxList());
        // Reset the id counter used to marking new bins
        Bin.resetRootBinCounter();
        //Add the first bin
        d.getBinList().add(new Bin(d.getBinLength(), d.getBinWidth(), d.getBinHeight()));

        for(Box box: d.getBoxList()) {
            int binListSize = d.getBinList().size();

            BinListLoop:
            for(int i = 0 ; i <= binListSize; i++){
                for (int j = 0; j < boxRotator.limit(); j++) {
                    Bin foundNode = d.getBinList().get(i).search(d.getPackingStrategy(), box); //< set the PackingStrategy search method
                    if (foundNode != null) {
                        foundNode.reserveBin(box);
                        foundNode.createChildren(box);
                        foundNode.removeAltSiblings();
                        break BinListLoop;
                    }
                    else
                        boxRotator.rotate(box);
                }

                if (i == binListSize - 1)
                    d.getBinList().add(new Bin(d.getBinLength(), d.getBinWidth(), d.getBinHeight()));
            }
        }
    }
}

