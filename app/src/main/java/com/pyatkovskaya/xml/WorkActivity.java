package com.pyatkovskaya.xml;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.pyatkovskaya.xml.models.Work;

import java.util.ArrayList;

/**
 * Created by Natali-Pi on 08.02.2017.
 */

public class WorkActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        ArrayList<Work> works = new ArrayList<>();
        works.add(new Work("aaaaa","aaa","30.04.1987","30.04.1987"));
        works.add(new Work("aaaaa2","aaa","30.04.1987","30.04.1987"));
        works.add(new Work("aaaaa3","aaa","30.04.1987","30.04.1987"));
        works.add(new Work("aaaaa4","aaa","30.04.1987","30.04.1987"));
        works.add(new Work("aaaaa5","aaa","30.04.1987","30.04.1987"));

        ListView list = (ListView) findViewById(R.id.list);

        WorkAdapter adapter = new WorkAdapter(this, works);
        list.setAdapter(adapter);

    }
}
