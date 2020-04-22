package com.uth.raad.darmaan;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.uth.raad.darmaan.encyclopedia_section.WikiMainActivity;
import com.uth.raad.darmaan.map_section.Province_selection;
import com.uth.raad.darmaan.news_section.NewsMainView;
import com.uth.raad.darmaan.pregnency_section.PregnantsGuide_MainActivity;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class HomeActivity extends AppCompatActivity {

    CircleMenu circleMenu;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);

        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.home_icon, R.drawable.icon_cancel);
        circleMenu.addSubMenu(Color.parseColor("#258CFF"), R.mipmap.ic_ic_profile)
                .addSubMenu(Color.parseColor("#f47b18"), R.mipmap.ic_ic_encyclopedia)
                .addSubMenu(Color.parseColor("#FF4B32"), R.mipmap.ic_ic_pregnency)
                .addSubMenu(Color.parseColor("#8A39FF"), R.mipmap.ic_ic_info)
                .addSubMenu(Color.parseColor("#FF6A00"), R.mipmap.ic_ic_map1)
                .addSubMenu(Color.parseColor("#30A400"), R.mipmap.ic_ic_news3);
        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {

             @Override
             public void onMenuSelected(int index) {
                 switch (index) {
                     case 0:
                         //Toast.makeText(HomeActivity.this, "Home icon blue", Toast.LENGTH_SHORT).show();
                         new Handler().postDelayed(new Runnable() {
                             @Override
                             public void run() {
                                 AlertDialog.Builder mBuilder = new AlertDialog.Builder(HomeActivity.this, R.style.AlertDialogCustom);
                                 mBuilder.setIcon(R.mipmap.ic_about_app);
                                 mBuilder.setTitle(R.string.profile_section_guide_title);
                                 mBuilder.setMessage(R.string.profile_section_guide_text );
                                 mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){

                                     @Override
                                     public void onClick(DialogInterface dialog, int which) {
                                         dialog.dismiss();
                                     }
                                 });
                                 AlertDialog alertDialog = mBuilder.create();
                                 alertDialog.show();

                             }
                         },600);
                         break;

                     // Encyclopedia
                     case 1:
                         //Toast.makeText(HomeActivity.this, "welcome to Darmaan Encyclopedia", Toast.LENGTH_SHORT).show();
                         new Handler().postDelayed(new Runnable() {
                             @Override
                             public void run() {
                                 final Intent mainIntent = new Intent(HomeActivity.this, WikiMainActivity.class);
                                 HomeActivity.this.startActivity(mainIntent);
                                 //HomeActivity.this.finish();
                             }
                         },600);
                         break;

                     //
                     case 2:
                         //Toast.makeText(HomeActivity.this, "Pregnency section", Toast.LENGTH_SHORT).show();
                         new Handler().postDelayed(new Runnable() {
                             @Override
                             public void run() {
                                 final Intent mainIntent = new Intent(HomeActivity.this, PregnantsGuide_MainActivity.class);
                                 HomeActivity.this.startActivity(mainIntent);
                                 //HomeActivity.this.finish();
                             }
                         },600);
                         break;

                     case 3:
                         // setting section used for about app detail

                         new Handler().postDelayed(new Runnable() {
                             @Override
                             public void run() {
                                 AlertDialog.Builder mBuilder = new AlertDialog.Builder(HomeActivity.this, R.style.AlertDialogCustom);
                                 mBuilder.setIcon(R.mipmap.ic_about_app);
                                 mBuilder.setTitle(R.string.about_app_title);
                                 mBuilder.setMessage(R.string.about_app_text );
                                 mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){

                                     @Override
                                     public void onClick(DialogInterface dialog, int which) {
                                         dialog.dismiss();
                                     }
                                 });
                                 AlertDialog alertDialog = mBuilder.create();
                                 alertDialog.show();

                             }
                         },600);

                         //Toast.makeText(HomeActivity.this, "setting icon purple", Toast.LENGTH_SHORT).show();
                         break;

                     case 4:
                         //Toast.makeText(HomeActivity.this, "Welcome to Map", Toast.LENGTH_SHORT).show();
                         new Handler().postDelayed(new Runnable() {
                             @Override
                             public void run() {
                                 final Intent mainIntent = new Intent(HomeActivity.this, Province_selection.class);
                                 HomeActivity.this.startActivity(mainIntent);
                                 //HomeActivity.this.finish();
                             }
                         },600);
                         break;

                     case 5:
                         //Toast.makeText(HomeActivity.this, "Welcome to News Feed", Toast.LENGTH_SHORT).show();
                         new Handler().postDelayed(new Runnable() {
                             @Override
                             public void run() {
                                 final Intent mainIntent = new Intent(HomeActivity.this, NewsMainView.class);
                                 HomeActivity.this.startActivity(mainIntent);
                                 //HomeActivity.this.finish();
                             }
                         },600);
                         break;
                 }
             }
        }
        );
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    circleMenu.openMenu();

            }
        },2000);
    }

    @Override
    public void onBackPressed() {

        if(circleMenu.isOpened())
            circleMenu.closeMenu();
        else
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }



}
