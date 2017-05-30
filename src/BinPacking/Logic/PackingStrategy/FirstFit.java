//package BinPacking.Logic.PackingStrategy;
//
//import javafx.collections.ObservableList;
//import BinPacking.Data.LogicUI.Bin;
//import BinPacking.Data.LogicUI.Box;
//
///**
// * Created by Xsignati on 24.01.2017.
// */
//public class FirstFit implements PackingStrategy {
//    @Override
//    public Bin search(Bin bin, Box box) {
//        if(bin.getState() == Bin.BinState.FULL) {
//            for (Bin subBin : bin.getBinChildren()) {
//                Bin foundNode;
//                if((foundNode = search(subBin, box)) != null) return foundNode;
//            }
//        }
//        else if (boxFitsToBin(bin, box))
//            return bin;
//        return null;
//    }
//
//    @Override
//    public void prepareInput(ObservableList<Box> boxList){
//        boxList.sort((b1,b2) -> b2.getVolume().compareTo(b1.getVolume()));
//    }
//}