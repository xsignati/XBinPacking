package BinPacking.Data.Logic.BinTree;

import BinPacking.Data.Logic.Bin.Bin;

/**
 * Created by Xsignati on 30.05.2017.
 */
public interface BasicBinTree {
    void addChildWith(Bin child);
    void setParent(BinTree binTree);
    Bin getData();
}
