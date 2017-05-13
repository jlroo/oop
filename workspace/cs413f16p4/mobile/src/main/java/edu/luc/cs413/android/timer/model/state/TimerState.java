package edu.luc.cs413.android.timer.model.state;
import edu.luc.cs413.android.timer.common.TimerUIListener;
import edu.luc.cs413.android.timer.model.clock.OnTickListener;

/**
 * A state in a state machine. This interface is part of the State pattern.
 */

interface TimerState extends TimerUIListener, OnTickListener {
    void updateView();
    int getId();
}
