package com.cookandroid.miniproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileInputStream;

public class home extends Fragment {
    TextView tv1, tv2;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.home, container, false);
        tv1 = view.findViewById(R.id.welcome);
        tv2 = view.findViewById(R.id.nowReading);
        try{
            String str = ((MainActivity) getActivity()).getUserInfo();
            String name = str.split("\n")[0];
            name = name.substring(4);
            tv1.setText("어서오세요,\n"+name+"님!");
        }catch(NullPointerException e ){
            Toast.makeText(getActivity(), "NULLPOINTER", Toast.LENGTH_SHORT).show();
            tv1.setText("어서오세요!");
        }

        try{
            String str = ((MainActivity) getActivity()).getPlan();
            tv2.setText(str);
        } catch (NullPointerException e){
            tv2.setText(e.toString());
        }

        return view;
    }
}