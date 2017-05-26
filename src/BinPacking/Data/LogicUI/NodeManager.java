package BinPacking.Data.LogicUI;

/**
 * Created by Xsignati on 25.05.2017.
 */
public interface NodeManager {
    void createChildren(Box box);
    void removeAltSiblings();
    void reserveBin(Box box);
}
