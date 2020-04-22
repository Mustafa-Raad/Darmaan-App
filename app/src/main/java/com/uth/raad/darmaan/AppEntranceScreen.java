package com.uth.raad.darmaan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uth.raad.darmaan.guide_slider.DGMainActivity;

public class AppEntranceScreen extends Activity {
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    /** Called when the activity is first created. */
    Thread splashTread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        StartAnimations();


    }
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);


// start of animation for darmaan logo on very first screen 1st is for picture 2nd is for slogan text under picture
     anim = AnimationUtils.loadAnimation(this, R.anim.translate);
       anim.reset();
       ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);

        TextView tt = (TextView) findViewById(R.id.textSlogan);
        tt.clearAnimation();
        tt.startAnimation(anim);


        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 5000) {
                        sleep(100);
                        waited += 100;
                    }
                    // splash screen bring to Guide activity
                    Intent intent = new Intent(AppEntranceScreen.this,
                           DGMainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    AppEntranceScreen.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    AppEntranceScreen.this.finish();
                }

            }
        };
        splashTread.start();

    }

}