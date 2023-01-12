package com.example.krucetwingman;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class TimeTable extends AppCompatActivity {
    PDFView pdftime;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference();
        StorageReference ref;
        ref = storageRef.child("DailyTimeTable.pdf");
        try {
            File pdffile = File.createTempFile("Timetable","pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdftime = findViewById(R.id.timetable_pdfView);
        pdftime.fromUri(Uri.parse("http://www.africau.edu/images/default/sample.pdf"));


    }
}