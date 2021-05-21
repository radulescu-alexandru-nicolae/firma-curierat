package Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PachetTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    Pachet pachet=new Pachet("Radulescu Aurora","Radulescu Alexandru","Craiova,Ion-Tuculescu","Bucuresti,Berceni","2021-01-01","13:00","2021-01-03","14:00");
    @Test
    void add() {
        pachet.addProdus(new Produs("Biblieeeee",1,"normal",pachet.getAWB()));
        Produs p= new Produs("Mancare",3,"normal",pachet.getAWB());
            pachet.addProdus(p);
        pachet.afisareProduse();
        assertEquals(false,pachet.checkProdus(new Produs("Canasaaf",3,"normal",pachet.getAWB())));
        System.out.println(pachet.toString()+"  "+pachet.getGreutate());;
    }
    @Test
    void afisare(){
        pachet.afisareProduse();
    }
    @Test
    void testGreutate(){
        pachet.addProdus(new Produs("Canaaaf",3,"normal",pachet.getAWB()));
        pachet.addProdus(new Produs("Canaaaf",3,"normal",pachet.getAWB()));
    }
    @Test
    void servicii(){
        pachet.addServicii("confirmare");
        pachet.addServicii("constatarea unor stricaciuni");
        pachet.addServicii("deschiderea coletului");
        pachet.afisareServiciiPachet();
    }
    @Test
    void calculDataSosire(){
        Pachet pachet=new Pachet("Radulescu Aurora","Radulescu Alexandru","Craiova,Ion-Tuculescu","Bucuresti,Berceni","2021-01-01","13:00");
        System.out.println(pachet);
    }
}