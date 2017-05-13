package edu.luc.cs413.android.timer.model.state;
import edu.luc.cs413.android.timer.R;

class RunningState implements TimerState {

    public RunningState(final TimerSMStateView sm) {
        this.sm = sm;
    }

    private final TimerSMStateView sm;

    @Override
    public void onStartStop() { sm.actionStop(); sm.toStoppedState();}

    @Override
    public void onIncrement(){ sm.actionStop(); sm.toStoppedState(); }

    @Override
    public void onTick() { sm.actionDec(); sm.toRunningState();}

    @Override
    public void onFinish(){sm.actionStop(); sm.toStoppedState();}

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.RUNNING;
    }

}
