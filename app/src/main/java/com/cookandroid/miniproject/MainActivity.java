package com.cookandroid.miniproject;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView; // 하단 목록

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.menuBar);

        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, new home()).commit(); //FrameLayout에 fragment.xml 띄우기

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new home()).commit();
                } else if (itemId == R.id.history) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new history()).commit();
                } else if (itemId == R.id.newPlan) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new newplan()).commit();
                } else if (itemId == R.id.login) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new login()).commit();
                }
                return true;
            }
        });
    }

    public void moveToSetting() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, setting.newInstance()).commit();
    }

    public void createPlanFile(int n, String str){
       try {
           FileOutputStream outFs = openFileOutput("plan"+n+".txt", Context.MODE_PRIVATE);
           outFs.write(str.getBytes());
           outFs.close();
       } catch (IOException e ) {}
    }

    public void setUserInfo(String str){
        try{
            FileOutputStream outFs = openFileOutput("userInfo.txt", Context.MODE_PRIVATE);
            outFs.write(str.getBytes());
            outFs.close();
        } catch (IOException e){}
    }

    public String getUserInfo(){
        try{
            FileInputStream inFs = openFileInput("userInfo.txt");
            StringBuilder sb = new StringBuilder();

            InputStreamReader utf8 = new InputStreamReader(inFs, "UTF-8");
            int i;
            while ((i=utf8.read())!=-1){
                sb.append((char)i);
            }
            inFs.close();
            return sb.toString();
        } catch (IOException e){
            return e.toString();
        }
    }

    public String getPlan(){
        try{
            File path = new File("data/data/com.cookandroid.miniproject/files");
            ArrayList<String> fileArr = new ArrayList<>();
            String[] list = path.list();
            if (list != null) {
                for (String s : list) {
                    if (s.startsWith("plan")) {
                        fileArr.add(s);
                    }
                }

                if (fileArr.isEmpty()) {
                    return "계획 파일이 없습니다.";
                }

                // 가장 마지막 계획 파일 선택
                String lastFile = fileArr.get(fileArr.size() - 1);

                FileInputStream inFs = openFileInput(lastFile);
                StringBuilder sb = new StringBuilder();

                InputStreamReader utf8 = new InputStreamReader(inFs, "UTF-8");
                int i;
                while((i=utf8.read())!=-1){
                    sb.append((char)i);
                }
                inFs.close();
                return sb.toString();
            } else {
                return "파일 목록을 가져오는 데 실패했습니다.";
            }
        } catch (NullPointerException e){
            return e.toString();
        } catch(FileNotFoundException e){
            return e.toString();
        } catch (IOException e) {
            return e.toString();
        } catch (IndexOutOfBoundsException e){
            return  e.toString();
        }
    }
    public Object[] getPlans(){
        try{
            File path = new File("data/data/com.cookandroid.miniproject/files");
            ArrayList<String> fileArr = new ArrayList<>(); //파일 이름 배열
            String[] list = path.list();
            ArrayList<String> fileContent = new ArrayList<>();
            if (list != null) {
                for (String s : list) {
                    if (s.startsWith("plan")) {
                        fileArr.add(s);
                    }
                }

                if (fileArr.isEmpty()) {
                    return null;
                }

                // 파일을 하나하나씩 읽어와서 fileContent 배열에 저장
                for(int i = 0; i<fileArr.size(); i++){
                    String file = fileArr.get(i);
                    FileInputStream inFs = openFileInput(file);
                    StringBuilder sb = new StringBuilder();
                    InputStreamReader utf8 = new InputStreamReader(inFs, "UTF-8");

                    int j;
                    while((j=utf8.read())!=-1){
                        sb.append((char)j);
                    }
                    inFs.close();
                    fileContent.add(sb.toString());
                }

                return fileContent.toArray(); // Object 형태로 history에 전달
            } else {
                return null;
            }
        } catch (NullPointerException e){
            return null;
        } catch(FileNotFoundException e){
            return null;
        } catch (IOException e) {
            return null;
        } catch (IndexOutOfBoundsException e){
            return null;
        }
    }
}