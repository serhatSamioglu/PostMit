package com.example.postmit.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.postmit.Fragments.ProductsFragment;
import com.example.postmit.R;

import java.util.ArrayList;

public class CustomeAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ProductsFragment.ListItem> listItemArrayList;

    public CustomeAdapter(Context context,ArrayList<ProductsFragment.ListItem> listItemArrayList) {

        this.context = context;
        this.listItemArrayList = listItemArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return listItemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return listItemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if(listItemArrayList.get(position).isHeader()){
                convertView = inflater.inflate(R.layout.lv_header, null, true);
                holder.tvLabel = (TextView) convertView.findViewById(R.id.tvVehicle);
            }
            else {
                convertView = inflater.inflate(R.layout.lv_child, null, true);
                holder.tvLabel = (TextView) convertView.findViewById(R.id.tvChild);
            }


            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvLabel.setText(listItemArrayList.get(position).getName());

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvLabel;

    }

}
