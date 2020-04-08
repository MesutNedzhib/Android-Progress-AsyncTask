package com.example.asynctask_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityTwo extends AppCompatActivity {

    Button button;
    EditText value;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button = findViewById(R.id.button);
        value = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExampleAsyncTask task  = new ExampleAsyncTask();
                String number = value.getText().toString();
                int count = Integer.parseInt(number);
                task.execute(count);
                textView.setText("");
                value.getText().clear();
            }
        });

    }


    private class ExampleAsyncTask extends AsyncTask<Integer,Integer,String>{
        boolean running;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ActivityTwo.this,
                    "ProgressDialog",
                    "Wait!");

            Toast.makeText(ActivityTwo.this,
                    "Progress Start",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(Integer... integers) {

            for(int i=integers[0];i>0;i--){
                    publishProgress((i));
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
            }
            return "Finish";
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setMessage(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Toast.makeText(ActivityTwo.this,
                    "Progress Ended",
                    Toast.LENGTH_LONG).show();

            textView.setText("Finish");
            textView.setTextColor(Color.GREEN);

            progressDialog.dismiss();

        }
    }

}
