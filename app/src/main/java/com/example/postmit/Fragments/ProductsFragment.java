package com.example.postmit.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.postmit.Classes.ChildModel;
import com.example.postmit.Classes.CustomeAdapter;
import com.example.postmit.Classes.HeaderModel;
import com.example.postmit.Classes.Product;
import com.example.postmit.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductsFragment extends Fragment {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;

    private ListView lv;
    private CustomeAdapter customeAdapter;

    private String[] productsTypes = new String[]{"İçecekler", "Yiyecekler"};

    private ArrayList<ListItem> listItemArrayList;
    private ArrayList<String> productsNames = new ArrayList<>();;
    int countDrinks=0;
    //private String[] productsNames = new String[];


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        handleProduct(view);
        //handleListView(view);

        return view;
    }

    /*public void handleListView(View view){
        lv = (ListView)view.findViewById(R.id.listView);

        listItemArrayList = new ArrayList<>();
        populateList();

        customeAdapter = new CustomeAdapter(getContext(),listItemArrayList);
        lv.setAdapter(customeAdapter);
    }*/

    public interface ListItem {
        boolean isHeader();
        String getName();
    }

    private void populateList(){

        int headerdone = 0, childdone = 0;

        for(int i = 0; i < productsTypes.length+productsNames.size(); i++){

            if(i == 0 || i == countDrinks+1){
                HeaderModel vehicleModel = new HeaderModel();
                vehicleModel.setheader(productsTypes[headerdone]);
                listItemArrayList.add(vehicleModel);
                headerdone = headerdone + 1;
            }else {
                ChildModel childModel = new ChildModel();
                childModel.setChild(productsNames.get(childdone));
                listItemArrayList.add(childModel);
                childdone = childdone + 1;
            }
        }
    }

    private void handleProduct(View view){

        lv = (ListView)view.findViewById(R.id.listView);

        listItemArrayList = new ArrayList<>();

        myRef.child("Products/Drinks").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot drinks) {
                countDrinks=(int)drinks.getChildrenCount();
                for(DataSnapshot drink: drinks.getChildren()){
                    productsNames.add(drink.getKey());
                }

                myRef.child("Products/Food").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot foods) {
                        for(DataSnapshot food: foods.getChildren()){
                            productsNames.add(food.getKey());
                        }

                        populateList();

                        customeAdapter = new CustomeAdapter(getContext(),listItemArrayList);
                        lv.setAdapter(customeAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
