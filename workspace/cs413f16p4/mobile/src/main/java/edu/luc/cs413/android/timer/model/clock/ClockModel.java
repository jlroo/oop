package edu.luc.cs413.android.timer.model.clock;

/**
 * The active model of the internal clock that periodically emits tick events.
 *
 */
public interface ClockModel extends OnTickSource {
    void start();
    void stop();
}
