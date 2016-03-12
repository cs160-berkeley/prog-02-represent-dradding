package com.cs160.joleary.catnip;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class detailed extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String info_string = extras.getString("info");
        try {
            JSONObject info = new JSONObject(info_string);

            TextView name = (TextView)findViewById(R.id.name);
            TextView party = (TextView)findViewById(R.id.party);
            TextView dates = (TextView)findViewById(R.id.dates);
            TextView committees = (TextView)findViewById(R.id.committees);
            TextView bill1 = (TextView)findViewById(R.id.bill1);
            TextView bill2 = (TextView)findViewById(R.id.bill2);
            TextView bill3 = (TextView)findViewById(R.id.bill3);

            name.setText(info.getString("first_name") + " " + info.getString("last_name"));
            party.setText(info.getString("party"));
            dates.setText("Current Term: " + info.getString("term_start") + "-" + info.getString("term_end"));

        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(), "something went wrong, start over", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

}
