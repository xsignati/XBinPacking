package sample.BinPackingLogic;

import javafx.collections.ObservableList;
import sample.GUI.BinView.InputData;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Xsignati on 24.01.2017.
 */
public class Loader {

    public void run(InputData d) {
        /**
         * Main Loader loop. Try to fit to the container all boxes.
         */

        d.getPackingStrategy().prepareInput(d.getBoxList());
        d.getBinList().add(new Bin(d.getBinLength(), d.getBinWidth(), d.getBinHeight()));

        for(Box box: d.getBoxList()) {
            int binListSize = d.getBinList().size();

            BinListLoop:
            for(int i = 0 ; i <= binListSize; i++){
                for (int j = 0; j < Box.ROTATIONS_NUM; j++) {
                    Bin foundNode = d.getBinList().get(i).search(d.getPackingStrategy(), box);
                    Box.Rotations currRotation = Box.Rotations.values()[j];
                    if (foundNode != null) {
                        foundNode.reserveBin(box);
                        foundNode.createChildren(box);
                        foundNode.removeAltChildren();
                        break BinListLoop;
                    } else {
                        box.rotate(currRotation);
                        System.out.println("rotation");
                    }
                }

                if (i == binListSize - 1)
                    d.getBinList().add(new Bin(d.getBinLength(), d.getBinWidth(), d.getBinHeight()));
            }
            System.out.println("RESULT: " + "X: " + box.getX() + " Y: " + box.getY() + " Z: " + box.getZ() +
                    "  length: " + box.getLength() + "width: " + box.getWidth() + "height: " + box.getHeight() + "containerID: " + box.getCid());
        }
    }

    public static void main(String argv[]) {
        System.out.println("main");
        Loader loader = new Loader();
        SearchStrategy firstFit = new FirstFit();
        SearchStrategy bestFit = new BestFit();
        SearchStrategy areaBestFit = new AreaBestFit();
        //loader.run(bestFit);
    }
}

