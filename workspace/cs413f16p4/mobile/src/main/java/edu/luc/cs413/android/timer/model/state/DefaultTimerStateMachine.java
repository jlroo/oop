package edu.luc.cs413.android.timer.model.state;
import edu.luc.cs413.android.timer.common.TimerUIUpdateListener;
import edu.luc.cs413.android.timer.model.clock.ClockModel;
import edu.luc.cs413.android.timer.model.countdown.CountDownModel;

/**
 * An implementation of the state machine for the stopwatch.
 *
 */


public class DefaultTimerStateMachine implements TimerStateMachine {

    public DefaultTimerStateMachine(final CountDownModel countdownModel, final ClockModel clockModel) {
        this.countdownModel = countdownModel;
        this.clockModel = clockModel;
    }

    public final CountDownModel countdownModel;
    private final ClockModel clockModel;
    private TimerState state;

    protected void setState(final TimerState state) {
        this.state = state;
        uiUpdateListener.updateState(state.getId());
    }

    private TimerUIUpdateListener uiUpdateListener;

    @Override
    public void setUIUpdateListener(final TimerUIUpdateListener uiUpdateListener) {this.uiUpdateListener = uiUpdateListener;}

    // forward event uiUpdateListener methods to the current state
    // these must be synchronized because events can come from the
    // UI thread or the timer thread

    @Override public synchronized void onStartStop() { state.onStartStop(); actionUpdateView(); }

    @Override
    public synchronized void onIncrement() { countdownModel.incCounter(); actionUpdateView(); }

    @Override
    public void onFinish() {
        state.onFinish();
        actionUpdateView();
    }

    @Override public synchronized void onTick()      { state.onTick(); }

    @Override public void updateUIRuntime() { uiUpdateListener.updateTime(countdownModel.getCounter()); }

    // known states
    private final TimerState STOPPED = new StoppedState(this);
    private final TimerState RUNNING = new RunningState(this);

    // transitions
    @Override public void toRunningState()    { setState(RUNNING); }
    @Override public void toStoppedState()    { setState(STOPPED); }

    // actions
    @Override public void actionInit()       { toStoppedState(); actionReset(); }
    @Override public void actionReset()      { countdownModel.resetCounter(); actionUpdateView();}
    @Override public void actionStart()      { clockModel.start(); }

    @Override public void actionStop()       {
        clockModel.stop();
        toStoppedState();
        actionReset();
    }

    @Override
    public void actionInc()      { countdownModel.incCounter(); actionUpdateView(); }

    @Override
    public void actionDec()        { countdownModel.decCounter(); actionUpdateView();
    }

    @Override public void actionUpdateView() { state.updateView(); }
}
