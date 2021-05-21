package Controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerContTest {
    private ControllerCont controllerCont=new ControllerCont();
    @Test
    void verificareCont(){
        assertEquals(true,controllerCont.verificareCont("radulescu.andu200@gmail.com"));
    }
    @Test
    void afisareConturi(){
        controllerCont.afisareConturi();
    }

}