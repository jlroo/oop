package edu.luc.cs413.android.timer.model.state;
import edu.luc.cs413.android.timer.R;

class StoppedState implements TimerState {

    public StoppedState(final TimerSMStateView sm) {
        this.sm = sm;
    }

    private final TimerSMStateView sm;

    @Override
    public void onStartStop() { sm.actionStart(); sm.toRunningState(); }

    @Override
    public void onIncrement(){ sm.actionInc(); sm.toStoppedState();}

    @Override
    public void onTick() {}

    @Override
    public void onFinish() {}

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.STOPPED;
    }

}
