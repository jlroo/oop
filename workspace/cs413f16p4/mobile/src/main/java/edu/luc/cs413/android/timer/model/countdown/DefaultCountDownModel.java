package edu.luc.cs413.android.timer.model.countdown;
import static edu.luc.cs413.android.timer.model.countdown.Constants.*;

/**
 * An implementation of the stopwatch data model.
 */

public class DefaultCountDownModel implements CountDownModel {

    private int value;

    @Override
    public void resetCounter() {value = 0;}

    @Override
    public void decCounter() {
        value = (value - SEC_PER_TICK) % SEC_PER_HOUR;
    }

    @Override
    public int getCounter() {
        return value;
    }

    @Override
    public void incCounter(){
        value++;
    }

}
