package edu.luc.cs413.android.timer.model.clock;

/**
 * A source of onTick events for the stopwatch.
 * This interface is typically implemented by the model.
 *
 */

public interface OnTickSource {
    void setOnTickListener(OnTickListener listener);
}
