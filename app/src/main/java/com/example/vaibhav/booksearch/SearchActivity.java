package com.example.vaibhav.booksearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    String inputType="";
    String input="";
    public static final String TYPE_SEARCH="type_of_search";
    public static final String TYPE_BOOK="book";
    public static final String TYPE_AUTHOR="author";
    public static final String TYPE_TOP="top";
    public static final String TYPE_CATEGORY="category";
    public static final String TYPE_MAGAZINE="magazine";
    public static final String TYPE_ORDER="order";
    public static final String TYPE_WISHLIST="wishlist";
    public static final String TYPE_FILTER="free-ebooks";

    RecyclerView rvListBook;

    public static final String KEY_ID="id",KEY_SELFLINK="selfLink",KEY_TITLE="title",KEY_SUBTITLE="subtitle",KEY_AUTHORS="authors",KEY_PUBLISHER="publisher",KEY_DESCRIPTION="description",KEY_PAGECOUNT="pageCount",KEY_SMALLTHUMBNAIL="smallThumbnail",KEY_LARGATHUMBNAIL="largeThumbnail",KEY_LANGUAGE="language",
            KEY_PREVIEWLINK="previewLink",KEY_INFOLINK="infoLink",KEY_BUYLINK="buyLink",KEY_DOWNLOADLINK="downloadLink",KEY_WEBREADERLINK="webreaderLink",
            KEY_COUNTRY="country",KEY_SALEABILITY="saleability",KEY_CATEGORIES="categories",KEY_AVERAGERATING="averageRating",KEY_MATURITY="MATURITY",KEY_ISEBOOK="isEbook",KEY_PDFAVAILABLE="pdfAvailable",KEY_PUBLISHEDDATE="publishedDate";

    String id,selfLink,title,subTitle,authors,publisher,publishedDate,description,pageCount,categories,averageRating,maturity,
            smallThumbnail,LargeThumbnail,language,previewLink,infoLink,buyLink,downloadLink,webreaderLink, country,saleability;
    Boolean isEbook,pdfAvailable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

/*
        id=getIntent().getStringExtra(KEY_ID);
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

        Book book=new Book(id,selfLink,title,subTitle,authors,publisher,publishedDate,description,pageCount,categories,averageRating,maturity,smallThumbnail,LargeThumbnail,language,previewLink,infoLink,country,saleability,buyLink,downloadLink,webreaderLink,isEbook,pdfAvailable);
        ArrayList<Book> bookArrayList=new ArrayList<>();
        bookArrayList.add(book);
*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SEARCH YOUR BOOKS");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
        input=getIntent().getStringExtra("SEARCH");
        inputType=getIntent().getStringExtra(TYPE_SEARCH);
        rvListBook = (RecyclerView) findViewById(R.id.listPosts);
        rvListBook.setLayoutManager(new LinearLayoutManager(this));

        final BookHomeAdapter bookHomeAdapter = new BookHomeAdapter(new ArrayList<Book>(), this);

        rvListBook.setAdapter(bookHomeAdapter);

        if(inputType.equals(TYPE_CATEGORY)){
            DownloadTask dTask = new DownloadTask(new DownloadTask.onDownloadListener() {
                @Override
                public void onDownloaded(ArrayList<Book> books) {
                    bookHomeAdapter.updatePostList(books);

                }
            });

            dTask.execute(("https://www.googleapis.com/books/v1/volumes?q=subject:"+input));
        }

        if(inputType.equals(TYPE_BOOK)){
            DownloadTask dTask = new DownloadTask(new DownloadTask.onDownloadListener() {
                @Override
                public void onDownloaded(ArrayList<Book> books) {
                    bookHomeAdapter.updatePostList(books);
                }
            });

            dTask.execute(("https://www.googleapis.com/books/v1/volumes?q=books&orderBy=newest&maxResults=20"));
        }

        if(inputType.equals(TYPE_TOP)){
            DownloadTask dTask = new DownloadTask(new DownloadTask.onDownloadListener() {
                @Override
                public void onDownloaded(ArrayList<Book> books) {
                    bookHomeAdapter.updatePostList(books);
                }
            });
        }
        if(inputType.equals(TYPE_AUTHOR)){
            DownloadTask dTask = new DownloadTask(new DownloadTask.onDownloadListener() {
                @Override
                public void onDownloaded(ArrayList<Book> books) {
                    bookHomeAdapter.updatePostList(books);
                }
            });

            dTask.execute(("https://www.googleapis.com/books/v1/volumes?q=books+inauthor:"+input));

        }

        if(inputType.equals(TYPE_MAGAZINE)){
            DownloadTask dTask = new DownloadTask(new DownloadTask.onDownloadListener() {
                @Override
                public void onDownloaded(ArrayList<Book> books) {
                    bookHomeAdapter.updatePostList(books);
                }
            });
            dTask.execute(("https://www.googleapis.com/books/v1/volumes?q=magazines&orderBy=newest&maxResults=20"));
        }

        if(inputType.equals(TYPE_ORDER)){
            DownloadTask dTask = new DownloadTask(new DownloadTask.onDownloadListener() {
                @Override
                public void onDownloaded(ArrayList<Book> books) {
                    bookHomeAdapter.updatePostList(books);
                }
            });
            dTask.execute(("https://www.googleapis.com/books/v1/volumes?q=essays&orderBy="+input));
        }


        if(inputType.equals(TYPE_FILTER)){
            DownloadTask dTask = new DownloadTask(new DownloadTask.onDownloadListener() {
                @Override
                public void onDownloaded(ArrayList<Book> books) {
                    bookHomeAdapter.updatePostList(books);
                }
            });
            dTask.execute("https://www.googleapis.com/books/v1/volumes?q=flowers&filter=free-ebooks");
        }

    }
}
