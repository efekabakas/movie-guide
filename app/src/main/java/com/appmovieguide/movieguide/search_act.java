package com.appmovieguide.movieguide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class search_act extends AppCompatActivity {
    ListView listView;

    Management mng = new Management();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_act);
        mng.readDatabase(getApplicationContext());
        listView = findViewById(R.id.searchList);

        Intent intent = getIntent();
        String searchParameter = intent.getStringExtra("searchParameter");



        List<ArrayList<Object>> deneme = mng.search(searchParameter);

        final ArrayList<String> listA = (ArrayList<String>)(Object)deneme.get(0);
        final int sizeMovie = deneme.get(0).size();
        String sizePart = listA.size() + " movies have found!";
        listA.add(0,sizePart);
        final ArrayList<String> listB = (ArrayList<String>)(Object)deneme.get(1);
        ArrayList<String> listC = (ArrayList<String>)(Object)deneme.get(2);
        sizePart = listB.size() + " players have found!";
        listA.add(sizePart);
        for (int i = 0; i < listB.size(); i++){
            listA.add(listB.get(i));


        }
        sizePart = listC.size() + " directors have found!";
        listA.add(sizePart);
        for (int i = 0; i < listC.size(); i++){
            listA.add(listC.get(i));
        }



        ArrayAdapter listAdapter = new ArrayAdapter(search_act.this, android.R.layout.simple_list_item_1, listA){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView textView = (TextView)super.getView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(i != 0 && i != sizeMovie+1 && i != sizeMovie + listB.size() + 2){
                    int decision = -1;   //first installation
                    String argumant = "empty";

                    if(i < sizeMovie + 1){
                        decision = 0;
                        argumant = mng.getMovies().get(listA.get(i)).toString();
                    }
                    else if(i > sizeMovie + 1 && i < sizeMovie + listB.size() + 2){
                        decision = 1;
                        argumant = mng.getPlayers().get(listA.get(i)).toString().replace("[", "").replace("]", "");
                    }
                    else if(i> sizeMovie + listB.size() + 2){
                        decision = 2;
                        argumant = mng.getDirectors().get(listA.get(i)).toString().replace("[", "").replace("]", "");
                    }
                    Intent detailedActivityIntent = new Intent(getApplicationContext(), DetailedSearchActivity.class);
                    detailedActivityIntent.putExtra("decision", decision);
                    detailedActivityIntent.putExtra("argumant", argumant);

                    startActivity(detailedActivityIntent);



                }
            }
        });

    }




}
