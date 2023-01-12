package com.example.krucetwingman;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Main_student_profile extends AppCompatActivity {
    TextView studentname, phone;
    TextView reg_no;
    Button Results,attendance,feestatus,resume,logout;
    FirebaseAuth fAuth;
    FirebaseFirestore store;
    DrawerLayout drawer;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_studentprofile);
        Results = findViewById(R.id.studentprofile_results);
        attendance = findViewById(R.id.studentprofile_attendance);
        feestatus = findViewById(R.id.studentprofile_feestatus);
        //resume = findViewById(R.id.studentprofile_resume);
        logout = findViewById(R.id.studentprofile_logout);
        reg_no = findViewById(R.id.studentprofile_regno);
        phone = findViewById(R.id.editTextPhone);
        studentname = findViewById(R.id.studentprofile_name);
        fAuth = FirebaseAuth.getInstance();
        store  = FirebaseFirestore.getInstance();
        navView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);
        String uid = fAuth.getUid();

        DocumentReference ref = store.collection("Krucet").document("krucet").collection("Users").document(uid);
        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String name  = documentSnapshot.get("nickname").toString();
                studentname.setText(name);
                String phonenum = documentSnapshot.get("phone").toString();
                phone.setText(phonenum);
               String regno = documentSnapshot.get("reg_no").toString();
                reg_no.setText(regno);
            }
        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Main_student_profile.this, "Error" + e, Toast.LENGTH_LONG).show();
                            }
                        });


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

        Results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultsintent = new Intent(Main_student_profile.this, results.class);
                startActivity(resultsintent);
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.signOut();
                Intent logoutintent = new Intent(Main_student_profile.this,loginActivity.class);
                startActivity(logoutintent);
                finish();
            }
        });
       attendance.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               new Intent(Intent.ACTION_MAIN);
               Intent attendanceint;
               PackageManager pmk = getPackageManager();
               attendanceint = pmk.getLaunchIntentForPackage("com.krucet.studentfee");
               attendanceint.addCategory(Intent.CATEGORY_LAUNCHER);
               //Intent attendanceint = getPackageManager().getLaunchIntentForPackage("com.krucet.studentfee");
               //if(attendanceint != null) {
                  startActivity(attendanceint);
               //}else{
                 //  Toast.makeText(Main_student_profile.this, "No package found", Toast.LENGTH_SHORT).show();
               //}
               //Intent attendanceint = new Intent(Intent.ACTION_MAIN);
               //attendanceint.setClassName("com.krucet.studentfee","com.krucet.studentfee.MainActivity");
               //startActivity(attendanceint);
               //finish();
           }
       });
       feestatus.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent feeint = new Intent(Intent.ACTION_MAIN);
               PackageManager pmmg = getPackageManager();
               feeint = pmmg.getLaunchIntentForPackage("com.krucet.studentfee");
               feeint.addCategory(Intent.CATEGORY_LAUNCHER);
               startActivity(feeint);
           }
       });
    }
}