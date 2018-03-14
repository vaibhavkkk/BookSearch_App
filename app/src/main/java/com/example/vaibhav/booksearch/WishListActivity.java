package com.example.vaibhav.booksearch;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WishListActivity extends AppCompatActivity {

    public static final String TYPE_SEARCH="type_of_search";
    public static final String TYPE_BOOK="book";
    public static final String TYPE_AUTHOR="author";
    public static final String TYPE_TOP="top";
    public static final String TYPE_CATEGORY="category";
    public static final String TYPE_MAGAZINE="magazine";
    public static final String TYPE_WISHLIST="magazine";

    public static final String KEY_ID="id",KEY_SELFLINK="selfLink",KEY_TITLE="title",KEY_SUBTITLE="subtitle",KEY_AUTHORS="authors",KEY_PUBLISHER="publisher",KEY_DESCRIPTION="description",KEY_PAGECOUNT="pageCount",KEY_SMALLTHUMBNAIL="smallThumbnail",KEY_LARGATHUMBNAIL="largeThumbnail",KEY_LANGUAGE="language",
            KEY_PREVIEWLINK="previewLink",KEY_INFOLINK="infoLink",KEY_BUYLINK="buyLink",KEY_DOWNLOADLINK="downloadLink",KEY_WEBREADERLINK="webreaderLink",
            KEY_COUNTRY="country",KEY_SALEABILITY="saleability",KEY_CATEGORIES="categories",KEY_AVERAGERATING="averageRating",KEY_MATURITY="MATURITY",KEY_ISEBOOK="isEbook",KEY_PDFAVAILABLE="pdfAvailable",KEY_PUBLISHEDDATE="publishedDate";

    DatabaseHelper myDB;
    RecyclerView rvListBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        myDB=new DatabaseHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Wishlist");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

        ArrayList<Book> bookArrayList=myDB.getBooksStored();

        rvListBook=(RecyclerView)findViewById(R.id.listPosts);
        rvListBook.setLayoutManager(new LinearLayoutManager(this));

        final BookHomeAdapter bookHomeAdapter=new BookHomeAdapter(bookArrayList,WishListActivity.this);

        rvListBook.setAdapter(bookHomeAdapter);

    }
/*
    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate( R.menu.search_menu, menu);

        final MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        myActionMenuItem.setTitle("WISHLIST SEARCH");
        final android.support.v7.widget.SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(WishListActivity.this, "You searched :"+query, Toast.LENGTH_LONG).show();

                Book book=myDB.getBookItem(query);
                Intent i=new Intent(WishListActivity.this,SearchActivity.class);
                i.putExtra(KEY_ID,book.getId());
                i.putExtra(KEY_SELFLINK,book.getSelfLink());
                i.putExtra(KEY_TITLE,book.getTitle());
                i.putExtra(KEY_SUBTITLE,book.getSubTitle());
                i.putExtra(KEY_AUTHORS,book.getAuthors());
                i.putExtra(KEY_PUBLISHER,book.getPublisher());
                i.putExtra(KEY_DESCRIPTION,book.getDescription());
                i.putExtra(KEY_PAGECOUNT,book.getPageCount());
                i.putExtra(KEY_SMALLTHUMBNAIL,book.getSmallThumbnail());
                i.putExtra(KEY_LARGATHUMBNAIL,book.getLargeThumbnail());
                i.putExtra(KEY_LANGUAGE,book.getLanguage());
                i.putExtra(KEY_PREVIEWLINK,book.getPreviewLink());
                i.putExtra(KEY_INFOLINK,book.getInfoLink());
                i.putExtra(KEY_BUYLINK,book.getBuyLink());
                i.putExtra(KEY_DOWNLOADLINK,book.getDownloadLink());
                i.putExtra(KEY_WEBREADERLINK,book.getWebreaderLink());
                i.putExtra(KEY_COUNTRY,book.getCountry());
                i.putExtra(KEY_SALEABILITY,book.getSaleability());
                i.putExtra(KEY_CATEGORIES,book.getCategories());
                i.putExtra(KEY_AVERAGERATING,book.getAverageRating());
                i.putExtra(KEY_MATURITY,book.getMaturity());
                i.putExtra(KEY_ISEBOOK,book.getEbook());
                i.putExtra(KEY_PDFAVAILABLE,book.getPdfAvailable());
                i.putExtra(KEY_PUBLISHEDDATE,book.getPublishedDate());
                i.putExtra(TYPE_SEARCH,TYPE_WISHLIST);
                startActivity(i);

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
*/
    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
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
