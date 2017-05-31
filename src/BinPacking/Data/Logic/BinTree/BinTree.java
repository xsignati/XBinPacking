package BinPacking.Data.Logic.BinTree;

import BinPacking.Data.Logic.Box.Box;
import BinPacking.Logic.PackingStrategy.PackingStrategy;

import java.util.List;

/**
 * Created by Xsignati on 25.05.2017.
 */
public interface BinTree extends BasicBinTree{
    List<BinTree> getChildren();
    void setId(int id);
    BinTree search(PackingStrategy packingStrategy, Box box);
    void reserveBinFor(Box box);
    void removeNotSelectedSubspaces();
    void tryToAddSubspacesFor(Box box);
    int getId();
}
