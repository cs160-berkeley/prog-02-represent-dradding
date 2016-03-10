package com.cs160.joleary.catnip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Created by dradding on 3/10/16.
 */
public class PersonArrayAdapter extends BaseAdapter {

    private final Context context;
    private final String[] names;
    private final String[] emails;
    private final String[] websites;
    private final String[] tweets;
    private final int[] photos;

    public PersonArrayAdapter(Context context, String[] names, String[] emails, String[] websites, String[] tweets, int[] photos) {

        this.context = context;
        this.names = names;
        this.emails = emails;
        this.websites = websites;
        this.tweets = tweets;
        this.photos = photos;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View personRow = inflater.inflate(R.layout.person_row_layout, parent, false);

        TextView nameView = (TextView) personRow.findViewById(R.id.name);
        TextView emailView = (TextView) personRow.findViewById(R.id.email);
        TextView websiteView = (TextView) personRow.findViewById(R.id.website);
        TextView tweetView = (TextView) personRow.findViewById(R.id.tweet);
        ImageView pictureView = (ImageView) personRow.findViewById(R.id.icon);

        nameView.setText(names[position]);
        emailView.setText(emails[position]);
        websiteView.setText(websites[position]);
        tweetView.setText(tweets[position]);
        pictureView.setImageResource(photos[position]);

        //Attaching onClickListener

//        personRow.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//
//                Toast.makeText(context, names[position], Toast.LENGTH_SHORT).show();
////                Intent intentDetailed = new Intent(getApplicationContext(), detailed.class);
////                intentDetailed.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                getApplicationContext().startActivity(intentDetailed);
//
//            }
//        });

        return personRow;

    }

    public Object getItem(int arg0) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public int getCount() {
        return -1;
    }
}