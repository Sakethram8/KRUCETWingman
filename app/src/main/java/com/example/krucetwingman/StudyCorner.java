package com.example.krucetwingman;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class StudyCorner extends AppCompatActivity {
    Spinner sem;
    Button fetch;
    FirebaseFirestore fstore;
    FirebaseAuth fauth;
    FirebaseRemoteConfig remote ;
    StorageReference store ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studycorner);
        sem = findViewById(R.id.spinner4);
        fetch = findViewById(R.id.button4);
        store = FirebaseStorage.getInstance().getReference();
        remote = FirebaseRemoteConfig.getInstance();
        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        //sub3 = findViewById(R.id.studycorner_sub3);
        //sub4 = findViewById(R.id.studycorner_sub4);
      //  sub5 = findViewById(R.id.studycorner_sub5);
        //sub6 = findViewById(R.id.studycorner_sub6);

     //   sub1.setText(remote.getString("studycorner_sub1"));
       // sub2.setText(remote.getString("studycorner_sub2"));
        //sub3.setText(remote.getString("studycorner_sub3"));
        //sub4.setText(remote.getString("studycorner_sub4"));
        //sub5.setText(remote.getString("studycorner_sub5"));
        //sub6.setText(remote.getString("studycorner_sub6"));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.sem_values,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sem.setAdapter(adapter);


        String semester = sem.getSelectedItem().toString();


        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(semester){
                    case "First Semester":

                        break;
                    case "Second Semester":
                        break;
                    case "Third Sermester":
                        break;
                    case "Fourth Semester":
                        break;
                    case "Fifth Semester":
                        break;
                    case "Sixth Semester":
                        break;
                    case "Seventh Semester":
                        break;
                    case "Eighth Semester":
                        break;

                }
            }
        });






    }
}