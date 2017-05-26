package BinPacking.UI;

import BinPacking.Data.LogicUI.*;
import BinPacking.Data.UI.*;
import BinPacking.Logic.BinPacker;
import BinPacking.Logic.PackingStrategy.*;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * UI view class.
 */
public class Controller {
    private final BoxList boxList = new BoxList();
    private final BinList binList = new BinList();
    private final SceneModelComposite modelComposite = new SceneModelComposite();

    /**
     * Configure FXML controls.
     */
    @FXML
    public void initialize(){
        modelComposite.add(boxList);
        modelComposite.add(binList);
        subSceneInit();
        boxTableInit();
        binSizeInit();
        binSelectInit();
        processInit();
    }

    @FXML
    private BinScene binScene;
    @FXML
    private Pane bsPane;

    /**
     * Bind the BinScene to its wrapper to make it resizable.
     */
    private void subSceneInit(){
        binScene.heightProperty().bind(bsPane.heightProperty());
        binScene.widthProperty().bind(bsPane.widthProperty());
    }

    @FXML
    private TableView<Box> boxListViewer;
    @FXML
    private TableColumn<Box,Double> lengthCol;
    @FXML
    private TableColumn<Box,Double> widthCol;
    @FXML
    private TableColumn<Box,Double> heightCol;
    @FXML
    private Label setSizeLabel;
    @FXML
    private TableColumn<Box,Double> xCol;
    @FXML
    private TableColumn<Box,Double> yCol;
    @FXML
    private TableColumn<Box,Double> zCol;
    @FXML
    private Button addBox;
    @FXML
    private TextField addLength;
    @FXML
    private TextField addWidth;
    @FXML
    private TextField addHeight;

    /**
     * Configure the table defined in FXML.
     */
    private void boxTableInit(){
        //Bind box table size properties to table size
        lengthCol.prefWidthProperty().bind(boxListViewer.widthProperty().multiply(0.25));
        widthCol.prefWidthProperty().bind(boxListViewer.widthProperty().multiply(0.25));
        heightCol.prefWidthProperty().bind(boxListViewer.widthProperty().multiply(0.25));

        //Set box table cell properties
        lengthCol.setCellValueFactory(
                new PropertyValueFactory<>("length")
        );
        widthCol.setCellValueFactory(
                new PropertyValueFactory<>("width")
        );
        heightCol.setCellValueFactory(
                new PropertyValueFactory<>("height")
        );
        xCol.setCellValueFactory(
                new PropertyValueFactory<>("x")
        );
        yCol.setCellValueFactory(
                new PropertyValueFactory<>("y")
        );
        zCol.setCellValueFactory(
                new PropertyValueFactory<>("z")
        );

        //Box table cells editor. Box size is changed in both - the observableList boxList and SubScene. A new box is automatic
        //rescaled.
        EventHandler<TableColumn.CellEditEvent<Box, Double>> tableEditEvent = ce -> {
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
                    if(binScene.getScale() != null)
                        binScene.rescale(be);
            };

        //Add box table event handlers
        lengthCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        lengthCol.setOnEditCommit(tableEditEvent);
        widthCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        widthCol.setOnEditCommit(tableEditEvent);
        heightCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        heightCol.setOnEditCommit(tableEditEvent);

        //Bind add box controls size properties to their wrapper
        addLength.prefWidthProperty().bind(addWrap.widthProperty().multiply(0.20));
        addWidth.prefWidthProperty().bind(addWrap.widthProperty().multiply(0.20));
        addHeight.prefWidthProperty().bind(addWrap.widthProperty().multiply(0.20));
        addBox.prefWidthProperty().bind(addWrap.widthProperty().multiply(0.20));

        //Add validators
        addLength.setTextFormatter(getDigitValidator());
        addWidth.setTextFormatter(getDigitValidator());
        addHeight.setTextFormatter(getDigitValidator());

        //Add box listener
        addBox.setOnAction(e -> {
                boxList.add(new Box(
                        Double.parseDouble(addLength.getText()),
                        Double.parseDouble(addWidth.getText()),
                        Double.parseDouble(addHeight.getText())
                ));
                addLength.clear();
                addWidth.clear();
                addHeight.clear();
            });

        //Add Box objects to the table observable list
        boxListViewer.setItems(boxList.get());
    }

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

    /**
     * Configure controls responsible for getting data about Bin size.
     */
    private void binSizeInit() {
        //Fields alignment
        setSizeLabel.prefWidthProperty().bind(setWrap.widthProperty().multiply(0.25));
        setLength.prefWidthProperty().bind(setWrap.widthProperty().multiply(0.25));
        setWidth.prefWidthProperty().bind(setWrap.widthProperty().multiply(0.25));
        setHeight.prefWidthProperty().bind(setWrap.widthProperty().multiply(0.25));

        //Add validators
        setLength.setTextFormatter(getDigitValidator());
        setWidth.setTextFormatter(getDigitValidator());
        setHeight.setTextFormatter(getDigitValidator());
    }

    @FXML
    private ComboBox<Bin> binSelector;
    @FXML
    private HBox selectorWrapper;
    @FXML
    private Button clearBtn;

    /**
     * Configure controls responsible for selecting Bins and clearing all data.
     */
    private void binSelectInit(){
        Callback<ListView<Bin>, ListCell<Bin>> cf = new Callback<ListView<Bin>, ListCell<Bin>>() {
            @Override
            public ListCell<Bin> call(ListView<Bin> list) {
                return new ListCell<Bin>(){
                    @Override
                    public void updateItem(Bin item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null)
                            setText(null);
                        else
                            setText("BIN " + Integer.toString(item.getId()));
                    }
                };
            }
        };

        binSelector.setButtonCell(cf.call(null));
        binSelector.setCellFactory(cf);
        binSelector.prefWidthProperty().bind(selectorWrapper.widthProperty().multiply(0.6));
        binSelector.getSelectionModel().selectedItemProperty().addListener((ov, oldVal, newVal) -> {
                if(newVal != null) {
                    binScene.clear();
                    binScene.add(modelComposite, newVal.getId());
                }
            }
        );
        binSelector.setItems(binList.get());

        clearBtn.prefWidthProperty().bind(selectorWrapper.widthProperty().multiply(0.20));
        binSelector.prefWidthProperty().bind(selectorWrapper.widthProperty().multiply(0.40));
    }

    /**
     * Delete all boxes and bins from binScene and table.
     */
    @FXML
    private void clear(){
        binScene.clear();
        binList.clear();
        boxList.clear();
    }

    @FXML
    private ToggleGroup algorithmButtons;
    @FXML
    private HBox processWrapper;
    @FXML
    private Button processBtn;

    private final ExecutorService loaderExecutor = Executors.newSingleThreadExecutor();
    public ExecutorService getLoaderExecutor(){
        return loaderExecutor;
    }

    /**
     * Every operation of bin packing is executed in a separated thread in a single Thread Executor.
     */
    class LoaderTask extends Task<Void> {
        @Override
        public Void call() {
            //Get a necessary data from controls
            double binLength = Double.parseDouble(setLength.getCharacters().toString());
            double binWidth = Double.parseDouble(setWidth.getCharacters().toString());
            double binHeight = Double.parseDouble(setHeight.getCharacters().toString());
            RadioButton selectedBtn = (RadioButton) algorithmButtons.getSelectedToggle();
            PackingStrategy packingAlg = PackingStrategyFactory.getPS(selectedBtn.getText());

            //Create the InputData structure for BinPacker
            InputData inputData = new InputData(binLength, binWidth, binHeight, binList.get(), packingAlg, boxList.get());
            BinPacker loader = new BinPacker();
            loader.pack(inputData);

            //Rescale BinScene
            binScene.init(binLength, binWidth, binHeight);
            binScene.rescale(modelComposite);

            return null;
        }

        public LoaderTask(){
            setOnSucceeded((WorkerStateEvent event) ->
                    Platform.runLater(() -> {
                                binScene.clear();
                                binScene.add(modelComposite, 0);
                            }
                    )
            );
        }
    }

    /**
     * Determine the process button size.
     */
    private void processInit(){
        processBtn.prefWidthProperty().bind(processWrapper.widthProperty().multiply(0.7));
    }

    /**
     * It executes a logic part of the application. Controls' data is checked for errors before task.
     */
    @FXML
    private void startProcess(){
        try {validate();}
        catch(NoInputException | TooLongInputException | EmptyListException | TooLargeBoxException e){
            e.printStackTrace(System.err);
            return;
        }

        binList.clear();
        loaderExecutor.submit(new LoaderTask());
    }

    /**
     * Validators.
     */
    private TextFormatter getDigitValidator(){
        return new TextFormatter<>(c -> {
            Locale locale = new Locale("en", "usa");
            NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
            DecimalFormat digitOnly = (DecimalFormat)numberFormat;
            digitOnly.applyPattern( "#.0" );

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

    class NoInputException extends Exception{}
    class TooLongInputException extends Exception{}
    class EmptyListException extends Exception{}
    class TooLargeBoxException extends Exception{}

    /**
     * @throws NoInputException Control's field is empty.
     * @throws TooLongInputException Control's input is too large.
     * @throws EmptyListException No boxes in boxList.
     * @throws TooLargeBoxException Box is bigger than bin.
     */
    private void validate() throws NoInputException, TooLongInputException, EmptyListException, TooLargeBoxException   {
        if(setWidth.getCharacters().length() == 0 || setLength.getCharacters().length() == 0 || setHeight.getCharacters().length() == 0)
            throw new NoInputException();

        if(setWidth.getCharacters().length() > 10 || setLength.getCharacters().length() == 10 || setHeight.getCharacters().length() > 10)
            throw new TooLongInputException();

        if(boxList.get().size() == 0)
            throw new EmptyListException();

        double binLength = Double.parseDouble(setLength.getCharacters().toString());
        double binWidth = Double.parseDouble(setWidth.getCharacters().toString());
        double binHeight = Double.parseDouble(setHeight.getCharacters().toString());
        for(Box box: boxList.get()){
            if(box.getLength() > binLength || box.getWidth() > binWidth || box.getHeight() > binHeight)
                throw new TooLargeBoxException();
        }
    }
}

