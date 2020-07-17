package com.example.ranjeetkumarrana.homeworkgurdian;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Home_Work_List extends ArrayAdapter<Home_Work_Details> {

    private Activity context;
    private List<Home_Work_Details> siemensList;

    public Home_Work_List(Activity context, List<Home_Work_Details> siemensList) {
        super(context, R.layout.list_layout_home_work, siemensList);
        this.context = context;
        this.siemensList = siemensList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout_home_work, null, true);
        TextView textview_S_type = listViewItem.findViewById(R.id.S_type_list);
        TextView textView_S_site = listViewItem.findViewById(R.id.S_site_list);
        TextView textView_S_date = listViewItem.findViewById(R.id.S_date_list);
        TextView textView_S_price = listViewItem.findViewById(R.id.S_price_list);
        TextView textView_S_Desc = listViewItem.findViewById(R.id.S_Description_list);

        Home_Work_Details siemensDetail = siemensList.get(position);
        textview_S_type.setText(siemensDetail.getStudent_Class());
        textView_S_site.setText(siemensDetail.getSubject());
        textView_S_date.setText(siemensDetail.getDate());
        textView_S_price.setText(siemensDetail.getRoll_no());
        textView_S_Desc.setText(siemensDetail.getDescription());

        return listViewItem;
    }
}
