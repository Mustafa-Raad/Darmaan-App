package com.uth.raad.darmaan.pregnency_section;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uth.raad.darmaan.R;

public class Pregnants_Week_Activity extends AppCompatActivity {

    private TextView tvtitle,tvdescription,tvcategory;
    private ImageView img;

    //details for text size incresing and decresing
    private MenuItem tInc, tDec;
    int txtSize = 60;

    //type face identifiers
    Typeface tf1, tf2, tf3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregnants_activity_week);

        //setting title for each week guide
        setTitle(getIntent().getExtras().getString("Title"));

        // top page action bar ability (toolbar)
        Toolbar mToolbar = (Toolbar) findViewById(R.id.prgnencyWeekToolbar);
        setSupportActionBar(mToolbar);

        // assigning text increser and decreser buttons
        tInc = (MenuItem) findViewById(R.id.zi);
        tDec = (MenuItem) findViewById(R.id.zo);

        //assigning the defined variable to local used variables
        tvtitle = (TextView) findViewById(R.id.txtTitle);
        tvcategory =(TextView) findViewById(R.id.txtCat);
        tvdescription =(TextView) findViewById(R.id.txtDesc);
        img = (ImageView) findViewById(R.id.bookthumbnail);

        //font style type face
        tf1 = Typeface.createFromAsset(getAssets(), "f1.ttf");
        tf2 = Typeface.createFromAsset(getAssets(), "f2.ttf");
        tf3 = Typeface.createFromAsset(getAssets(), "f3.ttf");

        //recive data
        Intent intent = getIntent();
        String Title =  intent.getExtras().getString("Title");
        String Category = intent.getExtras().getString("Category");
        String Description = intent.getExtras().getString("Description");
        int image = intent.getExtras().getInt("Thumbnail");

        //setting values
        tvtitle.setText(Title);
        tvcategory.setText(Category);
        tvdescription.setText(Description);
        img.setImageResource(image);

        //change description font style
        tvtitle.setTypeface(tf1);
        tvcategory.setTypeface(tf1);
        tvdescription.setTypeface(tf1);


    }
    //action bar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id==R.id.zi) {
            txtSize = txtSize + 5;
            tvdescription.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtSize);

            Toast.makeText(Pregnants_Week_Activity.this,"this is zoomin ",Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.zo){
            txtSize = txtSize - 5;
            tvdescription.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtSize);
            Toast.makeText(Pregnants_Week_Activity.this,"this is zoomout ",Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }

}
