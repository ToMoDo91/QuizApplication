package com.ntk.quizzy.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ntk.quizzy.Database.QuestionModel;
import com.ntk.quizzy.Repositories.Repository;

import java.util.List;


public class AllQuestionsViewModel extends AndroidViewModel {
    public Repository Qrepository;
    private LiveData<List<QuestionModel>> allQues;
    public int allQuesSize;

    public AllQuestionsViewModel(@NonNull Application application) {
        super(application);
        Qrepository = new Repository(application);
        allQues = Qrepository.getAllQuestions();
    }

    public void insert(QuestionModel Q) {
        Qrepository.insert(Q);
    }

    public void update(QuestionModel Q) {
        Qrepository.update(Q);
    }

    public boolean delete(QuestionModel Q, int allQuesSize) {
        if (allQuesSize > 1) {
            Qrepository.delete(Q);
            return true;
        } else {
            return false;
        }
    }

    public void GetAllQuesSize(int allQuesSize) {
       this.allQuesSize = allQuesSize;
    }

    public void deleteAllQuestions() {
        Qrepository.deleteAllQuestions();
    }

    public LiveData<List<QuestionModel>> getAllQuestions() {
        return allQues;
    }

}