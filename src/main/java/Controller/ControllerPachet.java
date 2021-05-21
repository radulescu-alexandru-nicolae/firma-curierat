package Controller;

import Model.Locatie;
import Model.Pachet;
import Model.Produs;
import Model.SQLUTIL;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.Scanner;

public class ControllerPachet {
private SQLUTIL sqlutil=null;
private List<Pachet> pachete=null;
private List<Locatie> locatii=null;
private List<Produs> listaProduse=null;
public ControllerPachet(){
    sqlutil=new SQLUTIL();
    pachete=sqlutil.listaPachete();
    locatii=sqlutil.listaLocatii();
    listaProduse=sqlutil.listaProduse();
}
public void informatii(){
    System.out.println("Introduceti AWB-ul pachetului");
    Scanner scanner = new Scanner(System.in);
    String awb=scanner.nextLine();
    if(verificare(awb)==true){
        informatii(awb);
    }else{
        System.out.println("In baza noastra de date nu se afla nici un colet cu acest AWB");
    }
}
public void informatii(String AWB){
    Pachet p=returnPacht(AWB);
    System.out.println("Pachetul cu AWB-ul:"+AWB+" este pe drum");
    System.out.println(p);
    System.out.println("Greutate pachet:"+calculGreutate(AWB)+"kg");
    Locatie expeditor=getLocation(p.getAdresa_Expeditor().split(",")[0]);
    Locatie destinatar=getLocation(p.getAdresa_Desitnatar().split(",")[0]);
    System.out.println("Distanta de parcurs:"+expeditor.distanceTo(destinatar)+" km");
}
protected Locatie getLocation(String name){
   if(verificareLocatie(name)==true){
       for(Locatie l:locatii){
           if(l.getName().equals(name)==true){
               return l;
           }
       }
   }else{
       System.out.println("Nu avem inca aceasta locatie in baza noastra de date");
   }
   return null;
}
public void urmariePachet(){
    System.out.println("Introduceti AWB-ul pachetului");
    Scanner scanner = new Scanner(System.in);
    String awb=scanner.nextLine();
    if(verificare(awb)==true) {
        urmarirePachet(awb);
    }else{
        System.out.println("In baza noastra de date nu se afla nici un colet cu acest AWB");
    }
    }
public void reprogramare(){
    Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti awb-ul coletului pentru a verifica daca se afla in baza noastra de date:");
        String awb=scanner.nextLine();
        if(verificare(awb)==true){
            Pachet p=returnPacht(awb);
            System.out.println("Introduceti noua data de sosire sub forma aceasta(yy-mm-dd,HH:mm).Vechea data de sosire era:"+p.getDataSosire());
            String data=scanner.nextLine();
                reprogramare(awb, data);
                System.out.println("Felicitari reprogramarea a fost realizata cu succes");
        }else{
            System.out.println("Ne cerem scuze dar in baza noastra de date nu se afla nici un colet cu acest awb");
        }


}
public void reprogramare(String awb,String data){
        Pachet p=returnPacht(awb);
        sqlutil.reprogramare(awb,data);

    }
public void updateBazaDeDate(){
    for(Pachet p:pachete){
        if(p.getDataSosire().compareTo(LocalDateTime.now())==0){
            sqlutil.deleteTable(p);
            deleteProduse(p.getAWB());
        }
    }
}
private void deleteProduse(String awb){
    for(Produs p:listaProduse){
        if(p.getAwb_pachet().equals(awb)){
            sqlutil.deleteTable(p);
        }
    }
}
public void deletePachet(){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Introduceti AWB-ul pachetului");
    String awb=scanner.nextLine();
    if(verificare(awb)==true){
        sqlutil.deleteTable(returnPacht(awb));
        deleteProduse(awb);

    }else{
        System.out.println("Nu exista nici un pachet cu acest awb in baza noastra de date");
    }
}
public void urmarirePachet(String AWB){
    Pachet p=returnPacht(AWB);
    Locatie expeditor=getLocation(p.getAdresa_Expeditor().split(",")[0]);
    Locatie destinatar=getLocation(p.getAdresa_Desitnatar().split(",")[0]);
    double distatna=expeditor.distanceTo(destinatar);
    LocalDateTime a=LocalDateTime.now();
    int zile=p.getDataSosire().compareTo(p.getDataPlecare());
    DecimalFormat df=new DecimalFormat("###.###");
    if(a.compareTo(p.getDataPlecare())==(zile/2)){
        distatna=distatna-(distatna*(50.0f/100.0f));
    }else if(a.compareTo(p.getDataPlecare())>1&&a.compareTo(p.getDataPlecare())<(zile/2)){
        distatna=distatna-(distatna*(25.0f/100.0f));
    }else if(a.compareTo(p.getDataPlecare())>(zile/2)){
        distatna=distatna-(distatna*(75.0f/100.0f));
    }
    System.out.println("Coletul mai are:"+Double.parseDouble(df.format(distatna))+" km"+" pana sa ajunga la destinatie");
}
public double calculGreutate(String AWB){
    double g=0;
    for(Produs p:listaProduse){
        if(p.getAwb_pachet().equals(AWB)){
            g+=p.getGreutate();
        }
    }
    return g;
}
private boolean verificareLocatie(String name){
    for(Locatie l:locatii){
        if(l.getName().equals(name)==true){

            return true;
        }
    }
    return false;
}
protected Pachet returnPacht(String awb){
    for(Pachet p:pachete){
        if(p.getAWB().equals(awb)==true){
            return p;
        }
    }
    return null;
}
public Pachet addPachet(){
    System.out.println("Introduceti in aceasta ordine informatiile:Nume Expeditor,Nume Destinatar,Adresa Expeditor,Adresa Destinatar,Data plecare(yyy-mm-dd HH:mm)."+"\nAtentie data de plecare trebuie sa fie dupa ziua de astazi:"+LocalDate.now());
    Scanner scanner = new Scanner(System.in);
    String[] informatii=scanner.nextLine().split(",");
    Pachet p=new Pachet(informatii[0],informatii[1],informatii[2],informatii[3],informatii[4].split(" ")[0],informatii[4].split(" ")[1]);
        sqlutil.insertTable(p);
        return p;
    }

public void updatePachet(){
    System.out.println("Introduceti AWB-ul pachetului sa vedem daca se afla in baza nosatra de date");
    Scanner scanner=new Scanner(System.in);
    String awb=scanner.nextLine();
    if(verificare(awb)==true){
        sqlutil.updatePachet(awb);
    }else{
        System.out.println("In baza noastra de date nu exista nici un pachet cu acest AWB");
    }
}
public boolean verificare(String awb){
    for(Pachet p:pachete){
        if(p.getAWB().equals(awb)){
            return true;
        }
    }
    return false;
}
public void crearePachet(){
    Pachet p=addPachet();
    Scanner scanner = new Scanner(System.in);
    System.out.println("Daca doriti sa adaugati un produs apasati tasta 1");
    System.out.println("Daca doriti sa iesiti din meniu apasati tasta 2");
    boolean quit=false;
    while (quit==false){
        int nr=scanner.nextInt();
        switch (nr){
            case 1:  p.addProdus();
            break;
            case 2:quit=true;
            break;
        }
    }
    p.addServicii();
    System.out.println(p);
    System.out.println("Felicitari pachetul a fost primit de catre curier.Acesta are AWB-ul:" +p.getAWB()+",si estimam ca va ajunge in data de :"+p.getDataSosire());
}
public void afisareServiciiPachet(String AWB){
    Pachet p=returnPacht(AWB);
    p.afisareServiciiPachet();
}
}
