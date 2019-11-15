package com.example.feliscatus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.feliscatus.database.AppDatabase;

public class CatDetailActivity extends AppCompatActivity {
    ConstraintLayout catConstraintLayout;
    TextView breedTV;
    TextView originTV;
    TextView descTV;
    TextView tempTV;
    TextView weightTV;
    TextView lifespanTV;
    TextView dogfriendTV;
    ImageView catImageView;
    TextView linkTV;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_breed_detail);

        catConstraintLayout = findViewById(R.id.cat_search);
        breedTV = catConstraintLayout.findViewById(R.id.cat_search);
        originTV = findViewById(R.id.origin);
        descTV = findViewById(R.id.desc);
        tempTV = findViewById(R.id.temp);
        weightTV = findViewById(R.id.weight);
        lifespanTV = findViewById(R.id.lifespan);
        dogfriendTV = findViewById(R.id.friendliness);
        catImageView = findViewById(R.id.cat_image);
        linkTV = findViewById(R.id.link);

        Intent intent = getIntent();

        String breed = intent.getStringExtra("breed");
        AppDatabase db = AppDatabase.getInstance(this);
        Cat cat = db.catDao().findCatByBreed(breed);

        breedTV.setText(cat.getBreed());
        originTV.setText(cat.getOrigin());
        descTV.setText(cat.getDesc());
        tempTV.setText(cat.getTemp());
        weightTV.setText(cat.getWeight());
        lifespanTV.setText(cat.getLifespan());
        dogfriendTV.setText(cat.getDogfriend());
        linkTV.setText(cat.getLink());

        String imageUrl = cat.getCatImage();
        //Glide.with(this).load(imageUrl).into(coverImageView);


    }
}
