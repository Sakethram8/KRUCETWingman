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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    EditText userEmail, userName, userREGNO, userpassword,batch;
    Button register, signin;
    FirebaseAuth fAuth;
    FirebaseFirestore db;
    FirebaseAuth.AuthStateListener fAuthListener;
    Spinner usertype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userEmail = findViewById(R.id.signup_email_field);
        userpassword = findViewById(R.id.signup_password_field);
        userREGNO = findViewById(R.id.signup_reg_field);
        userName = findViewById(R.id.signup_name_field);
        register = findViewById(R.id.signup_reg_button);
        signin = findViewById(R.id.signup_login_button);
        batch = findViewById(R.id.signup_batchnumber);
        usertype = findViewById(R.id.spinner);
        db = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.user_type,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        usertype.setAdapter(adapter);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userEmail.getText().toString();
                String password = userpassword.getText().toString();
                String username = userName.getText().toString();
                String selected = usertype.getSelectedItem().toString();
                switch(selected){
                    case "Admin":
                    case "Teacher":
                        Toast.makeText(SignupActivity.this, "Not authorized. Contact admin", Toast.LENGTH_SHORT).show();
                        break;
                    case "Student":
                        String regno =userREGNO.getText().toString();
                        String batchno = batch.getText().toString();

                        if(TextUtils.isEmpty(username)|TextUtils.isEmpty(regno)|TextUtils.isEmpty(batchno)){
                            Toast.makeText(SignupActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                        }else{
                            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    String uid = fAuth.getUid();
                                    String branch = regno.substring(3,6);
                                    DocumentReference data = db.collection("Krucet").document("krucet").collection("Users").document(uid);
                                    Map<String,Object> user  =new HashMap<>();
                                    user.put("profileflag",false);
                                    data.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(SignupActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                            Intent profileint  = new Intent(getApplicationContext(),profile.class);
                                            profileint.putExtra("userEmail",email);
                                            profileint.putExtra("userpassword",password);
                                            profileint.putExtra("username",username);
                                            profileint.putExtra("usertype",selected);
                                            profileint.putExtra("batch",batchno);
                                            profileint.putExtra("branch",branch);
                                            profileint.putExtra("regno",regno);
                                            startActivity(profileint);
                                            finish();

                                        }
                                    })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(SignupActivity.this, "Error "+e, Toast.LENGTH_SHORT).show();
                                                    Log.e( "Error","onFailure: ",e );
                                                }
                                            });


                                }
                            })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(SignupActivity.this, "Error"+e, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                        break;
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SignupActivity.this, loginActivity.class);
                startActivity(intent1);
            }
        });

        usertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = usertype.getSelectedItem().toString();
                switch(selected){
                    case"Admin":
                        break;
                    case "Teacher":
                        break;
                    case "Student":
                        userREGNO.setVisibility(View.VISIBLE);
                        batch.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





    }
}
  