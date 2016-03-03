package com.cs160.joleary.catnip;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class congressional extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congressional);
//        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        rv.setLayoutManager(llm);


        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                Intent intentDetailed = new Intent(getApplicationContext(), detailed.class);
                intentDetailed.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intentDetailed);
            }
        });


    }

    class Person{
        String name;
        String email;
        String website;
        String tweet;
        String party;
        String committee;
        String bill;
        int photoID;

        Person(String name, String email, String website, String tweet, String party, String committee, String bill, int photoId) {
            this.name = name;
            this.email = email;
            this.website = website;
            this.tweet = tweet;
            this.party = party;
            this.committee = committee;
            this.bill = bill;
            this.photoID = photoId;
        }
    }

    private List<Person> persons;

    private void initializeData() {
        persons = new ArrayList<>();
        persons.add(new Person("Cat", "Cat","Cat", "Cat", "Cat", "Cat", "Cat", R.drawable.lexy_160));
        persons.add(new Person("Cat2", "Cat","Cat", "Cat", "Cat", "Cat", "Cat", R.drawable.lexy_160));
        persons.add(new Person("Cat3", "Cat","Cat", "Cat", "Cat", "Cat", "Cat", R.drawable.lexy_160));

    }

}
