package com.ntk.quizzy.Views.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.ntk.quizzy.R;
import com.ntk.quizzy.ViewModels.MainActivityViewModel;


public class MainActivity extends AppCompatActivity {

    private TextView AppTitle;
    private Button aboutBTN;
    private Button startQuiz;
    private Button allQues;
    private Button addQues;

    ////////////////////////////////////////////
    private MainActivityViewModel MAVM;
    ////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //////////////////////////// Finding IDs ////////////////////////////
        AppTitle = findViewById(R.id.AppTitle);
        startQuiz = findViewById(R.id.startQuiz);
        allQues = findViewById(R.id.allQues);
        addQues = findViewById(R.id.addQues);
        aboutBTN = findViewById(R.id.aboutBTN);
        //////////////////////////// Finding IDs ////////////////////////////

        /////// Bind ViewModel ///////
        MAVM = new ViewModelProvider(this).get(MainActivityViewModel.class);
        /////// Bind ViewModel ///////

        ////////////////////////// Set New Title //////////////////////////
        MAVM.getTitle().setValue("Quizzy using LiveData now");
        ////////////////////////// Set New Title //////////////////////////

        //////////////////////////////////////////////////////////////////////////////
        // Create the observer which updates the UI.
        final Observer<String> TitleObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String TT) {
                // Update the UI, in this case, a TextView.
                AppTitle.setText(TT);
            }
        };
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        MAVM.getTitle().observe(this, TitleObserver);
        //////////////////////////////////////////////////////////////////////////////

        /////////////////// startQuiz Section ///////////////////
        startQuiz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // We can open the about page here but to make the code better
                        // we can call function
                        openQuestionActivity(); //This function will open our startQuiz page
                    }
                }
        );
        /////////////////// startQuiz Section ///////////////////

        /////////////////// allQues Section ///////////////////
        allQues.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // We can open the about page here but to make the code better
                        // we can call function
                        openallQuesActivity(); //This function will open our allques page
                    }
                }
        );
        /////////////////// allQues Section ///////////////////

        /////////////////// addQues Section ///////////////////
        addQues.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // We can open the about page here but to make the code better
                        // we can call function
                        openaddQuesActivity(); //This function will open our add ques page
                    }
                }
        );
        /////////////////// addQues Section ///////////////////

        /////////////////// about Section ///////////////////
        aboutBTN.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // We can open the about page here but to make the code better
                        // we can call function
                        openaboutActivity(); //This function will open our about page
                    }
                }
        );
        /////////////////// addQues Section ///////////////////

    }

    /////////////////// startQuiz Section ///////////////////
    public void openQuestionActivity() {
        Intent StartQuestionActivity = new Intent(this, QuestionActivity.class);
        startActivity(StartQuestionActivity);
    }
    /////////////////// startQuiz Section ///////////////////

    /////////////////// allQues Section ///////////////////
    public void openallQuesActivity() {
        Intent StartallQuesActivity = new Intent(this, AllQuestions.class);
        startActivity(StartallQuesActivity);
    }
    /////////////////// allQues Section ///////////////////

    /////////////////// addQues Section ///////////////////
    public void openaddQuesActivity() {
        Intent StartaddQuesActivity = new Intent(this, AddQuestion.class);
        startActivity(StartaddQuesActivity);
    }
    /////////////////// addQues Section ///////////////////

    /////////////////// about Section ///////////////////
    public void openaboutActivity() {
        Intent StartaboutActivity = new Intent(this, About.class);
        startActivity(StartaboutActivity);
    }
    /////////////////// about Section ///////////////////

}