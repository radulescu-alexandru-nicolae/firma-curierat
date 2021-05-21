package Controller;

import Model.Cont;
import Model.Locatie;
import Model.Pachet;
import org.junit.jupiter.api.Test;

import javax.xml.stream.Location;

import static org.junit.jupiter.api.Assertions.*;

class ControllerPachetTest {
@Test
    void informatii(){
    ControllerPachet controllerPachet=new ControllerPachet();
    controllerPachet.informatii("ROEHM202105");
}
@Test
void getLocation(){
    ControllerPachet controllerPachet=new ControllerPachet();
    Locatie l=controllerPachet.getLocation("Bucuresti");
    System.out.println(l);
}
@Test
    void returnPachet(){
    ControllerPachet controllerPachet=new ControllerPachet();
    Pachet p=controllerPachet.returnPacht("ROEHM202105");
    System.out.println(p);
}
@Test
    void urmarie(){
    ControllerPachet controllerPachet=new ControllerPachet();
    Pachet p=controllerPachet.returnPacht("ROEHM202105");
    controllerPachet.urmarirePachet("ROZYS202101");
}
@Test
    void verificare(){
    ControllerPachet controllerPachet=new ControllerPachet();
   assertEquals(true, controllerPachet.verificare("ROEHM202105"));
}
}