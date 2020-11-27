package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private AnimationDrawable animationDrawable;
    private TextView tvStatus;
    private Button startButton;
    private Button stopButton;
    private ProgressBar progressBar;
    private ProgressTask progressTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvStatus = findViewById(R.id.tv_status);
        startButton = findViewById(R.id.button);
        stopButton = findViewById(R.id.button2);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }

    public void startAnimation(View view) {
        progressTask = new ProgressTask();
        progressTask.execute();



    }

    public void stopAnimation(View view) {
        progressTask.cancel(true);
    }

    class ProgressTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvStatus.setText("In Progress");
            startButton.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                TimeUnit.SECONDS.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tvStatus.setText("finish");
            progressBar.setProgress(0);
            progressBar.setVisibility(ProgressBar.INVISIBLE);
            startButton.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            tvStatus.setText("stopped");
            progressBar.setProgress(0);
            progressBar.setVisibility(ProgressBar.INVISIBLE);
            startButton.setVisibility(View.VISIBLE);
        }
    }
}