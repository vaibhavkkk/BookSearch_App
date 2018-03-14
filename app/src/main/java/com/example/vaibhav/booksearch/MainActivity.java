package com.example.vaibhav.booksearch;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    RecyclerView rvListBook;
    LinearLayout gallery1,gallery2,gallery3,gallery4;
    public static final String KEY_BOOK_SEARCH_TYPE="bookSearchType";
    public static final String TYPE_SEARCH="type_of_search";
    public static final String TYPE_BOOK="book";
    public static final String TYPE_AUTHOR="author";
    public static final String TYPE_TOP="top";
    public static final String TYPE_CATEGORY="category";
    public static final String TYPE_MAGAZINE="magazine";
    public static final String TYPE_WISHLIST="wishlist";
    public static final String TYPE_ORDER="order";
    public static final String TYPE_FILTER="free-ebooks";
    public static final String KEY_ID="id",KEY_SELFLINK="selfLink",KEY_TITLE="title",KEY_SUBTITLE="subtitle",KEY_AUTHORS="authors",KEY_PUBLISHER="publisher",KEY_DESCRIPTION="description",KEY_PAGECOUNT="pageCount",KEY_SMALLTHUMBNAIL="smallThumbnail",KEY_LARGATHUMBNAIL="largeThumbnail",KEY_LANGUAGE="language",
            KEY_PREVIEWLINK="previewLink",KEY_INFOLINK="infoLink",KEY_BUYLINK="buyLink",KEY_DOWNLOADLINK="downloadLink",KEY_WEBREADERLINK="webreaderLink",
            KEY_COUNTRY="country",KEY_SALEABILITY="saleability",KEY_CATEGORIES="categories",KEY_AVERAGERATING="averageRating",KEY_MATURITY="MATURITY",KEY_ISEBOOK="isEbook",KEY_PDFAVAILABLE="pdfAvailable",KEY_PUBLISHEDDATE="publishedDate";

    TextView classicMore,ebooksMore,latestMore,magazineMore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        View x = LayoutInflater.from(getBaseContext()).inflate(R.layout.navigation_drawer_header, navigationView);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);

        assert drawerLayout != null;
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.id_home:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.id_wishlist:
                        item.setChecked(true);
                        Intent intent = new Intent(MainActivity.this, WishListActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.id_settings:
                        item.setChecked(true);
                        intent = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.search_store:
                        intent = new Intent(MainActivity.this, BookCategoryActivity.class);
//                        intent.putExtra(KEY_BOOK_SEARCH_TYPE,"newest");
                        startActivity(intent);
                        item.setChecked(true);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        finish();
                        break;
                    case R.id.search_relevance:
                        item.setChecked(true);
                        intent = new Intent(MainActivity.this, SearchActivity.class);
                        intent.putExtra(TYPE_SEARCH, TYPE_ORDER);
                        intent.putExtra("SEARCH", "relevance");
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.search_latest:
                        item.setChecked(true);
                        //item.setChecked(true);
                        intent = new Intent(MainActivity.this, SearchActivity.class);
                        intent.putExtra(TYPE_SEARCH, TYPE_ORDER);
                        intent.putExtra("SEARCH", "newest");
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.search_books:
                        item.setChecked(true);
                        //item.setChecked(true);
                        intent = new Intent(MainActivity.this, SearchActivity.class);
                        intent.putExtra(TYPE_SEARCH, TYPE_BOOK);
                        intent.putExtra("SEARCH", "book");
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.search_magazine:
                        item.setChecked(true);
                        //item.setChecked(true);
                        intent = new Intent(MainActivity.this, SearchActivity.class);
                        intent.putExtra(TYPE_SEARCH, TYPE_MAGAZINE);
                        intent.putExtra("SEARCH", "magazine");
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.search_adventure:
                        item.setChecked(true);
                        //item.setChecked(true);
                        intent = new Intent(MainActivity.this, SearchActivity.class);
                        intent.putExtra(TYPE_SEARCH, TYPE_CATEGORY);
                        intent.putExtra("SEARCH", "adventure");
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.search_humor:
                        item.setChecked(true);
                        //item.setChecked(true);
                        intent = new Intent(MainActivity.this, SearchActivity.class);
                        intent.putExtra(TYPE_SEARCH, TYPE_CATEGORY);
                        intent.putExtra("SEARCH", "humor");
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.search_literature:
                        item.setChecked(true);
                        //item.setChecked(true);
                        intent = new Intent(MainActivity.this, SearchActivity.class);
                        intent.putExtra(TYPE_SEARCH, TYPE_CATEGORY);
                        intent.putExtra("SEARCH", "literature");
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.search_technical:
                        item.setChecked(true);
                        //item.setChecked(true);
                        intent = new Intent(MainActivity.this, SearchActivity.class);
                        intent.putExtra(TYPE_SEARCH, TYPE_CATEGORY);
                        intent.putExtra("SEARCH", "technical");
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.search_nature:
                        item.setChecked(true);
                        //item.setChecked(true);
                        intent = new Intent(MainActivity.this, SearchActivity.class);
                        intent.putExtra(TYPE_SEARCH, TYPE_CATEGORY);
                        intent.putExtra("SEARCH", "nature");
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });

        DownloadTask dTask = new DownloadTask(new DownloadTask.onDownloadListener() {
            @Override
            public void onDownloaded(ArrayList<Book> books) {
                for (int i = 0; i < books.size(); i++) {
                    LayoutInflater mInflater;

                    mInflater = LayoutInflater.from(MainActivity.this);
                    View view = mInflater.inflate(R.layout.book_item_view_home_layout,
                            gallery1, false);
                    ImageView img = (ImageView) view
                            .findViewById(R.id.home_item_image);
                    TextView title = (TextView) view.findViewById(R.id.home_item_title);
                    TextView author = (TextView) view.findViewById(R.id.home_item_authors);
                    TextView category = (TextView) view.findViewById(R.id.home_item_category);
                    title.setText(books.get(i).getTitle());
                    author.setText(books.get(i).getAuthors());
                    category.setText(books.get(i).getTitle());
                    String bookImageUrl = books.get(i).getSmallThumbnail();
                    Picasso.with(MainActivity.this).load(bookImageUrl).into(img);
                    gallery1.addView(view);
                }
            }
        });
        dTask.execute(("https://www.googleapis.com/books/v1/volumes?q=subject:classic"));

        DownloadTask dTask2 = new DownloadTask(new DownloadTask.onDownloadListener() {
            @Override
            public void onDownloaded(ArrayList<Book> books) {
                for (int i = 0; i < books.size(); i++) {
                    LayoutInflater mInflater;

                    mInflater = LayoutInflater.from(MainActivity.this);
                    View view = mInflater.inflate(R.layout.book_item_view_home_layout,
                            gallery2, false);
                    ImageView img = (ImageView) view
                            .findViewById(R.id.home_item_image);
                    TextView title = (TextView) view.findViewById(R.id.home_item_title);
                    TextView author = (TextView) view.findViewById(R.id.home_item_authors);
                    TextView category = (TextView) view.findViewById(R.id.home_item_category);
                    title.setText(books.get(i).getTitle());
                    author.setText(books.get(i).getAuthors());
                    category.setText(books.get(i).getTitle());
                    String bookImageUrl = books.get(i).getSmallThumbnail();
                    Picasso.with(MainActivity.this).load(bookImageUrl).into(img);
                    gallery2.addView(view);
                }
            }
        });
        dTask2.execute(("https://www.googleapis.com/books/v1/volumes?q=books&filter=free-ebooks"));

        DownloadTask dTask3 = new DownloadTask(new DownloadTask.onDownloadListener() {
            @Override
            public void onDownloaded(ArrayList<Book> books) {
                for (int i = 0; i < books.size(); i++) {
                    LayoutInflater mInflater;

                    mInflater = LayoutInflater.from(MainActivity.this);
                    View view = mInflater.inflate(R.layout.book_item_view_home_layout,
                            gallery3, false);
                    ImageView img = (ImageView) view
                            .findViewById(R.id.home_item_image);
                    TextView title = (TextView) view.findViewById(R.id.home_item_title);
                    TextView author = (TextView) view.findViewById(R.id.home_item_authors);
                    TextView category = (TextView) view.findViewById(R.id.home_item_category);
                    title.setText(books.get(i).getTitle());
                    author.setText(books.get(i).getAuthors());
                    category.setText(books.get(i).getTitle());
                    String bookImageUrl = books.get(i).getSmallThumbnail();
                    Picasso.with(MainActivity.this).load(bookImageUrl).into(img);
                    final Book thisBook = books.get(i);
                    gallery3.addView(view);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(MainActivity.this, "OnCLick Called", Toast.LENGTH_LONG).show();
                            /*Intent i = new Intent(MainActivity.this, BookHomeDetailsActivity.class);
                            i.putExtra(KEY_ID, thisBook.getId());
                            i.putExtra(KEY_SELFLINK, thisBook.getSelfLink());
                            i.putExtra(KEY_TITLE, thisBook.getTitle());
                            i.putExtra(KEY_SUBTITLE, thisBook.getSubTitle());
                            i.putExtra(KEY_AUTHORS, thisBook.getAuthors());
                            i.putExtra(KEY_PUBLISHER, thisBook.getPublisher());
                            i.putExtra(KEY_DESCRIPTION, thisBook.getDescription());
                            i.putExtra(KEY_PAGECOUNT, thisBook.getPageCount());
                            i.putExtra(KEY_SMALLTHUMBNAIL, thisBook.getSmallThumbnail());
                            i.putExtra(KEY_LARGATHUMBNAIL, thisBook.getLargeThumbnail());
                            i.putExtra(KEY_LANGUAGE, thisBook.getLanguage());
                            i.putExtra(KEY_PREVIEWLINK, thisBook.getPreviewLink());
                            i.putExtra(KEY_INFOLINK, thisBook.getInfoLink());
                            i.putExtra(KEY_BUYLINK, thisBook.getBuyLink());
                            i.putExtra(KEY_DOWNLOADLINK, thisBook.getDownloadLink());
                            i.putExtra(KEY_WEBREADERLINK, thisBook.getWebreaderLink());
                            i.putExtra(KEY_COUNTRY, thisBook.getCountry());
                            i.putExtra(KEY_SALEABILITY, thisBook.getSaleability());
                            i.putExtra(KEY_CATEGORIES, thisBook.getCategories());
                            i.putExtra(KEY_AVERAGERATING, thisBook.getAverageRating());
                            i.putExtra(KEY_MATURITY, thisBook.getMaturity());
                            i.putExtra(KEY_ISEBOOK, thisBook.getEbook());
                            i.putExtra(KEY_PDFAVAILABLE, thisBook.getPdfAvailable());
                            i.putExtra(KEY_PUBLISHEDDATE, thisBook.getPublishedDate());
                            startActivity(i);*/
                        }
                    });
                }
            }
        });
        dTask3.execute(("https://www.googleapis.com/books/v1/volumes?q=books&orderBy=relevance"));

        DownloadTask dTask4 = new DownloadTask(new DownloadTask.onDownloadListener() {
            @Override
            public void onDownloaded(ArrayList<Book> books) {
                for (int i = 0; i < books.size(); i++) {
                    LayoutInflater mInflater;

                    mInflater = LayoutInflater.from(MainActivity.this);
                    View view = mInflater.inflate(R.layout.book_item_view_home_layout,
                            gallery4, false);
                    ImageView img = (ImageView) view
                            .findViewById(R.id.home_item_image);
                    TextView title = (TextView) view.findViewById(R.id.home_item_title);
                    TextView author = (TextView) view.findViewById(R.id.home_item_authors);
                    TextView category = (TextView) view.findViewById(R.id.home_item_category);
                    title.setText(books.get(i).getTitle());
                    author.setText(books.get(i).getAuthors());
                    category.setText(books.get(i).getTitle());
                    String bookImageUrl = books.get(i).getSmallThumbnail();
                    Picasso.with(MainActivity.this).load(bookImageUrl).into(img);
                    gallery4.addView(view);
                }
            }
        });
        dTask4.execute(("https://www.googleapis.com/books/v1/volumes?q=time&printType=magazines"));

    }

    public void initialize(){
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        navigationView=(NavigationView)findViewById(R.id.navigation_view);
        gallery1=(LinearLayout)findViewById(R.id.id_gallery);
        gallery2=(LinearLayout)findViewById(R.id.id_gallery2);
        gallery3=(LinearLayout)findViewById(R.id.id_gallery3);
        gallery4=(LinearLayout)findViewById(R.id.id_gallery4);
        classicMore=(TextView)findViewById(R.id.classicMore);
        ebooksMore=(TextView)findViewById(R.id.booksMore);
        latestMore=(TextView)findViewById(R.id.latestMore);
        magazineMore=(TextView)findViewById(R.id.magazineMore);

        classicMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent=new Intent(MainActivity.this,BookCategoryActivity.class);
                startActivity(intent);
            }
        });

        ebooksMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,BookCategoryActivity.class);
                intent.putExtra(TYPE_SEARCH,TYPE_FILTER);
                intent.putExtra("SEARCH","newest");
                startActivity(intent);
            }
        });

        latestMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SearchActivity.class);
                intent.putExtra(TYPE_SEARCH,TYPE_ORDER);
                intent.putExtra("SEARCH","newest");
                startActivity(intent);
            }
        });

        magazineMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SearchActivity.class);
                intent.putExtra(TYPE_SEARCH,TYPE_MAGAZINE);
                intent.putExtra("SEARCH","magazine");
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate( R.menu.search_menu, menu);

        final MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        final android.support.v7.widget.SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "You searched :"+query, Toast.LENGTH_LONG).show();
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
