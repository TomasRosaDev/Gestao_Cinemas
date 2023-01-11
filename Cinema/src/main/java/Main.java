import Cliente.*;
import DadosPermanentes.*;
import Gestor.*;
import Inferfaces.HomePage;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        //watchDog
        int tentativa=0;
        HomePage homePage=new HomePage();
        SbdHandler sbdHandler;
        while(true){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                sbdHandler=new SbdHandler();

                homePage.newCliente(sbdHandler);
                break;
            }
            catch (Exception e){
                tentativa++;
                homePage.failConnection(e,tentativa);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        /*


        SbdHandler sbdHandler=new SbdHandler();
        Cinema cinema=new Cinema(sbdHandler);
        Cliente cliente=new Cliente();

         */
    }
}
