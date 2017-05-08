package com.example.daniel.discovermovies_3.model;

import android.content.Intent;

import com.example.daniel.discovermovies_3.adapter.MoviesAdapter;
import com.example.daniel.discovermovies_3.utils.MoviesConstants;

/**
 * Created by Daniel on 5/4/17.
 */

public class MovieDetails {

    private String title;
    private String date;
    private String details;
    private String rank;
    private String posterPath;

    public MovieDetails fromExtras(Intent details) {
        this.title = details.getStringExtra(MoviesConstants.MOVIE_TITLE_EXTRA);
        this.date = retrieveDate(details.getStringExtra(MoviesConstants.MOVIE_DATA_EXTRA));
        this.details = details.getStringExtra(MoviesConstants.MOVIE_DESCRIPTION_EXTRA);
        this.rank = details.getStringExtra(MoviesConstants.MOVIE_RANK_EXTRA);
        this.posterPath = details.getStringExtra(MoviesConstants.MOVIE_POSTER_EXTRA);
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDetails() {
        return details;
    }

    public String getRank() {
        return rank;
    }

    public String getPosterPath() {
        return posterPath;
    }

    private String retrieveDate(String date) {
        if (!date.isEmpty()) {
            return date.substring(0, 4);
        } else {
            return date;
        }
    }
}
