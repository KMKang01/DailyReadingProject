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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.login, container, false);
        loginBtn = (Button) view.findViewById(R.id.loginBtn);
        userID = (EditText) view.findViewById(R.id.userID);
        userPW = (EditText) view.findViewById(R.id.userPW);

        id = userID.getText().toString();
        pw = userPW.getText().toString();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id.equals("user")&&pw.equals("1234")){
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    setting settingFragment = new setting();
                    transaction.replace(R.id.setting, settingFragment);
                    transaction.commit();
                } else {
                    Toast.makeText(getActivity(), "ID 혹은 PW가 틀립니다!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}