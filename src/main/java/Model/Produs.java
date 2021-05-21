package Model;

import java.time.LocalDate;
import java.util.Locale;

public class Produs {
    private String name;
    private double greutate;
    private TipProdus tipProdus;
    private String awb_pachet;
    public Produs(String name, double greutate,String tip,String awb_pachet) {
        this.name = name;
        this.greutate=greutate;
        setTipProdus(tip);
        this.awb_pachet=awb_pachet;
    }

    public Produs(String name, String awb_pachet) {
        this.name = name;
        this.awb_pachet = awb_pachet;
    }

    public void setTipProdus(String produs){
        produs=produs.toLowerCase();
        switch (produs){
            case"normal":this.tipProdus=TipProdus.NORMAL;
            break;
            case"fragil":this.tipProdus=TipProdus.FRAGIL;
            break;
        }
    }
    public String getProdus(){
        switch (this.tipProdus){
            case FRAGIL: return "fragil";
            case NORMAL:return "normal";
            default:return "nothing";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TipProdus getTipProdus() {
        return tipProdus;
    }

    public void setTipProdus(TipProdus tipProdus) {
        this.tipProdus = tipProdus;
    }

    public double getGreutate() {
        return greutate;
    }

    public void setGreutate(double greutate) {
        this.greutate = greutate;
    }

    public String getAwb_pachet() {
        return awb_pachet;
    }

    public void setAwb_pachet(String awb_pachet) {
        this.awb_pachet = awb_pachet;
    }

    @Override
    public boolean equals(Object obj) {
        Produs produs=(Produs) obj;
        return produs.getName().equals(this.getName());
    }

    @Override
    public String toString() {
        return "Produs{" +
                "name='" + name + '\'' +
                ", greutate=" + greutate +
                ", awb_pachet='" + awb_pachet + '\'' +
                '}';
    }
}
