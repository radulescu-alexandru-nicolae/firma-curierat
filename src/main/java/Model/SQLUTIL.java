package Model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SQLUTIL {
    private String JDBCurl = "jdbc:mysql://localhost:3306/firma_curierat?autoReconnect=true&uSSL=false";
    private String username = "root";
    private String password = "root";
    private Connection connection = null;
    private Statement statement = null;
    public SQLUTIL() {
        try {
            connection = DriverManager.getConnection(JDBCurl, username, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void executeStatement(String execute) {
        try {
            statement.execute(execute);

        } catch (SQLException e) {
            System.out.println("Nu am reusit:" + execute);
        }
    }
    public void insertTable(Object obj) {
        String insertTo = "";
        if (obj instanceof Cont) {
            Cont cont = (Cont) obj;
            insertTo += "insert into cont(email,password,first_name,last_name)values(";
            insertTo += String.format("'%s','%s','%s','%s'", cont.getEmail(), cont.getPassword(), cont.getFirst_name(), cont.getLast_name());
            insertTo += ")";
            executeStatement(insertTo);
        } else if (obj instanceof Produs) {
            Produs produs = (Produs) obj;
            insertTo += "insert into produs(name,greutate,tip_produs,awb_pachet)values(";
            insertTo += String.format("'%s','%f','%s','%s'", produs.getName(), produs.getGreutate(), produs.getTipProdus(), produs.getAwb_pachet());
            insertTo += ")";
            executeStatement(insertTo);
        } else if (obj instanceof Pachet) {
            Pachet pachet = (Pachet) obj;
            insertTo += "insert into pachet(nume_expeditor,nume_destinatar,adresa_expeditor,adresa_destinatar,data_plecare,data_sosire,AWB)values(";
            insertTo += String.format("'%s','%s','%s','%s','%s','%s','%s'", pachet.getNume_expeditor(), pachet.getNume_destinatar(), pachet.getAdresa_Expeditor(), pachet.getAdresa_Desitnatar(), pachet.getDataPlecare(), pachet.getDataSosire(), pachet.getAWB());
            insertTo += ")";
            executeStatement(insertTo);
        } else if (obj instanceof Locatie) {
            Locatie location = (Locatie) obj;
            insertTo += "insert into location(nume_oras,latitudine,longitudine)values(";
            insertTo += String.format("'%s','%f','%f'", location.getName(), location.getLatitude(), location.getLongitude());
            insertTo += ")";
            executeStatement(insertTo);
        }
    }
    public void deleteTable(Object obj){
        String delete="";
        if(obj instanceof Cont){
            Cont cont=(Cont) obj;
            delete+=String.format("delete from cont where email='%s'",cont.getEmail());
        }else if(obj instanceof Locatie){
            Locatie locatie=(Locatie) obj;
            delete+=String.format("delete from location where nume_oras='%s'",locatie.getName());
        }else if(obj instanceof Produs){
            Produs produs=(Produs) obj;
            delete+=String.format("delete from produs where awb_pachet='%s' and name='%s'",produs.getAwb_pachet(),produs.getName());
        }else if(obj instanceof Pachet){
            Pachet pachet=(Pachet) obj;
            delete+=String.format("delete from pachet where AWB='%s'",pachet.getAWB());
        }
        executeStatement(delete);
    }
    public void updateLocation(Locatie locatie){
        Scanner scanner = new Scanner(System.in);
        Locatie aux=locatie;
        String update="update location set ";
        System.out.println("Intorudceti in aceasta ordinte separate prin virgula detaliile pe care doriti sa le modificati:nume_oras,latitudine,longitudine");
    String[] prop=scanner.nextLine().split(",");
    for(String s:prop){
        switch (s){
            case "nume_oras":
                System.out.println("Introduceti noul nume");
                String oras=scanner.nextLine();
                if(prop[0].equals("nume_oras")){
                    update+=String.format(" nume_oras='%s'",oras);
                }else{
                    update+=String.format(",nume_oras='%s'",oras);
                }
                break;
            case "latitudine":
                System.out.println("Introduceti noua latitudine");
                Double latitudine=scanner.nextDouble();
                if(prop[0].equals("latitudine")){
                    update+=String.format(" latitudine='%s'",latitudine);
                }else{
                    update+=String.format(",latitudine='%s'",latitudine);
                }
                break;
            case "longitudine":
                System.out.println("Introduceti noua longitudine");
                Double longitudine=scanner.nextDouble();
                if(prop[0].equals("longitudine")){
                    update+=String.format(" longitudine='%s'",longitudine);
                }else{
                    update+=String.format(",longitudine='%s'",longitudine);
                }
                break;
        }
    }
    update+=String.format("where nume_oras='%s'",locatie.getName());
    executeStatement(update);
    }
    public void updateProdus(String awb,String nume){
        String update="update produs set ";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti in aceasta ordine separate prin virgula detaliile pe care doriti sa le schimbati:name,greutate,awb_pachet");
        String[] prop=scanner.nextLine().split(",");
        for(String s:prop){
            switch (s){
                case "name":
                    System.out.println("Introduceti noul nume");
                    String numee=scanner.nextLine();
                    if(prop[0].equals("name")){
                        update+=String.format("name='%s'",numee);
                    }else{
                        update+=String.format(",name='%s'",numee);
                    }
                    break;
                case "greutate":
                    System.out.println("Introdu noua greutate");
                    double greutate=scanner.nextDouble();
                    if(prop[0].equals("greutate")){
                        update+=String.format("greutate='%f'",greutate);
                    }else{
                        update+=String.format(",greutate='%f'",greutate);
                    }
                    break;
                case "awb_pachet":
                    System.out.println("Introduceti noul awb");
                    String ppp=scanner.nextLine();
                    if(prop[0].equals("awb_pachet")){
                        update+=String.format("awb_pachet='%s'",ppp);
                    }else{
                        update+=String.format(",awb_pachet='%s'",ppp);
                    }
            }
        }
        update+=String.format(" where name='%s' and awb_pachet='%s'",nume,awb);
        System.out.println(update);
        executeStatement(update);
    }
    public void updatePachet(String AWB){
        String update="update pachet set ";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti in aceasta ordine modificiarile pe care doriti sa le faceti separate prin virgula(nume_expeditor,nume_destinatar,adresa_expeditor,adresa_destinatar,data_plecare,data_sosire,AWB");
            String[] prop=scanner.nextLine().split(",");
            for(String s:prop){
                switch (s){
                    case "nume_expeditor":
                        System.out.println("Introduceti noul nume(expeditor)");
                        String numeExp=scanner.nextLine();
                        if(prop[0].equals("nume_expeditor")){
                            update+=String.format(" nume_expeditor='%s'",numeExp);
                        }else{
                            update+=String.format(",nume_expeditor='%s'",numeExp);
                        }
                        break;
                    case "nume_destinatar":
                    System.out.println("Introduceti noul nume(destinatar)");
                    String numeDEST=scanner.nextLine();
                    if(prop[0].equals("nume_destinatar")){
                        update+=String.format(" nume_destinatar='%s'",numeDEST);
                    }else{
                        update+=String.format(",nume_destinatar='%s'",numeDEST);
                    }
                    break;
                    case "adresa_expeditor":
                        System.out.println("Introduceti noua adresa(expeditor):");
                        String adresa=scanner.nextLine();
                        if(prop[0].equals("adresa_expeditor")){
                            update+=String.format(" adresa_expeditor='%s'",adresa);
                        }else{
                            update+=String.format(",adresa_expeditor='%s'",adresa);
                        }
                        break;

                    case "data_plecare":
                        System.out.println("Introduceti noua data de plecare in aceasta forma:yyy-mm-dd,HH:mm");
                        String data=scanner.nextLine();
                        if(prop[0].equals("data_plecare")){
                            update+=String.format("data_plecare='%s'",data);
                        }else{
                            update+=String.format(",data_plecare='%s'",data);
                        }
                        break;
                    case "data_sosire":
                        System.out.println("Introduceti noua data de sosire in aceasta forma:yyy-mm-dd,HH:mm");
                        String dataS=scanner.nextLine();
                        if(prop[0].equals("data_sosire")){
                            update+=String.format("data_sosire='%s'",dataS);
                        }else{
                            update+=String.format(",data_sosire='%s'",dataS);
                        }
                        break;

                    case "AWB":
                        System.out.println("Introduceti noul AWB");
                        String awb=scanner.nextLine();
                        if(prop[0].equals("AWB")){
                            update+=String.format("AWB='%s'",awb);
                        }else{
                            update+=String.format(",AWB='%s'",awb);
                        }
                }
            }
            update+=String.format(" where AWB='%s'",AWB);
            executeStatement(update);
    }
    public void reprogramare(String awb,String data){
        String update="update pachet set ";
        update+=String.format(" data_sosire='%s'",data);
        update+=String.format(" where AWB='%s'",awb);
        executeStatement(update);
    }
    public void updateCont(Cont cont){
        String update="update cont set ";
        Cont aux=cont;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti detaliile pe care doriti sa le schimbati la cont separate prin virgula si in aceasta orinde:email,password,first_name,last_name");
        String[] prop=scanner.nextLine().split(",");
        for(String s:prop){
            switch (s){
                case "email":
                    System.out.println("Introduceti noul email");
                    String email=scanner.nextLine();
                    if(prop[0].equals("email")){
                        update+=String.format("email='%s'",email);
                    }else{
                        update+=String.format(",email='%s'",email);
                    }
                    break;
                case "password":
                    System.out.println("Introduceti noua parola");
                    String password=scanner.nextLine();
                    if(prop[0].equals("password")){
                        update+=String.format("password='%s'",password);
                    }else{
                        update+=String.format(",password='%s'",password);
                    }
                    break;
                case "first_name":
                    System.out.println("Introduceti first_name-ul");
                    String name=scanner.nextLine();
            if(prop[0].equals("first_name")){
                update+=String.format("first_name='%s'",name);
            }else{
                update+=String.format(",first_name='%s'",name);
            }
            break;
                case "last_name":
                    System.out.println("Introduceti last_name-ul");
                    String last=scanner.nextLine();
                    if(prop[0].equals("last_name")){
                        update+=String.format("last_name='%s'",last);
                    }else{
                        update+=String.format(",last_name='%s'",last);
                    }
                    break;
            }
        }
        update+=String.format(" where email='%s'",aux.getEmail());
        executeStatement(update);
    }
    public void resetareParola(String email,String parola){
        String update="update cont set ";
        update+=String.format("password='%s'",parola);
        update+=String.format(" where email='%s'",email);
        executeStatement(update);
    }
    public ResultSet allCont() {
        executeStatement("select * from cont");
        try {
            return statement.getResultSet();
        } catch (Exception e) {
            System.out.println("Nu s-a executat");
            return null;
        }
    }
    public ResultSet allLocation() {
        executeStatement("select * from location");
        try {
            return statement.getResultSet();
        } catch (Exception e) {
            System.out.println("Nu s-a executat");
            return null;
        }
    }
    public ResultSet allProdus() {
        executeStatement("select * from produs");
        try {
            return statement.getResultSet();
        } catch (Exception e) {
            System.out.println("Nu s-a executat");
            return null;
        }
    }
    public ResultSet allPachet(){
        executeStatement("select * from pachet");
        try{
            return statement.getResultSet();
        }catch (Exception e){
            System.out.println("Nu s-a executat");
            return null;
        }
    }
    public List<Cont> listaConturi(){
        ResultSet set=allCont();
        List<Cont> conturi=new ArrayList<>();
        try{
            while (set.next()){
                conturi.add(new Cont(set.getString(2),set.getString(3),set.getString(4),set.getString(5)));
            }
            return conturi;
        }catch (Exception e){
            System.out.println("Nu s-a creat lista conturi");
            return null;
        }
    }
    public List<Produs> listaProduse(){
        ResultSet set=allProdus();
        List<Produs> listaProduse=new ArrayList<>();
        try{
            while (set.next()){
                listaProduse.add(new Produs(set.getString(2),Double.parseDouble(set.getString(3)),set.getString(4),set.getString(5)));

            }
            return listaProduse;
        }catch (Exception e){
            System.out.println("Nu s-a realizat lista produse");
            return null;
        }
    }
    public List<Locatie> listaLocatii(){
        ResultSet set=allLocation();
        List<Locatie> listaLocatii=new ArrayList<>();
        try{
            while (set.next()){
                listaLocatii.add(new Locatie(set.getString(2),Double.parseDouble(set.getString(3)),Double.parseDouble(set.getString(4))));
            }
            return listaLocatii;
        }catch (Exception e){
            System.out.println("Nu s-a creat lista locatii");
            return null;
        }
    }
    public List<Pachet> listaPachete(){
        ResultSet set=allPachet();
        List<Pachet> pachete=new ArrayList<>();
        try{
            while (set.next()){
                DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyy-MM-dd,HH:mm");
                  LocalDateTime datainceput=LocalDateTime.of(LocalDate.parse(set.getString(6).split(" ")[0]),LocalTime.parse(set.getString(6).split(" ")[1]));
                    LocalDateTime datasfarsit=LocalDateTime.of(LocalDate.parse(set.getString(7).split(" ")[0]),LocalTime.parse(set.getString(7).split(" ")[1]));
               String data1=datainceput.format(dateTimeFormatter);
               String data2=datasfarsit.format(dateTimeFormatter);
               pachete.add(new Pachet(set.getString(2),set.getString(3),set.getString(4),set.getString(5),data1.split(",")[0],data1.split(",")[1],data2.split(",")[0],data2.split(",")[1],set.getString(8)));

            }
            return pachete;
        }catch (Exception e){
            System.out.println("Nu s-a realizat lista pachete");
            return null;
        }
    }

}
