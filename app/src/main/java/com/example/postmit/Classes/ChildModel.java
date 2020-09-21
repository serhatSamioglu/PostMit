package com.example.postmit.Classes;

import com.example.postmit.Fragments.ProductsFragment;

public class ChildModel implements ProductsFragment.ListItem {
    String child;

    public void setChild(String child) {
        this.child = child;
    }

    @Override
    public boolean isHeader() {
        return false;
    }

    @Override
    public String getName() {
        return child;
    }
}
