package Model;

import java.util.Random;

public enum Vreme {
    INSORITA,PLOIOSA,PARTIALNOROS,NINSOARE;
    public static Vreme getRandom(){
        Random random=new Random();
        return values()[random.nextInt(values().length)];
    }
}
