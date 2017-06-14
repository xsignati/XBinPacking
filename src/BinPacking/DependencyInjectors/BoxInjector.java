package BinPacking.DependencyInjectors;

import BinPacking.Data.Logic.BinSpace.Dimensions;
import BinPacking.Data.Logic.Box.Box;

/**
 * Created by Xsignati on 14.06.2017.
 */
public class BoxInjector {
    public static Box get(double l, double w, double h){
        return new Box(new Dimensions(l,w,h));
    }
}
