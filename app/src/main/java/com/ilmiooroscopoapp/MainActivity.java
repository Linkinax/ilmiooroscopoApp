package com.ilmiooroscopoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        AdapterView.OnItemSelectedListener{


    private TextView mTextMessage;
    private String Sign;
    private TextView LavoroText;
    private TextView JobText;
    private TextView HealthText;
    private Spinner SignSpinner;
    private TextView SignText;
    private TextView DateText;
    private ImageView ForewardImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        mTextMessage = (TextView) findViewById(R.id.message);

        SignSpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.signs, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        SignSpinner.setAdapter(adapter);
        SignSpinner.setOnItemSelectedListener(this);

        ForewardImage = (ImageView) findViewById(R.id.imageView3);
        ForewardImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent act2 = new Intent(view.getContext(), ActivitySettimanale.class);
                startActivity(act2);
            }
        } );



        LavoroText = (TextView) findViewById(R.id.textView6);
        JobText = (TextView) findViewById(R.id.textView7);
        HealthText = (TextView) findViewById(R.id.textView8);
        SignText = (TextView) findViewById(R.id.textView5);
        DateText = (TextView) findViewById(R.id.textView9);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_description) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



        String text = parent.getItemAtPosition(position).toString();
        Sign= text.toLowerCase();

        SignText.setText(text);


        com.ilmiooroscopoapp.AsyncDataRetriver ass = new com.ilmiooroscopoapp.AsyncDataRetriver( "Odierno/"+Sign);
        ass.doInBackground();

        LavoroText.setText(ass.getGeneralField());
        JobText.setText(ass.getAmoreField());
        HealthText.setText(ass.getHealthField());
        DateText.setText("Data:\t"+ ass.getDataField());



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
