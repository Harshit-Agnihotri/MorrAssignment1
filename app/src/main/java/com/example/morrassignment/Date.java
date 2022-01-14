package com.example.morrassignment;

import android.text.Editable;
import android.text.TextWatcher;

import com.example.morrassignment.databinding.ActivityMainBinding;
import com.example.morrassignment.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputEditText;

public class Date {
    private TextInputEditText textView;
    public Date(ActivityMainBinding activityMainBinding){
        textView = activityMainBinding.dateInputLayout;
    }

    public void dateFormat(){
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void afterTextChanged(Editable text) {
                if (text.length() == 2) {
                    text.append('/');
                }
            }
        });
    }
}
