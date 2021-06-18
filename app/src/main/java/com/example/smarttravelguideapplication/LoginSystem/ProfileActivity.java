package com.example.smarttravelguideapplication.LoginSystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smarttravelguideapplication.MainActivity;
import com.example.smarttravelguideapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {


    EditText firstName, LastName, UEmail, AddressLine;
    Button btnsave;
    ImageView cancelBtn;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firstName = findViewById(R.id.firstName);
        LastName = findViewById(R.id.lastName);
        UEmail = findViewById(R.id.emailAddress);
        AddressLine = findViewById(R.id.AddressLine);
        btnsave = findViewById(R.id.saveBtn);
        cancelBtn = findViewById(R.id.cancelBtn);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();

        final DocumentReference docref = firebaseFirestore.collection("users").document(userId);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(firstName.getText())) {
                    firstName.setError("Please enter First name");
                    firstName.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(LastName.getText())) {
                    LastName.setError("Please enter Last Name");
                    LastName.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(UEmail.getText())) {
                    UEmail.setError("Please enter valid Email");
                    UEmail.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(AddressLine.getText())) {
                    AddressLine.setError("Please enter Address");
                    AddressLine.requestFocus();
                    return;
                } else {
                    String fname = firstName.getText().toString();
                    String lname = LastName.getText().toString();
                    String userEmail = UEmail.getText().toString();
                    String userlocation = AddressLine.getText().toString();

                    Map<String, Object> user = new HashMap<>();
                    user.put("FirstName", fname);
                    user.put("LastName", lname);
                    user.put("Emailaddress", userEmail);
                    user.put("Location", userlocation);

                    docref.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(ProfileActivity.this, "Error! Data are not inserted.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
                        startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                        finish();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {// Do nothing
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}