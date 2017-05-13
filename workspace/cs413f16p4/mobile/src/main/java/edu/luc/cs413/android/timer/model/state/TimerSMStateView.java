package edu.luc.cs413.android.timer.model.state;

/**
 * The restricted view states have of their surrounding state machine.
 * This is a client-specific interface in Peter Coad's terminology.
 */

interface TimerSMStateView {

    // transitions
    void toRunningState();
    void toStoppedState();

    // actions
    void actionInit();
    void actionReset();
    void actionStart();
    void actionStop();
    void actionInc();
    void actionDec();
    void actionUpdateView();

    // state-dependent UI updates
    void updateUIRuntime();
}
