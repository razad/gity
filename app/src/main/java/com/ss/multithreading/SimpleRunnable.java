package com.ss.multithreading;

import android.util.Log;

/**
 * Created by Saeed Shahini on 8/16/2018.
 */
public class SimpleRunnable implements Runnable {
    @Override
    public void run() {
        Log.i("Thread","Thread Begins");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i("Thread","Thread Ends");
    }
}
