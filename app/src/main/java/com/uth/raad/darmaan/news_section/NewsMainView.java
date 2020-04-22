package com.uth.raad.darmaan.news_section;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uth.raad.darmaan.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class NewsMainView extends AppCompatActivity {
    private RecyclerView blogItems;

    private DatabaseReference db;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("خبرنامه طبی");
        setContentView(R.layout.news_main_view);
        db = FirebaseDatabase.getInstance().getReference().child("News_Box");

        blogItems = (RecyclerView) findViewById(R.id.blogList);
        blogItems.setHasFixedSize(true);
        blogItems.setLayoutManager(new LinearLayoutManager(this));

        Toolbar mToolbar = (Toolbar) findViewById(R.id.newsMainToolbar);
        setSupportActionBar(mToolbar);

    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                Blog.class,
                R.layout.blog_row,
                BlogViewHolder.class,
                db
        ) {


            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {
                //viewHolder.getItemId(position)
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setImage(getApplicationContext(), model.getImage());


            }
        };
        blogItems.setAdapter(firebaseRecyclerAdapter);
    }


    // start of blog_items list
    static TextView post_title;
    public static class BlogViewHolder extends RecyclerView.ViewHolder{
        View mView;

    public BlogViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
  }



    public void setTitle(String title){
         post_title = (TextView) mView.findViewById(R.id.post_title);
        post_title.setText(title);
    }
    public void setDescription(String description){
        TextView post_dsc = (TextView) mView.findViewById(R.id.post_dsc);
        post_dsc.setText(description);
    }
    public void setImage(final Context ctx, String image){
        final ImageView post_image = (ImageView) mView.findViewById(R.id.post_img);
        Picasso.with(ctx).load(image).into(post_image);
    }

    }



    // option bor top right cornor of screen codes
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.newsfeed_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //

    //TO ADD NEWS AT NEWS SECTION UNCOMMENT THIS CODES AND MENU ITEMS
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.addPost){
            startActivity(new Intent(NewsMainView.this, News_PostActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }


}
