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

public class EmailActivity extends AppCompatActivity {

    private EditText newEmail;
    private EditText password;
    private Button confirmEmailButton;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        newEmail = findViewById(R.id.newEmail);
        password = findViewById(R.id.password);
        confirmEmailButton = findViewById(R.id.confirmEmailButton);

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        mAuth=FirebaseAuth.getInstance();

        confirmEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), password.getText().toString()); // Current Login Credentials \\
                // Prompt the user to re-provide their sign-in credentials
                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    //Now change your email address \\
                                    //----------------Code for Changing Email Address----------\\
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                user.updateEmail(newEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(EmailActivity.this,"İşlem başarılı" , Toast.LENGTH_SHORT).show();
                                                    myRef.child("Users/"+mAuth.getCurrentUser().getUid()+"/userEmail").setValue(newEmail.getText().toString());
                                                    //finish();
                                                }else{
                                                    Toast.makeText(EmailActivity.this,"İşlem başarısız" , Toast.LENGTH_SHORT).show();
                                                    //finish();
                                                }
                                            }
                                        });
                                //----------------------------------------------------------\\
                                }else{
                                    //finish();
                                    Toast.makeText(EmailActivity.this,"İşlem başarısız" , Toast.LENGTH_SHORT).show();
                                }
                            }
                });
            }
        });
    }
}
