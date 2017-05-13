package edu.luc.cs413.android.timer.model;
import edu.luc.cs413.android.timer.common.TimerUIUpdateSource;
import edu.luc.cs413.android.timer.common.TimerUIListener;


/**
 * A thin model facade. Following the Facade pattern,
 * this isolates the complexity of the model from its clients (usually the adapter).
 *
 */

public interface TimerModelFacade extends TimerUIListener, TimerUIUpdateSource {
    void onStart();
}
