package com.cs160.joleary.catnip;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Arrays;
import java.util.List;

import io.fabric.sdk.android.Fabric;

import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;
import com.twitter.sdk.android.tweetui.UserTimeline;

/**
 * Created by dradding on 3/10/16.
 */
public class PersonArrayAdapter extends BaseAdapter {

    private static final String TWITTER_KEY = "yUeWj6i4abIalzjHXAboOiRQp";
    private static final String TWITTER_SECRET = "yRM6f4gm2dopgrxngI6HjmP4e5EdJVox1OPPwzlH2Wed3misFg";

    private final Context context;
    private final String[] names;
    private final String[] emails;
    private final String[] websites;
    private final String[] tweets;
    private final int[] photos;
    private final JSONObject[] info;

    public PersonArrayAdapter(Context context, String[] names, String[] emails, String[] websites, String[] tweets, int[] photos, JSONObject[] info) {

        this.context = context;
        this.names = names;
        this.emails = emails;
        this.websites = websites;
        this.tweets = tweets;
        this.photos = photos;
        this.info = info;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View personRow = inflater.inflate(R.layout.person_row_layout, parent, false);

        TextView nameView = (TextView) personRow.findViewById(R.id.name);
        TextView emailView = (TextView) personRow.findViewById(R.id.email);
        TextView websiteView = (TextView) personRow.findViewById(R.id.website);
        final TextView tweetView = (TextView) personRow.findViewById(R.id.tweet);
        ImageView pictureView = (ImageView) personRow.findViewById(R.id.icon);

        TwitterCore.getInstance().logInGuest(new Callback<AppSession>() {
            @Override
            public void success(Result<AppSession> appSessionResult) {
                AppSession session = appSessionResult.data;
                TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient(session);
                twitterApiClient.getStatusesService().userTimeline(null, tweets[position], 1,
                        null, null, false, false, false, true, new Callback<List<Tweet>>() {

                            @Override
                            public void success(Result<List<Tweet>> result) {
                                for (Tweet tweet : result.data) {
                                    //System.out.println("TWEET: " + tweet.text);
                                    tweetView.setText(tweet.text);

                                }
                            }

                            @Override
                            public void failure(TwitterException e) {
                                tweetView.setText("Twitter: " + tweets[position]);
                                e.printStackTrace();
                            }
                        });
            }

            public void failure(TwitterException e) {
                e.printStackTrace();
            }
        });


        nameView.setText(names[position]);
        emailView.setText(emails[position]);
        websiteView.setText(websites[position]);
        pictureView.setImageResource(photos[position]);

        personRow.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intentDetailed = new Intent(context.getApplicationContext(), detailed.class);
                intentDetailed.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intentDetailed.putExtra("info", info[position].toString());
                intentDetailed.putExtra("photo", photos[position]);
                context.getApplicationContext().startActivity(intentDetailed);

            }
        });

        return personRow;

    }

    public Object getItem(int arg0) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public int getCount() {
        return names.length;
    }
}