package Cliente;

import Inferfaces.HomePage;

public class WatchDog extends Thread{
    private boolean on;
    private int contador;
    private int minutos;
    private HomePage homePage;
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
                //clock
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
