package com.ntk.quizzy.ViewModels;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


import com.ntk.quizzy.Database.QuestionModel;
import com.ntk.quizzy.Repositories.Repository;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddQuesViewModel extends AndroidViewModel {

    private Repository repo;
    public byte[] ByteOfImage;
    public Uri selectedImage = Uri.parse("android.resource://com.ntk.quizzy/drawable/gen");
 
    public AddQuesViewModel(@NonNull Application application) {
        super(application);
        repo = new Repository(application);
    }

    public boolean AddQuesToRepo(String Title, String An1, String An2, String An3, String An4, String RightAn, byte[] SelQuesImage) {

        if (
                Title != null && !Title.trim().isEmpty() &&
                        An1 != null && !An1.trim().isEmpty() &&
                        An2 != null && !An2.trim().isEmpty() &&
                        An3 != null && !An3.trim().isEmpty() &&
                        An4 != null && !An4.trim().isEmpty() &&
                        SelQuesImage != null &&
                        RightAn != null && !RightAn.trim().isEmpty()) {

            if (isInteger(RightAn)) {

                int Right = Integer.parseInt(RightAn);
                if (1 <= Right && Right <= 4) {

                    QuestionModel Nq = new QuestionModel(Title, An1, An2, An3, An4, Right, SelQuesImage);
                    repo.insert(Nq);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        }
        return false;
    }

    //Not needed just extra :-)
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public byte[] getBytes(Uri selectedImage) throws IOException {
        ByteArrayOutputStream iStream = new ByteArrayOutputStream();
        Bitmap b = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), selectedImage);
        //Convert Image to small size image
        Matrix m = new Matrix();
        m.setRectToRect(new RectF(0, 0, b.getWidth(), b.getHeight()), new RectF(0, 0, 500, 500), Matrix.ScaleToFit.CENTER);
        Bitmap z = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m, true);
        z.compress(Bitmap.CompressFormat.PNG, 100, iStream);  //Compress
        //Convert Image to small size image
        return iStream.toByteArray();
    }

}


