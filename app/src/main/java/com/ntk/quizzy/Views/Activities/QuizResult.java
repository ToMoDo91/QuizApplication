package com.ntk.quizzy.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.ntk.quizzy.R;

public class QuizResult extends AppCompatActivity {
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
        resultText = findViewById(R.id.resultText);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nOfRightAnswers = extras.getString("nOfRightAnswers");
            String nOfQuestions = extras.getString("nOfQuestions");
            resultText.setText(nOfRightAnswers + " Of " + nOfQuestions);

        }

    }
}