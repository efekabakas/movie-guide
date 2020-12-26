package com.appmovieguide.movieguide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class activity_filter extends AppCompatActivity {
    Management mng;
    ListView listView;
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        Intent intent = getIntent();
        String ranking = intent.getStringExtra("ranking");
        String year = intent.getStringExtra("year");
        String runTime = intent.getStringExtra("runTime");
        String star = intent.getStringExtra("star");
        String director = intent.getStringExtra("director");
        mng = new Management();

        filterMovies(ranking, year, runTime, star, director);



    }
    void filterMovies(String ranking, String year, String runTime, String star, String director){
        mng.readDatabase(getApplicationContext());
        ArrayList<Movie> filteredMovies = mng.filterBy(ranking,year,runTime,star,director);
        ArrayList<String> filteredMoviesNames = new ArrayList();

        for(int i = 0; i < filteredMovies.size(); i++){
            if(filteredMovies.get(i) != null) {
                Movie movie = filteredMovies.get(i);
                String addedMovie = movie.getRanking() + " | " + movie.getReleaseDate().getYear() + " | " + movie.getRunTime() + " | " + movie.getDirector().getName() + " | " + movie.getTitle();
                filteredMoviesNames.add(addedMovie);
            }
        }

        listView = findViewById(R.id.filteredMovies);
        arrayAdapter = new ArrayAdapter(activity_filter.this, android.R.layout.simple_list_item_1,filteredMoviesNames){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView textView = (TextView)super.getView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };

        listView.setAdapter(arrayAdapter);
    }
}
