package com.pyatkovskaya.xml;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.pyatkovskaya.xml.models.Educate;
import com.pyatkovskaya.xml.models.Person;

/**
 * Created by kostya on 02.02.2017.
 */

public class EditPersonActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.okButton) {

            EditText fNameEdit = (EditText) findViewById(R.id.fNameEdit);
            String name = fNameEdit.getText().toString();
            EditText lNameEdit = (EditText) findViewById(R.id.lNameEdit);
            String lname = lNameEdit.getText().toString();
            EditText sNameEdit = (EditText) findViewById(R.id.sNameEdit);
            String sname = sNameEdit.getText().toString();
            EditText passportEdit = (EditText) findViewById(R.id.PassportEdit);
            String passport = passportEdit.getText().toString();
            EditText editCountry = (EditText) findViewById(R.id.editCountry);
            String country = editCountry.getText().toString();
            EditText editBirthsday = (EditText) findViewById(R.id.editBirthsday);
            String birthsday = editBirthsday.getText().toString();
            EditText editSchool = (EditText) findViewById(R.id.editSchool);
            String school = editSchool.getText().toString();
            EditText editBegin = (EditText) findViewById(R.id.editBegin);
            int begin = Integer.parseInt(editBegin.getText().toString());
            EditText editEnd = (EditText) findViewById(R.id.editEnd);
            int end = Integer.parseInt(editEnd.getText().toString());
            RadioGroup gender = (RadioGroup) findViewById(R.id.gender);
             boolean isMale = gender.getCheckedRadioButtonId()==R.id.male;
            RadioGroup married = (RadioGroup) findViewById(R.id.married);
            boolean isMarried = married.getCheckedRadioButtonId()==R.id.yes;

            //Log.d("MainActivity", " item " + );
            Educate education = new Educate(begin, end, school, "");
            Person person = new Person(name,lname,sname, passport, country, birthsday, education, isMale, isMarried);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("EditPerson Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
