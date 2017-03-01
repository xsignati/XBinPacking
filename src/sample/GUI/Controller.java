package sample.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.converter.DoubleStringConverter;
import sample.GUI.BinView.BinModel;
import sample.GUI.BinView.CameraModel;
import sample.BinPackingLogic.*;
import sample.GUI.BinView.InputData;

import java.util.Collections;

public class Controller {
    /**
     * Main methods and variables
     */
    @FXML
    private Text title;
    @FXML
    public SubScene binScene;
    @FXML
    public Pane bsPane;
    @FXML
    private CameraModel cameraModel;
    @FXML
    private Group bin;
    @FXML
    private Group boxes;
    @FXML
    private SceneAntialiasing ssaa = SceneAntialiasing.BALANCED;

    public Group getBin() {
        return bin;
    }

    public CameraModel getCameraModel() {return cameraModel;}

    public Text getTitle() {
        return title;
    }

    public SubScene getBinScene() {
        return binScene;
    }

    public Pane getBsPane() {return bsPane; }

    SubScene getDrawArea() { return binScene; }

    @FXML
    public void initialize(){
        //Bind Bin subScene size to its wrapper
        binScene.heightProperty().bind(bsPane.heightProperty());
        binScene.widthProperty().bind(bsPane.widthProperty());

        //
        binScene.setCamera(cameraModel.getCamera());
        binScene.setOnMousePressed(pressEvent);
        binScene.setOnMouseDragged(dragEvent);
        binScene.setOnScroll(scrollEvent);

        boxsetter();
        binSizeInit();
        binSelectInit();
        processInit();

    }

    /**
     * Bin View Event handlers
     */
    private double rotationSpeed = 0.2;
    private double scrollSpeed = 50;
    private double mousePosX;
    private double mousePosY;
    private double mouseOldX;
    private double mouseOldY;
    private double mouseDeltaX;
    private double mouseDeltaY;
    private double scrollDelta;
    private double scrollPosZ;

    @FXML
    EventHandler<MouseEvent> pressEvent = new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent me) {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseOldX = me.getSceneX();
            mouseOldY = me.getSceneY();
        }
    };

    @FXML
    EventHandler<MouseEvent> dragEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent me) {
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseDeltaX = (mousePosX - mouseOldX);
            mouseDeltaY = (mousePosY - mouseOldY);

            if (me.isPrimaryButtonDown()) {
                cameraModel.getRz().setAngle(cameraModel.getRz().getAngle() - mouseDeltaX * rotationSpeed);
                cameraModel.getRx().setAngle(cameraModel.getRx().getAngle() + mouseDeltaY * rotationSpeed);
            }
        }
    };

    EventHandler<ScrollEvent> scrollEvent = new EventHandler<ScrollEvent>(){
        @Override
        public void handle(ScrollEvent se) {
            scrollDelta = se.getDeltaY() > 0 ?  scrollSpeed : -scrollSpeed;
            scrollPosZ = cameraModel.getCamera().getTranslateZ();
            cameraModel.getCamera().setTranslateZ(scrollPosZ + scrollDelta);
        }
    };

    /**
     * Post settings
     */
   // private double scale;
    private void settings(){
        getCameraModel().setDistance(binScene.getWidth()*4);
        getCameraModel().reset();
        scale = new Scale(binScene.getWidth()/getBinLength(), binScene.getHeight()/getBinHeight(), binScene.getWidth()/getBinWidth());
        //scale = subSceneScale();

    }

    private Scale scale;
    class Scale{
        private double scale;
        public Scale(double s1, double s2, double s3){
            scale = getMin(s1, s2, s3);
        }
        private double getMin(double r1, double r2, double r3) {
            return Math.min(r1, Math.min(r2, r3));
        }
        public double getScale() {
            return scale;
        }
    }



    /**
     * *******************************************************
     */
    public void test() {

        settings();

        boxList.add(new Box(200,300,400));
        boxList.add(new Box(100,200,300));

        //BinModel binModel = new BinModel(getBinLength(),getBinWidth(),getBinHeight());
        //binModel.scale(scale.getScale());
        //bin.getChildren().addAll(binModel);

       // boxsetter();

    }

    private int getBinLength(){
        return 500;
    }
    private int getBinWidth(){ return 500; }
    private int getBinHeight(){ return 500;
    }




    /**
     * *******************************************************
     */
    /**
    * Box list
    */
    @FXML
    private TableView<Box> boxListViewer;
    @FXML
    private TableColumn lengthCol;
    @FXML
    private TableColumn widthCol;
    @FXML
    private TableColumn heightCol;
    @FXML
    private Label setSizeLabel;
    @FXML
    private TableColumn xCol;
    @FXML
    private TableColumn yCol;
    @FXML
    private TableColumn zCol;
    @FXML
    private Button addBox;
    @FXML
    private TextField addLength;
    @FXML
    private TextField addWidth;
    @FXML
    private TextField addHeight;
    private ObservableList<Box> boxList = FXCollections.observableArrayList();

    private void boxsetter(){
        //Bind box table size properties to table size
        lengthCol.prefWidthProperty().bind(boxListViewer.widthProperty().multiply(0.25));
        widthCol.prefWidthProperty().bind(boxListViewer.widthProperty().multiply(0.25));
        heightCol.prefWidthProperty().bind(boxListViewer.widthProperty().multiply(0.25));

        //Set box table cell properties
        lengthCol.setCellValueFactory(
                new PropertyValueFactory<Box,Double>("length")
        );
        widthCol.setCellValueFactory(
                new PropertyValueFactory<Box,Double>("width")
        );
        heightCol.setCellValueFactory(
                new PropertyValueFactory<Box,Double>("height")
        );
        xCol.setCellValueFactory(
                new PropertyValueFactory<Box,Double>("x")
        );
        yCol.setCellValueFactory(
                new PropertyValueFactory<Box,Double>("y")
        );
        zCol.setCellValueFactory(
                new PropertyValueFactory<Box,Double>("z")
        );


        //Add box table event handlers
        lengthCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        lengthCol.setOnEditCommit(tableEditEvent);
        widthCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        widthCol.setOnEditCommit(tableEditEvent);
        heightCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        heightCol.setOnEditCommit(tableEditEvent);

        //Bind add box controls size properties to their wrapper
        addLength.prefWidthProperty().bind(addWrap.widthProperty().multiply(0.25));
        addWidth.prefWidthProperty().bind(addWrap.widthProperty().multiply(0.25));
        addHeight.prefWidthProperty().bind(addWrap.widthProperty().multiply(0.25));
        addBox.prefWidthProperty().bind(addWrap.widthProperty().multiply(0.25));

        //Add box listener
        addBox.setOnAction(addBoxEvent);

        //Add Box objects to the box table observable list
        boxListViewer.setItems(boxList);
    }

    //Add box event listener
    EventHandler<ActionEvent> addBoxEvent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            boxList.add(new Box(
                    Double.parseDouble(addLength.getText()),
                    Double.parseDouble(addWidth.getText()),
                    Double.parseDouble(addHeight.getText())
            ));
            addLength.clear();
            addWidth.clear();
            addHeight.clear();


        }
    };

    EventHandler<TableColumn.CellEditEvent<Box, Double>> tableEditEvent =
            new EventHandler<TableColumn.CellEditEvent<Box, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Box, Double> ce) {
                Box be = (ce.getTableView().getItems().get(ce.getTablePosition().getRow()));

                if(ce.getSource() == lengthCol) {
                    be.setLength(ce.getNewValue());
                }
                else if(ce.getSource() == widthCol){
                    be.setWidth(ce.getNewValue());
                }
                else{
                    be.setHeight(ce.getNewValue());
                }
                be.scale(scale.getScale());
            }
        };

    /**
     * Bin size control
     */
    @FXML
    private TextField setLength;
    @FXML
    private TextField setWidth;
    @FXML
    private TextField setHeight;
    @FXML
    private HBox setWrap;
    @FXML
    private HBox addWrap;

    private void binSizeInit() {
        //Bind bin size controls size properties to their wrapper
        setSizeLabel.prefWidthProperty().bind(setWrap.widthProperty().multiply(0.25));
        setLength.prefWidthProperty().bind(setWrap.widthProperty().multiply(0.25));
        setWidth.prefWidthProperty().bind(setWrap.widthProperty().multiply(0.25));
        setHeight.prefWidthProperty().bind(setWrap.widthProperty().multiply(0.25));
    }

    /**
     * Loaded bins control
     */
    @FXML
    private ComboBox binSelector;
    @FXML
    private HBox selectorWrapper;
    private ObservableList<Bin> binIdList = FXCollections.observableArrayList();

    private void binSelectInit(){
        binSelector.prefWidthProperty().bind(selectorWrapper.widthProperty().multiply(0.6));
        binSelector.setItems(binIdList);
    }

    /**
     * Process control
     */
    @FXML
    private HBox processWrapper;
    @FXML
    private Button processBtn;

    private void processInit(){
        processBtn.prefWidthProperty().bind(processWrapper.widthProperty().multiply(0.7));
    }

    /**
     * Draw Boxes
     */
    private void draw(InputData d){
        for(Box box: boxList){
            boxes.getChildren().add(box);
        }

        d.getBin().scale(scale.getScale());
        bin.getChildren().addAll(d.getBin());
    }

    /**
     * Process
     */
    @FXML
    private void startProcess(){
        InputData inputData = new InputData(new Bin(500,500,500), new BestFit(), boxList);
        for (Box box: boxList){
            System.out.println(box.getHeight() + " " + box.getWidth() + " "  + box.getLength());
        }

        Loader loader = new Loader();


        Collections.sort(boxList);
        loader.run(inputData);

        for (Box box: boxList){
            box.setBoxes();
            box.scale(scale.getScale());
        }

        boxes.getChildren().clear();
        bin.getChildren().clear();
        draw(inputData);
    }


}

