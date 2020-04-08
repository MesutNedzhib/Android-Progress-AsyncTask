package com.example.asynctask_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button2);
        button2 = findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityTwo();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityTree();
            }
        });
    }

    public void openActivityTwo(){
        Intent intent = new Intent(this,ActivityTwo.class);
        startActivity(intent);
    }

    public void openActivityTree(){
        Intent intent = new Intent(this,ActivityTree.class);
        startActivity(intent);
    }

}
