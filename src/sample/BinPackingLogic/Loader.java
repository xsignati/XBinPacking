package sample.BinPackingLogic;

import javafx.collections.ObservableList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Xsignati on 24.01.2017.
 */
public class Loader {
    private List<Bin> binTrees;
    private ObservableList<Box> boxes;


    public Loader() {
//        boxes = new LinkedList<>();
          binTrees = new LinkedList<>();
//
//
//        Collections.sort(boxes);
//        for (Box b : boxes) {
//            System.out.println(b.getVolume());
//        }
    }


    public boolean run(SearchStrategy packingStrategy, ObservableList<Box> boxes) {
        this.boxes = boxes;
        /**
         * Main Loader loop. Try to fit to the container all boxes.
         */
        boolean continueLoop = true;
        final int ROTATIONS_NUM = Box.Rotations.values().length;
        List<Box> remainingBoxes = new LinkedList<>(boxes);
        binTrees.add(new Bin(500, 500, 500));
        while (continueLoop) {
            Bin currRoot = binTrees.get(binTrees.size()-1);
            for (Iterator<Box> iterator = remainingBoxes.iterator(); iterator.hasNext();) {
                Box box = iterator.next();
                for (int i = 0; i < ROTATIONS_NUM; i++) {
                    Bin foundNode = currRoot.search(packingStrategy, box);
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
            else{
                binTrees.add(new Bin(100, 100, 100));
            }

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

