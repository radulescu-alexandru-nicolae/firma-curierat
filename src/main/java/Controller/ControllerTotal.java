package Controller;
import java.util.Scanner;
public class ControllerTotal {
    private ControllerProdus controllerProdus=new ControllerProdus();
    private ControllerLocatie controllerLocatie=new ControllerLocatie();
    private ControllerPachet controllerPachet=new ControllerPachet();
    private ControllerCont controllerCont=new ControllerCont();
    public void play(){
        Scanner scanner = new Scanner(System.in);
        meniuFinal();
        int nr=scanner.nextInt();
        switch (nr){
            case 1:
                playCont();
                break;
            case 2:
                playMeniuAdmin();
                break;
            case 3:
                playMeniuNormal();
                break;

        }
    }
    public void playMeniuAdmin(){
        Scanner scanner = new Scanner(System.in);
        meniuAdmin();
        int nr=scanner.nextInt();
        switch (nr){
            case 1:
                playLocatie();
                break;
            case 2:
                playMeniuPachetAdmin();
                break;
            case 3:
                playMeniuProdusAdmin();
                break;
        }
    }
    public void playMeniuProdusAdmin(){
        Scanner scanner = new Scanner(System.in);
        boolean quit=false;
        meniuProdusAdmin();
        while (quit==false){
            int nr=scanner.nextInt();
            switch (nr){
                case 1:controllerProdus.updateProdus();
                break;
                case 2:controllerProdus.deleteProdus();
                break;
                case 3:quit=true;
                break;
            }
        }
    }
    public void playMeniuPachetAdmin(){
        Scanner scanner = new Scanner(System.in);
        meniuPachetAdmin();
        boolean quit=false;
        while (quit==false){
            int nr=scanner.nextInt();
            switch (nr){
                case 1:controllerPachet.updatePachet();
                break;
                case 2:controllerPachet.deletePachet();
                break;
                case 3:controllerPachet.updateBazaDeDate();
                break;
                case 4:
                    quit=true;
                    break;
            }
        }
    }
    public void playMeniuNormal(){
        Scanner scanner = new Scanner(System.in);
        meniuNormal();
        boolean quit=false;
        while (quit==false){
            int nr=scanner.nextInt();
            switch (nr){
                case 1:controllerLocatie.afisareLocatii();
                break;
                case 2:
                    playCont();
                    break;
                case 3:
                    System.out.println("Suntem o firma specializata in curierat si incercam sa oferim cele mai bune servicii pentru dumneavoastra");
                    break;
                case 4:
                    quit=true;
                    break;
            }
        }
    }
    public void playPachet(){
        boolean quit=false;
        Scanner scanner =new Scanner(System.in);
        meniuPachet();
        while (quit==false){
            int nr=scanner.nextInt();
            switch (nr) {
                case 1:controllerPachet.crearePachet();

                    break;
                case 2:controllerPachet.reprogramare();
                    break;
                case 3:controllerPachet.informatii();
                    break;
                case 4:controllerPachet.urmariePachet();
                    break;
                case 5:
                    quit=true;
                    break;
            }
        }
    }
    public void playCont(){
        Scanner scanner=new Scanner(System.in);
        meniuCont();
        boolean quit=false;
        while (quit==false){
            int nr=scanner.nextInt();
            switch (nr){
                case 1:if(controllerCont.conecaterCont()==true) {
                    playPachet();
                }else{
                    System.out.println("Email-ul sau parola gresita");
                }
                    break;
                case 2:controllerCont.creareCont();
                    break;
                case 3:controllerCont.resetareParola();
                    break;
                case 4:controllerCont.updateCont();
                    break;
                case 5:
                    quit=true;
                    break;
            }
        }
    }
    public void playLocatie(){
        Scanner scanner=new Scanner(System.in);
        meniuLocatie();
        boolean quit=false;
        while (quit==false){
            int nr=scanner.nextInt();
            switch (nr){
                case 1:controllerLocatie.addLocatie();
                    break;
                case 2:controllerLocatie.updateLocatie();
                    break;
                case 3:controllerLocatie.deleteLocatie();
                    break;
                case 4:
                    quit=true;
                    break;
            }
        }
    }
    public void meniuFinal(){
        System.out.println("Daca esti client apasa tasta 1");
        System.out.println("Daca esti admin apasa tasta 2");
        System.out.println("Daca doresti sa aflii informatii despre noi apasa tasta 3");
    }
    public void meniuCont(){
        System.out.println("Daca doriti sa va conectati la un cont apastati tasta 1");
        System.out.println("Daca doriti sa va creati un cont apasati tasta 2");
        System.out.println("Daca doriti sa va resetati parola apasati tasta 3");
        System.out.println("Daca doriti sa schimbati datele contului dumneavoastra apasati tasta 4");
        System.out.println("Daca doriti sa iesiti din meniu apasati tasta 5");
    }
    public void meniuPachet(){
        System.out.println("Daca doriti sa plasati un pachet apasati tasta 1");
        System.out.println("Daca doriti sa reprogramati un pachet apasati tasta 2");
        System.out.println("Daca doriti sa aflati informatii depsre pachet apasati tasta 3");
        System.out.println("Daca doriti sa urmariti pachetul apasati tasta 4");
        System.out.println("Daca doriti sa iesiti din meniu apasati tasta 5");
    }
    public void meniuNormal(){
        System.out.println("Daca doresti sa vezi locatiile in care trimitem pachete  apasa tasta 1");
        System.out.println("Daca doresti sa intrii in meniul clientului apasa tasta 2");
        System.out.println("Daca doresti sa vezi informatii despre firma apasa tasta 3");
        System.out.println("Daca doresti sa iesi din meniu apasa tasta 4");
    }
    public void meniuLocatie(){
        System.out.println("Daca doresti sa introduci o noua locatie apasa tasta 1");
        System.out.println("Daca doresti sa modifici o locatie apasata tasta 2");
        System.out.println("Daca doresti sa stergi o locatie apasa tasta 3");
        System.out.println("Daca doresti sa iesi din meniu apasa tasta 4");
    }
    public void meniuAdmin(){
        System.out.println("Daca doresti sa ai acces la meniul pentru locatii apasa tasta 1");
        System.out.println("Daca doresti sa ai acces la meniul pentru pachete apasa tasta 2");
        System.out.println("Daca doresti sa ai acces la meniul pentru produse apasa tasta 3");
    }
    public void meniuPachetAdmin(){
        System.out.println("Daca doresti sa modifici detaliile unui pachet apasa tasta 1");
        System.out.println("Daca doresti sa anulezi un pachet apasa tasta 2");
        System.out.println("Daca doresti sa updatezi baza de date apasa tasta 3");
        System.out.println("Daca doresti sa iesi din acest meniu apasa tasta 4");
    }
    public void meniuProdusAdmin(){
        System.out.println("Daca doresti sa modifici detaliile unui produs apasa tasta 1");
        System.out.println("Daca doresti sa stergi un produs apasa tasta 2");
        System.out.println("Daca doresti sa iesi din meniu apasa tasta 3");
    }

}
