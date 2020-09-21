package com.example.postmit.Classes;

import com.example.postmit.Fragments.ProductsFragment;

public class HeaderModel implements ProductsFragment.ListItem{

    String header;

    public void setheader(String header) {
        this.header = header;
    }

    @Override
    public boolean isHeader() {
        return true;
    }

    @Override
    public String getName() {
        return header;
    }
}
