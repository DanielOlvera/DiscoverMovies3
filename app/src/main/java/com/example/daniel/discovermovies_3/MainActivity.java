package com.example.daniel.discovermovies_3;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.daniel.discovermovies_3.adapter.MoviesAdapter;
import com.example.daniel.discovermovies_3.api.ApiClient;
import com.example.daniel.discovermovies_3.api.ApiInterface;
import com.example.daniel.discovermovies_3.model.Movie;
import com.example.daniel.discovermovies_3.model.MoviesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button starSearchBtn;
    private Button searchBtn;
    private EditText movieName;
    private RelativeLayout relativeLayout;

    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "64109f042c7753f1e85ebb645d55cee8";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        starSearchBtn = (Button) findViewById(R.id.mStartSearch);
        starSearchBtn.setOnClickListener(this);
        searchBtn = (Button) findViewById(R.id.submit_search);
        searchBtn.setOnClickListener(this);
        relativeLayout = (RelativeLayout) findViewById(R.id.mRelativeLay);
        movieName = (EditText) findViewById(R.id.search_field);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_LONG).show();
            return;
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.mStartSearch){
            starSearchBtn.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
        }
        if(view.getId() == R.id.submit_search){
            //TODO: Place here teh logic for the search

            String name = movieName.getText().toString();
            final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);

            Call<MoviesResponse> call = apiService.getSearch(API_KEY, name);
            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    int statusCode = response.code();
                    List<Movie> movies = response.body().getResults();
                    recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                }
            });
        }
    }
}
