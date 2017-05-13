package edu.luc.cs413.android.timer.common;

/**
 * Created by jrodriguezorjuela on 11/22/2016.
 */

public interface TimerUIUpdateListener {
    void updateTime(int timeValue);
    void updateState(int stateId);
}
