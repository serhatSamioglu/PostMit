package com.example.postmit.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.postmit.EmailActivity;
import com.example.postmit.MainActivity;
import com.example.postmit.PasswordActivity;
import com.example.postmit.R;
import com.example.postmit.SplashActivity;
import com.example.postmit.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private TextView userName;
    private TextView userEmail;
    private Button changeEmail;
    private Button changePassword;
    private Button contract;
    private Button signout;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        userName = view.findViewById(R.id.userName);
        userEmail= view.findViewById(R.id.userEmail);
        changeEmail= view.findViewById(R.id.changeEmail);
        changePassword = view.findViewById(R.id.changePassword);
        contract = view.findViewById(R.id.contract);
        signout = view.findViewById(R.id.signOut);

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        mAuth=FirebaseAuth.getInstance();


        changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EmailActivity.class);
                startActivity(intent);
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PasswordActivity.class);
                startActivity(intent);
            }
        });

        contract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), SplashActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        handleUser();
    }

    public void handleUser(){
        myRef.child("Users/"+mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User tempUser = dataSnapshot.getValue(User.class);
                userName.setText(tempUser.getUserName());
                userEmail.setText(tempUser.getUserEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
