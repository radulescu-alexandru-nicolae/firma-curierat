package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdusTest {
    @Test
    void equals(){
        Produs p1=new Produs("Cana",1,"fragil","RO!@#");
        Produs p2=new Produs("Cana",1,"fragil","RO!@#");
        System.out.println(p1.equals(p2));
    }

}