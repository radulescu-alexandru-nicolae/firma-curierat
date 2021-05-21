package Controller;

import Model.Locatie;
import Model.SQLUTIL;

import java.util.List;
import java.util.Scanner;

public class ControllerLocatie {
    private SQLUTIL sqlutil = null;
    private List<Locatie> locatii = null;
    public ControllerLocatie() {
        sqlutil = new SQLUTIL();
        locatii = sqlutil.listaLocatii();
    }
    public void addLocatie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti numele locatiei:");
        String nume=scanner.nextLine();
        if(verificareLocatie(nume)==true){
            System.out.println("Deja se afla aceasta locatie in baza noastra de date");
        }else{
            System.out.println("Introduceti latitudinea ");
            Double latitudine=scanner.nextDouble();
            System.out.println("Introduceti longitudinea ");
            Double longitudine=scanner.nextDouble();
            sqlutil.insertTable(new Locatie(nume,latitudine,longitudine));
            System.out.println("Felicitari ai introdus locatia cu succes");
        }

    }
    public void deleteLocatie(){
        System.out.println("Introduceti numele locatiei pe care doriti sa o stergeti:");
        Scanner scanner = new Scanner(System.in);
        String nume=scanner.nextLine();
        if(verificareLocatie(nume)==true){
            sqlutil.deleteTable(new Locatie(nume));
            System.out.println("Felicitari ai sters locatia:" +nume+" cu succes");
        }else{
            System.out.println("Aceasta locatie nu se afla in baza noastra de date");
        }
    }
    public void updateLocatie(){
        System.out.println("Introduceti numele locatie careia doriti sa ii aduceti modificari:");
        Scanner scanner=new Scanner(System.in);
        String nume=scanner.nextLine();
        if(verificareLocatie(nume)==true){
            sqlutil.updateLocation(new Locatie(nume));
            System.out.println("Felicitari ai updatat locatia: "+nume+" cu succes");
        }else{
            System.out.println("Aceasta locatie nu se afla in baza noastra de date");
        }
    }
    public void afisareLocatii(){
        for(Locatie l:locatii){
            System.out.println(l.getName());
        }
    }

    public boolean verificareLocatie(String name) {
        for (Locatie l : locatii) {
            if (l.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
