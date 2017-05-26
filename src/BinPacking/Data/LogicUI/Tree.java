package BinPacking.Data.LogicUI;

import java.util.List;

/**
 * Created by Xsignati on 25.05.2017.
 */
public interface Tree<E> {
    void addChild(E child);
    TreeNode<E> getParent();
    List<TreeNode<E>> getChildren();
    E getData();
}
