package sample.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import sample.GUI.BinView.BinModel;
import sample.GUI.BinView.BoxModel;
import sample.GUI.BinView.CameraModel;
import sample.BinPackingLogic.Box;

import java.util.concurrent.SynchronousQueue;

public class Controller {
    @FXML
    private Text title;
    @FXML
    public SubScene binScene;
    @FXML
    public Pane bsPane;
    @FXML
    public Group  binsView;
    @FXML
    private CameraModel cameraModel;
    @FXML
    private Group bin;
    @FXML
    private SceneAntialiasing ssaa = SceneAntialiasing.BALANCED;

    public Group getBin() {
        return bin;
    }

    public CameraModel getCameraModel() {return cameraModel;}

    public Group getBinsView() {
        return binsView;
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

    @FXML
    public void initialize(){
        binScene.heightProperty().bind(bsPane.heightProperty());
        binScene.widthProperty().bind(bsPane.widthProperty());

        binScene.setCamera(cameraModel.getCamera());
        binScene.setOnMousePressed(pressEvent);
        binScene.setOnMouseDragged(dragEvent);
        binScene.setOnScroll(scrollEvent);

        addBox.setOnAction(addBoxEvent);

    }

    /**
     * TESTER
     */

    public void test() {

        System.out.println(binScene.getHeight());
        System.out.println(binScene.getWidth());
        getCameraModel().setDistance(binScene.getWidth()*4);
        getCameraModel().reset();
        double scale = subSceneScale();

        BinModel binModel = new BinModel(getBinWidth(),getBinHeight(),getBinDepth(),scale);
        bin.getChildren().addAll(binModel);

        BoxModel box1 = new BoxModel(0,0,0,50,50,50, scale);
        BoxModel box2 = new BoxModel(50,0,0,50,50,50, scale);
        BoxModel box3 = new BoxModel(0,50,0,50,50,50, scale);
        BoxModel box4 = new BoxModel(50,0,50,50,50,50, scale);

        bin.getChildren().add(box1);
        bin.getChildren().add(box2);
        bin.getChildren().add(box3);
        bin.getChildren().add(box4);

        boxsetter();

    }

    private int getBinWidth(){
        return 500;
    }
    private int getBinHeight(){ return 300; }
    private int getBinDepth(){ return 400;
    }

    private double subSceneScale(){
        return getMin(binScene.getWidth()/getBinWidth(), binScene.getHeight()/getBinDepth(), binScene.getWidth()/getBinHeight());
    }

    private double getMin(double r1, double r2, double r3) {
        return Math.min(r1, Math.min(r2, r3));
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
    private TableColumn widthCol;
    @FXML
    private TableColumn heightCol;
    @FXML
    private TableColumn depthCol;
    @FXML
    private TableColumn xCol;
    @FXML
    private TableColumn yCol;
    @FXML
    private TableColumn zCol;
    @FXML
    private Button addBox;
    @FXML
    private TextField addWidth;
    @FXML
    private TextField addHeight;
    @FXML
    private TextField addDepth;

    private ObservableList<Box> boxList = FXCollections.observableArrayList();

    private void boxsetter(){
        widthCol.prefWidthProperty().bind(boxListViewer.widthProperty().multiply(0.25));
        heightCol.prefWidthProperty().bind(boxListViewer.widthProperty().multiply(0.25));
        depthCol.prefWidthProperty().bind(boxListViewer.widthProperty().multiply(0.25));
        addWidth.prefWidthProperty().bind(boxListViewer.widthProperty().multiply(0.25));
        addHeight.prefWidthProperty().bind(boxListViewer.widthProperty().multiply(0.25));
        addDepth.prefWidthProperty().bind(boxListViewer.widthProperty().multiply(0.25));
        addBox.prefWidthProperty().bind(boxListViewer.widthProperty().multiply(0.25));

        boxList.add(new Box(20,30,40));
        boxList.add(new Box(20,30,40));

        widthCol.setCellValueFactory(
                new PropertyValueFactory<Box,Integer>("w")
        );
        heightCol.setCellValueFactory(
                new PropertyValueFactory<Box,Integer>("h")
        );
        depthCol.setCellValueFactory(
                new PropertyValueFactory<Box,Integer>("d")
        );

        boxListViewer.setItems(boxList);

        widthCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        widthCol.setOnEditCommit(widthEditEvent);
        heightCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        heightCol.setOnEditCommit(heightEditEvent);
        depthCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        depthCol.setOnEditCommit(depthEditEvent);

    }


    EventHandler<ActionEvent> addBoxEvent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            boxList.add(new Box(
                    Integer.parseInt(addWidth.getText()),
                    Integer.parseInt(addHeight. getText()),
                    Integer.parseInt(addDepth.getText())
            ));
            addWidth.clear();
            addHeight.clear();
            addDepth.clear();


        }
    };


    EventHandler<TableColumn.CellEditEvent<Box, Integer>> widthEditEvent =
            new EventHandler<TableColumn.CellEditEvent<Box, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Box, Integer> ce) {
                (ce.getTableView().getItems().get(
                 ce.getTablePosition().getRow())
                ).setW(ce.getNewValue());
            }
        };
    EventHandler<TableColumn.CellEditEvent<Box, Integer>> heightEditEvent =
            new EventHandler<TableColumn.CellEditEvent<Box, Integer>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Box, Integer> ce) {
                    System.out.println(ce.getTableView().getItems());
                    (ce.getTableView().getItems().get(
                     ce.getTablePosition().getRow())
                    ).setH(ce.getNewValue());

                    for (Box b:boxList){
                        System.out.println(b.getH());
                    }
                }
            };
    EventHandler<TableColumn.CellEditEvent<Box, Integer>> depthEditEvent =
            new EventHandler<TableColumn.CellEditEvent<Box, Integer>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Box, Integer> ce) {
                    (ce.getTableView().getItems().get(
                     ce.getTablePosition().getRow())
                    ).setD(ce.getNewValue());
                }
            };


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

}

