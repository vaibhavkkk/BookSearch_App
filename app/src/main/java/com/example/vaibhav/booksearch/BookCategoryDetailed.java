package com.example.vaibhav.booksearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BookCategoryDetailed extends AppCompatActivity {

    String search="";
    RecyclerView rvListBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_category_detailed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

        search=getIntent().getStringExtra("SEARCH");
        getSupportActionBar().setTitle(search.toUpperCase());

        if(search.equals("science")){
            search="science%20fiction";
        }
        if (search.equals("short stories")){
            search="stories";
        }
        if (search.equals("western stories")){
            search="western%20story";
        }
        if (search.equals("friendship")){
            search="friend";
        }
        if (search.equals("voyages and travel")){
            search="voyages%20and%20travel";
        }if (search.equals("religion")){
            search="religions";
        }

        rvListBook = (RecyclerView) findViewById(R.id.listPosts);
        rvListBook.setLayoutManager(new LinearLayoutManager(this));
        final BookHomeAdapter bookHomeAdapter = new BookHomeAdapter(new ArrayList<Book>(), this);

        rvListBook.setAdapter(bookHomeAdapter);
        DownloadTask dTask = new DownloadTask(new DownloadTask.onDownloadListener() {
            @Override
            public void onDownloaded(ArrayList<Book> books) {
                bookHomeAdapter.updatePostList(books);
            }
        });

        dTask.execute(("https://www.googleapis.com/books/v1/volumes?q=subject:"+search));

    }
}
