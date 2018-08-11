package com.khan.maaz.uberapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.khan.maaz.uberapp.adapters.PlaceAdapter;
import com.khan.maaz.uberapp.listeners.OnItemClickListener;
import com.khan.maaz.uberapp.models.Place;

import java.util.ArrayList;
import java.util.List;

public class PlacesActivity extends AppCompatActivity {
    private Spinner user;
    ArrayAdapter<CharSequence> adapteruser;

    private ArrayList<Place> places;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        user = (Spinner) findViewById(R.id.user);
        adapteruser = ArrayAdapter.createFromResource(this, R.array.for_user, android.R.layout.simple_spinner_item);
        adapteruser.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        user.setAdapter(adapteruser);

        places = prepareDummyData();

        recyclerView = (RecyclerView) findViewById(R.id.list_view);



        PlaceAdapter adapter = new PlaceAdapter(places, new OnItemClickListener() {
            @Override
            public void onItemClick(Place place) {
                Toast.makeText(PlacesActivity.this, place.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PlacesActivity.this, routemap.class);
                startActivityForResult(intent, 0);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public ArrayList<Place> prepareDummyData() {
        ArrayList<Place> places = new ArrayList<Place>();
        Resources rs = getResources();
        String[] titles = rs.getStringArray(R.array.titles_of_list);
        String[] descriptions = rs.getStringArray(R.array.descriptions_of_list);
        for (int i = 0; i < titles.length; i++) {
            Place place = new Place(titles[i], descriptions[i]);
            places.add(place);
        }
        return places;
    }
}
