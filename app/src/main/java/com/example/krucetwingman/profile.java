package com.example.krucetwingman;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class profile extends AppCompatActivity {

    EditText nickname,phone_no,batch,branchname,hostelerstate,hostel;
    Spinner hostelselect,hostelstatus;
    Button create,trouble;
    FirebaseFirestore fstore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        phone_no = findViewById(R.id.phone_no_profile);
        nickname = findViewById(R.id.nickname_profile);
        //batch = findViewById(R.id.profile_batch_name);
        hostelstatus = findViewById(R.id.spinner2);
        //branchname = findViewById(R.id.editTextTextname3);
        hostelerstate = findViewById(R.id.profile_hostel_select);
        hostel = findViewById(R.id.editTexthostelselect);
        //hostelstatus = findViewById(R.id.profile_hostel_spinner);
        hostelselect = findViewById(R.id.profile_hostel_select_spinner);
        create = findViewById(R.id.create_profile);
        trouble = findViewById(R.id.profile_trouble_button);

        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();


        Intent i = getIntent();
        String password = i.getStringExtra("userpassword");
        String reg_no = i.getStringExtra("userRegno");
        String name = i.getStringExtra("userName");
        String email = i.getStringExtra("userEmail");
        String branch = i.getStringExtra("branch");
        String batch = i.getStringExtra("batch");
        String usertype = i.getStringExtra("usertype");

        //= i.getStringExtra("uid");

        //semester.setText(text);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.hostel_list,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        hostelselect.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.sem_values, R.layout.support_simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        hostelstatus.setAdapter(adapter2);

        //ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.branch_list, R.layout.support_simple_spinner_dropdown_item);
        //dapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        //branch.setAdapter(adapter3);


        //branch.setOnItemSelectedListener(this);






        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String batch_name = batch.getText().toString();
                //String branch_name = branchname.getText().toString();
                String hostelsem = hostelstatus.getSelectedItem().toString();
                String nick_name = nickname.getText().toString().trim();
                String text = hostelselect.getSelectedItem().toString();
                String phone = phone_no.getText().toString();
                if(TextUtils.isEmpty(text)){
                    Toast.makeText(profile.this, "Please select if you're hosteler or not", Toast.LENGTH_SHORT).show();
                }else{
                    switch(text){
                        case "Yes":
                        case "No":
                            if(TextUtils.isEmpty(nick_name)|TextUtils.isEmpty(phone)){
                                nickname.setError("Name required");
                                phone_no.setError("Phone number required");
                            }else{
                                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        Log.e("TAG","success");
                                        Toast.makeText(profile.this, "User created succesfully", Toast.LENGTH_SHORT).show();
                                        String u_id = auth.getCurrentUser().getUid();
                                        DocumentReference documentReference;
                                        documentReference = fstore.collection("Krucet").document("krucet").collection("Users").document(u_id);
                                        Map<String, Object> user = new HashMap<>();
                                        Date time = null;
                                        switch(text){
                                            case "Yes":
                                                hostelstatus.setVisibility(View.INVISIBLE);
                                                user.put("isHostel",text);
                                                user.put("1e","unpaid");
                                                user.put("2e","unpaid");
                                                user.put("3e","unpaid");
                                                user.put("4e","unpaid");
                                                user.put("1edu", "");
                                                user.put("1ephone", "");
                                                user.put("1etime",time);
                                                user.put("2edu", "");
                                                user.put("2ephone", "");
                                                user.put("2etime", time);
                                                user.put("3edu", "");
                                                user.put("3ephone", "");
                                                user.put("3etime", time);
                                                user.put("4edu", "");
                                                user.put("4ephone", "");
                                                user.put("4etime", time);
                                                break;
                                            case"No":
                                                hostelstatus.setVisibility(View.INVISIBLE);
                                                user.put("isHostel",text);
                                                user.put("1e","NA");
                                                user.put("2e","NA");
                                                user.put("3e","NA");
                                                user.put("4e","NA");
                                                break;
                                            case "Not currently":
                                                hostelstatus.setVisibility(View.VISIBLE);
                                                user.put("isHostel",text);
                                                switch(hostelsem){
                                                    case"First Semester":
                                                    case"Second Semester":
                                                        user.put("1e","unpaid");
                                                        user.put("1edu", "");
                                                        user.put("1ephone", "");
                                                        user.put("1etime",time);
                                                        user.put("2e","NA");
                                                        user.put("3e","NA");
                                                        user.put("4e","NA");
                                                        break;
                                                    case "Fourth Semester":
                                                    case "Third Semester":
                                                        user.put("1e","unpaid");
                                                        user.put("1edu", "");
                                                        user.put("1ephone", "");
                                                        user.put("1etime",time);
                                                        user.put("2e","unpaid");
                                                        user.put("2edu", "");
                                                        user.put("2ephone", "");
                                                        user.put("2etime",time);
                                                        user.put("3e","NA");
                                                        user.put("4e","NA");
                                                        break;
                                                    case"Fifth Semester":
                                                    case "Sixth Semester":
                                                        user.put("1e","unpaid");
                                                        user.put("1edu", "");
                                                        user.put("1ephone", "");
                                                        user.put("1etime",time);
                                                        user.put("2e","unpaid");
                                                        user.put("2edu", "");
                                                        user.put("2ephone", "");
                                                        user.put("2etime",time);
                                                        user.put("3e","unpaid");
                                                        user.put("3edu", "");
                                                        user.put("3ephone", "");
                                                        user.put("3etime",time);
                                                        user.put("4e","NA");
                                                        break;
                                                    case"Seventh Semester":
                                                    case "Eighth Semester":
                                                        user.put("isHostel","yes");
                                                        user.put("1e","unpaid");
                                                        user.put("2e","unpaid");
                                                        user.put("3e","unpaid");
                                                        user.put("4e","unpaid");
                                                        user.put("1edu", "");
                                                        user.put("1ephone", "");
                                                        user.put("1etime",time);
                                                        user.put("2edu", "");
                                                        user.put("2ephone", "");
                                                        user.put("2etime", time);
                                                        user.put("3edu", "");
                                                        user.put("3ephone", "");
                                                        user.put("3etime", time);
                                                        user.put("4edu", "");
                                                        user.put("4ephone", "");
                                                        user.put("4etime", time);
                                                        break;
                                                }
                                                break;
                                        }

                                        //user.put("username", name);
                                        user.put("nickname", nick_name);
                                        user.put("userEmail", email);
                                        user.put("1a", "unpaid");
                                        user.put("1b", "unpaid");
                                        user.put("1c", "unpaid");
                                        user.put("1d", "unpaid");
                                        //user.put("1e", "unpaid");
                                        user.put("1adu", "");
                                        user.put("1aphone", "");
                                        user.put("1bdu", "");
                                        user.put("1bphone", "");
                                        user.put("1cdu", "");
                                        user.put("1cphone", "");
                                        user.put("1dphone", "");
                                        user.put("1ddu", "");
                                        user.put("2a", "unpaid");
                                        user.put("2b", "unpaid");
                                        user.put("2c", "unpaid");
                                        user.put("2d", "unpaid");
                                        //user.put("2e", "unpaid");
                                        user.put("2adu", "");
                                        user.put("2aphone", "");
                                        user.put("2bdu", "");
                                        user.put("2bphone", "");
                                        user.put("2cdu", "");
                                        user.put("2cphone", "");
                                        user.put("2dphone", "");
                                        user.put("2ddu", "");

                                        user.put("3a", "unpaid");
                                        user.put("3b", "unpaid");
                                        user.put("3c", "unpaid");
                                        user.put("3d", "unpaid");
                                        //user.put("3e", "unpaid");
                                        user.put("3adu", "");
                                        user.put("3aphone", "");
                                        user.put("3bdu", "");
                                        user.put("3bphone", "");
                                        user.put("3cdu", "");
                                        user.put("3cphone", "");
                                        user.put("3dphone", "");
                                        user.put("3ddu", "");

                                        user.put("4a", "unpaid");
                                        user.put("4b", "unpaid");
                                        user.put("4c", "unpaid");
                                        user.put("4d", "unpaid");
                                        //user.put("4e", "unpaid");
                                        user.put("4adu", "");
                                        user.put("4aphone", "");
                                        user.put("4bdu", "");
                                        user.put("4bphone", "");
                                        user.put("4cdu", "");
                                        user.put("4cphone", "");
                                        user.put("4dphone", "");
                                        user.put("4ddu", "");

                                        user.put("1f", 0);
                                        user.put("2f", 0);
                                        user.put("3f", 0);
                                        user.put("4f", 0);

                                        //user.put("isHostel", text);

                                        //Map<String, Object> mapper = new HashMap<>();
                                        //Date time = null;
                                        user.put("1atime", time);
                                        user.put("1btime", time);
                                        user.put("1ctime", time);
                                        user.put("1dtime", time);


                                        user.put("2atime", time);
                                        user.put("2btime", time);
                                        user.put("2ctime", time);
                                        user.put("2dtime", time);


                                        user.put("3atime", time);
                                        user.put("3btime", time);
                                        user.put("3ctime", time);
                                        user.put("3dtime", time);


                                        user.put("4atime", time);
                                        user.put("4btime", time);
                                        user.put("4ctime", time);
                                        user.put("4dtime", time);

                                        user.put("usertype", usertype);
                                        user.put("branch",branch);
                                        user.put("batch",batch);

                                        //user.put("isAdmin", false);
                                        user.put("reg_no", reg_no);

                                        Map<String,Object> map = new HashMap<>();

                                        documentReference.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(profile.this, "Profile created", Toast.LENGTH_SHORT).show();
                                                    fstore.collection("Krucet").document("krucet").collection("Users").document(u_id).update("profileflag",true).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void unused) {
                                                                    Intent main = new Intent(getApplicationContext(), MainActivity.class);
                                                                    startActivity(main);
                                                                    finish();
                                                                }
                                                            })
                                                            .addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    Toast.makeText(profile.this, "error "+e, Toast.LENGTH_SHORT).show();
                                                                }
                                                            });

                                                } else {
                                                    Toast.makeText(profile.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                            break;
                        case "Not Currently":
                            if(TextUtils.isEmpty(nick_name)|TextUtils.isEmpty(phone)|TextUtils.isEmpty(hostelsem)){
                                nickname.setError("Name required");
                                phone_no.setError("Phone number required");
                                Toast.makeText(profile.this, "Please select an option", Toast.LENGTH_SHORT).show();
                            }else{
                                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        Log.e("TAG","success");
                                        Toast.makeText(profile.this, "User created succesfully", Toast.LENGTH_SHORT).show();
                                        String u_id = auth.getCurrentUser().getUid();
                                        DocumentReference documentReference;
                                        documentReference = fstore.collection("Krucet").document("krucet").collection("Users").document(u_id);
                                        Map<String, Object> user = new HashMap<>();
                                        Date time = null;
                                        switch(text){
                                            case "Yes":
                                                hostelstatus.setVisibility(View.INVISIBLE);
                                                user.put("isHostel",text);
                                                user.put("1e","unpaid");
                                                user.put("2e","unpaid");
                                                user.put("3e","unpaid");
                                                user.put("4e","unpaid");
                                                user.put("1edu", "");
                                                user.put("1ephone", "");
                                                user.put("1etime",time);
                                                user.put("2edu", "");
                                                user.put("2ephone", "");
                                                user.put("2etime", time);
                                                user.put("3edu", "");
                                                user.put("3ephone", "");
                                                user.put("3etime", time);
                                                user.put("4edu", "");
                                                user.put("4ephone", "");
                                                user.put("4etime", time);
                                                break;
                                            case"No":
                                                hostelstatus.setVisibility(View.INVISIBLE);
                                                user.put("isHostel",text);
                                                user.put("1e","NA");
                                                user.put("2e","NA");
                                                user.put("3e","NA");
                                                user.put("4e","NA");
                                                break;
                                            case "Not currently":
                                                hostelstatus.setVisibility(View.VISIBLE);
                                                user.put("isHostel",text);
                                                switch(hostelsem){
                                                    case"First Semester":
                                                    case"Second Semester":
                                                        user.put("1e","unpaid");
                                                        user.put("1edu", "");
                                                        user.put("1ephone", "");
                                                        user.put("1etime",time);
                                                        user.put("2e","NA");
                                                        user.put("3e","NA");
                                                        user.put("4e","NA");
                                                        break;
                                                    case "Fourth Semester":
                                                    case "Third Semester":
                                                        user.put("1e","unpaid");
                                                        user.put("1edu", "");
                                                        user.put("1ephone", "");
                                                        user.put("1etime",time);
                                                        user.put("2e","unpaid");
                                                        user.put("2edu", "");
                                                        user.put("2ephone", "");
                                                        user.put("2etime",time);
                                                        user.put("3e","NA");
                                                        user.put("4e","NA");
                                                        break;
                                                    case"Fifth Semester":
                                                    case "Sixth Semester":
                                                        user.put("1e","unpaid");
                                                        user.put("1edu", "");
                                                        user.put("1ephone", "");
                                                        user.put("1etime",time);
                                                        user.put("2e","unpaid");
                                                        user.put("2edu", "");
                                                        user.put("2ephone", "");
                                                        user.put("2etime",time);
                                                        user.put("3e","unpaid");
                                                        user.put("3edu", "");
                                                        user.put("3ephone", "");
                                                        user.put("3etime",time);
                                                        user.put("4e","NA");
                                                        break;
                                                    case"Seventh Semester":
                                                    case "Eighth Semester":
                                                        user.put("isHostel","yes");
                                                        user.put("1e","unpaid");
                                                        user.put("2e","unpaid");
                                                        user.put("3e","unpaid");
                                                        user.put("4e","unpaid");
                                                        user.put("1edu", "");
                                                        user.put("1ephone", "");
                                                        user.put("1etime",time);
                                                        user.put("2edu", "");
                                                        user.put("2ephone", "");
                                                        user.put("2etime", time);
                                                        user.put("3edu", "");
                                                        user.put("3ephone", "");
                                                        user.put("3etime", time);
                                                        user.put("4edu", "");
                                                        user.put("4ephone", "");
                                                        user.put("4etime", time);
                                                        break;
                                                }
                                                break;
                                        }


                                        //user.put("username", name);
                                        user.put("nickname", nick_name);
                                        user.put("userEmail", email);
                                        user.put("1a", "unpaid");
                                        user.put("1b", "unpaid");
                                        user.put("1c", "unpaid");
                                        user.put("1d", "unpaid");
                                        //user.put("1e", "unpaid");
                                        user.put("1adu", "");
                                        user.put("1aphone", "");
                                        user.put("1bdu", "");
                                        user.put("1bphone", "");
                                        user.put("1cdu", "");
                                        user.put("1cphone", "");
                                        user.put("1dphone", "");
                                        user.put("1ddu", "");
                                        user.put("2a", "unpaid");
                                        user.put("2b", "unpaid");
                                        user.put("2c", "unpaid");
                                        user.put("2d", "unpaid");
                                        //user.put("2e", "unpaid");
                                        user.put("2adu", "");
                                        user.put("2aphone", "");
                                        user.put("2bdu", "");
                                        user.put("2bphone", "");
                                        user.put("2cdu", "");
                                        user.put("2cphone", "");
                                        user.put("2dphone", "");
                                        user.put("2ddu", "");

                                        user.put("3a", "unpaid");
                                        user.put("3b", "unpaid");
                                        user.put("3c", "unpaid");
                                        user.put("3d", "unpaid");
                                        //user.put("3e", "unpaid");
                                        user.put("3adu", "");
                                        user.put("3aphone", "");
                                        user.put("3bdu", "");
                                        user.put("3bphone", "");
                                        user.put("3cdu", "");
                                        user.put("3cphone", "");
                                        user.put("3dphone", "");
                                        user.put("3ddu", "");

                                        user.put("4a", "unpaid");
                                        user.put("4b", "unpaid");
                                        user.put("4c", "unpaid");
                                        user.put("4d", "unpaid");
                                        //user.put("4e", "unpaid");
                                        user.put("4adu", "");
                                        user.put("4aphone", "");
                                        user.put("4bdu", "");
                                        user.put("4bphone", "");
                                        user.put("4cdu", "");
                                        user.put("4cphone", "");
                                        user.put("4dphone", "");
                                        user.put("4ddu", "");

                                        user.put("1f", 0);
                                        user.put("2f", 0);
                                        user.put("3f", 0);
                                        user.put("4f", 0);

                                        //user.put("isHostel", text);

                                        //Map<String, Object> mapper = new HashMap<>();
                                        //Date time = null;
                                        user.put("1atime", time);
                                        user.put("1btime", time);
                                        user.put("1ctime", time);
                                        user.put("1dtime", time);


                                        user.put("2atime", time);
                                        user.put("2btime", time);
                                        user.put("2ctime", time);
                                        user.put("2dtime", time);


                                        user.put("3atime", time);
                                        user.put("3btime", time);
                                        user.put("3ctime", time);
                                        user.put("3dtime", time);


                                        user.put("4atime", time);
                                        user.put("4btime", time);
                                        user.put("4ctime", time);
                                        user.put("4dtime", time);

                                        user.put("usertype", usertype);
                                        user.put("branch",branch);
                                        user.put("batch",batch);

                                        //user.put("isAdmin", false);
                                        user.put("reg_no", reg_no);

                                        Map<String,Object> map = new HashMap<>();

                                        documentReference.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(profile.this, "Profile created", Toast.LENGTH_SHORT).show();
                                                    fstore.collection("Krucet").document("krucet").collection("Users").document(u_id).update("profileflag",true).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void unused) {
                                                                    Intent main = new Intent(getApplicationContext(), MainActivity.class);
                                                                    startActivity(main);
                                                                    finish();
                                                                }
                                                            })
                                                            .addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    Toast.makeText(profile.this, "error "+e, Toast.LENGTH_SHORT).show();
                                                                }
                                                            });

                                                } else {
                                                    Toast.makeText(profile.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                });
                            }

                            break;

                    }
                }






                }
            });

        trouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent (getApplicationContext(),troubleActivity.class);
                startActivity(inten);
            }
        });

        hostelselect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(hostelselect.getSelectedItem().toString()){
                    case "Yes":
                        break;
                    case "No":
                        break;
                    case "Not Currently":
                        hostelstatus.setVisibility(View.VISIBLE);
                        hostel.setVisibility(View.VISIBLE);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        }



    //@Override
    //public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
      //  String selecteditem  = parent.getItemAtPosition(position).toString();
        //switch (parent.getId())
        //{
          //  case R.id.spinner2 :
            //    hostel.setText(selecteditem);
              //  break;
            //case R.id.profile_hostel_select_spinner:
              //  hostelerstate.setText(selecteditem);
                //break;

           // default:
             //   return;

        //}
    //}

    //@Override
    //public void onNothingSelected(AdapterView<?> parent) {
      //  Toast.makeText(this, "Select any option", Toast.LENGTH_SHORT).show();

    //}

}
