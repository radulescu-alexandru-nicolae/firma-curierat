package Controller;

import Model.Cont;
import Model.SQLUTIL;

import java.util.List;
import java.util.Scanner;

public class ControllerCont {
    private SQLUTIL sqlutil=null;
    private List<Cont> conturi=null;
    public ControllerCont(){
        sqlutil=new SQLUTIL();
        conturi=sqlutil.listaConturi();
    }
    public void creareCont(){
        System.out.println("Introduceti in aceasta ordine dateele:email,parola,First Name,Last Name");
        Scanner scanner = new Scanner(System.in);
        String[] informatii=scanner.nextLine().split(",");
        if(verificareCont(informatii[0])==false){
            sqlutil.insertTable(new Cont(informatii[0],informatii[1],informatii[2],informatii[3]));
            System.out.println("Felicitari ati creat contul cu succes");
        }
    }
    public boolean conecaterCont(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti email-ul");
        String email=scanner.nextLine();
        if(verificareCont(email)==true) {
            System.out.println("Introduceti parola");
            String parola=scanner.nextLine();
            if (conectareCont(email, parola)) {
                return true;
            } else {
                System.out.println("Parola este gresita");
                return false;
            }
        }else{
            return false;
        }
    }
    public boolean conectareCont(String email,String password){
        for(Cont c:conturi){
            if(c.getEmail().equals(email)&&c.getPassword().equals(password)) {
                System.out.println("Conectare reusita");
                return true;
            }
        }
        return false;
    }
    public void resetareParola(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti email-ul contului pentru a-i putea reseta parola:");
        String email=scanner.nextLine();
        if(verificareCont(email)==true){
            System.out.println("Introduceti noua parola a contului:");
            String parola=scanner.nextLine();
            sqlutil.resetareParola(email,parola);
            System.out.println("Felicitari ati resetat parola cu succes");
        }else{
            System.out.println("Nu se afla un cont care sa aiba aceasta adresa de email in baza noastra de date");
        }
    }
    public void deleteCont(){
        System.out.println("Introduceti email-ul contului pe care doriti sa il stergeti");
        Scanner scanner = new Scanner(System.in);
        String email=scanner.nextLine();
        if(verificareCont(email)==true){
            sqlutil.deleteTable(getCont(email));
        }else{
            System.out.println("Nu se afla un cont care sa aiba aceasta adresa de email in baza noastra de date");
        }
    }
    public void updateCont(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti email-ul contului pentru a verifica daca se afla in baza noastra de date");
        String email=scanner.nextLine();
        if(verificareCont(email)==true){
            sqlutil.updateCont(getCont(email));
        }else{
            System.out.println("Nu se afla un cont care sa aiba aceasta adresa de email in baza noastra de date");
        }
    }
    public Cont getCont(String email){
        for(Cont c:conturi){
            if(c.getEmail().equals(email)){
                return c;
            }
        }
        return null;
    }
    public boolean verificareCont(String email){
        for(Cont c:conturi){
            if(c.getEmail().equals(email)==true){
                return true;
            }
        }
        return false;
    }
    public void afisareConturi(){
        for(Cont c:conturi){
            System.out.println(c);
        }
    }
}
