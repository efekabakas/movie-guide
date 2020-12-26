package com.appmovieguide.movieguide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> listOptions;
    ArrayAdapter listAdapter;
    ListView options;
    ArrayList<String> filterByOptions;
    ArrayList<Integer> clickedItems;
    ArrayAdapter filterListAdapter;
    ListView filterListView;
    Button clickedItemButtons;
    EditText editText1, editText2, editText3, editText4, editText5;
    ArrayList<EditText> editTexts;
    Button goFilterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        editText1 = findViewById(R.id.editText1); editText2 = findViewById(R.id.editText2); editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4); editText5 = findViewById(R.id.editText5);

        editTexts = new ArrayList<>();
        editTexts.add(editText1); editTexts.add(editText2); editTexts.add(editText3); editTexts.add(editText4); editTexts.add(editText5);

        for (int i = 0; i < editTexts.size(); i++){
            editTexts.get(i).setVisibility(View.INVISIBLE);
        }

        clickedItems = new ArrayList<>();
        filterListView = findViewById(R.id.filterList);
        filterListView.setVisibility(View.INVISIBLE);

        goFilterButton = findViewById(R.id.goFilter);
        goFilterButton.setVisibility(View.INVISIBLE);

        clickedItemButtons = findViewById(R.id.clickedItemButtons);
        clickedItemButtons.setVisibility(View.INVISIBLE);

        options = findViewById(R.id.sortOptions);
        options.setVisibility(View.INVISIBLE);
        listOptions = new ArrayList();

        //default initialize
        listOptions.add("A to Z");
        listOptions.add("Z to A");
        listOptions.add("Year ▲");
        listOptions.add("Year ▼");
        listOptions.add("Ranking ▲");
        listOptions.add("Ranking ▼");
        listOptions.add("Run Time ▲");
        listOptions.add("Run Time ▼");

        listAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, listOptions){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView textView = (TextView)super.getView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };

        options.setAdapter(listAdapter);

        filterByOptions = new ArrayList<>();
        filterByOptions.add("Ranking");
        filterByOptions.add("Year");
        filterByOptions.add("Run Time");
        filterByOptions.add("Star");
        filterByOptions.add("Director");

        filterListAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_multiple_choice, filterByOptions){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView textView = (TextView)super.getView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };

        filterListView = findViewById(R.id.filterList);
        filterListView.setAdapter(filterListAdapter);
        filterListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);



    }
    public void filterBy(View view){
        clickedItems.clear();
        if(filterListView.getVisibility() == View.VISIBLE){
            filterListView.setVisibility(View.INVISIBLE);
            clickedItemButtons.setVisibility(View.INVISIBLE);
            for (int i = 0; i < editTexts.size(); i++){
                editTexts.get(i).setVisibility(View.INVISIBLE);
            }
            filterListView.clearChoices();
            filterListAdapter.notifyDataSetChanged();
            goFilterButton.setVisibility(View.INVISIBLE);
        }
        else{
            filterListView.setVisibility(View.VISIBLE);
            clickedItemButtons.setVisibility(View.VISIBLE);
            for (int i = 0; i < editTexts.size(); i++){
                editTexts.get(i).setVisibility(View.INVISIBLE);
            }
            filterListView.clearChoices();
            filterListAdapter.notifyDataSetChanged();
            goFilterButton.setVisibility(View.INVISIBLE);

        }
        filterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //clickedItems.add(i);
            }
        });




    }

    public void search(View view) throws IOException{
        EditText editText = findViewById(R.id.searchText);
        Intent i = new Intent(getApplicationContext(), search_act.class);
        i.putExtra("searchParameter", editText.getText().toString().toUpperCase());


        startActivity(i);

    }
    public void next(View view){

        goFilterButton.setVisibility(View.VISIBLE);
        SparseBooleanArray checked = filterListView.getCheckedItemPositions();

        for(int i = 0; i < filterByOptions.size(); i++){
            if(checked.get(i))
                clickedItems.add(i);

        }
        checked.clear();


        filterListView.setVisibility(View.INVISIBLE);
        clickedItemButtons.setVisibility(View.INVISIBLE);
        int size = clickedItems.size();
        if(size == 0){

        }
        else{
            for(int i = 0; i < size; i++){
                editTexts.get(i).setVisibility(View.VISIBLE);
                editTexts.get(i).setHint(filterByOptions.get(clickedItems.get(i)));
            }
        }


    }

    public void goFilter(View view){
        Intent intent = new Intent(getApplicationContext(), activity_filter.class);
        String ranking = "", year = "", runTime = "", star = "", director = "";
        for(int i = 0; i < clickedItems.size(); i++){
            if(filterByOptions.get(clickedItems.get(i)) == "Ranking"){
                ranking = editTexts.get(i).getText().toString();
            }
            else if(filterByOptions.get(clickedItems.get(i)) == "Year"){
                year = editTexts.get(i).getText().toString();
            }
            else if(filterByOptions.get(clickedItems.get(i)) == "Run Time"){
                runTime = editTexts.get(i).getText().toString();
            }
            else if(filterByOptions.get(clickedItems.get(i)) == "Star"){
                star = editTexts.get(i).getText().toString();
            }
            else if(filterByOptions.get(clickedItems.get(i)) == "Director"){
                director = editTexts.get(i).getText().toString();
            }
        }
        intent.putExtra("ranking", ranking);
        intent.putExtra("year", year);
        intent.putExtra("runTime", runTime);
        intent.putExtra("star", star);
        intent.putExtra("director", director);

        startActivity(intent);

    }

    public void sort(View view){




        if(options.getVisibility() == View.VISIBLE){
            options.setVisibility(View.INVISIBLE);
        }
        else{
            options.setVisibility(View.VISIBLE);

            //Click Listener
            options.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getApplicationContext(), DetailedSortActivity.class);
                    intent.putExtra("index", i);
                    startActivity(intent);
                }
            });
        }



    }


}
