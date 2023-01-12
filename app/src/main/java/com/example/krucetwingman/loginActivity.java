package com.example.krucetwingman;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class loginActivity extends AppCompatActivity {
static EditText userEmail;
    EditText userPassword;
Button signup, login, trouble;
FirebaseAuth fAuth;
FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEmail = findViewById(R.id.login_email_field);
        userPassword = findViewById(R.id.login_password_field);
        signup = findViewById(R.id.login_signup_button);
        login = findViewById(R.id.login_login_button);
        fAuth = FirebaseAuth.getInstance();
        trouble = findViewById(R.id.login_trouble_button);
        fstore = FirebaseFirestore.getInstance();

        if (fAuth.getCurrentUser() != null) {
            String uid = fAuth.getUid();
            DocumentReference datastore = fstore.collection("Krucet").document("krucet").collection("Users").document(fAuth.getUid());//.collection("User" + fAuth.getCurrentUser().getUid()).document("Details");
            datastore.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
               @Override
               public void onSuccess(DocumentSnapshot documentSnapshot) {
                   Boolean profile = (Boolean) documentSnapshot.get("profileflag");
                   if(profile == true) {
                       String utype = documentSnapshot.get("usertype").toString();
                       switch(utype){
                           case "Student":
                               Intent newintent = new Intent(loginActivity.this, MainActivity.class);
                               startActivity(newintent);
                               finish();
                               break;
                           case "Admin":
                               Toast.makeText(loginActivity.this, "Not authorized", Toast.LENGTH_SHORT).show();
                               Intent adminint = new Intent(Intent.ACTION_MAIN);
                               PackageManager pmk = getPackageManager();
                               adminint = pmk.getLaunchIntentForPackage("com.krucet.adminbuddy");
                               adminint.addCategory(Intent.CATEGORY_LAUNCHER);
                               startActivity(adminint);
                               break;
                           case "Teacher":
                               Toast.makeText(loginActivity.this, "Not authorized", Toast.LENGTH_SHORT).show();
                               Intent teacherint = new Intent(Intent.ACTION_MAIN);
                               PackageManager pk = getPackageManager();
                               adminint = pk.getLaunchIntentForPackage("com.krucet.adminbuddy");
                               teacherint.addCategory(Intent.CATEGORY_LAUNCHER);
                               startActivity(teacherint);
                               break;

                       }

                   }else{
                       startActivity(new Intent (getApplicationContext(),profile.class));
                       finish();
                   }
               }
           }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   Log.e("LOGIN ","Error",e);
                   Toast.makeText(loginActivity.this, "Error"+e, Toast.LENGTH_SHORT).show();
               }
           });
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userEmail.getText().toString().trim();
                String password = userPassword.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    userEmail.setError("E-mail is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    userPassword.setError("Password is required");
                    return;
                }
                //authentication

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {

                            Toast.makeText(loginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                           DocumentReference dref = fstore.collection("Krucet").document("krucet").collection("Users").document(fAuth.getUid());
                           dref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                               @Override
                               public void onSuccess(DocumentSnapshot documentSnapshot) {
                                   String utype = documentSnapshot.get("usertype").toString();
                                   switch(utype){
                                       case "Student":
                                           Intent newintentnew = new Intent(loginActivity.this, MainActivity.class);
                                           startActivity(newintentnew);
                                           finish();
                                           break;
                                       case "Admin":
                                           Toast.makeText(loginActivity.this, "Not authorized", Toast.LENGTH_SHORT).show();
                                           Intent adminintnew = new Intent(Intent.ACTION_MAIN);
                                           PackageManager pmk = getPackageManager();
                                           adminintnew = pmk.getLaunchIntentForPackage("com.krucet.adminbuddy");
                                           adminintnew.addCategory(Intent.CATEGORY_LAUNCHER);
                                           startActivity(adminintnew);
                                           break;
                                       case "Teacher":
                                           Toast.makeText(loginActivity.this, "Not authorized", Toast.LENGTH_SHORT).show();
                                           Intent teacherintnew = new Intent(Intent.ACTION_MAIN);
                                           PackageManager pk = getPackageManager();
                                           teacherintnew = pk.getLaunchIntentForPackage("com.krucet.adminbuddy");
                                           teacherintnew.addCategory(Intent.CATEGORY_LAUNCHER);
                                           startActivity(teacherintnew);
                                           break;

                                   }
                               }
                           })
                                   .addOnFailureListener(new OnFailureListener() {
                                       @Override
                                       public void onFailure(@NonNull Exception e) {
                                           Toast.makeText(loginActivity.this, "Error" + e, Toast.LENGTH_SHORT).show();

                                       }
                                   });
                        }else {
                            Toast.makeText(loginActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

        trouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(loginActivity.this, troubleActivity.class);
                startActivity(o);
            }
        });
    }
}