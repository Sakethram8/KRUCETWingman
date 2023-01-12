package com.example.krucetwingman;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Student_portal extends AppCompatActivity {
    TextView timetable,syllabus,faculty,holiday,studycorner,student_corner,open,logout;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_studentportal);
       CardView timetable = findViewById(R.id.tt);
         CardView syllabus = findViewById(R.id.sb);
          //CardView faculty  = findViewById(R.id.fac);
        CardView holiday = findViewById(R.id.ho);
        //CardView studycorner = findViewById(R.id.ln);
        CardView student_corner = findViewById(R.id.btncontact);
        //CardView open = findViewById(R.id.btnTodo);
        CardView logout = findViewById(R.id.logout);
        fauth = FirebaseAuth.getInstance();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.home:
                        Intent home = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(home);
                        break;
                    case R.id.studentportal:
                        Intent portal = new Intent(getApplicationContext(),Student_portal.class);
                        startActivity(portal);
                        break;
                    case R.id.studentprofile:
                        Intent profile = new Intent(getApplicationContext(),Main_student_profile.class);
                        startActivity(profile);
                        break;

                }
                drawer.closeDrawer(GravityCompat.START);

                return false;
            }
        });



        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent timetableintent = new Intent(getApplicationContext(),TimeTable.class);
                startActivity(timetableintent);
            }
        });

        syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent syllabusintent = new Intent(getApplicationContext(),Syllabus_1.class);
                startActivity(syllabusintent);
            }
        });


        holiday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent holidayintent = new Intent(getApplicationContext(),holiday.class);
                startActivity(holidayintent);
            }
        });





        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fauth.signOut();
                Intent logoutintent = new Intent(Student_portal.this,SignupActivity.class);
                startActivity(logoutintent);
                finish();

            }
        });
    }
}