package sample.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;

public class Controller {
    @FXML
    private Text title;
    @FXML
    public SubScene binScene;
    @FXML
    public Pane bsPane;
    @FXML
    public Pane binsView;
    @FXML
    public Box box;

    @FXML
    public Button myButton;
    public Pane getBinsView() {
        return binsView;
    }

    public Box getBox() {
        return box;
    }

    public Text getTitle() {
        return title;
    }

    public SubScene getBinScene() {
        return binScene;
    }

    public Pane getBsPane() {
        return bsPane;
    }

    SubScene getDrawArea() { return binScene; }


    // Handle Button event.
    public void handleButtonAction(MouseEvent event) {
        System.out.println("You clicked me!");
    }

    @FXML
    public void initialize(){
    }
}

