package BinPacking.Data.UI.SceneModels;

import javafx.scene.Group;

/**
 * Created by Xsignati on 13.04.2017.
 */
public interface SceneModel {
    /**
     * Fit a model to the BinScene size.
     * @param scale scale factor.
     */
    void scale(double scale);

    /**
     * Add models to the BinScene.
     * @param binSceneModels graphics instance of the object required by the BinScene.
     */
    void addModel(Group binSceneModels);

    /**
     * Add only if id matches.
     * @param binSceneModels igraphics instance of the object required by the BinScene.
     * @param id model's id.
     */
    void addModel(Group binSceneModels, int id);
}
