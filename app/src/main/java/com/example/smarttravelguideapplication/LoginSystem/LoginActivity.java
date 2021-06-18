package com.example.smarttravelguideapplication.LoginSystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.smarttravelguideapplication.MainActivity;
import com.example.smarttravelguideapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    ImageView nxt_btn_id;
    EditText editTextCountryCode, editTextPhone;
    AppCompatButton buttonContinue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextCountryCode = findViewById(R.id.editTextCountryCode);
        editTextPhone = findViewById(R.id.editTextPhone);
        nxt_btn_id = findViewById(R.id.nxt_btn_id);



        nxt_btn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String code = editTextCountryCode.getText().toString().trim();
                String number = editTextPhone.getText().toString().trim();

                if (number.isEmpty() || number.length() < 10) {
                    editTextPhone.setError("Valid number is required");
                    editTextPhone.requestFocus();
                    return;
                }

                String phoneNumber = code + number;

                Intent intent = new Intent(LoginActivity.this, VerificationActivity.class);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


}