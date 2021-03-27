package com.example.application;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter  {
    Context context;
    ArrayList<Reseau> listReseau = new ArrayList<Reseau>();

    public MyAdapter(Context context, ArrayList<Reseau> listReseau ) {
        this.context = context;
        this.listReseau = listReseau;
    }
    @Override
    public int getCount() {
        return listReseau.size();
    }

    @Override
    public Object getItem(int position) {
        return listReseau.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem;
        LayoutInflater inflater = LayoutInflater.from(context);
        if(convertView==null){
            layoutItem = (LinearLayout) inflater.inflate(R.layout.adapter_item, parent, false);
        }else{
            layoutItem = (LinearLayout) convertView;
        }


        TextView Region = layoutItem.findViewById(R.id.item_nomreg);
        Region.setText(listReseau.get(position).getNomRegion());

        TextView CodePostale = layoutItem.findViewById(R.id.item_code);
        CodePostale.setText(listReseau.get(position).getCodePostale());

        TextView Operator = layoutItem.findViewById(R.id.item_operator);
        Operator.setText(listReseau.get(position).getOperateur());

        TextView Technology = layoutItem.findViewById(R.id.item_tech);
        Technology.setText(listReseau.get(position).getTechnology());

        layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MapsActivity.class);
                intent.putExtra("LAT",listReseau.get(position).getLatitude());
                intent.putExtra("LON",listReseau.get(position).getLongitude());
                context.startActivity(intent);
            }
        });

        return layoutItem;
    }


}
