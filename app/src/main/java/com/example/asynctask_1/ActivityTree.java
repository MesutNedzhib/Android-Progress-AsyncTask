package com.example.asynctask_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class ActivityTree extends AppCompatActivity {

    private ProgressBar progressBar,progressBar2,progressBar3;
    private Button button;
    private TextView textView,textView2,textView3;
    private boolean downloadSimulation;
    private boolean loginSimulation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);
        progressBar = findViewById(R.id.progressBar);
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar3 = findViewById(R.id.progressBar3);
        textView = findViewById(R.id.textView2);
        textView2 = findViewById(R.id.textView3);
        textView3 = findViewById(R.id.textView4);
        button = findViewById(R.id.button5);

        textView.setVisibility(View.INVISIBLE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.INVISIBLE);
                DownloadImageSimulationTask task = new DownloadImageSimulationTask();
                LoginProcessSimulationTask task2 = new LoginProcessSimulationTask();
                progressBar2.setVisibility(ProgressBar.VISIBLE);
                task.execute();
                task2.execute();
            }
        });
    }

    private class DownloadImageSimulationTask extends AsyncTask<Void,Integer,Boolean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(ProgressBar.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            Random random = new Random();
                int count = random.nextInt(2) + 3;
                for (int i = 0; i < count; i++) {
                         publishProgress((i * 100) / count);
                         try {
                             Thread.sleep(1000);
                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         }
                }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean result){
            super.onPostExecute(result);
            progressBar.setProgress(0);
            progressBar.setVisibility(ProgressBar.INVISIBLE);
            textView2.setVisibility(View.INVISIBLE);
            downloadSimulation=result;
            tasksDone();
        }
    }


    private class LoginProcessSimulationTask extends AsyncTask<Void,Integer,Boolean>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar3.setVisibility(ProgressBar.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            Random random = new Random();

            int count = random.nextInt(3) + 2;
            for (int i = 0; i < count; i++) {
                publishProgress((i * 100) / count);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar3.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            progressBar3.setProgress(0);
            progressBar3.setVisibility(ProgressBar.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);
            loginSimulation=result;
            tasksDone2();
            success();
        }
    }

    private boolean tasksDone() {
         if(downloadSimulation){
            return true;
        }else
         {
             return false;
         }
    }

    private boolean tasksDone2() {
        if(loginSimulation){
            return true;
        }else
        {
            return false;
        }
    }

    private void success(){
        textView.setVisibility(View.VISIBLE);
        progressBar2.setVisibility(progressBar2.INVISIBLE);
        if(tasksDone()&&tasksDone2()){
            textView.setText("SUCCESSFULLY");
        }else
        {
            textView.setText("NOT SUCCESSFULLY");
        }
    }

}

