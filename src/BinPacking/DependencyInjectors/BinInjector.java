package BinPacking.DependencyInjectors;

import BinPacking.Data.Logic.Bin.Bin;
import BinPacking.Data.Logic.BinSpace.Dimensions;
import BinPacking.Data.Logic.BinSpace.Point;

/**
 * Created by Xsignati on 13.06.2017.
 */
public class BinInjector {
    public static Bin get(double x, double y, double z, double l, double w, double h, Bin.Type t){
        return new Bin(new Point(x,y,z), new Dimensions(l,w,h), t);
    }

    public static Bin get(double l, double w, double h){
        return new Bin(new Point(0,0,0), new Dimensions(l,w,h), Bin.Type.ROOT);
    }
}
