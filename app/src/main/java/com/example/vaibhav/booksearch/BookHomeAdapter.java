package com.example.vaibhav.booksearch;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by vaibhav on 27-06-2017.
 */

public class BookHomeAdapter extends RecyclerView.Adapter<BookHomeAdapter.BookViewHolder> {
    public static final String KEY_ID="id",KEY_SELFLINK="selfLink",KEY_TITLE="title",KEY_SUBTITLE="subtitle",KEY_AUTHORS="authors",KEY_PUBLISHER="publisher",KEY_DESCRIPTION="description",KEY_PAGECOUNT="pageCount",KEY_SMALLTHUMBNAIL="smallThumbnail",KEY_LARGATHUMBNAIL="largeThumbnail",KEY_LANGUAGE="language",
    KEY_PREVIEWLINK="previewLink",KEY_INFOLINK="infoLink",KEY_BUYLINK="buyLink",KEY_DOWNLOADLINK="downloadLink",KEY_WEBREADERLINK="webreaderLink",
    KEY_COUNTRY="country",KEY_SALEABILITY="saleability",KEY_CATEGORIES="categories",KEY_AVERAGERATING="averageRating",KEY_MATURITY="MATURITY",KEY_ISEBOOK="isEbook",KEY_PDFAVAILABLE="pdfAvailable",KEY_PUBLISHEDDATE="publishedDate";
    private ArrayList<Book> bookList;
    private Context context;

    private View.OnClickListener onItemclickListener;

    public BookHomeAdapter(View.OnClickListener onItemclickListener) {
        this.onItemclickListener = onItemclickListener;
    }

    interface OnItemClickListener{
        void onItemClicked(int pos);
    }

    public BookHomeAdapter(ArrayList<Book> bookList, Context context) {
        this.bookList = bookList;
        this.context = context;
    }

    void updatePostList(ArrayList<Book> postList) {
        this.bookList = postList;
        notifyDataSetChanged();
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li =
                (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE
                );
        View itemView = li.inflate(R.layout.list_book_home_item, parent, false);

        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        final Book thisBook=bookList.get(position);

        holder.bookTitle.setText(thisBook.getTitle());
        holder.bookAuthor.setText(thisBook.getAuthors());
        holder.bookType.setText(thisBook.getCategories());
        Picasso.with(context).load(String.valueOf(bookList.get(position).getLargeThumbnail())).into(holder.bookImage);
        holder.menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(context, view);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.book_options_menu, popup.getMenu());
                popup.getMenu().add(1, R.id.Price, 0, " Buy");
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.Price:
                                Toast.makeText(context, "Can't Complete Purchase", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.free:
                                Uri uri = Uri.parse(thisBook.getWebreaderLink());
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                context.startActivity(intent);
                                break;
                            case R.id.wislist:
                                Toast.makeText(context, "Open And Add to Wishlist", Toast.LENGTH_LONG).show();
                                break;
                        }
                        return true;
                    }
                    });

            }
        });
        holder.bookLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,BookHomeDetailsActivity.class);
                i.putExtra(KEY_ID,thisBook.getId());
                i.putExtra(KEY_SELFLINK,thisBook.getSelfLink());
                i.putExtra(KEY_TITLE,thisBook.getTitle());
                i.putExtra(KEY_SUBTITLE,thisBook.getSubTitle());
                i.putExtra(KEY_AUTHORS,thisBook.getAuthors());
                i.putExtra(KEY_PUBLISHER,thisBook.getPublisher());
                i.putExtra(KEY_DESCRIPTION,thisBook.getDescription());
                i.putExtra(KEY_PAGECOUNT,thisBook.getPageCount());
                i.putExtra(KEY_SMALLTHUMBNAIL,thisBook.getSmallThumbnail());
                i.putExtra(KEY_LARGATHUMBNAIL,thisBook.getLargeThumbnail());
                i.putExtra(KEY_LANGUAGE,thisBook.getLanguage());
                i.putExtra(KEY_PREVIEWLINK,thisBook.getPreviewLink());
                i.putExtra(KEY_INFOLINK,thisBook.getInfoLink());
                i.putExtra(KEY_BUYLINK,thisBook.getBuyLink());
                i.putExtra(KEY_DOWNLOADLINK,thisBook.getDownloadLink());
                i.putExtra(KEY_WEBREADERLINK,thisBook.getWebreaderLink());
                i.putExtra(KEY_COUNTRY,thisBook.getCountry());
                i.putExtra(KEY_SALEABILITY,thisBook.getSaleability());
                i.putExtra(KEY_CATEGORIES,thisBook.getCategories());
                i.putExtra(KEY_AVERAGERATING,thisBook.getAverageRating());
                i.putExtra(KEY_MATURITY,thisBook.getMaturity());
                i.putExtra(KEY_ISEBOOK,thisBook.getEbook());
                i.putExtra(KEY_PDFAVAILABLE,thisBook.getPdfAvailable());
                i.putExtra(KEY_PUBLISHEDDATE,thisBook.getPublishedDate());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {

        TextView id,selfLink,title,subTitle,authors,publisher,publishedDate,description,pageCount,categories,averageRating,maturity,
                smallThumbnail,LargeThumbnail,language,previewLink,infoLink,buyLink,downloadLink,webreaderLink, country,saleability;
        TextView bookTitle,bookAuthor,bookType;
        ImageView bookImage,menuImage;
        LinearLayout bookLayout;
        ImageView imageView;
        View rootView;
        public BookViewHolder(View itemView) {
            super(itemView);
/*
            id = (TextView) itemView.findViewById(R.id.tvId);
            selfLink = (TextView) itemView.findViewById(R.id.tvSelfLink);
            title = (TextView) itemView.findViewById(R.id.tvTitle);
            authors = (TextView) itemView.findViewById(R.id.tvAuthors);
            subTitle = (TextView) itemView.findViewById(R.id.tvsubTitle);
            publishedDate = (TextView) itemView.findViewById(R.id.tvPublishedDate);
            publisher = (TextView) itemView.findViewById(R.id.tvPublisher);
            description = (TextView) itemView.findViewById(R.id.tvDesc);
            pageCount = (TextView) itemView.findViewById(R.id.tvPageCount);
            categories = (TextView) itemView.findViewById(R.id.tvCategory);
            averageRating = (TextView) itemView.findViewById(R.id.tvAverageRating);
            maturity = (TextView) itemView.findViewById(R.id.tvMaturity);
            smallThumbnail = (TextView) itemView.findViewById(R.id.tvSmallThumbnail);
            LargeThumbnail = (TextView) itemView.findViewById(R.id.tvLargeThumbnail);
            language = (TextView) itemView.findViewById(R.id.tvlanguage);
            previewLink = (TextView) itemView.findViewById(R.id.tvpreviewLink);
            infoLink = (TextView) itemView.findViewById(R.id.tvinfoLink);
            buyLink = (TextView) itemView.findViewById(R.id.tvbuyLink);
            downloadLink = (TextView) itemView.findViewById(R.id.tvDownloadLink);
            webreaderLink = (TextView) itemView.findViewById(R.id.tvWebReaderLink);
            country = (TextView) itemView.findViewById(R.id.tvCountry);
            saleability = (TextView) itemView.findViewById(R.id.tvSaleability);
            imageView=(ImageView)itemView.findViewById(R.id.tvImage);
*/
            rootView=itemView;
            bookImage=(ImageView) itemView.findViewById(R.id.bookHomeOutsideImage);
            menuImage=(ImageView)itemView.findViewById(R.id.bookHomeOutsideMenuImage);
            bookTitle=(TextView)itemView.findViewById(R.id.bookHomeOutsideTitle);
            bookAuthor=(TextView)itemView.findViewById(R.id.bookHomeOutsideAuthor);
            bookType=(TextView)itemView.findViewById(R.id.bookHomeOutsideType);
            bookLayout=(LinearLayout)itemView.findViewById(R.id.bookHomeOutsideLayout);
        }
    }

}