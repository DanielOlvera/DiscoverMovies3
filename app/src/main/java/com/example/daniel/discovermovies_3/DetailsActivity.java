package com.example.daniel.discovermovies_3;

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

        moviePosther = (ImageView) findViewById(R.id.dMoviePosther);
        titleText = (TextView) findViewById(R.id.dTitle);
        yearText = (TextView) findViewById(R.id.dYear);
        detailTxt = (TextView) findViewById(R.id.dDetails);
        rankTxt = (TextView) findViewById(R.id.dRanking);

        titleText.setText(getIntent().getStringExtra("title"));
        if (!getIntent().getStringExtra("data").isEmpty()) {
            yearText.setText(getIntent().getStringExtra("data").substring(0, 4));
        } else {
            yearText.setText(getIntent().getStringExtra("data"));
        }
        detailTxt.setText(getIntent().getStringExtra("description"));
        rankTxt.setText(getIntent().getStringExtra("rating"));
        moviePosther.setImageResource(getIntent().getExtras().getInt("posther"));
    }
}
