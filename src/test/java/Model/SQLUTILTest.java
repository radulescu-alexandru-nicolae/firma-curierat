package Model;

import org.junit.jupiter.api.Test;

class SQLUTILTest {
    private SQLUTIL sqlutil=new SQLUTIL();
    @Test
    void insert() {
//        sqlutil.insertTable(new Cont("radulescu.alexandru@gmail.com","qwert","Radulescu","Mihnea"));
//
        //sqlutil.insertTable(new Produs("Bibelou",1.2,"fragil","qwers"));
       sqlutil.insertTable(new Pachet("Radulescu Aurora","Radulescu Alexandru","Craiova,Ion-Tuculescu","Bucuresti,Berceni","2021-01-01","13:00","2021-01-03","14:00"));
//        sqlutil.insertTable(new Locatie("Craiova",44.333,23.817));
//        sqlutil.insertTable(new Locatie("Bucuresti",44.439663,26.096306));
//        sqlutil.insertTable(new Locatie("Brasov",45.65,25.6));
//        sqlutil.insertTable(new Locatie("Cluj",46.7667,23.6));
//        sqlutil.insertTable(new Locatie("Oradea",47.0514,21.9403));
    }
    @Test
    void listaPachete(){
        System.out.println(sqlutil.listaPachete());;
    }
    @Test
    void listaLocaiti(){
        System.out.println(sqlutil.listaLocatii());
    }
    @Test
    void listaProduse(){
        System.out.println(sqlutil.listaProduse());
    }
    @Test
    void deleteTable(){
        //sqlutil.deleteTable(new Produs("Mancare",3,"normal","ROUXX202101"));
        //sqlutil.deleteTable(new Cont("radulescu.andu200@gmail.com","qwert","Radulescu","Alexandru"));
        //sqlutil.deleteTable(new Locatie("Craiova",44.333333, 23.816667));
        sqlutil.deleteTable(new Pachet("Radulescu Aurora","Radulescu Alexandru","Craiova,Ion-Tuculescu","Bucuresti,Berceni","2021-01-01","13:00","2021-01-03","14:00","ROZYS202101"));
    }
    @Test
    void reprogramare(){
        sqlutil.reprogramare("ROPSY202105","2021-05-18,13:00");
    }

}