package com.uth.raad.darmaan.pregnency_section;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.uth.raad.darmaan.R;

import java.util.ArrayList;
import java.util.List;

public class PregnantsGuide_MainActivity extends AppCompatActivity {

    List<Pregnants_Week> lstPregnantsWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregnants_guide_activity_main);
// title for cardbiew activity
        setTitle("راهنمای مراقبت از طفل و مادر");

// top page action bar ability (toolbar)
        Toolbar mToolbar = (Toolbar) findViewById(R.id.prgnencyMainToolbar);
        setSupportActionBar(mToolbar);

// card view contents
        lstPregnantsWeek = new ArrayList<>();
        lstPregnantsWeek.add(new Pregnants_Week("هفته اول","ماه اول",getString(R.string.week1),R.drawable.care_fig_1));
        lstPregnantsWeek.add(new Pregnants_Week("هفته دوم","ماه اول",getString(R.string.week2),R.drawable.care_fig_7));
        lstPregnantsWeek.add(new Pregnants_Week("هفته سوم","ماه اول",getString(R.string.week3),R.drawable.care_fig_3));
        lstPregnantsWeek.add(new Pregnants_Week("هفته چهارم","ماه اول",getString(R.string.week4),R.drawable.care_fig_4));
        lstPregnantsWeek.add(new Pregnants_Week("هفته پنجم","ماه اول",getString(R.string.week5),R.drawable.care_fig_6));
        lstPregnantsWeek.add(new Pregnants_Week("هفته ششم","ماه دوم",getString(R.string.week6),R.drawable.care_fig_1));
        lstPregnantsWeek.add(new Pregnants_Week("هفته هفتم","ماه دوم",getString(R.string.week7),R.drawable.care_fig_7));
        lstPregnantsWeek.add(new Pregnants_Week("هفته هشتم","ماه دوم",getString(R.string.week8),R.drawable.care_fig_3));
        lstPregnantsWeek.add(new Pregnants_Week("هفته نهم","ماه دوم",getString(R.string.week9),R.drawable.care_fig_4));
        lstPregnantsWeek.add(new Pregnants_Week("هفته دهم","ماه دوم",getString(R.string.week10),R.drawable.care_fig_6));
        lstPregnantsWeek.add(new Pregnants_Week("هفته یازدهم","ماه سوم",getString(R.string.week11),R.drawable.care_fig_1));
        lstPregnantsWeek.add(new Pregnants_Week("هفته دوازدهم","ماه سوم",getString(R.string.week12),R.drawable.care_fig_7));
        lstPregnantsWeek.add(new Pregnants_Week("هفته سیزدهم","ماه سوم",getString(R.string.week13),R.drawable.care_fig_3));
        lstPregnantsWeek.add(new Pregnants_Week("هفته چهاردهم","ماه سوم",getString(R.string.week14),R.drawable.care_fig_4));
        lstPregnantsWeek.add(new Pregnants_Week("هفته پانزدهم","ماه سوم",getString(R.string.week15),R.drawable.care_fig_6));
        lstPregnantsWeek.add(new Pregnants_Week("هفته شانزدهم","ماه چهارم",getString(R.string.week16),R.drawable.care_fig_1));
        lstPregnantsWeek.add(new Pregnants_Week("هفته هفدهم","ماه چهارم",getString(R.string.week17),R.drawable.care_fig_7));
        lstPregnantsWeek.add(new Pregnants_Week("هفته هژدهم","ماه چهارم",getString(R.string.week18),R.drawable.care_fig_3));
        lstPregnantsWeek.add(new Pregnants_Week("هفته نزدهم","ماه چهارم",getString(R.string.week19),R.drawable.care_fig_4));
        lstPregnantsWeek.add(new Pregnants_Week("هفته بیستم","ماه چهارم",getString(R.string.week20),R.drawable.care_fig_6));
        lstPregnantsWeek.add(new Pregnants_Week("هفته بیست ویکم","ماه پنجم",getString(R.string.week21),R.drawable.care_fig_1));
        lstPregnantsWeek.add(new Pregnants_Week("هفته بیست ودوم","ماه پنجم",getString(R.string.week22),R.drawable.care_fig_7));
        lstPregnantsWeek.add(new Pregnants_Week("هفته بیست وسوم","ماه پنجم",getString(R.string.week23),R.drawable.care_fig_3));
        lstPregnantsWeek.add(new Pregnants_Week("هفته بیست وچهارم","ماه پنجم",getString(R.string.week24),R.drawable.care_fig_4));
        lstPregnantsWeek.add(new Pregnants_Week("هفته بیست وپنجم","ماه پنجم",getString(R.string.week25),R.drawable.care_fig_6));
        lstPregnantsWeek.add(new Pregnants_Week("هفته بیست وششم","ماه ششم",getString(R.string.week26),R.drawable.care_fig_1));
        lstPregnantsWeek.add(new Pregnants_Week("هفته بیست وهفتم","ماه ششم",getString(R.string.week27),R.drawable.care_fig_7));
        lstPregnantsWeek.add(new Pregnants_Week("هفته بیست وهشتم","ماه ششم",getString(R.string.week28),R.drawable.care_fig_3));
        lstPregnantsWeek.add(new Pregnants_Week("هفته بیست ونهم","ماه ششم",getString(R.string.week29),R.drawable.care_fig_4));
        lstPregnantsWeek.add(new Pregnants_Week("هفته سی ام","ماه ششم",getString(R.string.week30),R.drawable.care_fig_5));
        lstPregnantsWeek.add(new Pregnants_Week("هفته سی ویکم","ماه هفتم",getString(R.string.week31),R.drawable.care_fig_6));
        lstPregnantsWeek.add(new Pregnants_Week("هفته سی ودوم","ماه هفتم",getString(R.string.week32),R.drawable.care_fig_7));
        lstPregnantsWeek.add(new Pregnants_Week("هفته سی وسوم","ماه هفتم",getString(R.string.week33),R.drawable.care_fig_1));
        lstPregnantsWeek.add(new Pregnants_Week("هفته سی وچهارم","ماه هفتم",getString(R.string.week34),R.drawable.care_fig_4));
        lstPregnantsWeek.add(new Pregnants_Week("هفته سی وپنجم","ماه هفتم",getString(R.string.week35),R.drawable.care_fig_7));
        lstPregnantsWeek.add(new Pregnants_Week("هفته سی وششم","ماه هشتم",getString(R.string.week36),R.drawable.care_fig_6));
        lstPregnantsWeek.add(new Pregnants_Week("هفته سی وهفتم","ماه هشتم",getString(R.string.week37),R.drawable.care_fig_5));
        lstPregnantsWeek.add(new Pregnants_Week("هفته سی وهشتم","ماه هشتم",getString(R.string.week38),R.drawable.care_fig_3));
        lstPregnantsWeek.add(new Pregnants_Week("هفته سی ونهم","ماه هشتم",getString(R.string.week39),R.drawable.care_fig_1));
        lstPregnantsWeek.add(new Pregnants_Week("هفته چهلم","ماه هشتم",getString(R.string.week40),R.drawable.care_fig_6));
        lstPregnantsWeek.add(new Pregnants_Week("هفته چهل ویکم","ماه نهم",getString(R.string.week41),R.drawable.care_fig_8));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        PregnantsGuide_RecyclerViewAdapter myAdapter = new PregnantsGuide_RecyclerViewAdapter(this, lstPregnantsWeek);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.info){
            AlertDialog.Builder mBulider  = new AlertDialog.Builder(PregnantsGuide_MainActivity.this, R.style.AlertDialogCustom);
            mBulider.setIcon(R.mipmap.ic_info3);
            mBulider.setTitle(getString(R.string.info_title));
            mBulider.setMessage(getString(R.string.info));

            mBulider.setPositiveButton("OK", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = mBulider.create();
            alertDialog.show();


        }
        return super.onOptionsItemSelected(item);
    }
}
