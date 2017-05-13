package edu.luc.cs413.android.timer.android;
import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.RingtoneManager;

import edu.luc.cs413.android.timer.R;
import edu.luc.cs413.android.timer.model.countdown.Constants;
import edu.luc.cs413.android.timer.common.TimerUIUpdateListener;
import edu.luc.cs413.android.timer.model.ConcreteTimerModelFacade;
import edu.luc.cs413.android.timer.model.TimerModelFacade;

/**
 * A thin adapter component for the stopwatch.
 *
 */

public class TimerAdapter extends Activity implements TimerUIUpdateListener {

    private static String TAG = "timer-android-activity";

    /**
     * The state-based dynamic model.
     */

    private TimerModelFacade model;
    private CountDownTimer tickCounter;
    private int flag = 0;
    private int MAX = 10;
    private int alarm = 0;

    protected void setModel(final TimerModelFacade model) {
        this.model = model;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inject dependency on view so this adapter receives UI events
        setContentView(R.layout.activity_timer);
        // inject dependency on model into this so model receives UI events
        this.setModel(new ConcreteTimerModelFacade());
        // inject dependency on this into model to register for UI updates
        model.setUIUpdateListener(this);

        tickCounter = new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished) {
                alarm++;
            }
            public void onFinish() {
                model.onStartStop();
                tickCounter.cancel();
                flag = 1;
                cnt = 0;
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        model.onStart();
    }

    /**
     * Plays the default notification sound.
     */
    protected void playDefaultNotification() {
        final Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final MediaPlayer mediaPlayer = new MediaPlayer();
        final Context context = getApplicationContext();
        try {
            mediaPlayer.setDataSource(context, defaultRingtoneUri);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);
            //mediaPlayer.start(); # TRIGGER MEDIA EVENT FROM HERE
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Updates the seconds and minutes in the UI.
     * @param time
     */

    private int countDown;

    public void updateTime(final int time) {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(() -> {
            final TextView CounterDisplay = (TextView) findViewById(R.id.display);
            final int counter = (time % Constants.SEC_PER_MIN);
            CounterDisplay.setText(String.format("%02d",counter));
            countDown=counter;
        });
    }

    /**
     * Updates the state name in the UI.
     * @param stateId
     */

    public void updateState(final int stateId) {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(() -> {
            final TextView stateName = (TextView) findViewById(R.id.stateName);
            if (countDown==0){
                flag = 1; // BUTTON ACTS AS RESET AND STOPS ALARM
                model.onFinish();
                stateName.setText("Stopped");
                playDefaultNotification();
                // START ALARM SOUND (TRIGGER)
            }else{
                stateName.setText(getString(stateId));
            }
        });
    }

    private int cnt;

    // forward event listener methods to the model
    public void onStartStop(final View view) {
        switch (flag) {
            case 0:
                model.onIncrement();
                tickCounter.start();
                cnt++;
                if (cnt==MAX){
                    tickCounter.cancel();
                    model.onStartStop();
                    flag = 1;
                    cnt = 0;
                    break;
                }
                if(1< alarm <4){
                    playDefaultNotification();
                    // ALARM START
                    // LENGTH 1 SEC
                }
                break;

            case 1:
                model.onStartStop();
                flag = 0;
                break;
        }
    }
}
