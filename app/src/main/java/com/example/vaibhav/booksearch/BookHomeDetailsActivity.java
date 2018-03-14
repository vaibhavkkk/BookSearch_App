package com.example.vaibhav.booksearch;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.URI;

public class BookHomeDetailsActivity extends AppCompatActivity {

    
    DatabaseHelper myDB;
    public static final String KEY_ID="id",KEY_SELFLINK="selfLink",KEY_TITLE="title",KEY_SUBTITLE="subtitle",KEY_AUTHORS="authors",KEY_PUBLISHER="publisher",KEY_DESCRIPTION="description",KEY_PAGECOUNT="pageCount",KEY_SMALLTHUMBNAIL="smallThumbnail",KEY_LARGATHUMBNAIL="largeThumbnail",KEY_LANGUAGE="language",
            KEY_PREVIEWLINK="previewLink",KEY_INFOLINK="infoLink",KEY_BUYLINK="buyLink",KEY_DOWNLOADLINK="downloadLink",KEY_WEBREADERLINK="webreaderLink",
            KEY_COUNTRY="country",KEY_SALEABILITY="saleability",KEY_CATEGORIES="categories",KEY_PUBLISHEDDATE="publishedDate",KEY_AVERAGERATING="averageRating",KEY_MATURITY="MATURITY",KEY_ISEBOOK="isEbook",KEY_PDFAVAILABLE="pdfAvailable";

    String id,selfLink,title,subTitle,authors,publisher,publishedDate,description,pageCount,categories,averageRating,maturity,
            smallThumbnail,LargeThumbnail,language,previewLink,infoLink,buyLink,downloadLink,webreaderLink, country,saleability;
    Boolean isEbook,pdfAvailable;
    FloatingActionButton addToWishlist;
    ImageView bookImage,bookInfo;
    TextView bookTitle,bookAuthor,bookPreviewLink,bookDesciption,bookReadMore,bookPublisher;
    LinearLayout ReadBook,ShareBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_home_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        
        myDB=new DatabaseHelper(this);
        ReadBook=(LinearLayout)findViewById(R.id.ReadBookOption);
        ShareBook=(LinearLayout)findViewById(R.id.shareBookOption);

        addToWishlist=(FloatingActionButton)findViewById(R.id.bookFabWishlist);
        selfLink=getIntent().getStringExtra(KEY_SELFLINK);
        title=getIntent().getStringExtra(KEY_TITLE);
        subTitle=getIntent().getStringExtra(KEY_SUBTITLE);
        authors=getIntent().getStringExtra(KEY_AUTHORS);
        publisher=getIntent().getStringExtra(KEY_PUBLISHER);
        publishedDate=getIntent().getStringExtra(KEY_PUBLISHEDDATE);
        description=getIntent().getStringExtra(KEY_DESCRIPTION);
        pageCount=getIntent().getStringExtra(KEY_PAGECOUNT);
        categories=getIntent().getStringExtra(KEY_CATEGORIES);
        averageRating=getIntent().getStringExtra(KEY_AVERAGERATING);
        maturity=getIntent().getStringExtra(KEY_MATURITY);
        smallThumbnail=getIntent().getStringExtra(KEY_SMALLTHUMBNAIL);
        LargeThumbnail=getIntent().getStringExtra(KEY_LARGATHUMBNAIL);
        language=getIntent().getStringExtra(KEY_LANGUAGE);
        previewLink=getIntent().getStringExtra(KEY_PREVIEWLINK);
        infoLink=getIntent().getStringExtra(KEY_INFOLINK);
        buyLink=getIntent().getStringExtra(KEY_BUYLINK);
        downloadLink=getIntent().getStringExtra(KEY_DOWNLOADLINK);
        webreaderLink=getIntent().getStringExtra(KEY_WEBREADERLINK);
        country=getIntent().getStringExtra(KEY_COUNTRY);
        saleability=getIntent().getStringExtra(KEY_SALEABILITY);
        isEbook=getIntent().getExtras().getBoolean(KEY_ISEBOOK);
        pdfAvailable=getIntent().getExtras().getBoolean(KEY_PDFAVAILABLE);


        getSupportActionBar().setTitle(title);

        bookInfo=(ImageView)findViewById(R.id.bookHomeDetailsInfoImage);
        bookImage=(ImageView)findViewById(R.id.bookHomeDetailsImage);
        Picasso.with(this).load(LargeThumbnail).into(bookImage);
        bookTitle=(TextView)findViewById(R.id.bookHomeDetailsTitle);
        bookAuthor=(TextView)findViewById(R.id.bookHomeDetailsAutor);
        bookPreviewLink=(TextView)findViewById(R.id.bookHomeDetailsPreviewLink);
        bookDesciption=(TextView)findViewById(R.id.bookHomeDetailsDescription);
        bookReadMore=(TextView)findViewById(R.id.bookHomeDetailsReadMore);
        bookPublisher=(TextView)findViewById(R.id.bookHomeDetailsPublisher);

        bookTitle.setText(title);
        bookAuthor.setText("by "+authors);
        bookPreviewLink.setText(previewLink);
        bookDesciption.setText(description+"");
        bookPublisher.setText(publisher);
        bookReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookDesciption.setMaxLines(20);
                bookReadMore.setVisibility(View.INVISIBLE);
            }
        });
        bookPreviewLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse(previewLink);
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        ReadBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
                Uri uri=Uri.parse(webreaderLink);
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
*/

                Intent intent=new Intent(BookHomeDetailsActivity.this,BookReadWebView.class);
                intent.putExtra("READWEBURL",webreaderLink);
                startActivity(intent);
            }
        });

        ShareBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent=new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT,infoLink);
                startActivity(Intent.createChooser(shareIntent,"Share Using.."));
            }
        });

        bookInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context wrapper = new ContextThemeWrapper(BookHomeDetailsActivity.this, R.style.popup);
                PopupMenu popup = new PopupMenu(wrapper, view);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.book_info, popup.getMenu());
                popup.getMenu().add(1, R.id.info_book, 0, "Title: "+title);
                popup.getMenu().add(1, R.id.info_book, 0, "Authors:    "+authors);
                popup.getMenu().add(1, R.id.info_book, 0, "Publisher:    "+publisher);
                popup.getMenu().add(1, R.id.info_book, 0, "Published Date:    "+publishedDate);
                popup.getMenu().add(1, R.id.info_book, 0, "Page Count:    "+pageCount);
                popup.getMenu().add(1, R.id.info_book, 0, "Average rating:    "+averageRating);
                popup.getMenu().add(1, R.id.info_book, 0, "Maturity:    "+maturity);
                popup.getMenu().add(1, R.id.info_book, 0, "Language:    "+language);
                popup.getMenu().add(1, R.id.info_book, 0, "Country:    "+country);
                popup.getMenu().add(1, R.id.info_book, 0, "Saleability:    "+saleability);
                popup.getMenu().add(1, R.id.info_book, 0, "IsEbook:    "+isEbook);
                popup.getMenu().add(1, R.id.info_book, 0, "IsPdf:    "+pdfAvailable);

                        popup.show();
/*
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()){
                                    case R.id.Price:
                                        Toast.makeText(context, "Can't Complete Purchase", Toast.LENGTH_LONG).show();
                                        break;
                                    case R.id.free:
                                        Toast.makeText(context, "Free", Toast.LENGTH_SHORT).show();
                                        break;
                                    case R.id.wislist:
                                        Toast.makeText(context, "Added to your WishList", Toast.LENGTH_LONG).show();
                                }
                                return true;
                            }
                        });
*/
                    }
                });
        
        
        addToWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean insertName=myDB.addToWishlistBook(id,selfLink,title,subTitle,authors,publisher,publishedDate,description,pageCount,categories,averageRating,maturity,smallThumbnail,LargeThumbnail,language,previewLink,infoLink,buyLink,downloadLink,webreaderLink,country,saleability);
                if(insertName){
                    Toast.makeText(BookHomeDetailsActivity.this, "Book Added to wishList", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(BookHomeDetailsActivity.this, "Book Can't be Added to  Wishlist", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
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
