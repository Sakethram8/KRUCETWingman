package com.example.krucetwingman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class faculty extends AppCompatActivity {
    TextView faculty_name1, faculty_name2,faculty_name3,faculty_name4,faculty_name5,faculty_name6;
    EditText faculty_email1,faculty_email2,faculty_email3,f_email4,f_email5,f_email6,f_phone1,f_phone2,f_phone3,f_phone4,f_phone5,f_phone6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
    }
}