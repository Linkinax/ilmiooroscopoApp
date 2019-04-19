package com.ilmiooroscopoapp;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


public class ActivitySettimanale extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView mTextMessage;
    private String Sign;
    private TextView LavoroText;
    private TextView JobText;
    private TextView HealthText;
    private Spinner SignSpinner;
    private TextView SignText;
    private TextView DateText;
    private ImageView BackImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settimanale);
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

        BackImage = (ImageView) findViewById(R.id.imageView3);
        BackImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent act2 = new Intent(view.getContext(), MainActivity.class);
                startActivity(act2);
            }
        } );



        LavoroText = (TextView) findViewById(R.id.textView6);
        JobText = (TextView) findViewById(R.id.textView7);
        HealthText = (TextView) findViewById(R.id.textView8);
        SignText = (TextView) findViewById(R.id.textView5);
        DateText = (TextView) findViewById(R.id.textView9);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        String text = parent.getItemAtPosition(position).toString();
        Sign= text.toLowerCase();

        SignText.setText(text);


        com.ilmiooroscopoapp.AsyncDataRetriver ass = new com.ilmiooroscopoapp.AsyncDataRetriver("Settimanale/"+ Sign);
        ass.doInBackground();

        LavoroText.setText(ass.getGeneralField());
        JobText.setText(ass.getAmoreField());
        HealthText.setText(ass.getHealthField());
        DateText.setText("Data:\t"+ ass.getDataField());


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }}