package com.example.vaibhav.booksearch;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Top_Books extends Fragment {
    RecyclerView rvListBook;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_adventure, container, false);
        rvListBook = (RecyclerView) v.findViewById(R.id.listPosts);
        rvListBook.setLayoutManager(new LinearLayoutManager(getContext()));

        final BookHomeAdapter bookHomeAdapter = new BookHomeAdapter(new ArrayList<Book>(), getContext());

        rvListBook.setAdapter(bookHomeAdapter);

        DownloadTask dTask = new DownloadTask(new DownloadTask.onDownloadListener() {
            @Override
            public void onDownloaded(ArrayList<Book> books) {
                bookHomeAdapter.updatePostList(books);
            }
        });

        dTask.execute(("https://www.googleapis.com/books/v1/volumes?q=books&orderBy=newest&maxResults=20"));

        return v;
    }
}
