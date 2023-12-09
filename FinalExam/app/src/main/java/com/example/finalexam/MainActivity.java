package com.example.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    ListView listView;
    ArrayList<String> countriesList;
    ArrayAdapter<String > countriesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.dog_616554);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        searchView = (SearchView)findViewById(R.id.searchView);
        listView = (ListView)findViewById(R.id.countriesListView);

        countriesList = new ArrayList<>();
        countriesList.add("Canada");
        countriesList.add("France");
        countriesList.add("Japan");
        countriesList.add("USA");

        countriesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,countriesList);
        listView.setAdapter(countriesAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String country = countriesAdapter.getItem(i);
                Toast.makeText(MainActivity.this, "Country name: "+country, Toast.LENGTH_LONG).show();

                if(country.equals("USA")){
                    startActivity(new Intent(getApplicationContext(),BrowserActivity.class));
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (countriesList.contains(query)) {
                    countriesAdapter.getFilter().filter(query);
                } else {
                    Toast.makeText(MainActivity.this, "No Match found", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                countriesAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}