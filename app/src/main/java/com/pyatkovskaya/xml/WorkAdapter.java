package com.pyatkovskaya.xml;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pyatkovskaya.xml.models.Work;

import java.util.List;

/**
 * Created by Natali-Pi on 08.02.2017.
 */

public class WorkAdapter extends BaseAdapter{
    private List<Work> works;
    private Context context;
    private LayoutInflater inflater;
    public WorkAdapter(Context context, List<Work> works) {
        this.context = context;
        this.works = works;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return works.size();
    }

    @Override
    public Object getItem(int i) {
        return works.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View root = view;
        if (root == null) {
            root = inflater.inflate(R.layout.work_list_item, viewGroup, false);
        }
        TextView workName = (TextView) root.findViewById(R.id.field1);
        workName.setText(context.getString(R.string.workName,works.get(position).getName()));

        TextView workSpecialty = (TextView) root.findViewById(R.id.field2);
        workSpecialty.setText(context.getString(R.string.specialty,works.get(position).getSpecialty()));


        TextView beginDate = (TextView) root.findViewById(R.id.field3);
        beginDate.setText(context.getString(R.string.begin_s,works.get(position).getBeginDate()));

        TextView endDate = (TextView) root.findViewById(R.id.field4);
        endDate.setText(context.getString(R.string.end_s,works.get(position).getEndDate()));

        return root;
    }
}
