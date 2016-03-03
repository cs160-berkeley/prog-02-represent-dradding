//package com.cs160.joleary.catnip;
//
///**
// * Created by dradding on 3/3/16.
// */
//import android.app.Activity;
//import android.os.Bundle;
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class RecyclerViewActivity extends Activity {
//
//    private List<Person> persons;
//    private RecyclerView rv;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_congressional);
//
//        rv=(RecyclerView)findViewById(R.id.rv);
//
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        rv.setLayoutManager(llm);
//        rv.setHasFixedSize(true);
//
//        initializeData();
//        initializeAdapter();
//    }
//
//    private void initializeData(){
//        persons = new ArrayList<>();
//        persons.add(new Person("Cat", "Cat","Cat", "Cat", "Cat", "Cat", "Cat", R.drawable.lexy_160));
//        persons.add(new Person("Cat2", "Cat","Cat", "Cat", "Cat", "Cat", "Cat", R.drawable.lexy_160));
//        persons.add(new Person("Cat3", "Cat","Cat", "Cat", "Cat", "Cat", "Cat", R.drawable.lexy_160));
//    }
//
//    private void initializeAdapter(){
//        RVAdapter adapter = new RVAdapter(persons);
//        rv.setAdapter(adapter);
//    }
//}
//
