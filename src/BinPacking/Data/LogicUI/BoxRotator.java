package BinPacking.Data.LogicUI;

/**
 * Created by Xsignati on 24.05.2017.
 */
public class BoxRotator implements Rotator {
    public enum Rotations {WLH, LHW, HLW, WHL, HWL, LWH }
    private static final int ROTATIONS_NUM = Rotations.values().length;
    private int rotationStep;
    private Rotations rotation;

    @Override
    public void rotate(BinSpace box) {
        rotation = Rotations.values()[rotationStep];
        System.out.println("rotation step: " + rotationStep + "rotation" + rotation);
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

    @Override
    public int limit(){
        return ROTATIONS_NUM;
    }

    /**
     * Increment a step
     */
    public void next(){
        rotationStep = rotationStep < ROTATIONS_NUM ? rotationStep + 1 : 0 ;
    }

}
