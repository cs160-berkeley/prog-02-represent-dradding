package com.cs160.joleary.catnip;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
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
import io.fabric.sdk.android.Fabric;

public class MainActivity extends Activity  {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "yUeWj6i4abIalzjHXAboOiRQp";
    private static final String TWITTER_SECRET = "yRM6f4gm2dopgrxngI6HjmP4e5EdJVox1OPPwzlH2Wed3misFg";

    //there's not much interesting happening. when the buttons are pressed, they start
    //the PhoneToWatchService with the cat name passed in.

//    private Button mFredButton;
//    private Button mLexyButton;
    private Button mLocationButton;
    private GPSActivity GoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        GoogleApiClient = new GPSActivity();
        //GoogleApiClient.onStart();

        setContentView(R.layout.activity_main);
        mLocationButton = (Button)findViewById(R.id.location_btn);
        final EditText zip = (EditText)findViewById(R.id.zipcode);

//        public void getLocation(View view) {
//            TextView tv = (TextView) findViewById(R.id.gps_coord_view);
//            LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
//                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                        && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                    tv.setText("Latitude: " + loc.getLatitude() + "\nLongitude: " + loc.getLongitude());
//            }
//        }

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
                } else {
                    LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
                    Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    //tv.setText("Latitude: " + loc.getLatitude() + "\nLongitude: " + loc.getLongitude());
                    Context context = getApplicationContext();
                    CharSequence text = "Latitude: " + loc.getLatitude() + "\nLongitude: " + loc.getLongitude();
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }

//                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//                sendIntent.putExtra("REP_NAME", "REP A");
//                startService(sendIntent);
//
//                Intent i = new Intent(getApplicationContext(), congressional.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                getApplicationContext().startActivity(i);

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
                        Intent i = new Intent(getApplicationContext(), congressional.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplicationContext().startActivity(i);
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

    public class GPSActivity extends Activity implements
            GoogleApiClient.ConnectionCallbacks,
            GoogleApiClient.OnConnectionFailedListener {

        private GoogleApiClient mGoogleApiClient;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addApi(Wearable.API)  // used for data layer API
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
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
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            if (mLastLocation != null) {
                mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
                mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
            }
        }

        @Override
        public void onConnectionSuspended(int i) {}

        @Override
        public void onConnectionFailed(ConnectionResult connResult) {}

        protected void onStart() {
            mGoogleApiClient.connect();
            super.onStart();
        }

        protected void onStop() {
            mGoogleApiClient.disconnect();
            super.onStop();
        }

    }
}