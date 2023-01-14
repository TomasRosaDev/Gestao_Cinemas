package Cliente;

import Inferfaces.HomePage;

import java.sql.Date;

public class WatchDog extends Thread{
    private boolean on;
    private int contador;
    private int minutos;
    private HomePage homePage;
    private Date date;
    public WatchDog(HomePage homePage,int minutos){
        this.homePage=homePage;
        this.minutos=minutos;
        on=false;
        resetCont();
    }

    public void turnOn(){
        on=true;
    }
    public void turnOff(){
        on=false;
        resetCont();
    }
    public void resetCont(){
        contador=minutos*60;
    }

    @Override
    public void run() {
        while (true){
            try {
                this.sleep(1000);
                date=new java.sql.Date(System.currentTimeMillis());
                homePage.updateClock(date);
                if(on){
                    contador--;
                    if (contador==0){
                        turnOff();
                        homePage.resetPurchase();
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
