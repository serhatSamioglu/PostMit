package com.example.postmit.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.postmit.QRCodeActivity;
import com.example.postmit.R;
import com.example.postmit.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    ImageView coffee1ImageView;
    ImageView coffee2ImageView;
    ImageView coffee3ImageView;
    ImageView coffee4ImageView;
    ImageView coffee5ImageView;
    ImageView coffee6ImageView;
    ImageView[] coffees;

    ImageView todayImageView;
    ImageView giftCoffeeImageView;
    TextView todayEditText;
    TextView giftCoffeeEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        mAuth=FirebaseAuth.getInstance();
        coffees = new ImageView[6];

        handleScreen(view);
        handleCoffee();
        handleToday();
        // kahvelerin filan çekilmesi lazım
        return view;
    }

    public void handleToday(){
        myRef.child("Today").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String imageUrl = dataSnapshot.child("imageUrl").getValue(String.class);
                Picasso.get().load(imageUrl).into(todayImageView);

                todayEditText.setText(dataSnapshot.child("description").getValue(String.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void handleCoffee(){
        myRef.child("Users/"+mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User tempUser = dataSnapshot.getValue(User.class);

                for(int i=0;i<tempUser.getCoffeeCount();i++){
                    coffees[i].setImageResource(R.drawable.renkli);
                }

                for(int j=tempUser.getCoffeeCount();j<6;j++){
                    coffees[j].setImageResource(R.drawable.renksiz);
                }

                giftCoffeeEditText.setText("İkram içeceğiniz "+tempUser.getGiftCoffeeCount()+" adet");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void openQRCode(){
        Intent intent = new Intent(getActivity(), QRCodeActivity.class);
        startActivity(intent);
    }

    public void handleScreen(View view){
        coffee1ImageView = view.findViewById(R.id.coffee1);
        coffee2ImageView = view.findViewById(R.id.coffee2);
        coffee3ImageView = view.findViewById(R.id.coffee3);
        coffee4ImageView = view.findViewById(R.id.coffee4);
        coffee5ImageView = view.findViewById(R.id.coffee5);
        coffee6ImageView = view.findViewById(R.id.coffee6);
        todayImageView = view.findViewById(R.id.todayImageView);
        giftCoffeeImageView = view.findViewById(R.id.giftCoffee);
        todayEditText = view.findViewById(R.id.todayTextView);
        giftCoffeeEditText = view.findViewById(R.id.giftTextView);

        coffees[0]=coffee1ImageView;
        coffees[1]=coffee2ImageView;
        coffees[2]=coffee3ImageView;
        coffees[3]=coffee4ImageView;
        coffees[4]=coffee5ImageView;
        coffees[5]=coffee6ImageView;

        coffee1ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQRCode();
            }
        });
        coffee2ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQRCode();
            }
        });
        coffee3ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQRCode();
            }
        });
        coffee4ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQRCode();
            }
        });
        coffee5ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQRCode();
            }
        });
        coffee6ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQRCode();
            }
        });
        giftCoffeeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQRCode();
            }
        });
    }
    
}
