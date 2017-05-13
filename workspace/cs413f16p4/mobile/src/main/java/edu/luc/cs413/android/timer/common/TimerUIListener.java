package edu.luc.cs413.android.timer.common;

/**
 * A listener for stopwatch events coming from the UI.
 *
 */

public interface TimerUIListener {
    void onStartStop();
    void onIncrement();
    void onFinish();
}
