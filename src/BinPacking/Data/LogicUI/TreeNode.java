package BinPacking.Data.LogicUI;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Xsignati on 23.05.2017.
 */
public class TreeNode<E> implements Tree<E>{
    private static int objectCounter;
    private TreeNode<E> parent;
    private List<TreeNode<E>> children;
    private E data;
    private int id;

    public TreeNode(E data) {
        id = objectCounter++;
        this.data = data;
        this.children = new LinkedList<>();
    }

    @Override
    public void addChild(E child){
        TreeNode<E> childNode = new TreeNode<>(child);
        childNode.parent = this;
        childNode.id = id;
        children.add(childNode);
    }

    @Override
    public TreeNode<E> getParent(){
        return parent;
    }

    @Override
    public List<TreeNode<E>> getChildren(){
        return children;
    }

    @Override
    public E getData(){
        return data;
    }
}