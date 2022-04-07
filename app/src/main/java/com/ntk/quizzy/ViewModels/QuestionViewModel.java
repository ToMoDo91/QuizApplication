package com.ntk.quizzy.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.ntk.quizzy.Database.QuestionModel;
import com.ntk.quizzy.Repositories.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionViewModel extends AddQuesViewModel {

    private Repository repo;
    private LiveData<List<QuestionModel>> allQues;
    private MutableLiveData<QuestionModel> QuestionToView;
    private MutableLiveData<Boolean> weFinished;
    private MutableLiveData<Boolean> noQues;
    public List<QuestionModel> CurrentListOfQues;

    ////////////////////////////////////// We need it to pick random number from our Question list
    public Random random = new Random();
    //////////////////////////////////////

    //////////////////////////////////////
    //We need this list to store our asked question
    //We do not want to ask the question two time
    public ArrayList<Integer> AskedQuestionIds = new ArrayList<Integer>();
    //////////////////////////////////////

    //////////////////////////////////////
    public QuestionModel QuestionTOAsk;
    //////////////////////////////////////

    ////////////////////////////////////// After all we will send this to the result view
    public int NumberOfRightAnswer = 0;
    //////////////////////////////////////

    //////////////////////////////////////
    public int NumberOfAllQues = 0;
    //////////////////////////////////////

    public QuestionViewModel(@NonNull Application application) {
        super(application);
        repo = new Repository(application);
        allQues = repo.getAllQuestions();
        AskQuestion();
    }

    public LiveData<List<QuestionModel>> GetAllQues() {
        if (allQues == null) {
            allQues = new LiveData<List<QuestionModel>>() {
                @Override
                public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super List<QuestionModel>> observer) {
                    super.observe(owner, observer);
                }
            };
        }
        return allQues;
    }

    public void setCurrent(List<QuestionModel> CurrentListOfQues) {
        if (this.CurrentListOfQues == null) {
            this.CurrentListOfQues = CurrentListOfQues;
            if (CurrentListOfQues.size() > 0) {
                AskQuestion();
            } else {
                noQues.setValue(true);
            }
        }
    }

    public LiveData<QuestionModel> GetQuestionToView() {
        if (QuestionToView == null) {
            QuestionToView = new MutableLiveData<>();
        }
        return QuestionToView;
    }

    public LiveData<Boolean> GetFinished() {
        if (weFinished == null) {
            weFinished = new MutableLiveData<>();
        }
        return weFinished;
    }

    public LiveData<Boolean> GetnoQues() {
        if (noQues == null) {
            noQues = new MutableLiveData<>();
        }
        return noQues;
    }


    public void AskQuestion() {
        if (CurrentListOfQues != null) {
            NumberOfAllQues = CurrentListOfQues.size();
            int QuesNumber = random.nextInt(CurrentListOfQues.size());
            QuestionTOAsk = CurrentListOfQues.get(QuesNumber);
            if (AskedQuestionIds.contains(QuestionTOAsk.getId()) == false) {
                QuestionToView.setValue(QuestionTOAsk);
                AskedQuestionIds.add(QuestionTOAsk.getId());
            } else if (AskedQuestionIds.size() < CurrentListOfQues.size()) { //if the question being asked before we will repeat picking.
                AskQuestion();
            } else {
                weFinished.setValue(true);
            }
        }
    }

    public void isItRightAnswer(int x) {
        if (QuestionTOAsk != null) {
            if (QuestionTOAsk.getRightAnswer() == x) {
                NumberOfRightAnswer = NumberOfRightAnswer + 1;
            }
        }
    }

}
