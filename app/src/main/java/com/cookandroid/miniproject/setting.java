package com.cookandroid.miniproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

public class setting extends Fragment {
    View view;
    EditText username, useralias, userage, userpromise;

    RadioGroup rGroup;
    Button editBtn;
    String name, alias, age, sex, promise;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.setting, container, false)
        username = (EditText) view.findViewById(R.id.userName);
        useralias = (EditText) view.findViewById(R.id.userAlias);
        userage = (EditText) view.findViewById(R.id.userAge);
        userpromise = (EditText) view.findViewById(R.id.myPromise);
        rGroup = (RadioGroup) view.findViewById(R.id.rGroup1);

        name = username.getText().toString();
        alias = useralias.getText().toString();
        age = userage.getText().toString();
        promise = userpromise.getText().toString();

        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.male) sex = "남자";
                else sex = "여자";
            }
        });

        


        // Inflate the layout for this fragment
        return view;
    }
}