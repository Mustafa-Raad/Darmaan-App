package com.uth.raad.darmaan.map_section;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.uth.raad.darmaan.R;

public class Balkh_Map_Section_Parts extends Activity implements View.OnClickListener {
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balkh_map_section_parts);

        btn2 = (Button) findViewById(R.id.doctor);
        btn3 = (Button) findViewById(R.id.drugstore);
        btn4 = (Button) findViewById(R.id.hospital);
        btn5 = (Button) findViewById(R.id.clinics);

        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
   }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.doctor) {
            Intent i = new Intent(this, Balkh_Doctors_Map_Locator.class);
            startActivity(i);
        }
        if (v.getId() == R.id.drugstore) {
            Intent i = new Intent(this, Balkh_Drugstores_Map_Locator.class);
            startActivity(i);
        }
        if (v.getId() == R.id.hospital){
            Intent i = new Intent(this, Balkh_Hospitals_Map_Locator.class);
            startActivity(i);
        }
        if (v.getId() == R.id.clinics){
            Intent i = new Intent(this, Balkh_Clinics_Map_Locator.class);
            startActivity(i);
        }
    }

}
