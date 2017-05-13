package edu.luc.cs413.android.timer.common;

/**
 * A source of UI update events for the stopwatch.
 * This interface is typically implemented by the model.
 *
 */

public interface TimerUIUpdateSource {
    void setUIUpdateListener(TimerUIUpdateListener listener);
}
