package com.example.vaibhav.booksearch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by vaibhav on 10-07-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="books.db";
    public static final String TABLE_NAME="Wishlist";
    private static final String COL_ID="id";
    private static final String COL_Name="name";
    private static final String COL_SELFLINK="selflink";
    private static final String COL_TITLE="title";
    private static final String COL_SUBTITLE="subtitile";
    private static final String COL_AUTHORS="authors";
    private static final String COL_PUBLISHER="publisher";
    private static final String COL_PUBLISHED_DATE="publishdate";
    private static final String COL_DESCRIPTION="description";
    private static final String COL_PAGECOUNT="pagecount";
    private static final String COL_CATEGORIES="categories";
    private static final String COL_AVERAGERATING="averagerating";
    private static final String COL_MATURITY="maturity";
    private static final String COL_SMALLTHUMBNAIL="smallthumbnail";
    private static final String COL_LARGETHUMBNAIL="largethumbnail";
    private static final String COL_LANGUAGE="language";
    private static final String COL_PREVIEWLINK="previewlink";
    private static final String COL_INFOLINK="infolink";
    private static final String COL_BUYLINK="buylink";
    private static final String COL_DOWNLOADLINK="downloadlink";
    private static final String COL_WEBREADERLINK="webreaderlink";
    private static final String COL_COUNTRY="country";
    private static final String COL_SALEABILITY="saleability";
    private static final String COL_ISEBOOK="isebook";
    private static final String COL_PDFAVAILABLE="pdfavailable";
    private static Context ctx;

    SQLiteDatabase db;

    private  static final String CREATE_TABLE="create table "+TABLE_NAME+"(id integer primary key not null ,  " +
            "selflink text not null , title  text not null , subtitile  text not null , authors  text not null , publisher  text not null , publishdate  text not null , " +
            " description  text not null , pagecount  text not null , categories  text not null , averagerating  text not null , maturity  text not null , " +
            " smallthumbnail text not null , largethumbnail  text not null , language text not null , previewlink text not null , infolink text not null , " +
            " country  text not null , saleability  text not null , buylink text , downloadlink text not null , webreaderlink" +
            "  text not null , isebook text  , pdfavailable text  );";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        ctx=context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    public boolean insertBook(String bookName){
        db=this.getWritableDatabase();
        String Query="SELECT * FROM "+TABLE_NAME;
        Cursor cursor=db.rawQuery(Query,null);
        int count=cursor.getCount();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_ID,count);
        contentValues.put(COL_TITLE,bookName);

        long result=db.insert(TABLE_NAME,null,contentValues);
        Log.d("DB INSERTION",String.valueOf(result));
        db.close();
        cursor.close();
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public boolean addToWishlistBook(String id,String selfLink,String title,String subTitle,String authors,String publisher,
                                     String publishedDate,String description,String pageCount,String categories,String averageRating,
                                     String maturity,String smallThumbnail,String LargeThumbnail,String language,String previewLink,
                                     String infoLink,String buyLink,String downloadLink,String webreaderLink, String country, String saleability){
        db=this.getWritableDatabase();
        String Query="SELECT * FROM "+TABLE_NAME;
        Cursor cursor=db.rawQuery(Query,null);
        int count=cursor.getCount();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_ID,id);
        contentValues.put(COL_SELFLINK,selfLink);
        contentValues.put(COL_TITLE,title);
        contentValues.put(COL_SUBTITLE,subTitle);
        contentValues.put(COL_AUTHORS,authors);
        contentValues.put(COL_PUBLISHER,publisher);
        contentValues.put(COL_PUBLISHED_DATE,publishedDate);
        contentValues.put(COL_DESCRIPTION,description);
        contentValues.put(COL_PAGECOUNT,pageCount);
        contentValues.put(COL_CATEGORIES,categories);
        contentValues.put(COL_AVERAGERATING,averageRating);
        contentValues.put(COL_MATURITY,maturity);
        contentValues.put(COL_SMALLTHUMBNAIL,smallThumbnail);
        contentValues.put(COL_LARGETHUMBNAIL,LargeThumbnail);
        contentValues.put(COL_LANGUAGE,language);
        contentValues.put(COL_PREVIEWLINK,previewLink);
        contentValues.put(COL_INFOLINK,infoLink);
        contentValues.put(COL_BUYLINK,buyLink);
        contentValues.put(COL_DOWNLOADLINK,downloadLink);
        contentValues.put(COL_WEBREADERLINK,webreaderLink);
        contentValues.put(COL_COUNTRY,country);
        contentValues.put(COL_SALEABILITY,saleability);

        long result=db.insert(TABLE_NAME,null,contentValues);
        Log.d("DB INSERTION",String.valueOf(result));
        db.close();
        cursor.close();
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public ArrayList<Book> getBooksStored(){

        db=this.getReadableDatabase();
        String Query="SELECT * FROM "+TABLE_NAME;

        Cursor cursor=db.rawQuery(Query,null);
        String bookName="";
        ArrayList<Book> bookList=new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Book book=new Book(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),
                        cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13),cursor.getString(14),cursor.getString(15),
                        cursor.getString(15),cursor.getString(16),cursor.getString(17),cursor.getString(18),cursor.getString(19),cursor.getString(20),true,true);
                bookList.add(book);
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return bookList;
    }

    public Book getBookItem(String titleName){
        db=this.getReadableDatabase();
        String Query="SELECT * FROM "+TABLE_NAME;

        Cursor cursor=db.rawQuery(Query,null);
        Book book=null;
        if(cursor.moveToFirst()){
            do{
                if(titleName.equalsIgnoreCase(cursor.getString(3))){
                    book=new Book(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),
                            cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13),cursor.getString(14),cursor.getString(15),
                            cursor.getString(15),cursor.getString(16),cursor.getString(17),cursor.getString(18),cursor.getString(19),cursor.getString(20),true,true);
                }
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return book;

    }
}
