package com.cs160.joleary.catnip;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class detailed extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String info_string = extras.getString("info");
        Bitmap pic = (Bitmap) extras.get("photo");

        final ImageView pictureView = (ImageView) findViewById(R.id.imageView);
        pictureView.setImageBitmap(pic);
        try {
            JSONObject info = new JSONObject(info_string);

            TextView name = (TextView)findViewById(R.id.name);
            TextView party = (TextView)findViewById(R.id.party);
            TextView dates = (TextView)findViewById(R.id.dates);

            name.setText(info.getString("first_name") + " " + info.getString("last_name"));
            party.setText(info.getString("party"));
            dates.setText("Current Term: " + info.getString("term_start") + "-" + info.getString("term_end"));

            new SunshineRestClient().execute(info.getString("bioguide_id"));

        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(), "something went wrong, start over", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    private class SunshineRestClient extends AsyncTask<String, String, String> {
        private final String baseUrl = "http://congress.api.sunlightfoundation.com/committees?";
        private final String baseUrl2 = "http://congress.api.sunlightfoundation.com/bills?";
        private final String apikey="&apikey=bc29918f07bb41ceb87fdb41db03658f";
        @Override
        protected String doInBackground(String... params) {

            String urlString=baseUrl + "member_ids=" + params[0] + apikey; // URL to call
            String urlString2=baseUrl2 + "sponsor_id=" + params[0] + apikey; // URL to call

            String resultToDisplay = "";
            String resultToDisplay2 = "";

            InputStream in = null;
            InputStream in2 = null;

            // HTTP Get
            try {

                URL url = new URL(urlString);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                in = new BufferedInputStream(urlConnection.getInputStream());

            } catch (Exception e ) {

                System.out.println(e.getMessage());

                //return e.getMessage();

            }

            try {
                //System.out.println("RESULTS: " + new JSONObject(in.toString()));
                byte[] contents = new byte[1024];
                int bytesRead=0;
                String strFileContents = "";
                while( (bytesRead = in.read(contents)) != -1){
                    strFileContents += new String(contents, 0, bytesRead);
                }
                //System.out.print("RESULTS: " + strFileContents);
                resultToDisplay = strFileContents;
            } catch (Exception e) {

                System.out.println("FUCK");
                //return e.getMessage();
            }

            // HTTP Get
            try {

                URL url = new URL(urlString2);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                in2 = new BufferedInputStream(urlConnection.getInputStream());

            } catch (Exception e ) {

                System.out.println(e.getMessage());

                //return e.getMessage();

            }

            try {
                //System.out.println("RESULTS: " + new JSONObject(in.toString()));
                byte[] contents = new byte[1024];
                int bytesRead=0;
                String strFileContents = "";
                while( (bytesRead = in2.read(contents)) != -1){
                    strFileContents += new String(contents, 0, bytesRead);
                }
                //System.out.print("RESULTS: " + strFileContents);
                resultToDisplay2 = strFileContents;
            } catch (Exception e) {

                System.out.println("FUCK");
                //return e.getMessage();
            }

            System.out.println(resultToDisplay + resultToDisplay2);
            return resultToDisplay + "PaulRyanIsALumberJack" +resultToDisplay2;
        }

        protected void onPostExecute(String result) {
            String[] results = new String[2];
            results = result.split("PaulRyanIsALumberJack");
            TextView committees_all = (TextView)findViewById(R.id.committees);
            TextView bill1 = (TextView)findViewById(R.id.bill1);
            TextView bill2 = (TextView)findViewById(R.id.bill2);
            TextView bill3 = (TextView)findViewById(R.id.bill3);

            try {
                JSONObject committees = new JSONObject(results[0]);
                JSONObject bills = new JSONObject(results[1]);
                int count = committees.getInt("count");
                JSONArray committeesArray = committees.getJSONArray("results");
                int zero = 0;
                String committees_string = committeesArray.getJSONObject(zero).getString("name");
                for(int i=1; i<count; i++) {
                    JSONObject info = committeesArray.getJSONObject(i);
                    committees_string += ", " + info.getString("name");
                }

                JSONArray billsArray = bills.getJSONArray("results");
                String[] billsSelect = new String[3];
                for(int i=0; i<3; i++) {
                    JSONObject billObject = billsArray.getJSONObject(i);
                    String billName = billObject.getString("short_title");
                    billsSelect[i] = billName;
                }

                committees_all.setText(committees_string);
                bill1.setText(billsSelect[0]);
                bill2.setText(billsSelect[1]);
                bill3.setText(billsSelect[2]);
            } catch (Exception e) {
                System.out.println("committee or bill json failed to parse");
            }
        }
    }

}
