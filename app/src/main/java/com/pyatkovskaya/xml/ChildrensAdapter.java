package com.pyatkovskaya.xml;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pyatkovskaya.xml.models.Child;
import com.pyatkovskaya.xml.models.Work;

import java.util.List;

/**
 * Created by Natali-Pi on 08.02.2017.
 */

public class ChildrensAdapter extends BaseAdapter {
    private List<Child> childs;
    private Context context;
    private LayoutInflater inflater;

    public ChildrensAdapter(Context context, List<Child> childs) {
        this.childs = childs;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return childs.size();
    }

    @Override
    public Object getItem(int i) {
        return childs.get(i);
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

        TextView firstName = (TextView) root.findViewById(R.id.field1);
        firstName.setText(context.getString(R.string.firstname_s,childs.get(position).getFirstname()));

        TextView lastName = (TextView) root.findViewById(R.id.field2);
        lastName.setText(context.getString(R.string.lastname_s,childs.get(position).getLastname()));


        TextView surName = (TextView) root.findViewById(R.id.field3);
        surName.setText(context.getString(R.string.surname_s,childs.get(position).getSurname()));

        TextView endDate = (TextView) root.findViewById(R.id.field4);
        endDate.setText(context.getString(R.string.birthYear_s, childs.get(position).getBirthYear()));

        return root;
    }
}
