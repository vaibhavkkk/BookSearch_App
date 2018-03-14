package com.example.vaibhav.booksearch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by vaibhav on 10-07-2017.
 */

public class BookAuthorAdapter extends RecyclerView.Adapter<BookAuthorAdapter.BookViewHolder> {
    public static final String KEY_BOOK_SEARCH_TYPE="bookSearchType";

    private ArrayList<String> bookArrayList;
    private Context context;

    private View.OnClickListener onItemclickListener;

    public BookAuthorAdapter(ArrayList<String> bookArrayList, Context context) {
        this.bookArrayList = bookArrayList;
        this.context = context;
    }


    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater li=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=li.inflate(R.layout.book_category_author_item_layout,parent,false);

        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BookViewHolder holder, int position) {

        final String Author=bookArrayList.get(position);

        holder.authorName.setText(Author);

        DownloadTask dTask=new DownloadTask(new DownloadTask.onDownloadListener() {
            @Override
            public void onDownloaded(ArrayList<Book> books) {

                Book book1=books.get(0);
                Book book2=books.get(1);
                Book book3=books.get(2);

                Picasso.with(context).load(book1.getSmallThumbnail()).into(holder.bookImage1);
                holder.bookTitle1.setText(book1.getTitle());

                Picasso.with(context).load(book2.getSmallThumbnail()).into(holder.bookImage2);
                holder.bookTitle2.setText(book2.getTitle());

                Picasso.with(context).load(book3.getSmallThumbnail()).into(holder.bookImage3);
                holder.bookTitle3.setText(book3.getTitle());

            }
        });

        dTask.execute(("https://www.googleapis.com/books/v1/volumes?q=books+inauthor:"+Author));

        holder.moreBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,BookAuthorActivity.class);
                i.putExtra(KEY_BOOK_SEARCH_TYPE,Author);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder{

        View rootView;
        ImageView bookImage1,bookImage2,bookImage3;
        TextView bookTitle1,bookTitle2,bookTitle3,authorName,moreBooks;
        public BookViewHolder(View itemView) {
            super(itemView);
            rootView=itemView;
            bookImage1=(ImageView)itemView.findViewById(R.id.bookAuthorImage1);
            bookImage2=(ImageView)itemView.findViewById(R.id.bookAuthorImage2);
            bookImage3=(ImageView)itemView.findViewById(R.id.bookAuthorImage3);
            bookTitle1=(TextView) itemView.findViewById(R.id.bookAuthorTitle1);
            bookTitle2=(TextView) itemView.findViewById(R.id.bookAuthorTitle2);
            bookTitle3=(TextView) itemView.findViewById(R.id.bookAuthorTitle3);
            authorName=(TextView)itemView.findViewById(R.id.authorSearchBook);
            moreBooks=(TextView)itemView.findViewById(R.id.authorMoreBook);
        }
    }
}
