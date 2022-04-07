package com.ntk.quizzy.Views.Activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ntk.quizzy.R;
import com.ntk.quizzy.ViewModels.AddQuesViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AddQuestion extends AppCompatActivity {


    private EditText addQTitle, addQop1, addQop2, addQop3, addQop4, addRight;
    private Button save, addQimage;
    private ImageView PreviewImage;
    private AddQuesViewModel AQVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        ///Binding
        addQTitle = findViewById(R.id.addQTitle);
        addQop1 = findViewById(R.id.addQop1);
        addQop2 = findViewById(R.id.addQop2);
        addQop3 = findViewById(R.id.addQop3);
        addQop4 = findViewById(R.id.addQop4);
        addRight = findViewById(R.id.addRight);
        save = findViewById(R.id.addQsave);
        addQimage = findViewById(R.id.addQImage);

        PreviewImage = findViewById(R.id.PreviewImage);
        ///Binding

        /////// Bind ViewModel ///////
        AQVM = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(AddQuesViewModel.class);
        /////// Bind ViewModel ///////

        PreviewImage.setImageURI(AQVM.selectedImage);
        /////////////////// startQuiz Section ///////////////////
        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            AQVM.ByteOfImage = AQVM.getBytes(AQVM.selectedImage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        boolean x = AQVM.AddQuesToRepo(addQTitle.getText().toString(), addQop1.getText().toString(), addQop2.getText().toString(), addQop3.getText().toString(), addQop4.getText().toString(), addRight.getText().toString(), AQVM.ByteOfImage);
                        if (x) {
                            Toast.makeText(AddQuestion.this, "Saved", Toast.LENGTH_SHORT).show();
                            close();

                        } else {
                            Toast.makeText(AddQuestion.this, "Please add logical question", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
        );
        /////////////////// startQuiz Section ///////////////////

        addQimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                QStartForResult.launch(i);
            }
        });

    }

    ActivityResultLauncher<Intent> QStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        AQVM.selectedImage = intent.getData();
                        PreviewImage.setImageURI(AQVM.selectedImage);
                    }
                }
            });

    public void close() {
        this.finish();
    }
}