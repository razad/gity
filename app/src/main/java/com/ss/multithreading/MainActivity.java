package com.ss.multithreading;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button=findViewById(R.id.btn_main_asyncTask);
        button.post(new Runnable() {
            @Override
            public void run() {
                Log.i("Handler", "onCreate: "+button.getWidth());
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_startThread:
                startThread();
                break;
            case R.id.btn_main_asyncTask:
                SimpleAsyncTask task = new SimpleAsyncTask();
                task.execute();
                break;
            case R.id.btn_main_Handler:
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },3000);
        }
    }

    private void startThread() {
        Thread task1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("Thread", "Task1 Begins, " + Thread.currentThread().getName());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("Thread", "Task1 Ends");
            }
        });
        task1.start();

        Thread task2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("Thread", "Task2 Begins, " + Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("Thread", "Task2 Ends");
            }
        });
        task2.start();

        Thread task3 = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("Thread", "Task3 Begins, " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("Thread", "Task3 Ends");
            }
        });
        task3.start();
    }

    public class SimpleAsyncTask extends AsyncTask<Void, Integer, Void> {
        private ProgressDialog progressDialog;

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgress(0);
            progressDialog.setTitle("Do Some Work");
            progressDialog.setMessage("Please wait... ");
            progressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
        }
    }
}
