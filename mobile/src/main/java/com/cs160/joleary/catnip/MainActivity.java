package com.cs160.joleary.catnip;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.wearable.Wearable;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "yUeWj6i4abIalzjHXAboOiRQp";
    private static final String TWITTER_SECRET = "yRM6f4gm2dopgrxngI6HjmP4e5EdJVox1OPPwzlH2Wed3misFg";

    //there's not much interesting happening. when the buttons are pressed, they start
    //the PhoneToWatchService with the cat name passed in.

//    private Button mFredButton;
//    private Button mLexyButton;
    private Button mLocationButton;
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    protected static final String ADDRESS_REQUESTED_KEY = "address-request-pending";
    protected static final String LOCATION_ADDRESS_KEY = "location-address";

    protected static final String TAG = "main-activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        setContentView(R.layout.activity_main);
        mLocationButton = (Button)findViewById(R.id.location_btn);
        final EditText zip = (EditText)findViewById(R.id.zipcode);


//        mFredButton = (Button) findViewById(R.id.fred_btn);
//        mLexyButton = (Button) findViewById(R.id.lexy_btn);
//
//        mFredButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//                sendIntent.putExtra("CAT_NAME", "Fred");
//                startService(sendIntent);
//            }
//        });
//
        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( ContextCompat.checkSelfPermission( getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission( getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, "No Location Permission", duration);
                    toast.show();

                    System.out.println("PERMISSION ISSUE");

                } else {
//                    LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
//                    Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                    //tv.setText("Latitude: " + loc.getLatitude() + "\nLongitude: " + loc.getLongitude());

                    //mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

                    Context context = getApplicationContext();

                    int duration = Toast.LENGTH_SHORT;

                    if (mLastLocation == null) {
                        Toast toast = Toast.makeText(context, "SHIT", duration);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(context, "NOT SHIT", duration);
                        toast.show();
                    }

                    //CharSequence text = "Latitude: " + String.valueOf(mLastLocation.getLatitude()) + "\nLongitude: " + String.valueOf(mLastLocation.getLongitude());

//                    Toast toast = Toast.makeText(context, text, duration);
//                    toast.show();

                }

//                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//                sendIntent.putExtra("REP_NAME", "REP A");
//                startService(sendIntent);
//
                Intent i = new Intent(getApplicationContext(), congressional.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("latitude", "37.8671390");
                i.putExtra("longitude", "-122.2503590");
                i.putExtra("type", "geo");
                getApplicationContext().startActivity(i);

            }
        });

        zip.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence a, int b, int c, int d) {
            }

            public void beforeTextChanged(CharSequence a, int b, int c, int d) {
            }

            public void afterTextChanged(Editable s) {
                zip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new SunshineRestClient().execute("zip=" + zip.getText().toString());
//                        Intent i = new Intent(getApplicationContext(), congressional.class);
//                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        i.putExtra("zip", zip.getText().toString());
//                        i.putExtra("type", "zip");
//                        getApplicationContext().startActivity(i);
                    }
                });
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
    }
    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "GOOGLE API CONNECTED!");
        if (mGoogleApiClient!=null){
            System.out.println("mGOOGLEAPICLIENT NOT IS NULL " + mGoogleApiClient);
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

        if (mLastLocation == null) {
            System.out.println("THIS SHIT IS NULL !!!!!!!!!!!!!!!!!!!!!!!!! " + mLastLocation);
//            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
//            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
        } else {
            System.out.println("THIS SHIT IS NOT NULL !!!!!!!!!!!!!!!!!!!!!!!!! " + mLastLocation);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connResult) {}

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    private class SunshineRestClient extends AsyncTask<String, String, String> {
        private final String baseUrl = "http://congress.api.sunlightfoundation.com/legislators/locate?";
        private final String apikey="&apikey=bc29918f07bb41ceb87fdb41db03658f";
        @Override
        protected String doInBackground(String... params) {

            String urlString=baseUrl + params[0] + apikey; // URL to call

            String resultToDisplay = "";

            InputStream in = null;

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
            return resultToDisplay;
        }

        protected void onPostExecute(String result) {

            Intent i = new Intent(getApplicationContext(), congressional.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("results", result);
            getApplicationContext().startActivity(i);
        }
    }
}