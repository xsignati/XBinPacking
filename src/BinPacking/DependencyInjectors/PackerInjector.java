package BinPacking.DependencyInjectors;

import BinPacking.Logic.Packer.BinPacker;
import BinPacking.Logic.Packer.Packer;

/**
 * Created by Xsignati on 14.06.2017.
 */
public class PackerInjector {
    public static Packer get(){
        return new BinPacker();
    }
}
