package com.example.mastermindtriona;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ListViewItem> items;


    public CustomAdapter(Context context, ArrayList<ListViewItem> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).
                    inflate(R.layout.list_view, parent, false);
        }
        ListViewItem item = (ListViewItem) getItem(position);

        TextView textView = view.findViewById(R.id.textView);
        ImageView imageView = view.findViewById(R.id.icon);

        textView.setText(item.getGues());
        imageView.setImageResource(item.getImage());

        return view;
    }
}
