package com.example.vaibhav.booksearch;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.interpolator.linear;


public class Category extends Fragment {

    Button Poetry,Adventure,Woman,Love,Humor,Science_Fiction,Detective,Politics,India,Short_Stories,
            Fantasy,Crime,Western_Stories,Comidies,Fiction,Friendship,Girls,Animals,Literature,Horror,
            Discovery,Schools,Essays,Voyages_Travel,Music,Travel,Religion,Read_More;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_category, container, false);

        Poetry=(Button)v.findViewById(R.id.btnPoetry);
        Adventure=(Button)v.findViewById(R.id.btnAdventure);
        Woman=(Button)v.findViewById(R.id.btnWoman);
        Love=(Button)v.findViewById(R.id.btnLove);
        Humor=(Button)v.findViewById(R.id.btnHumor);
        Science_Fiction=(Button)v.findViewById(R.id.btnScienceFiction);
        Detective=(Button)v.findViewById(R.id.btnDetective);
        Politics=(Button)v.findViewById(R.id.btnPolitics);
        India=(Button)v.findViewById(R.id.btnIndia);
        Short_Stories=(Button)v.findViewById(R.id.btnShortStudies);
        Fantasy=(Button)v.findViewById(R.id.btnFantasy);
        Crime=(Button)v.findViewById(R.id.btnCrime);
        Western_Stories=(Button)v.findViewById(R.id.btnWestern);
        Comidies=(Button)v.findViewById(R.id.btnComedy);
        Fiction=(Button)v.findViewById(R.id.btnFiction);
        Friendship=(Button)v.findViewById(R.id.btnFriendship);
        Girls=(Button)v.findViewById(R.id.btnGirls);
        Animals=(Button)v.findViewById(R.id.btnAnimals);
        Literature=(Button)v.findViewById(R.id.btnLiterature);
        Horror=(Button)v.findViewById(R.id.btnHorror);
        Discovery=(Button)v.findViewById(R.id.btnDiscovery);
        Schools=(Button)v.findViewById(R.id.btnSchools);
        Essays=(Button)v.findViewById(R.id.btnEssays);
        Voyages_Travel=(Button)v.findViewById(R.id.btnVoyages);
        Music=(Button)v.findViewById(R.id.btnMusic);
        Travel=(Button)v.findViewById(R.id.btnTravel);
        Religion=(Button)v.findViewById(R.id.btnReligion);
        Read_More=(Button)v.findViewById(R.id.btnLoadMore);

        //Invisibility
        Western_Stories.setVisibility(View.INVISIBLE);
        Friendship.setVisibility(View.INVISIBLE);
        Girls.setVisibility(View.INVISIBLE);
        Animals.setVisibility(View.INVISIBLE);
        Literature.setVisibility(View.INVISIBLE);
        Horror.setVisibility(View.INVISIBLE);
        Discovery.setVisibility(View.INVISIBLE);
        Comidies.setVisibility(View.INVISIBLE);
        Fiction.setVisibility(View.INVISIBLE);
        Schools.setVisibility(View.INVISIBLE);
        Essays.setVisibility(View.INVISIBLE);
        Voyages_Travel.setVisibility(View.INVISIBLE);
        Music.setVisibility(View.INVISIBLE);
        Travel.setVisibility(View.INVISIBLE);
        Religion.setVisibility(View.INVISIBLE);



        Poetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("Search","books");
                startActivity(intent);
            }
        });

        Adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","adventure");
                startActivity(intent);
            }
        });

        Woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","woman");
                startActivity(intent);
            }
        });

        Love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","love");
                startActivity(intent);
            }
        });

        Humor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","humor");
                startActivity(intent);
            }
        });

        Science_Fiction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","science fiction");
                startActivity(intent);
            }
        });

        Detective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","detective");
                startActivity(intent);
            }
        });

        Politics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","politics");
                startActivity(intent);
            }
        });

        India.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","india");
                startActivity(intent);
            }
        });

        Short_Stories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","short stories");
                startActivity(intent);
            }
        });

        Fantasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","fantasy");
                startActivity(intent);
            }
        });

        Crime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","crimes");
                startActivity(intent);
            }
        });

        Western_Stories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","western stories");
                startActivity(intent);
            }
        });

        Comidies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","comedies");
                startActivity(intent);
            }
        });

        Fiction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","fiction");
                startActivity(intent);
            }
        });

        Friendship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","friendship");
                startActivity(intent);
            }
        });

        Girls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","girls");
                startActivity(intent);
            }
        });

        Animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","animals");
                startActivity(intent);
            }
        });

        Literature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","literature");
                startActivity(intent);
            }
        });

        Horror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","horror");
                startActivity(intent);
            }
        });

        Discovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","discovery");
                startActivity(intent);
            }
        });

        Schools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","schools");
                startActivity(intent);
            }
        });

        Essays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","essays");
                startActivity(intent);
            }
        });

        Voyages_Travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","voyages and travel");
                startActivity(intent);
            }
        });

        Music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","music");
                startActivity(intent);
            }
        });

        Travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","travel");
                startActivity(intent);
            }
        });

        Religion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BookCategoryDetailed.class);
                intent.putExtra("SEARCH","religion");
                startActivity(intent);
            }
        });

        Read_More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Western_Stories.setVisibility(View.VISIBLE);
                Friendship.setVisibility(View.VISIBLE);
                Girls.setVisibility(View.VISIBLE);
                Animals.setVisibility(View.VISIBLE);
                Literature.setVisibility(View.VISIBLE);
                Horror.setVisibility(View.VISIBLE);
                Discovery.setVisibility(View.VISIBLE);
                Comidies.setVisibility(View.VISIBLE);
                Fiction.setVisibility(View.VISIBLE);
                Schools.setVisibility(View.VISIBLE);
                Essays.setVisibility(View.VISIBLE);
                Voyages_Travel.setVisibility(View.VISIBLE);
                Music.setVisibility(View.VISIBLE);
                Travel.setVisibility(View.VISIBLE);
                Religion.setVisibility(View.VISIBLE);
                Read_More.setVisibility(View.INVISIBLE);
            }
        });



        return v;

    }

}