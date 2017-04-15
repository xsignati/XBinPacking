package BinPacking.Data.LogicUI;

import javafx.scene.Group;

/**
 * Created by Xsignati on 13.04.2017.
 */
public interface SceneModel {
    /**
     * Fit a model to the SubScene size
     * @param scale scale factor
     */
    void scale(double scale);

    /**
     * Graphics instance of the object required by the BinScene
     * @param binSceneModels
     */
    void addModel(Group binSceneModels);

    /**
     * Id required to draw elements that belong to a specific bin
     * @return id of the object
     */
    int getId();
}
