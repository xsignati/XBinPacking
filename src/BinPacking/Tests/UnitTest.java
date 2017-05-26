package BinPacking.Tests;

import BinPacking.Data.LogicUI.Bin;
import BinPacking.Data.LogicUI.Box;
import BinPacking.Data.LogicUI.InputData;
import BinPacking.Logic.BinPacker;
import BinPacking.Logic.PackingStrategy.PackingStrategyFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;


import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Xsignati on 21.03.2017.
 * TEST VERSION. REAL UNIT TESTS TO DO.
 */
public class UnitTest {
    @Test
    public void test() {
        //source lists
        ObservableList<Box> boxList = FXCollections.observableArrayList();
        ObservableList<Bin> binList = FXCollections.observableArrayList();
        ObservableList<Box> testBoxList = FXCollections.observableArrayList();

        double binLength;
        double binWidth;
        double binHeight;

        //Read data from txt. First line = bin size. Other lines = Box [l w h x y z]
        try {
            FileInputStream fi = new FileInputStream("src/BinPacking/Tests/input.txt");
            InputStreamReader isr = new InputStreamReader(fi);
            BufferedReader br = new BufferedReader(isr);

            String line = br.readLine();

            String[] values = line.split(" ");
            binLength = Double.parseDouble(values[0]);
            binWidth = Double.parseDouble(values[1]);
            binHeight = Double.parseDouble(values[2]);

            while((line = br.readLine()) != null){
                values = line.split(" ");
                double boxLength = Double.parseDouble(values[0]);
                double boxWidth = Double.parseDouble(values[1]);
                double boxHeight = Double.parseDouble(values[2]);
                double boxX = Double.parseDouble(values[3]);
                double boxY = Double.parseDouble(values[4]);
                double boxZ = Double.parseDouble(values[5]);

                boxList.add(new Box(boxLength, boxWidth, boxHeight));

                Box testBox = new Box(boxLength, boxWidth, boxHeight);
                testBox.setCoordinates(boxX, boxY, boxZ);
                testBoxList.add(testBox);
            }

            InputData inputData = new InputData(binLength, binWidth,binHeight, binList, PackingStrategyFactory.getPS("BestFit"), boxList);
            BinPacker loader = new BinPacker();
            loader.pack(inputData);

            br.close();


        }
        catch(FileNotFoundException e)
            {e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}


        assertThat(boxList, is(testBoxList));
    }
}
