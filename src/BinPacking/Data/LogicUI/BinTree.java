package BinPacking.Data.LogicUI;

import BinPacking.Logic.PackingStrategy.PackingStrategy;

import java.util.List;

/**
 * Created by Xsignati on 25.05.2017.
 */
public interface BinTree {
    void addChildWith(Bin child);
    BinTree getParent();
    void setParent(BinTree binTree);
    List<BinTree> getChildren();
    Bin getData();
    void setId(int id);
    BinTree search(PackingStrategy packingStrategy, Box box);
    void reserveBinFor(Box box);
    void removeNotSelectedSubspaces();
    void tryToAddSubspacesFor(Box box);
    int getId();
}
