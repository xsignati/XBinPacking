package sample.BinPackingLogic;

/**
 * Created by Xsignati on 06.03.2017.
 */
public class PackingStrategyFactory {

    public static PackingStrategy getPS(String psType){
        if(psType == null){
            return null;
        }
        if(psType.equalsIgnoreCase("BestFit")){
            return new BestFit();

        }
        else if(psType.equalsIgnoreCase("AreaBestFit")){
            return new AreaBestFit();

        }
        else if(psType.equalsIgnoreCase("FirstFit")){
            return new FirstFit();
        }

        return null;
    }
}
