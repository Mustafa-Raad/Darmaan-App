package com.uth.raad.darmaan.map_section;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.uth.raad.darmaan.R;

public class Province_selection extends Activity implements View.OnClickListener {
    Button btn2, btn3, btn4, btn5, btn6;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.province_selection);

        btn2 = (Button) findViewById(R.id.balkh);
        btn3 = (Button) findViewById(R.id.kabul);
        btn4 = (Button) findViewById(R.id.herat);
        btn5 = (Button) findViewById(R.id.nangarhar);
        btn6 = (Button) findViewById(R.id.kandahar);

        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
   }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.balkh) {
            Intent i = new Intent(this, Balkh_Map_Section_Parts.class);
            startActivity(i);
        }
        if (v.getId() == R.id.kabul) {
//            Intent i = new Intent(this, Kabul_Map_Section_Parts.class);
//            startActivity(i);

            AlertDialog.Builder mBuilder = new AlertDialog.Builder(Province_selection.this, R.style.AlertDialogCustom);
            mBuilder.setIcon(R.mipmap.ic_about_app);
            mBuilder.setTitle(R.string.title_all_sections);
            mBuilder.setMessage(R.string.kabul_map );
            mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = mBuilder.create();
            alertDialog.show();
        }
        if (v.getId() == R.id.herat){
//            Intent i = new Intent(this, Herat_Map_Section_Parts.class);
//            startActivity(i);

            AlertDialog.Builder mBuilder = new AlertDialog.Builder(Province_selection.this, R.style.AlertDialogCustom);
            mBuilder.setIcon(R.mipmap.ic_about_app);
            mBuilder.setTitle(R.string.title_all_sections);
            mBuilder.setMessage(R.string.herat_map );
            mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = mBuilder.create();
            alertDialog.show();
        }
        if (v.getId() == R.id.nangarhar){
//            Intent i = new Intent(this, Nangarhar_Map_Section_Parts.class);
//            startActivity(i);

            AlertDialog.Builder mBuilder = new AlertDialog.Builder(Province_selection.this, R.style.AlertDialogCustom);
            mBuilder.setIcon(R.mipmap.ic_about_app);
            mBuilder.setTitle(R.string.title_all_sections);
            mBuilder.setMessage(R.string.nangarhar_map );
            mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = mBuilder.create();
            alertDialog.show();
        }
        if (v.getId() == R.id.kandahar){
//            Intent i = new Intent(this, Kandahar_Map_Section_Parts.class);
//            startActivity(i);

            AlertDialog.Builder mBuilder = new AlertDialog.Builder(Province_selection.this, R.style.AlertDialogCustom);
            mBuilder.setIcon(R.mipmap.ic_about_app);
            mBuilder.setTitle(R.string.title_all_sections);
            mBuilder.setMessage(R.string.kandahar_map );
            mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = mBuilder.create();
            alertDialog.show();
        }
    }

}
