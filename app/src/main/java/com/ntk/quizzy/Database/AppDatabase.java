package com.ntk.quizzy.Database;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ntk.quizzy.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Database(entities = {QuestionModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract QuesDAO QuesDAO();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "Ques_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PDBAsyncTask(instance).execute();
        }
    };

    private static class PDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private QuesDAO QuesDAO;

        private PDBAsyncTask(AppDatabase db) {
            QuesDAO = db.QuesDAO();
        }

        //On First Install, Add Three Questions To Our Database
        @Override
        protected Void doInBackground(Void... voids) {
            QuesDAO.insert(new QuestionModel("Ques 1", "false", "false", "True", "false", 3,null));
            QuesDAO.insert(new QuestionModel("Ques 2", "false", "True", "false", "false", 2,null));
            QuesDAO.insert(new QuestionModel("Ques 3", "false", "false", "false", "True", 4,null));
            return null;
        }

    }

}
