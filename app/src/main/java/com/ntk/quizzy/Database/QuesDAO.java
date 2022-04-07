package com.ntk.quizzy.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuesDAO {

    @Insert
    void insert(QuestionModel QuestionModel);

    @Update
    void update(QuestionModel QuestionModel);

    @Delete
    void delete(QuestionModel QuestionModel);

    @Query("DELETE FROM Ques_table")
    void deleteAllQuess();

    @Query("SELECT * FROM Ques_table")
    LiveData<List<QuestionModel>> getAllQuess();

}
