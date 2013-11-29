package com.aiman.demoandroidcrud;

import com.aiman.salamdunia.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DemoAndroidCrudActivity extends Activity {

    // Declare variables
    private Button fvbutton;
    private Button svbutton;
    private Button tvbutton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_salam_dunia);
 
        // Locate buttons in activity_main.xml
        fvbutton = (Button)findViewById(R.id.firstview);
        svbutton = (Button)findViewById(R.id.secondview);
        tvbutton = (Button)findViewById(R.id.thirdview);
 
        // Listen for first view button click
        fvbutton.setOnClickListener(new OnClickListener() {
 
            public void onClick(View arg0) {
                // Open FirstView.class
                Intent myIntent = new Intent(DemoAndroidCrudActivity.this, FirstView.class);
                DemoAndroidCrudActivity.this.startActivity(myIntent);
                // TODO Auto-generated method stub
            }
        });
 
        // Listen for second view button click
        svbutton.setOnClickListener(new OnClickListener() {
 
            public void onClick(View arg0) {
                // Open SecondView.class
                Intent myIntent = new Intent(DemoAndroidCrudActivity.this, SecondView.class);
                DemoAndroidCrudActivity.this.startActivity(myIntent);
                // TODO Auto-generated method stub
            }
        });
        
        // Listen for third view button click
        tvbutton.setOnClickListener(new OnClickListener() {
 
            public void onClick(View arg0) {
                // Open ThirdView.class
                Intent myIntent = new Intent(DemoAndroidCrudActivity.this, ThirdView.class);
                DemoAndroidCrudActivity.this.startActivity(myIntent);
                // TODO Auto-generated method stub
            }
        });
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.salam_dunia, menu);
        return true;
    }

}
