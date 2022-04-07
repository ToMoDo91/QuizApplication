package com.ntk.quizzy.Views.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.ntk.quizzy.Adapters.QuesAdapter;
import com.ntk.quizzy.Database.QuestionModel;
import com.ntk.quizzy.R;
import com.ntk.quizzy.ViewModels.AllQuestionsViewModel;


import java.util.List;

public class AllQuestions extends AppCompatActivity {

    ////////////////////////////////////////////
    private AllQuestionsViewModel AQVM;
    ////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_questions);


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final QuesAdapter adapter = new QuesAdapter();
        recyclerView.setAdapter(adapter);

        /////// Bind ViewModel ///////
        AQVM = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(AllQuestionsViewModel.class);
        /////// Bind ViewModel ///////

        //////////////////////////////////////////////////////////////////////////////
        // Create the observer which updates the UI.
        final androidx.lifecycle.Observer<List<QuestionModel>> QuestionsObserver = new Observer<List<QuestionModel>>() {
            @Override
            public void onChanged(@Nullable final List<QuestionModel> TT) {
                adapter.setQues(TT);
                AQVM.GetAllQuesSize(TT.size());

            }
        };
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        AQVM.getAllQuestions().observe(this, QuestionsObserver);
        //////////////////////////////////////////////////////////////////////////////

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                boolean isQuesdeleted = AQVM.delete(adapter.GetQuestionAt(viewHolder.getBindingAdapterPosition()), AQVM.allQuesSize);
                if (isQuesdeleted) {
                    Toast.makeText(AllQuestions.this, "Question Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AllQuestions.this, "You should keep at least one question.", Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
            }
        }).attachToRecyclerView(recyclerView);
    }
}