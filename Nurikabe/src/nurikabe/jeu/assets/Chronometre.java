/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.jeu.assets;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.Timer;
import java.util.TimerTask;

public class Chronometre {

    static private Chronometre mChronometre;

    private Timer mTimer;
    private Label mLabel;
    private long time;
    
    public Chronometre(long saveTime) {
        this.time = saveTime;
    }
    
    public void start() {
        if (mTimer == null) {
            mTimer = new Timer();
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    time++;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            mLabel.setText(formatTime());
                        }
                    });
                }
            }, 1000, 1000);
        }
    }
    
    public void pause() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }
    
    public void setTime(long time){
        this.time=time;
    }
    
    
    public void setLabel(Label label) {
        mLabel = label;
    }
    
    private String formatTime() {
        long minutes = time / 60;
        long seconds = time % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
    
    public long getTime(){
        return this.time;
    }
    
    static public Chronometre getTimer() {
        if(mChronometre == null) {
            mChronometre = new Chronometre(0);
        }
        return mChronometre;
    }
}
