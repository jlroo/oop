package edu.luc.cs413.android.timer.model.state;
import edu.luc.cs413.android.timer.common.TimerUIUpdateSource;
import edu.luc.cs413.android.timer.common.TimerUIListener;
import edu.luc.cs413.android.timer.model.clock.OnTickListener;

/**
 * The state machine for the state-based dynamic model of the stopwatch.
 * This interface is part of the State pattern.
 *
 */

public interface TimerStateMachine extends TimerUIListener, OnTickListener, TimerUIUpdateSource, TimerSMStateView { }
