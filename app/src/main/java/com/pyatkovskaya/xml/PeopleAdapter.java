package com.pyatkovskaya.xml;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pyatkovskaya.xml.models.Person;

import java.util.ArrayList;

/**
 * Created by kostya on 31.01.2017.
 */

public class PeopleAdapter extends BaseAdapter {
    private ArrayList<Person> persons;
    private Context context;
    LayoutInflater inflater;
    public PeopleAdapter(Context context, ArrayList<Person> persons) {
        this.persons = persons;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int i) {
        return persons.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View root = view;
        if (root == null) {
            root = inflater.inflate(R.layout.worker_item, viewGroup, false);
        }

        Person person = persons.get(position);

        TextView mainData = (TextView)root.findViewById(R.id.mainData);
        mainData.setText(position + ". " + person.getFirstname() + " " + person.getLastname() + " " + person.getSurname());

        TextView pcb = (TextView)root.findViewById(R.id.passport_country_birth);
        pcb.setText(context.getString(R.string.pcb_line,person.getPasport(), person.getCountry(), person.getBirthdayString()));

        TextView shg = (TextView)root.findViewById(R.id.school_higher_gender);
        shg.setText(context.getString(R.string.shg_line,person.getSchool().getName(),""+person.getHighSchool().size(),""+person.isGender()));

        TextView mcw = (TextView)root.findViewById(R.id.married_childs_work);
        mcw.setText(context.getString(R.string.mcw_line,""+person.isMaried(),""+person.getChilds().size(),""+person.getWorks().size()));
        return root;
    }
}
