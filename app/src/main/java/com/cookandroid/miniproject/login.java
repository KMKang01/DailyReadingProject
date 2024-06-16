package com.cookandroid.miniproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

//로그인 성공 시에만 setting 화면으로 넘어감
public class login extends Fragment {
    View view;
    Button loginBtn;
    EditText userID, userPW;
    String id, pw;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.login, container, false);
        loginBtn = (Button) view.findViewById(R.id.loginBtn);
        userID = (EditText) view.findViewById(R.id.userID);
        userPW = (EditText) view.findViewById(R.id.userPW);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = userID.getText().toString();
                pw = userPW.getText().toString();

                if (id.equals("user") && pw.equals("1234")) {
                    try{
                        ((MainActivity) getActivity()).moveToSetting();
                    } catch (NullPointerException e ){
                        Toast.makeText(getActivity(), "NULLPOINTER", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "ID 또는 비밀번호가 틀렸습니다!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}