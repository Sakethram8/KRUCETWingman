package com.example.krucetwingman;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Syllabus_1 extends AppCompatActivity {
    Button syllabus_7,syllabus_8;
    PDFView syllabus_pdfview;
    FirebaseStorage firestorage = FirebaseStorage.getInstance();

    StorageReference storeref = firestorage.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus1);
       // syllabus_7 = findViewById(R.id.syllabus_7);
        //syllabus_8 = findViewById(R.id.syllabus_8);
        //syllabus_pdfview = findViewById(R.id.syllabus_pdfview);
        //syllabus_7.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
              //  syllabus_pdfview.fromUri(Uri.parse("gs://krucet-wingman.appspot.com/Seventh Semester/CS6612-COMPILER-LABORATORY-2 (1).pdf"));
            //}
        //});

        //syllabus_8.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  syllabus_pdfview.fromUri(Uri.parse("gs://krucet-wingman.appspot.com/Seventh Semester/CS6612-COMPILER-LABORATORY-2 (1).pdf"));

            //}
        //});

    }
}