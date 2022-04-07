package com.ntk.quizzy.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ntk.quizzy.Database.QuestionModel;
import com.ntk.quizzy.R;

import com.ntk.quizzy.ViewModels.QuestionViewModel;

import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    private TextView questitle, allQuesTextView, scoreTextView;
    private Button btnop1, btnop2, btnop3, btnop4;
    private ImageView quesImage;

    ////////////////////////////////////////////
    private QuestionViewModel QVM;
    ////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //////////////////////////////////////
        //Bind the view with the Code
        questitle = findViewById(R.id.questitle);
        btnop1 = findViewById(R.id.btnop1);
        btnop2 = findViewById(R.id.btnop2);
        btnop3 = findViewById(R.id.btnop3);
        btnop4 = findViewById(R.id.btnop4);
        quesImage = findViewById(R.id.quesImage);
        allQuesTextView = findViewById(R.id.allQuesTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        //////////////////////////////////////

        /////// Bind ViewModel ///////
        QVM = new ViewModelProvider(this).get(QuestionViewModel.class);
        /////// Bind ViewModel ///////

        //////////////////////////////////////////////////////////////////////////////
        QVM.GetAllQues().observe(this, new Observer<List<QuestionModel>>() {
            @Override
            public void onChanged(List<QuestionModel> allQuestions) {
                QVM.setCurrent(allQuestions);

            }
        });
        //////////////////////////////////////////////////////////////////////////////

        QVM.GetFinished().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    ResPage(QVM.NumberOfRightAnswer, QVM.CurrentListOfQues.size());
                    close();
                }
            }
        });

        QVM.GetnoQues().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Toast.makeText(QuestionActivity.this, "Please Add questions", Toast.LENGTH_SHORT).show();
                    close();
                }
            }
        });

        //////////////////////////////////////////////////////////////////////////////
        // Create the observer which updates the UI.
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        QVM.GetQuestionToView().observe(this, new Observer<QuestionModel>() {
            @Override
            public void onChanged(QuestionModel questionToView) {
                allQuesTextView.setText(String.valueOf(QVM.NumberOfAllQues));
                scoreTextView.setText(String.valueOf(QVM.NumberOfRightAnswer));
                questitle.setText(questionToView.getTitle());
                btnop1.setText(questionToView.getAnswerOne());
                btnop2.setText(questionToView.getAnswerTwo());
                btnop3.setText(questionToView.getAnswerThree());
                btnop4.setText(questionToView.getAnswerFour());
                if (questionToView.getQuestionImage() != null) {
                    Bitmap bmp = BitmapFactory.decodeByteArray(questionToView.getQuestionImage(), 0, questionToView.getQuestionImage().length);
                    quesImage.setImageBitmap(bmp);
                }

            }
        });
        //////////////////////////////////////////////////////////////////////////////


        btnop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QVM.isItRightAnswer(1);
                QVM.AskQuestion();

            }
        });
        btnop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QVM.isItRightAnswer(2);
                QVM.AskQuestion();
            }
        });
        btnop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QVM.isItRightAnswer(3);
                QVM.AskQuestion();
            }
        });
        btnop4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QVM.isItRightAnswer(4);
                QVM.AskQuestion();
            }
        });
    }

    public void close() {
        this.finish();
    }

    public void ResPage(int R, int All) {
        Intent res = new Intent(this, QuizResult.class);
        res.putExtra("nOfRightAnswers", String.valueOf(R));
        res.putExtra("nOfQuestions", String.valueOf(All));
        startActivity(res);
    }


}