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
     * Add models to the subScene
     * @param binSceneModels graphics instance of the object required by the BinScene
     */
    void addModel(Group binSceneModels);

    /**
     * Add if id matches
     * @param binSceneModels id of the object
     * @param id model's id
     */
    void addModel(Group binSceneModels, int id);
}
