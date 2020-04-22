package com.uth.raad.darmaan.encyclopedia_section;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.uth.raad.darmaan.R;

import java.util.HashMap;
import java.util.Map;

public class Encyclopedia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Toolbar mToolbar;
        TextView mText;
        EditText fullText;
        Map<String, String> myData = new HashMap<>();

        myData.put("آسم", getString(R.string.Asm_Content));
        myData.put("اسهال مسافران", getString(R.string.eshal_mosferan));
        myData.put("آفتاب سوختگی", getString(R.string.aftab_sokhtagi));
        myData.put("آکنه", getString(R.string.akna));
        myData.put("آنفولانزای مرغی", getString(R.string.enflonza_morghi));
        myData.put("اوتیسم", getString(R.string.otism));
        myData.put("بی خوابی", getString(R.string.bi_khobi));
        myData.put("پارگی پرده گوش", getString(R.string.paragi_parda_gosh));
        myData.put("تب خال", getString(R.string.tab_khal));
        myData.put("تومور مغزی", getString(R.string.tomor_maghzi));

        myData.put("دندان درد",getString(R.string.dandan_drd));
        myData.put("سیاه زخم",getString(R.string.siah_zkhm));
        myData.put("سیاه سرفه",getString(R.string.siah_sorfa));
        myData.put("دیابت نوع 1",getString(R.string.diabt1));
        myData.put("دیابت نوع 2",getString(R.string.diabt2));
        myData.put("سرخک",getString(R.string.sorkhak));
        myData.put("سرطان بیضه",getString(R.string.sartan_baiza));
        myData.put("سرطان پروستات",getString(R.string.sartan_prostat));
        myData.put("سرما خوردگی (ریزش)",getString(R.string.sarma_khordgi));
        myData.put("سرما زذگی(سینه و بغل)",getString(R.string.sarma_zadgi));
        myData.put("سل",getString(R.string.sel));
        myData.put("سنگ کیسه صفرا",getString(R.string.sang_safora));
        myData.put("سنگ کلیه",getString(R.string.sang_kolia));
        myData.put("سوء هاضمه",getString(R.string.so_hazima));
        myData.put("سوء تغذیه",getString(R.string.so_taghzya));
        myData.put("سوزاک",getString(R.string.sozak));
        myData.put("شپش ها",getString(R.string.shepeshHa));
        myData.put("شوره سر",getString(R.string.shori_sar));
        myData.put("طاعون",getString(R.string.taoon));
        myData.put("طاسی مردانه",getString(R.string.tasiMardana));
        myData.put("صافی کف پا",getString(R.string.safi_kaf_pa));
        myData.put("کابوس(خواب خراب)",getString(R.string.kabus));
        myData.put("کمبود ویتامین B12",getString(R.string.kmbud_b));
        myData.put("کمبود ویتامین(اسکوروی C)",getString(R.string.kmbud_c));
        myData.put("کم خونی",getString(R.string.kmKhoni));
        myData.put("کور رنگی",getString(R.string.korangi));
        myData.put("گاز(نفخ معده)",getString(R.string.gaz));
        myData.put("گزیدن حشرات",getString(R.string.gzeshHashrat));
        myData.put("ملاریا",getString(R.string.malarya));
        myData.put("مسمومیت",getString(R.string.masmomyet));
        myData.put("نقرس",getString(R.string.noqrs));
        myData.put("هپاتیت A",getString(R.string.hepatyt_A));
        myData.put("هپاتیت B",getString(R.string.hepatyt_b));
        myData.put("هپاتیت C",getString(R.string.hepatyt_c));
        myData.put("وبا",getString(R.string.wba));



        super.onCreate(savedInstanceState);
        setContentView(R.layout.encyclopedia);

        mToolbar = (Toolbar) findViewById(R.id.toolbar2);
//        fullText = (EditText) findViewById(R.id.content);
        mText = (TextView) findViewById(R.id.textView);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            mToolbar.setTitle(bundle.getString(String.valueOf("ItemsName")));

            String desc = bundle.getString(String.valueOf("ItemsName"));

            mText.setText(myData.get(desc));
        }
    }
}
