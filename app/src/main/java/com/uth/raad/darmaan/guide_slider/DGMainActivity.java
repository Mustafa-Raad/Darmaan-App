package com.uth.raad.darmaan.guide_slider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uth.raad.darmaan.HomeActivity;
import com.uth.raad.darmaan.R;

public class DGMainActivity extends AppCompatActivity {

    private ViewPager pager;
    private LinearLayout mLayout;

    // buttons for next and previous
    private Button mBtnNext;
    private Button mBtnPrevious;

    int mCurrent;

    //text view for dots of buttom place
    private TextView[] mDots;

    //slider adapter
    private Slider_adapter slider_adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dgmain);

        pager = (ViewPager) findViewById(R.id.slideViewPager);
        mLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        mBtnNext = (Button) findViewById(R.id.nextBtn);
        mBtnPrevious = (Button) findViewById(R.id.previousBtn);

        slider_adapter = new Slider_adapter(this);
        pager.setAdapter(slider_adapter);

        addDotsIndicator(0);

        pager.addOnPageChangeListener(viewListener);

// next and back button on page listener method
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pager.setCurrentItem(mCurrent+1);

                   if(mBtnNext.getText().toString()=="Finish" ){
                        Intent intent = new Intent(DGMainActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
        });
// back button on page listener
        mBtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pager.setCurrentItem(mCurrent-1);
            }
        });
    }
// add 3 dots indicator for page recogrnation (methods below)
    public void addDotsIndicator(int ii){
        mDots = new TextView[6];
        mLayout.removeAllViews();

        for (int i=0; i<mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.whiteYellow));

            mLayout.addView(mDots[i]);
        }

        if (mDots.length > 0){
            mDots[ii].setTextColor(getResources().getColor(R.color.dialog_back));
        }
    }

// view pager methods
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);

            mCurrent = position;

            if(position == 0){
                mBtnNext.setEnabled(true);
                mBtnPrevious.setEnabled(false);
                mBtnPrevious.setVisibility(View.INVISIBLE);

                mBtnNext.setText("Next");
                mBtnPrevious.setText("");

            }else if(position == mDots.length-1){
                mBtnNext.setEnabled(true);
                mBtnPrevious.setEnabled(true);
                mBtnPrevious.setVisibility(View.VISIBLE);

                mBtnNext.setText("Finish");
                mBtnPrevious.setText("Back");
            }else{
                mBtnNext.setEnabled(true);
                mBtnPrevious.setEnabled(true);
                mBtnPrevious.setVisibility(View.VISIBLE);

                mBtnNext.setText("Next");
                mBtnPrevious.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
