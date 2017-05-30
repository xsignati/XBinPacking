package BinPacking.Data.LogicUI;

/**
 * Created by Xsignati on 24.05.2017.
 */
public class BoxRotator{
    public enum Rotations {WLH, LHW, HLW, WHL, HWL, LWH }
    public static final int ROTATIONS_NUM = Rotations.values().length;
    private int rotationStep;
    private Rotations rotation;

    public void rotate(BinSpace box) {
        System.out.println("rotationstep " + rotationStep);
        rotation = Rotations.values()[rotationStep];
        switch (rotation) {
            case WLH:
                box.setSize(box.ORIGINAL_WIDTH, box.ORIGINAL_LENGTH, box.ORIGINAL_HEIGHT);
                break;
            case LHW:
                box.setSize(box.ORIGINAL_LENGTH, box.ORIGINAL_HEIGHT, box.ORIGINAL_WIDTH);
                break;
            case HLW:
                box.setSize(box.ORIGINAL_HEIGHT, box.ORIGINAL_LENGTH, box.ORIGINAL_WIDTH);
                break;
            case WHL:
                box.setSize(box.ORIGINAL_WIDTH, box.ORIGINAL_HEIGHT, box.ORIGINAL_LENGTH);
                break;
            case HWL:
                box.setSize(box.ORIGINAL_HEIGHT, box.ORIGINAL_WIDTH, box.ORIGINAL_LENGTH);
                break;
            case LWH:
                box.setSize(box.ORIGINAL_LENGTH, box.ORIGINAL_WIDTH, box.ORIGINAL_HEIGHT);
                break;
            default:
                box.setSize(box.ORIGINAL_LENGTH, box.ORIGINAL_WIDTH, box.ORIGINAL_HEIGHT);
                break;
        }
        next();
    }
    public void next(){
        rotationStep = rotationStep < ROTATIONS_NUM - 1 ? rotationStep + 1 : 0 ;
    }
}
