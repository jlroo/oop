package edu.luc.cs413.android.timer.model.countdown;

/**
 * The passive data model of the stopwatch.
 * It does not emit any events.
 *
 */

public interface CountDownModel {
    void resetCounter();
    void decCounter();
    void incCounter();
    int getCounter();
}
