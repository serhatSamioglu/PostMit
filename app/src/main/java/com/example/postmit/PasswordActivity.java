package com.example.postmit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PasswordActivity extends AppCompatActivity {

    private EditText currentPassword;
    private EditText newPassword;
    private EditText confirmPassword;
    private Button confirmPasswordButton;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        currentPassword = findViewById(R.id.currentPassword);
        newPassword = findViewById(R.id.newPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        confirmPasswordButton = findViewById(R.id.confirmPasswordButton);

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        mAuth=FirebaseAuth.getInstance();

        confirmPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO boş olma kontrollerini yap
                if(newPassword.getText().toString().equals(confirmPassword.getText().toString())){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), currentPassword.getText().toString());
                    user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                user.updatePassword(newPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(PasswordActivity.this,"İşlem başarılı" , Toast.LENGTH_SHORT).show();
                                            //Password updated
                                        } else {
                                            Toast.makeText(PasswordActivity.this,"İşlem başarısız" , Toast.LENGTH_SHORT).show();
                                            //Error password not updated
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(PasswordActivity.this,"İşlem başarısız" , Toast.LENGTH_SHORT).show();
                                //Error auth failed
                            }
                        }
                    });
                }else{
                    Toast.makeText(PasswordActivity.this,"Parola eşleşmiyor" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
