package com.example.vaibhav.booksearch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by vaibhav on 09-07-2017.
 */

public class BookHomehorizontalSlider extends RecyclerView.Adapter<BookHomehorizontalSlider.ImageViewHolder>{

    private ArrayList<String> bookTypeList;
    private Context context;

    private View.OnClickListener onItemclickListener;

    public BookHomehorizontalSlider(View.OnClickListener onItemclickListener) {
        this.onItemclickListener = onItemclickListener;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li =
                (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE
                );
        View itemView = li.inflate(R.layout.book_category_home_item_layout, parent, false);

        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {
        final String bookType=bookTypeList.get(position);
        holder.searchItem.setText(bookType.toUpperCase());
        holder.getDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,BookCategoryDetailed.class);
                intent.putExtra("SEARCH",bookType);
                context.startActivity(intent);
            }
        });

        DownloadTask dTask = new DownloadTask(new DownloadTask.onDownloadListener() {
            @Override
            public void onDownloaded(ArrayList<Book> books) {
                for (int i = 0; i < books.size(); i++)
                {
                    LayoutInflater mInflater;

                    mInflater = LayoutInflater.from(context);
                    View view = mInflater.inflate(R.layout.book_home_book_image,
                            holder.gallery, false);
                    ImageView img = (ImageView) view
                            .findViewById(R.id.id_index_gallery_item_image);
                    TextView tv=(TextView)view.findViewById(R.id.id_index_gallery_item_title);
                    tv.setText(books.get(i).getTitle());
                    String bookImageUrl=books.get(i).getSmallThumbnail();
                    Picasso.with(context).load(bookImageUrl).into(img);
                    holder.gallery.addView(view);
                }

            }
        });
        dTask.execute(("https://www.googleapis.com/books/v1/volumes?q=subject:"+bookType));




    }

    @Override
    public int getItemCount() {
        return bookTypeList.size();
    }

    interface OnItemClickListener{
        void onItemClicked(int pos);
    }

    public BookHomehorizontalSlider(ArrayList<String> bookList, Context context) {
        this.bookTypeList = bookList;
        this.context = context;
    }

    void updatePostList(ArrayList<String> postList) {
        this.bookTypeList = postList;
        notifyDataSetChanged();
    }



    class ImageViewHolder extends RecyclerView.ViewHolder{

        TextView searchItem;
        RelativeLayout getDetail;
        LinearLayout gallery;
        View rootView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            rootView=itemView;
            searchItem=(TextView)itemView.findViewById(R.id.homeFragmentBookSearch);
            getDetail=(RelativeLayout)itemView.findViewById(R.id.book_home_fragment_detail);
            gallery=(LinearLayout)itemView.findViewById(R.id.id_gallery);
        }
    }
}
