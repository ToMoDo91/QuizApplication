package com.ntk.quizzy.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ntk.quizzy.Database.AppDatabase;
import com.ntk.quizzy.Database.QuestionModel;
import com.ntk.quizzy.Database.QuesDAO;

import java.util.List;

public class Repository {

    private AppDatabase database;
    private QuesDAO QuesDAO;
    private LiveData<List<QuestionModel>> allQues;


    public Repository(Application app) {
        database = AppDatabase.getInstance(app);
        QuesDAO = database.QuesDAO();
        allQues = QuesDAO.getAllQuess();

    }

    public void insert(QuestionModel question) {
        new InsertQuesAsyncTask(QuesDAO).execute(question);
    }

    public void update(QuestionModel question) {
        new UpdateQuesAsyncTask(QuesDAO).execute(question);
    }

    public void delete(QuestionModel question) {
        new DeleteQuesAsyncTask(QuesDAO).execute(question);
    }

    public void deleteAllQuestions() {
        new DeleteAllQuessAsyncTask(QuesDAO).execute();
    }

    public LiveData<List<QuestionModel>> getAllQuestions() {
        if (allQues == null) {
            allQues = new MutableLiveData<List<QuestionModel>>();
        }
        return allQues;
    }

    private static class InsertQuesAsyncTask extends AsyncTask<QuestionModel, Void, Void> {
        private QuesDAO QuesDAO;

        private InsertQuesAsyncTask(QuesDAO QuesDAO) {
            this.QuesDAO = QuesDAO;
        }

        @Override
        protected Void doInBackground(QuestionModel... questionModels) {
            QuesDAO.insert(questionModels[0]);
            return null;
        }
    }

    private static class UpdateQuesAsyncTask extends AsyncTask<QuestionModel, Void, Void> {
        private QuesDAO QuesDAO;

        private UpdateQuesAsyncTask(QuesDAO QuesDAO) {
            this.QuesDAO = QuesDAO;
        }

        @Override
        protected Void doInBackground(QuestionModel... questionModels) {
            QuesDAO.update(questionModels[0]);
            return null;
        }
    }

    private static class DeleteQuesAsyncTask extends AsyncTask<QuestionModel, Void, Void> {
        private QuesDAO QuesDAO;

        private DeleteQuesAsyncTask(QuesDAO QuesDAO) {
            this.QuesDAO = QuesDAO;
        }

        @Override
        protected Void doInBackground(QuestionModel... questionModels) {
            QuesDAO.delete(questionModels[0]);
            return null;
        }
    }

    private static class DeleteAllQuessAsyncTask extends AsyncTask<Void, Void, Void> {
        private QuesDAO QuesDAO;

        private DeleteAllQuessAsyncTask(QuesDAO QuesDAO) {
            this.QuesDAO = QuesDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            QuesDAO.deleteAllQuess();
            return null;
        }
    }

}