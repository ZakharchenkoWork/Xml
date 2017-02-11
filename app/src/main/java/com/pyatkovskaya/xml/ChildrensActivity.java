package com.pyatkovskaya.xml;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.pyatkovskaya.xml.models.Child;

import java.util.ArrayList;

/**
 * Created by Natali-Pi on 08.02.2017.
 */

public class ChildrensActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(new ChildrensAdapter(this, new ArrayList<Child>()));
    }
}
