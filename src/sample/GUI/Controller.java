package sample.GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import sample.GUI.BinView.Bin;
import sample.GUI.BinView.Box;
import sample.GUI.BinView.CameraModel;
import sample.BinPackingLogic.*;
import sample.GUI.BinView.InputData;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

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
    private Group bins;
    @FXML
    private Group boxes;
    @FXML
    private SceneAntialiasing ssaa = SceneAntialiasing.BALANCED;


    public CameraModel getCameraModel() {return cameraModel;}

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
    private EventHandler<MouseEvent> pressEvent = new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent me) {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseOldX = me.getSceneX();
            mouseOldY = me.getSceneY();
        }
    };

    @FXML
    private EventHandler<MouseEvent> dragEvent = new EventHandler<MouseEvent>() {
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

    private EventHandler<ScrollEvent> scrollEvent = new EventHandler<ScrollEvent>(){
        @Override
        public void handle(ScrollEvent se) {
            scrollDelta = se.getDeltaY() > 0 ?  scrollSpeed : -scrollSpeed;
            scrollPosZ = cameraModel.getCamera().getTranslateZ();
            cameraModel.getCamera().setTranslateZ(scrollPosZ + scrollDelta);
        }
    };



    /**
    * Box table
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

        addLength.setTextFormatter(getDigitValidator());
        addWidth.setTextFormatter(getDigitValidator());
        addHeight.setTextFormatter(getDigitValidator());
        //Add box listener
        addBox.setOnAction(addBoxEvent);

        //Add Box objects to the table observable list
        boxListViewer.setItems(boxList);
    }

    //Add box event listener
    private EventHandler<ActionEvent> addBoxEvent = new EventHandler<ActionEvent>() {
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

    private EventHandler<TableColumn.CellEditEvent<Box, Double>> tableEditEvent =
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
                else if(ce.getSource() == heightCol){
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

        setLength.setTextFormatter(getDigitValidator());
        setWidth.setTextFormatter(getDigitValidator());
        setHeight.setTextFormatter(getDigitValidator());
    }

    /**
     * Packing algorithm controls
     */
    @FXML
    private ToggleGroup algorithmButtons;
    @FXML
    private RadioButton algBtn1;
    @FXML
    private RadioButton algBtn2;


    /**
     * Loaded bins control
     */
    @FXML
    private ComboBox binSelector;
    @FXML
    private HBox selectorWrapper;
    private ObservableList<Bin> binList = FXCollections.observableArrayList();

    private Callback<ListView<Bin>, ListCell<Bin>> cf = new Callback<ListView<Bin>, ListCell<Bin>>() {
        @Override
        public ListCell<Bin> call(ListView<Bin> list) {
            return new ListCell<Bin>(){
                @Override
                public void updateItem(Bin item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null) {
                        setText(null);
                    } else {
                        setText("BIN " + Integer.toString(item.getCid()));
                    }
                }
            };
        }
    };

    private void binSelectInit(){
        binSelector.setButtonCell(cf.call(null));
        binSelector.setCellFactory(cf);
        binSelector.prefWidthProperty().bind(selectorWrapper.widthProperty().multiply(0.6));

        binSelector.getSelectionModel().selectedItemProperty().addListener((ov, old_val, new_val) -> {
                if(new_val != null)
                    drawScene(((Bin)new_val).getCid());
            }
        );
        binSelector.setItems(binList);
    }

    /**
     * Display bin scene
     */
    // private double scale;
    private void prepareScene(double binLength, double binWidth, double binHeight){
        getCameraModel().setDistance(binScene.getWidth()*4);
        getCameraModel().reset();
        scale = new Scale(binScene.getWidth()/binLength, binScene.getHeight()/binWidth, binScene.getWidth()/binHeight);

        for (Box box: boxList){
            box.setBoxes();
            box.scale(scale.getScale());
        }

        for (Bin bin: binList) {
            bin.scale(scale.getScale());
        }
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
     * Process control
     */
    @FXML
    private HBox processWrapper;
    @FXML
    private Button processBtn;

    private void processInit(){
        processBtn.prefWidthProperty().bind(processWrapper.widthProperty().multiply(0.7));
    }

    @FXML
    private void startProcess(){
        if(!validate()){
            System.err.println("ERROR");
            return;
        }

        double binLength = Double.parseDouble(setLength.getCharacters().toString());
        double binWidth = Double.parseDouble(setWidth.getCharacters().toString());
        double binHeight = Double.parseDouble(setHeight.getCharacters().toString());
        RadioButton selectedBtn = (RadioButton)algorithmButtons.getSelectedToggle();
        PackingStrategy packingAlg = PackingStrategyFactory.getPS(selectedBtn.getText());

        clearBinList();

        InputData inputData = new InputData(binLength, binWidth, binHeight, binList, packingAlg, boxList);
        Loader loader = new Loader();
        loader.run(inputData);

        prepareScene(binLength, binWidth, binHeight);

        drawScene(0);

    }

    /**
     * clean
     */
    private void clearBinList(){
        binList.clear();
    }

    /**
     * Draw Boxes
     */
    private void drawScene(int binId){
        boxes.getChildren().clear();
        bins.getChildren().clear();

        for(Box box: boxList){
            if(box.getCid() == binId)
                boxes.getChildren().add(box);
        }

         bins.getChildren().add(binList.get(binId));

    }

    /**
     * Validator
     */

    private TextFormatter getDigitValidator(){
        Locale locale = new Locale("en", "usa");
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        DecimalFormat digitOnly = (DecimalFormat)numberFormat;
        digitOnly.applyPattern( "#.0" );

        return new TextFormatter<>(c ->
        {
            if ( c.getControlNewText().isEmpty() ) {
                return c;
            }

            ParsePosition parsePosition = new ParsePosition( 0 );
            Object object = digitOnly.parse( c.getControlNewText(), parsePosition );

            if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() ){
                return null;
            }
            else {
                return c;
            }
        });
    }


    public boolean validate(){
        boolean status = true;
        if(setWidth.getCharacters().length() == 0 || setLength.getCharacters().length() == 0 || setHeight.getCharacters().length() == 0){
            status = false;
        }
        if(setWidth.getCharacters().length() > 10 || setLength.getCharacters().length() == 10 || setHeight.getCharacters().length() > 10){
            status = false;
        }
        if(boxList.size() == 0)
            status = false;
        return status;
    }


    /**
     * *******************************************************
     */
    public void test() {
        boxList.add(new Box(600,600,330));
        boxList.add(new Box(600,600,330));
        boxList.add(new Box(600,600,330));
        boxList.add(new Box(600,600,330));
        boxList.add(new Box(600,600,330));
        boxList.add(new Box(600,600,330));
        boxList.add(new Box(600,600,330));
        boxList.add(new Box(600,600,330));
        boxList.add(new Box(600,600,330));
        boxList.add(new Box(600,600,330));
        boxList.add(new Box(600,600,330));
        boxList.add(new Box(600,600,330));
        boxList.add(new Box(600,600,330));

    }

    /**
     * *******************************************************
     */


}

