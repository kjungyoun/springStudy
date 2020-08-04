package com.example.task2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class UTCTimer extends Thread implements MyTimer{
    @Override
    public void showTime() {
        while(true){
            TimeZone time;
            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss (z Z)");
            time = TimeZone.getTimeZone("UTC");
            format.setTimeZone(time);
            System.out.format("%s%n%s%n%n",time.getDisplayName(),format.format(date));
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
