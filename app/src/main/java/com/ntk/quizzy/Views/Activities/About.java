package com.ntk.quizzy.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.ntk.quizzy.R;

public class About extends AppCompatActivity {

    private TextView ver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ver = findViewById(R.id.ver);
        ver.setText("0.1.1b");
    }
}