package com.example.daniel.discovermovies_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.daniel.discovermovies_3.model.MovieDetails;

public class DetailsActivity extends AppCompatActivity {

    private ImageView moviePosther;
    private TextView titleText;
    private TextView yearText;
    private TextView detailTxt;
    private TextView rankTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initViews();
        loadDetails(getIntent());
    }

    private void initViews(){
        moviePosther = (ImageView) findViewById(R.id.dMoviePosther);
        titleText = (TextView) findViewById(R.id.dTitle);
        yearText = (TextView) findViewById(R.id.dYear);
        detailTxt = (TextView) findViewById(R.id.dDetails);
        rankTxt = (TextView) findViewById(R.id.dRanking);
    }

    private void loadDetails(Intent intent){
        if (intent.getExtras() != null) {
            MovieDetails movieDetails = new MovieDetails().fromExtras(intent);
            titleText.setText(movieDetails.getTitle());
            yearText.setText(movieDetails.getDate());
            detailTxt.setText(movieDetails.getDetails());
            rankTxt.setText(movieDetails.getRank());
            Glide.with(this)
                    .load(movieDetails.getPosterPath())
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(moviePosther);
        }
    }
}
