package Hilos;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Hilo extends Thread {

    public Hilo(){
    }

    public void run(){

        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

        System.out.println(timeStamp);
    }

}
