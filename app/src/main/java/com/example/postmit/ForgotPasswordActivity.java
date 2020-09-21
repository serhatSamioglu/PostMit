package com.example.postmit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText forgotEmailEditText;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mAuth=FirebaseAuth.getInstance();
        forgotEmailEditText = findViewById(R.id.forgotEmailEditText);

    }

    public void sendEmailTabbed(View view){
        mAuth.sendPasswordResetEmail(forgotEmailEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ForgotPasswordActivity.this,"E-mail gönderildi",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(ForgotPasswordActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
            }
        });
    }
}
