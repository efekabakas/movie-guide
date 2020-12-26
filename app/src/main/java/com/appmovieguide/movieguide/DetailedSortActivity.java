package com.appmovieguide.movieguide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailedSortActivity extends AppCompatActivity {
    ListView sortList;
    ArrayList<String> shownList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_sort);

        sortList = findViewById(R.id.sortList);
        shownList = new ArrayList();

        setList();
    }
    void setList(){
        Intent intent = getIntent();

        int index = intent.getIntExtra("index", -1);

        Management mng = new Management();
        mng.readDatabase(getApplicationContext());
        String searchParam = "";

        if(index == 0){ searchParam = "aToZ";}
        else if(index == 1){ searchParam = "zToA"; }
        else if(index == 2){ searchParam = "yearInc"; }
        else if(index == 3){ searchParam = "yearDec"; }
        else if(index == 4){ searchParam = "rankingInc"; }
        else if(index == 5){ searchParam = "rankingDec"; }
        else if(index == 6){ searchParam = "runTimeInc"; }
        else if(index == 7){ searchParam = "runTimeDec"; }
        Log.e("State", searchParam);
        ArrayList<Movie> sortedMovieList = mng.sort(searchParam);

        for (int i = 0; i < sortedMovieList.size(); i++){
            Movie movie = sortedMovieList.get(i);
            String ranking = movie.getRanking();
            String year = movie.getReleaseDate().getYear();
            String runTime = movie.getRunTime() + "m";
            if(runTime.length() == 3){
                runTime = " " + runTime;
            }
            shownList.add(ranking + " | " + year + " | " + runTime + " | " + movie.getTitle());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, shownList){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView textView = (TextView)super.getView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };
        sortList.setAdapter(arrayAdapter);

    }
}
