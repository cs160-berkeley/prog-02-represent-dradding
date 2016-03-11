package com.cs160.joleary.catnip;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
//import com.loopj.android.http.*;
import org.json.*;


import java.util.ArrayList;
import java.util.List;

public class congressional extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congressional);
        final ListView listView = (ListView)findViewById(R.id.personView);
        String[] names = {"Alice", "Bob", "Eve"};
        String[] emails = {"email@Alice", "email@Bob", "email@Eve"};
        String[] websites = {"Alice.com", "Bob.com", "Eve.com"};
        String[] tweets = {"I am Alice", "I am Bob", "I am Eve"};
        int[] photos = {R.drawable.lexy_160, R.drawable.lexy_160, R.drawable.lexy_160};
        final PersonArrayAdapter adapter = new PersonArrayAdapter(this, names, emails, websites, tweets, photos);
        listView.setAdapter(adapter);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            String type = extras.getString("type");
            if (type.equals("zip")) {
                String zip = extras.getString("zip");
                RequestParams params = new RequestParams();
                params.put("zip", zip);
                params.put("apikey", "bc29918f07bb41ceb87fdb41db03658f");
                SunshineRestClient.get("legislators/locate", new JsonHttpResponseHandler() {
                    //@Override
                    public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONObject response) {
                        // If the response is JSONObject instead of expected JSONArray
                    }

                    @Override
                    public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONArray timeline) {
                        // Pull out the first event on the public timeline
                        JSONObject firstEvent = timeline.get(0);
                    String tweetText = firstEvent.getString("text");

                        // Do something with the response
                        System.out.println(tweetText);
                    }
                });

            } else {
                String latitude = extras.getString("latitude");
                String  longitude = extras.getString("longitude");
            }

        }
    }
}
