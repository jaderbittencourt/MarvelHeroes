package com.marvel.jaderbittencourt.marvelheroes.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.marvel.jaderbittencourt.marvelheroes.R;
import com.squareup.picasso.Picasso;

public class ActivityDetails extends AppCompatActivity {

    ImageView thumbnail;
    TextView heroName;
    TextView heroDescription;
    TextView comics;
    TextView series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        loadViews();
        loadInfo();
    }

    private void loadViews() {
        this.thumbnail = findViewById(R.id.detailThumbnail);
        this.heroName = findViewById(R.id.detailHeroName);
        this.heroDescription = findViewById(R.id.detailHeroDescription);
        this.comics = findViewById(R.id.detailHeroComics);
        this.series = findViewById(R.id.detailHeroSeries);
    }

    private void loadInfo() {
        Bundle bundle = getIntent().getExtras();

        this.heroName.setText(bundle.getString("name"));
        this.heroDescription.setText(bundle.getString("description"));
        this.comics.setText(bundle.getString("comics"));
        this.series.setText(bundle.getString("series"));
        Picasso.with(getApplicationContext()).load(bundle.getString("thumbnail")).into(this.thumbnail);
    }
}
