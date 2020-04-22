package com.uth.raad.darmaan.encyclopedia_section;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.uth.raad.darmaan.R;

import java.util.ArrayList;

public class WikiMainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    public ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setTitle("دایره المعارف درمان");

        setContentView(R.layout.activity_wiki_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.list);

        items = new ArrayList<>();

        items.add("آسم");
        items.add("اسهال مسافران");
        items.add("آفتاب سوختگی");
        items.add("آکنه");
        items.add("آنفولانزای مرغی");
        items.add("اوتیسم");
        items.add("بی خوابی");
        items.add("پارگی پرده گوش");
        items.add("تب خال");
        items.add("تومور مغزی");

        items.add("دندان درد");
        items.add("سیاه زخم");
        items.add("سیاه سرفه");
        items.add("دیابت نوع 1");
        items.add("دیابت نوع 2");
        items.add("سرخک");
        items.add("سرطان بیضه");
        items.add("سرطان پروستات");
        items.add("سرما خوردگی (ریزش)");
        items.add("سرما زذگی(سینه و بغل)");
        items.add("سل");
        items.add("سنگ کیسه صفرا");
        items.add("سنگ کلیه");
        items.add("سوء هاضمه");
        items.add("سوء تغذیه");
        items.add("سوزاک");
        items.add("شپش ها");
        items.add("شوره سر");
        items.add("طاعون");
        items.add("طاسی مردانه");
        items.add("صافی کف پا");
        items.add("کابوس(خواب خراب)");
        items.add("کمبود ویتامین B12");
        items.add("کمبود ویتامین(اسکوروی C)");
        items.add("کم خونی");

        items.add("کور رنگی");
        items.add("گاز(نفخ معده)");
        items.add("گزیدن حشرات");
        items.add("ملاریا");
        items.add("مسمومیت");
        items.add("نقرس");
        items.add("هپاتیت A");
        items.add("هپاتیت B");
        items.add("هپاتیت C");
        items.add("وبا");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(WikiMainActivity.this, Encyclopedia.class);
                intent.putExtra("ItemsName",listView.getItemAtPosition(position).toString());

                Toast.makeText(getApplicationContext(),  listView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();

                startActivity(intent);
                //String text = listView.getItemAtPosition(position).toString();
                //Toast.makeText(WikiMainActivity.this, "" + text, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search_item);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> templist = new ArrayList<>();

                for (String temp : items){
                    if(temp.toLowerCase().contains(newText.toLowerCase())){
                        templist.add(temp);
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(WikiMainActivity.this,
                        android.R.layout.simple_list_item_1, templist);
                listView.setAdapter(adapter);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


}
