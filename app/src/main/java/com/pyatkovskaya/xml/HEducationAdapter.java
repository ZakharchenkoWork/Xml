package com.pyatkovskaya.xml;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pyatkovskaya.xml.models.Educate;

import java.util.List;

/**
 * Created by Natali-Pi on 08.02.2017.
 */

public class HEducationAdapter extends BaseAdapter {
    private List<Educate> educations;
    private Context context;
    private LayoutInflater inflater;

    public HEducationAdapter(Context context, List<Educate> educations ) {
        this.educations = educations;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return educations.size();
    }

    @Override
    public Object getItem(int i) {
        return educations.get(i);
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
        TextView name = (TextView) root.findViewById(R.id.field1);
        name.setText(context.getString(R.string.workName,educations.get(position).getName()));

        TextView profession = (TextView) root.findViewById(R.id.field2);
        profession.setText(context.getString(R.string.profession_s, educations.get(position).getProfession()));


        TextView beginYear = (TextView) root.findViewById(R.id.field3);
        beginYear.setText(context.getString(R.string.begin_s,""+educations.get(position).getBeginYear()));

        TextView endYear = (TextView) root.findViewById(R.id.field4);
        endYear.setText(context.getString(R.string.end_s,""+educations.get(position).getEndYear()));

        return root;
    }
}
