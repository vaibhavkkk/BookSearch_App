package com.example.vaibhav.booksearch;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class BookHomeActivity extends AppCompatActivity {

    public static final String TYPE_SEARCH="type_of_search";
    public static final String TYPE_BOOK="book";
    public static final String TYPE_AUTHOR="author";
    public static final String TYPE_TOP="top";
    public static final String TYPE_CATEGORY="category";
    public static final String TYPE_MAGAZINE="magazine";

    Button btnDownload;
    RecyclerView rvListBook;
    String bookSearchType="";
    public static final String KEY_BOOK_SEARCH_TYPE="bookSearchType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_home);
        bookSearchType=getIntent().getStringExtra(KEY_BOOK_SEARCH_TYPE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(bookSearchType);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
        btnDownload= (Button) findViewById(R.id.btnDownload);
        rvListBook=(RecyclerView)findViewById(R.id.listPosts);
        rvListBook.setLayoutManager(new LinearLayoutManager(this));

        final BookHomeAdapter bookHomeAdapter=new BookHomeAdapter(new ArrayList<Book>(),BookHomeActivity.this);

        btnDownload.setVisibility(View.INVISIBLE);
        rvListBook.setAdapter(bookHomeAdapter);

                DownloadTask dTask = new DownloadTask(new DownloadTask.onDownloadListener() {
                    @Override
                    public void onDownloaded(ArrayList<Book> books) {
                        bookHomeAdapter.updatePostList(books);
                    }
                });

                if (bookSearchType.equals("books")) {
                    getSupportActionBar().setTitle("BOOKS");
                    dTask.execute("https://www.googleapis.com/books/v1/volumes?q=flowers&filter=free-ebooks");
                }else if(bookSearchType.equals("newest")){
                    dTask.execute(("https://www.googleapis.com/books/v1/volumes?q=subject:humor"));
                }

    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate( R.menu.search_menu, menu);

        final MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        final android.support.v7.widget.SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(BookHomeActivity.this, "You searched :"+query, Toast.LENGTH_LONG).show();
                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.callSettings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
