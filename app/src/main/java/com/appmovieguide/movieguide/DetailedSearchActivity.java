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

public class DetailedSearchActivity extends AppCompatActivity {
    String argumant;
    int decision;
    TextView movieTitle, genre, ranking, budget, worldwideGross, releaseDate, director, runTime, player, directorName, castText;
    ListView cast, movieListMovie, movieListDirector;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_search);
        movieTitle = findViewById(R.id.movieTitle); genre = findViewById(R.id.genre); ranking = findViewById(R.id.ranking);
        budget = findViewById(R.id.budget); worldwideGross = findViewById(R.id.worldwideGross); releaseDate = findViewById(R.id.releaseDate);
        director = findViewById(R.id.director); runTime = findViewById(R.id.runTime); player = findViewById(R.id.playerName); directorName = findViewById(R.id.directorName);
        cast = findViewById(R.id.castList); movieListMovie = findViewById(R.id.playerList); movieListDirector = findViewById(R.id.directorMovieList);
        castText = findViewById(R.id.castText);

        player.setVisibility(View.INVISIBLE); director.setVisibility(View.INVISIBLE); ranking.setVisibility(View.INVISIBLE); releaseDate.setVisibility(View.INVISIBLE);
        budget.setVisibility(View.INVISIBLE); worldwideGross.setVisibility(View.INVISIBLE); movieListDirector.setVisibility(View.INVISIBLE); movieListMovie.setVisibility(View.INVISIBLE);
        movieTitle.setVisibility(View.INVISIBLE); genre.setVisibility(View.INVISIBLE); cast.setVisibility(View.INVISIBLE); directorName.setVisibility(View.INVISIBLE);
        runTime.setVisibility(View.INVISIBLE); castText.setVisibility(View.INVISIBLE);


        Intent intent = getIntent();
        argumant = intent.getStringExtra("argumant");

        decision = intent.getIntExtra("decision", -1);

        if(decision == 0){
            //Log.d("State", argumant);
            String[] splitArr = argumant.split(",");
            movieTitle.setVisibility(View.VISIBLE);
            movieTitle.setText(splitArr[0]);

            genre.setVisibility(View.VISIBLE);
            genre.setText(splitArr[1]);

            ranking.setVisibility(View.VISIBLE);
            ranking.setText(splitArr[2]);

            budget.setVisibility(View.VISIBLE);
            budget.setText(splitArr[3]);

            worldwideGross.setVisibility(View.VISIBLE);
            worldwideGross.setText(splitArr[4]);

            releaseDate.setVisibility(View.VISIBLE);
            releaseDate.setText(splitArr[5]);

            director.setVisibility(View.VISIBLE);
            director.setText(splitArr[6]);

            runTime.setVisibility(View.VISIBLE);
            runTime.setText(splitArr[7]);

            castText.setVisibility(View.VISIBLE);

            String[] arr = splitArr[8].split("=");
            ArrayList<String> castListMovie = new ArrayList<String>();
            castListMovie.add(arr[1]);
            for(int i = 9; i < splitArr.length; i++){
                castListMovie.add(splitArr[i]);
            }
            ArrayAdapter listAdapter = new ArrayAdapter(DetailedSearchActivity.this, android.R.layout.simple_list_item_1, castListMovie){
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    TextView textView = (TextView)super.getView(position, convertView, parent);
                    textView.setTextColor(Color.WHITE);
                    return textView;
                }
            };
            cast.setVisibility(View.VISIBLE);
            cast.setAdapter(listAdapter);
        }
        else if(decision == 1){
            String[] splitArr = argumant.split(",");
            player.setVisibility(View.VISIBLE);
            player.setText(splitArr[0]);

            ArrayList<String> playerMovieList = new ArrayList<String>();
            for(int i = 1; i < splitArr.length; i++){
                playerMovieList.add(splitArr[i]);
            }
            ArrayAdapter listAdapter = new ArrayAdapter(DetailedSearchActivity.this, android.R.layout.simple_list_item_1, playerMovieList){
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    TextView textView = (TextView)super.getView(position, convertView, parent);
                    textView.setTextColor(Color.WHITE);
                    return textView;
                }
            };
            movieListMovie.setVisibility(View.VISIBLE);
            movieListMovie.setAdapter(listAdapter);
            //Log.d("State", argumant);


        }
        else if(decision == 2){
            Log.d("State", argumant);
            String[] splitArr = argumant.split(",");
            directorName.setVisibility(View.VISIBLE);
            directorName.setText(splitArr[0]);

            ArrayList<String> directorMovieList = new ArrayList<String>();
            for(int i = 1; i < splitArr.length; i++){
                directorMovieList.add(splitArr[i]);
            }
            ArrayAdapter listAdapter = new ArrayAdapter(DetailedSearchActivity.this, android.R.layout.simple_list_item_1, directorMovieList){
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    TextView textView = (TextView)super.getView(position, convertView, parent);
                    textView.setTextColor(Color.WHITE);
                    return textView;
                }
            };
            movieListMovie.setVisibility(View.VISIBLE);
            movieListMovie.setAdapter(listAdapter);
        }
    }
}
