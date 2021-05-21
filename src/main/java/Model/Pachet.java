package Model;
import javax.xml.stream.Location;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
public class Pachet {
    private double greutate;
    private HashMap<Produs,Integer> listaProduse=new HashMap<>();
    private LocalDateTime dataPlecare;
    private LocalDateTime dataSosire;
    private String adresa_Expeditor;
    private String adresa_Desitnatar;
    private String nume_expeditor;
    private String nume_destinatar;
    private List<String> servicii=new ArrayList<>();
    private int intarziere;
    private String AWB;
    private SQLUTIL sqlutil=null;
    private List<Locatie> locatii=null;
    private Vreme vreme;
    public Pachet() {
    }
    public Pachet(String nume_expeditor,String nume_destinatar,String adresa_expeditor,String adresa_destinatar,String dataP,String oraP,String dataA,String oraA,String AWB ) {
        this.nume_expeditor=nume_expeditor;
        this.nume_destinatar=nume_destinatar;
        this.dataPlecare=LocalDateTime.of(LocalDate.parse(dataP), LocalTime.parse(oraP));
        this.dataSosire=LocalDateTime.of(LocalDate.parse(dataA), LocalTime.parse(oraA));
        this.AWB="RO"+generateCharacter()+dataP.split("-")[0]+dataP.split("-")[1];
        this.adresa_Expeditor=adresa_expeditor;
        this.adresa_Desitnatar=adresa_destinatar;
        this.greutate=0;
        this.AWB=AWB;
        sqlutil=new SQLUTIL();
    }
    public Pachet(String nume_expeditor,String nume_destinatar,String adresa_expeditor,String adresa_destinatar,String dataP,String oraP,String dataA,String oraA ) {
        this.nume_expeditor=nume_expeditor;
        this.nume_destinatar=nume_destinatar;
        this.dataPlecare=LocalDateTime.of(LocalDate.parse(dataP), LocalTime.parse(oraP));
        this.dataSosire=LocalDateTime.of(LocalDate.parse(dataA), LocalTime.parse(oraA));
        this.AWB="RO"+generateCharacter()+dataP.split("-")[0]+dataP.split("-")[1];
        this.adresa_Expeditor=adresa_expeditor;
        this.adresa_Desitnatar=adresa_destinatar;
        this.greutate=0;
        sqlutil=new SQLUTIL();
    }
    public Pachet(String nume_expeditor,String nume_destinatar,String adresa_expeditor,String adresa_destinatar,String dataP,String oraP ) {
        this.nume_expeditor=nume_expeditor;
        this.nume_destinatar=nume_destinatar;
        this.dataPlecare=LocalDateTime.of(LocalDate.parse(dataP), LocalTime.parse(oraP));
        this.AWB="RO"+generateCharacter()+dataP.split("-")[0]+dataP.split("-")[1];
        this.adresa_Expeditor=adresa_expeditor;
        this.adresa_Desitnatar=adresa_destinatar;
        this.greutate=0;
        sqlutil=new SQLUTIL();
        locatii=sqlutil.listaLocatii();
        this.dataSosire=calculDataSosire();
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
    public LocalDateTime calculDataSosire(){
        Locatie expeditor=getLocation(this.getAdresa_Expeditor().split(",")[0]);
        Locatie destinatar=getLocation(this.getAdresa_Desitnatar().split(",")[0]);
        LocalDateTime localDateTime=null;
        Vreme vreme=Vreme.getRandom();
        double distanta=expeditor.distanceTo(destinatar);
        if(distanta<100){
            localDateTime=this.getDataPlecare().plusDays(1);
        }
       else if(distanta>100){
            localDateTime=this.getDataPlecare().plusDays(2);
        }else if(distanta>150){
            localDateTime=this.getDataPlecare().plusDays(3);
        }else if(distanta>200){
            localDateTime=this.getDataPlecare().plusDays(4);
        }else{
            localDateTime=this.getDataPlecare().plusDays(9);
        }
       if(vreme==Vreme.NINSOARE){
           System.out.println("Buna ziua domnule "+this.getNume_expeditor().split(" ")[0]+" ne cerem scuze sa va informam dar datorita conditiilor meteo nefavorabile coletul dumneavoastra o sa aiba o intarzire de 2 zile.");
        localDateTime=   localDateTime.plusDays(2);
       }else if(vreme==Vreme.PLOIOSA){
           System.out.println("Buna ziua domnule "+this.getNume_expeditor().split(" ")[0]+" ne cerem scuze sa va informam dar datorita conditiilor meteo nefavorabile coletul dumneavoastra o sa aiba o intarziere de o zi.");
           localDateTime= localDateTime.plusDays(1);
       }else if(vreme==Vreme.PARTIALNOROS){
           System.out.println("Buna ziua domnule "+this.getNume_expeditor().split(" ")[0]+" ne cerem scuze sa va informam dar datorita conditiilor meteo nefavorabile coletul dumneavoastra o sa aiba o intarziere de 12 ore.");
           localDateTime=localDateTime.plusHours(12);
       }
       return localDateTime;
    }
    private boolean verificareLocatie(String name){
        for(Locatie l:locatii){
            if(l.getName().equals(name)==true){
                return true;
            }
        }
        return false;
    }
    public void addServicii(){
        System.out.println("Introduceti serviciile pe care le va contine acest colet:(confirmare,deschiderea coletului,plata ramburs,constatarea unor stricaciuni) separate prin virgula");
        Scanner scanner = new Scanner(System.in);
        String[] serv=scanner.nextLine().split(",");
        for(String s:serv){
            addServicii(s);
        }
    }
    public void afisareServiciiPachet(){
        for(String s:servicii){
            System.out.println(s);
        }
    }
    protected void addServicii(String ser){
        if(servicii.contains(ser)==false){
            servicii.add(ser);
        }else{
            System.out.println("Pachetul respectiv deja contine serviciul:"+ser);
        }
    }
    public void addProdus(){
        Scanner scanner=new Scanner(System.in);
                    System.out.println("Introduceti detalii despre produs in aceasta ordine:name,greutate,tip_produs(fragil sau normal)");
                    String[] informatii=scanner.nextLine().split(",");
                    addProdus(new Produs(informatii[0],Double.parseDouble(informatii[1]),informatii[2],this.getAWB()));
    }
        public void addProdus(Produs produs){
        if(checkProdus(produs)==false){
            listaProduse.put(produs, 1);
            sqlutil.insertTable(produs);
        }else{
                listaProduse.compute(produs,(key,value)->(value==null)?1:++value);
            }
        }
        protected void afisareProduse(){
        Iterator iterator=listaProduse.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Produs,Integer> a=(Map.Entry)iterator.next();
        }
    }
    protected boolean checkProdus(Produs produs){
      for(Map.Entry<Produs,Integer> p:listaProduse.entrySet()){
        if(p.getKey().equals(produs)){

            return true;
        }
      }
return false;
    }
    private String generateCharacter(){
        String string="";
        for(int i=0;i<3;i++){
            char c=(char)(Math.random()*26+'A');
            string+=c;
        }
        return string;
}

    @Override
    public String toString() {
        return "Nume expeditor:"+this.getNume_expeditor()+"\n"+
                "Adresa expeditor:"+this.getAdresa_Expeditor()+"\n"+
                "Data expediere:"+this.dataPlecare+"\n"+
                "Nume destinatar:"+this.getNume_destinatar()+"\n"+
                "Adresa destinatar:"+this.getAdresa_Desitnatar()+"\n"+
                "Data sosire:"+this.dataSosire+"\n"+
                "AWB: "+this.getAWB();

    }

    public String getNume_expeditor() {
        return nume_expeditor;
    }

    public void setNume_expeditor(String nume_expeditor) {
        this.nume_expeditor = nume_expeditor;
    }

    public String getNume_destinatar() {
        return nume_destinatar;
    }

    public void setNume_destinatar(String nume_destinatar) {
        this.nume_destinatar = nume_destinatar;
    }

    public double getGreutate() {
        return greutate;
    }

    public void setGreutate(double greutate) {
        this.greutate = greutate;
    }


    public HashMap<Produs, Integer> getListaProduse() {
        return listaProduse;
    }

    public void setListaProduse(HashMap<Produs, Integer> listaProduse) {
        this.listaProduse = listaProduse;
    }

    public LocalDateTime getDataPlecare() {
        return dataPlecare;
    }

    public void setDataPlecare(LocalDateTime dataPlecare) {
        this.dataPlecare = dataPlecare;
    }

    public LocalDateTime getDataSosire() {
        return dataSosire;
    }

    public void setDataSosire(LocalDateTime dataSosire) {
        this.dataSosire = dataSosire;
    }

    public String getAdresa_Expeditor() {
        return adresa_Expeditor;
    }

    public void setAdresa_Expeditor(String adresa_Expeditor) {
        this.adresa_Expeditor = adresa_Expeditor;
    }

    public String getAdresa_Desitnatar() {
        return adresa_Desitnatar;
    }

    public void setAdresa_Desitnatar(String adresa_Desitnatar) {
        this.adresa_Desitnatar = adresa_Desitnatar;
    }

    public List<String> getServicii() {
        return servicii;
    }

    public void setServicii(List<String> servicii) {
        this.servicii = servicii;
    }

    public int getIntarziere() {
        return intarziere;
    }

    public void setIntarziere(int intarziere) {
        this.intarziere = intarziere;
    }

    public String getAWB() {
        return this.AWB;
    }

    public void setAWB(String AWB) {
        this.AWB = AWB;
    }
}
