package edu.luc.cs413.android.timer.model;
import edu.luc.cs413.android.timer.common.TimerUIUpdateListener;
import edu.luc.cs413.android.timer.model.clock.ClockModel;
import edu.luc.cs413.android.timer.model.clock.DefaultClockModel;
import edu.luc.cs413.android.timer.model.state.DefaultTimerStateMachine;
import edu.luc.cs413.android.timer.model.state.TimerStateMachine;
import edu.luc.cs413.android.timer.model.countdown.DefaultCountDownModel;
import edu.luc.cs413.android.timer.model.countdown.CountDownModel;

/**
 * An implementation of the model facade.
 *
 */

public class ConcreteTimerModelFacade implements TimerModelFacade {

    private TimerStateMachine stateMachine;
    private ClockModel clockModel;
    private CountDownModel countdownModel;

    public ConcreteTimerModelFacade() {
        countdownModel = new DefaultCountDownModel();
        clockModel = new DefaultClockModel();
        stateMachine = new DefaultTimerStateMachine(countdownModel, clockModel);
        clockModel.setOnTickListener(stateMachine);
    }

    @Override
    public void onStart() {
        stateMachine.actionInit();
    }

    @Override
    public void setUIUpdateListener(final TimerUIUpdateListener listener) {
        stateMachine.setUIUpdateListener(listener);
    }

    @Override
    public void onStartStop() {
        stateMachine.onStartStop();
    }

    @Override
    public void onIncrement() { stateMachine.onIncrement(); }

    @Override
    public void onFinish() { stateMachine.onFinish();}

}
