package com.example.postmit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;

    EditText textBox1;
    EditText textBox2;
    EditText textBox3;
    Button login;
    TextView forgotPassword;

    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();

        textBox1 = findViewById(R.id.textBox1);
        textBox2 = findViewById(R.id.textBox2);
        textBox3 = findViewById(R.id.textBox3);
        login = findViewById(R.id.login);
        forgotPassword = findViewById(R.id.forgotPassword);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Giriş Yap"));
        tabLayout.addTab(tabLayout.newTab().setText("Kayıt Ol"));
        tabLayout.getTabAt(1).select();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        textBox2.setHint("Şifre");
                        textBox3.setVisibility(View.GONE);
                        login.setText("Giriş Yap");
                        break;

                    case 1:
                        textBox2.setHint("Kullanıcı adı");
                        textBox3.setVisibility(View.VISIBLE);
                        login.setText("Kayıt Ol");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

    }

    public void loginTabbed(View view){

        if(tabLayout.getSelectedTabPosition()==0){
            mAuth.signInWithEmailAndPassword(textBox1.getText().toString(),textBox2.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }).addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            mAuth.createUserWithEmailAndPassword(textBox1.getText().toString(),textBox3.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                /*User newUser = new User(mAuth.getCurrentUser().getUid(),emailTextRegister.getText().toString()
                                        ,passwordTextRegister.getText().toString(),
                                        userNameRegister.getText().toString());

                                myRef.child("Users").child(mAuth.getCurrentUser().getUid()).setValue(newUser);*/

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }).addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
