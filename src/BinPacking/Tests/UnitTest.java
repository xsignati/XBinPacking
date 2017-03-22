package BinPacking.Tests;

import BinPacking.Data.LogicUI.Bin;
import BinPacking.Data.LogicUI.Box;
import BinPacking.Data.LogicUI.InputData;
import BinPacking.Logic.Loader;
import BinPacking.Logic.PackingStrategy.PackingStrategyFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by Xsignati on 21.03.2017.
 * TEST VERSION. REAL UNIT TESTS TODO
 */
public class UnitTest {
    @Test
    public void test() {
        //source list
        ObservableList<Box> boxList = FXCollections.observableArrayList();
        ObservableList<Bin> binList = FXCollections.observableArrayList();
        boxList.add(new Box(100,100,100));
        boxList.add(new Box(100,100,100));
        boxList.add(new Box(100,100,100));
        boxList.add(new Box(100,100,100));

        //target list
        ObservableList<Box> testBoxList = FXCollections.observableArrayList();
        Box b1 = new Box(100,100,100);
        b1.setCoordinates(0,0,0);
        Box b2 = new Box(100,100,100);
        b2.setCoordinates(100,0,0);
        Box b3 = new Box(100,100,100);
        b3.setCoordinates(200,0,0);
        Box b4 = new Box(100,100,100);
        b4.setCoordinates(300,0,0);
        testBoxList.add(b1);
        testBoxList.add(b2);
        testBoxList.add(b3);
        testBoxList.add(b4);

        //run loader
        InputData inputData = new InputData(1000,1000,1000, binList, PackingStrategyFactory.getPS("BestFit"), boxList);
        Loader loader = new Loader();
        loader.run(inputData);

        assertThat(boxList, is(testBoxList));

    }
}
