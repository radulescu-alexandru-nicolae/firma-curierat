package Controller;
import Model.Produs;
import Model.SQLUTIL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class ControllerProdus {
    private SQLUTIL sqlutil=null;
    private List<Produs> produse=null;
    public ControllerProdus(){
        sqlutil=new SQLUTIL();
        produse=sqlutil.listaProduse();
    }
    public void updateProdus(){
        System.out.println("Introduceti numele si awb-ul produsului pentru a verifica daca se afla in baza noastra de date");
        Scanner scanner = new Scanner(System.in);
        String[] informatii=scanner.nextLine().split(",");
        if(verificareProdus(informatii[0],informatii[1])){
            sqlutil.updateProdus(informatii[1],informatii[0]);
        }else{
            System.out.println("Produsul nu se afla in baza noastra de date");
        }

    }
    public void deleteProdus(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti numele si awb-ul produsului pentru a verifica daca se afla in baza noastra de date");
        String[] informatii=scanner.nextLine().split(",");
        if(verificareProdus(informatii[0],informatii[1])){
            sqlutil.deleteTable(new Produs(informatii[0],informatii[1]));
        }else{
            System.out.println("Acest produs nu se afla in baza noastra de date");
        }
    }
    public boolean verificareProdus(String nume,String awb){
        for(Produs p:produse){
            if(p.getAwb_pachet().equals(awb)&&p.getName().equals(nume)){
                return true;
            }
        }
        return false;
    }

}
