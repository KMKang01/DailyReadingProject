package com.cookandroid.miniproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class setting extends Fragment {
    public static setting newInstance(){
        return new setting();
    }
    View view;
    EditText username, useralias, userage, userpromise;
    RadioGroup rGroup;
    Button editBtn;
    String name, nickname, age, sex, promise;
    StringBuilder sb = new StringBuilder();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.setting, container, false);
        username = (EditText) view.findViewById(R.id.userName);
        useralias = (EditText) view.findViewById(R.id.userAlias);
        userage = (EditText) view.findViewById(R.id.userAge);
        userpromise = (EditText) view.findViewById(R.id.myPromise);
        rGroup = (RadioGroup) view.findViewById(R.id.rGroup1);
        editBtn = (Button) view.findViewById(R.id.editBtn);

        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.male) sex = "남자";
                else if(checkedId == R.id.female) sex = "여자";
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = username.getText().toString().trim(); //이름
                nickname = useralias.getText().toString().trim(); //별명
                age = userage.getText().toString().trim(); //나이
                promise = userpromise.getText().toString().trim(); //다짐


                if(name.isEmpty()||nickname.isEmpty()||age.isEmpty()||promise.isEmpty()){
                    Toast.makeText(getActivity(), "모든 필드를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                sb.append("이름: ").append(name).append("\n")
                        .append("별명: ").append(nickname).append("\n")
                        .append("나이: ").append(age).append("\n")
                        .append("성별: ").append(sex).append("\n")
                        .append("다짐: ").append(promise).append("\n");

                try{
                    ((MainActivity) getActivity()).setUserInfo(sb.toString());
                    Toast.makeText(getActivity(), "수정 완료!", Toast.LENGTH_SHORT).show();
                } catch (NullPointerException e ){
                    Toast.makeText(getActivity(), "NULLPOINTER", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}