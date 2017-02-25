package sample.BinPackingLogic;

/**
 * Created by Xsignati on 24.01.2017.
 */
public class BestFit extends SearchStrategy {
    @Override
    public Bin search(Bin bin, Box box) {
        Bin minBin;

        if(bin.getBinState() == Bin.BinState.EMPTY && boxFitsToBin(bin, box))
            minBin = bin;
        else
            minBin = null;

        for (Bin child : bin.getChildren()) {
            minBin = min(minBin, search(child, box));
        }

        return minBin;
    }

    private Bin min(Bin bin1, Bin bin2){
        Bin minNode;
        if(bin1 == null)
            minNode = bin2;
        else{
            if(bin2 == null)
                minNode = bin1;
            else{
                if(bin2.getVolume() < bin1.getVolume())
                    minNode = bin2;
                else
                    minNode = bin1;
            }
        }
        return minNode;
    }

}

