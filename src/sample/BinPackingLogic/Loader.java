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

    public boolean run(InputData d) {
        /**
         * Main Loader loop. Try to fit to the container all boxes.
         */
        boolean continueLoop = true;
        final int ROTATIONS_NUM = Box.Rotations.values().length;
        List<Box> remainingBoxes = new LinkedList<>(d.getBoxList());
        while (continueLoop) {

            for (Iterator<Box> iterator = remainingBoxes.iterator(); iterator.hasNext();) {
                Box box = iterator.next();
                for (int i = 0; i < ROTATIONS_NUM; i++) {
                    Bin foundNode = d.getBin().search(d.getPackingStrategy(), box);
                    Box.Rotations currRotation = Box.Rotations.values()[i];
                    if (foundNode != null) {
                        foundNode.reserveBin(box);
                        foundNode.createChildren(box);
                        foundNode.removeAltChildren();
                        iterator.remove();
                        break;
                    } else {
                        box.rotate(currRotation);
                        System.out.println("rotation");
                    }
                }
                System.out.println("RESULT: " + "X: " + box.getX() + " Y: " + box.getY() + " Z: " + box.getZ() +
                        "  length: " + box.getLength() + "width: " + box.getWidth() + "height: " + box.getHeight() + "containerID: " + box.getBinId());

            }
            if(remainingBoxes.isEmpty())
                continueLoop = false;
            else
                d.setBin(new Bin(500,500,500));
        }
        return true;
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

