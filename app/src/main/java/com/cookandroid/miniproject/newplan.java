package com.cookandroid.miniproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.json.JSONObject;

public class newplan extends Fragment {
    static int file_count = 0;
    StringBuilder sb = new StringBuilder();
    EditText edt1, edt2, edt3, edt4;
    Button btn1;
    String bookName, author, motivation, pagesStr;
    int pages;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.newplan, container, false);
        edt1 = view.findViewById(R.id.bookName);
        edt2 = view.findViewById(R.id.pages);
        edt3 = view.findViewById(R.id.author);
        edt4 = view.findViewById(R.id.motivation);
        btn1 = view.findViewById(R.id.createPlan);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookName = edt1.getText().toString().trim();
                pagesStr = edt2.getText().toString().trim();
                author = edt3.getText().toString().trim();
                motivation = edt4.getText().toString().trim();

                if (bookName.isEmpty() || pagesStr.isEmpty() || author.isEmpty() || motivation.isEmpty()) {
                    Toast.makeText(getActivity(), "모든 필드를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    pages = Integer.parseInt(pagesStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "페이지 수는 숫자로 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                sb.append("책 제목: ").append(bookName).append("\n")
                        .append("페이지 수: ").append(pages).append("\n")
                        .append("저자: ").append(author).append("\n")
                        .append("동기: ").append(motivation);

                try{
                    ((MainActivity) getActivity()).createPlanFile(file_count, sb.toString());
                    file_count++;
                    Toast.makeText(getActivity(), "계획 생성 완료!", Toast.LENGTH_SHORT).show();
                } catch (NullPointerException e){
                    Toast.makeText(getActivity(), "NULLPOINTER", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }
}