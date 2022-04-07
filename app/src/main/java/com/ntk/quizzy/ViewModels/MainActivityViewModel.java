package com.ntk.quizzy.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<String> Title ;

    public MutableLiveData<String> getTitle() {
        if (Title == null) {
            Title = new MutableLiveData<String>();
        }
        return Title;
    }

}
