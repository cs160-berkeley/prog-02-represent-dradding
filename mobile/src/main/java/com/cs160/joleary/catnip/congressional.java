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
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
//                Intent intentDetailed = new Intent(getApplicationContext(), detailed.class);
//                intentDetailed.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                getApplicationContext().startActivity(intentDetailed);
//            }
//        });

//        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        rv.setLayoutManager(llm);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

//        if (extras != null) {
//            String type = extras.getString("type");
//            if (type.equals("zip")) {
//                String zip = extras.getString("zip");
//                RequestParams params = new RequestParams();
//                params.put("zip", zip);
//                params.put("apikey", "bc29918f07bb41ceb87fdb41db03658f");
//                SunshineRestClient.get("legislators/locate", new JsonHttpResponseHandler() {
//                    //@Override
//                    public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONObject response) {
//                        // If the response is JSONObject instead of expected JSONArray
//                    }
//
//                    @Override
//                    public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONArray timeline) {
//                        // Pull out the first event on the public timeline
//                        JSONObject firstEvent = timeline.get(0);
//                    String tweetText = firstEvent.getString("text");
//
//                        // Do something with the response
//                        System.out.println(tweetText);
//                    }
//                });
//
//            } else {
//                String latitude = extras.getString("latitude");
//                String  longitude = extras.getString("longitude");
//            }
//
//        }


    }

//    class Person{
//        String name;
//        String email;
//        String website;
//        String tweet;
//        String party;
//        String committee;
//        String bill;
//        int photoID;
//
//        Person(String name, String email, String website, String tweet, String party, String committee, String bill, int photoId) {
//            this.name = name;
//            this.email = email;
//            this.website = website;
//            this.tweet = tweet;
//            this.party = party;
//            this.committee = committee;
//            this.bill = bill;
//            this.photoID = photoId;
//        }
//    }
//
//    private List<Person> persons;
//
//    private void initializeData() {
//        persons = new ArrayList<>();
//        persons.add(new Person("Cat", "Cat","Cat", "Cat", "Cat", "Cat", "Cat", R.drawable.lexy_160));
//        persons.add(new Person("Cat2", "Cat","Cat", "Cat", "Cat", "Cat", "Cat", R.drawable.lexy_160));
//        persons.add(new Person("Cat3", "Cat","Cat", "Cat", "Cat", "Cat", "Cat", R.drawable.lexy_160));
//
//    }

}
