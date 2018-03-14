package com.example.vaibhav.booksearch;

import android.os.AsyncTask;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by vaibhav on 07-07-2017.
 */

public class DownloadTask extends AsyncTask<String ,Void,ArrayList<Book>> {

    public static final String TAG="REST";

    interface onDownloadListener{
        void onDownloaded(ArrayList<Book> books);
    }

    private onDownloadListener odl;

    public DownloadTask(onDownloadListener odl){
        this.odl=odl;
    }

    @Override
    protected ArrayList<Book> doInBackground(String... strings) {
        ArrayList<Book> bookArrayList=null;
        InputStream inputStream=null;
        try {
            URL url=new URL(strings[0]);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(10000);

            connection.connect();
            BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder sb=new StringBuilder();
            String buffer="";
            while(buffer!=null){
                sb.append(buffer);
                buffer=br.readLine();
            }

            String jsonResponse=sb.toString();
            bookArrayList=extractBooks(jsonResponse);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return bookArrayList;
    }

    private static ArrayList<Book> extractBooks(String jsonResponse){

        if(TextUtils.isEmpty(jsonResponse)){
            return null;
        }
        ArrayList<Book> bookArrayList=new ArrayList<>();
        String id="",selfLink="",title="",subTitle="",authors="",publisher="",publishedDate="",description="",pageCount=""
                ,categories="",averageRating="",maturity="",smallThumbnail="",LargeThumbnail="",language="",
                previewLink="",infoLink="", country="",saleability="",buyLink="",downloadLink="",webreaderLink="";
        Boolean isEbook=false,qouteSharinng=false,pdfAvailable=false;

        try {
            JSONObject baseResponse=new JSONObject(jsonResponse);
            JSONArray bookArray=baseResponse.getJSONArray("items");
            for (int i=0;i<bookArray.length();i++){
                JSONObject bookItem=bookArray.getJSONObject(i);
                // ID AND SELF LINK
                id=bookItem.getString("id");
                selfLink=bookItem.getString("selfLink");
                // VOLUME INFO
                JSONObject volumeInfo=bookItem.getJSONObject("volumeInfo");
                title=volumeInfo.getString("title");
                try {
                    subTitle=volumeInfo.getString("subtitle");
                }catch (Exception e){
                    subTitle="Subtitle ...";
                }
                try {
                    JSONArray authorsList = volumeInfo.getJSONArray("authors");
                    authors=authors+authorsList.getString(0);
                    if(authorsList.length()>1) {
                        for (int j = 1; i < authorsList.length(); i++) {
                            authors = authors + " - " + authorsList.getString(i);
                        }
                    }
                }catch (Exception e){
                    authors="Publisher details not available";
                }

                try {
                    description=volumeInfo.getString("description");
                }catch (Exception e){
                    description=title+" is written by "+authors;
                }

                try {
                    publisher=volumeInfo.getString("publisher");
                }catch (Exception e){
                    publisher="Certified Publisher";
                }



                if(!TextUtils.isEmpty(description)&&!TextUtils.isEmpty(publisher)){
                    try {
                        publishedDate=volumeInfo.getString("publishedDate");
                    }catch (Exception e) {
                        publishedDate = "2014";
                    }

                    try {
                        pageCount=String.valueOf(volumeInfo.getInt("pageCount"));
                    }catch (Exception e){
                        pageCount="300";
                    }
                    JSONArray categoryList;
                    try {
                        categoryList=volumeInfo.getJSONArray("categories");

                    for (int k=0;k<categoryList.length();k++){
                        categories=categories=" -"+categoryList.getString(k);
                    }}catch (Exception e){
                        categories="All categories";
                    }
                     try {
                         averageRating=volumeInfo.getString("averageRating");
                     }catch (Exception e){
                         averageRating="No Rating Available";
                     }
                     maturity=volumeInfo.getString("maturityRating");
                    JSONObject imageLinks=volumeInfo.getJSONObject("imageLinks");
                    smallThumbnail=imageLinks.getString("smallThumbnail");
                    LargeThumbnail=imageLinks.getString("thumbnail");
                    language=volumeInfo.getString("language");
                    previewLink=volumeInfo.getString("previewLink");
                    infoLink=volumeInfo.getString("infoLink");
                    JSONObject saleInfo=bookItem.getJSONObject("saleInfo");
                    country=saleInfo.getString("country");
                    saleability=saleInfo.getString("saleability");
                    isEbook=saleInfo.getBoolean("isEbook");

                    try {
                        buyLink=saleInfo.getString("buyLink");
                    }catch (Exception e){
                        buyLink="Cannot Buy";
                    }
                    JSONObject accessInfo=bookItem.getJSONObject("accessInfo");
                    JSONObject pdfInfo=accessInfo.getJSONObject("pdf");
                    pdfAvailable=pdfInfo.getBoolean("isAvailable");

                    try {
                        downloadLink=pdfInfo.getString("downloadLink");
                    }catch (Exception e){
                        downloadLink="Download Not Available";
                    }

                    webreaderLink=accessInfo.getString("webReaderLink");

                    Book bookFetched=new Book(id,selfLink,title,subTitle,authors,publisher,publishedDate,description,pageCount,categories,averageRating,maturity,smallThumbnail,LargeThumbnail,language,previewLink,infoLink,country,saleability,buyLink,downloadLink,webreaderLink,isEbook,pdfAvailable);
                    bookArrayList.add(bookFetched);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  bookArrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<Book> books) {
        super.onPostExecute(books);
        odl.onDownloaded(books);
    }
}
