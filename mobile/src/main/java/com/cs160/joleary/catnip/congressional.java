package com.cs160.joleary.catnip;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ListView;
import android.widget.Toast;
import org.json.*;
import java.util.ArrayList;
import java.util.List;

public class congressional extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congressional);
        final ListView listView = (ListView)findViewById(R.id.personView);

        List<String> temp_names = new ArrayList<String>();
        List<String> temp_emails = new ArrayList<String>();
        List<String> temp_websites = new ArrayList<String>();
        List<String> temp_tweets = new ArrayList<String>();
        List<Integer> temp_photos = new ArrayList<Integer>();
        List<JSONObject> temp_info = new ArrayList<JSONObject>();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        JSONObject sunshineJSON;

        String results = extras.getString("results");

        try {
            JSONObject resultsJSON = new JSONObject(results);
            int count = resultsJSON.getInt("count");
            JSONArray resultsArray = resultsJSON.getJSONArray("results");
            for(int i=0; i<count; i++) {
                JSONObject info = resultsArray.getJSONObject(i);
                temp_names.add(info.getString("first_name") + " " + info.getString("last_name"));
                temp_emails.add(info.getString("oc_email"));
                temp_websites.add(info.getString("website"));
                temp_tweets.add(info.getString("twitter_id"));
                temp_photos.add(R.drawable.fred_160);
                temp_info.add(info);
            }

            String[] names = new String[count];
            String[] emails = new String[count];
            String[] websites = new String[count];
            String[] tweets = new String[count];
            int[] photos = new int[count];
            JSONObject[] info = new JSONObject[count];

            names = temp_names.toArray(names);
            emails = temp_emails.toArray(emails);
            websites = temp_websites.toArray(websites);
            tweets = temp_tweets.toArray(tweets);
            info = temp_info.toArray(info);

            for (int i=0; i<count; i++) {
                photos[i] = temp_photos.get(i).intValue();
            }

            final PersonArrayAdapter adapter = new PersonArrayAdapter(this, names, emails, websites, tweets, photos, info);
            listView.setAdapter(adapter);
        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(), "something went wrong, start over", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
