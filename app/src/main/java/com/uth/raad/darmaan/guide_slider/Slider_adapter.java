package com.uth.raad.darmaan.guide_slider;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uth.raad.darmaan.R;

/**
 * Created by raad on 6/9/2018.
 */

public class Slider_adapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public Slider_adapter(Context context){
        this.context = context;
    }

    // arrays to store our slider data
    public int[] slide_images = {
            R.mipmap.ic_encyclopedia_nice,
            R.mipmap.ic_news_nice,
            R.mipmap.ic_pregnency_nice,
            R.mipmap.ic_map_nice,
            R.mipmap.ic_profile_nice,
            R.mipmap.darmaan1
    };

    public String[] slide_headings = {
            "دایره المعارف طبی",
            "خبرنامه طبی",
            "مراقبت های طفل و مادر",
            "نقشه طبی",
            "اسناد طبی",

            "خوش آمدید"
    };

    public String[] slide_descs = {
            "شما با وارد شدن در این بخش با یک مجموعه ای عظیم از امراض، ویروس ها و تهدید های صحی مواجه میشوید که با باز نمودن هر کدام برای تان توضیح داده میشود، و راه های پیشگیری و درمانی آن نیز بیان گردیده است. \n" +
                    "این بخش یک منبع معلومات دهنده خیلی موثر برای همه مردم میباشند تا درباره این تهدیدات صحی معلومات کامل پیدا نموده و محیط ما حول خویش را پاک نگهدارند و اگر احیاناٌ آلوده گردیدن راه حل های درمانی آنرا پیدا نموده و دوباره صحت یاب گردند.\n",


            "همه روزه در کشور عزیزمان و جهان اتفاقات خوب و خرابی طبی واقع میگردد که به ندرت به سمع و نظر همگان میرسد, بدین منظور همه استفاده کننده های برنامه درمان میتوانند از این بخش استفاده نموده و درباره آخرین رویداد های طبی منطقه و جهان باخبر گردند. \n" +
                    "این بخش بصورت آنلاین (با انترنت) فعالیت مینماید\n",


            "این بخش برای جلوگیری از تلف شدن طفل های تازه مادران آماده گردیده, که معلومات های مراقبتی دوران حاملگی در آن قرار گرفته است. تازه مادران که میخواهند طفل شان خوب رشد نموده و از یک تغذیه سالم در دوران حمل شان برخوردار باشند از این بخش خیلی عالی استفاده نموده میتوانند.\n" +
                    "هدف درمان از این بخش کاهش دادن سطح مرگ و میر تازه مادران و طفل شان میباشد.\n",

            "همه مردم برای یافتن محل های درمانی یک شهر مشکلات را دارند که نمیتواند به آسانی یک مرکز درمانی مشخص را پیدا نمایند، و این موضوع اکثر اوقات باعث اتفاق افتادن خطر های جبران ناپذیری برای مردم عزیزمان گردیده.\n" +
                    "حالا با استفاده از تکنالوژی درمان این مشکل را حل نموده و برای استفاده کننده های خود، نزذیک ترین آدرس از مکان صحی(داروخانه, داکتر, شفاخانه و کلینک) مورد نظرشان را میدهد.\n" +
                    "این بخش نیز با استفاده از انترنت فعالیت نموده و با یکبار اپدیت نمودن دیگر آفلاین نیز استفاده میتوانید\n",

            "از این بخش همه استفاده کننده های درمان میتوانند معلومات بیشتر درباره داکتران, شفاخانه ها و کلینک ها دریابند, معلومات از قبیل سابقه کاری, درجه های تحصیلی و میزان رضایت مراجعین از این مراکز صحی و داکتران. در پهلوی این موارد، استفاده کننده های درمان میتوانند با استفاده از این بخش وقت ملاقات (ویزیت) از داکتران مورد نظر شان بصورت آنلاین بگیرند\n" +
                    "نوت: این بخش در نسخه( ورژن) بعدی این اپلیکشن درمان اضافه میگردد\n",

            " "
    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slidDesc = (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slidDesc.setText(slide_descs[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
