package com.example.daniel.discovermovies_3.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.daniel.discovermovies_3.DetailsActivity;
import com.example.daniel.discovermovies_3.R;
import com.example.daniel.discovermovies_3.model.Movie;
import com.example.daniel.discovermovies_3.utils.MoviesConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 4/27/17.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private static final String BASE_URL_IMG = "https://image.tmdb.org/t/p/w150";

    private List<Movie> movies;
    private int rowLayout;
    private Context context;


    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        ImageView moviePosther;

        List<Movie> movies = new ArrayList<>();
        Context context;

        public MovieViewHolder(View v, Context context, List<Movie> movies) {
            super(v);
            v.setOnClickListener(this) ;
            this.context = context;
            this.movies = movies;
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);
            moviePosther = (ImageView) v.findViewById(R.id.movieCDPosther);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Movie movie = this.movies.get(position);
            //Here we launch the DetailsActivity
            Intent intent = prepareIntent(movie);
            context.startActivity(intent);
        }

        private Intent prepareIntent(Movie movie){
            Intent intent = new Intent(this.context, DetailsActivity.class);
            intent.putExtra(MoviesConstants.MOVIE_TITLE_EXTRA, movie.getOriginalTitle());
            intent.putExtra(MoviesConstants.MOVIE_DATA_EXTRA, movie.getReleaseDate());
            intent.putExtra(MoviesConstants.MOVIE_DESCRIPTION_EXTRA, movie.getOverview());
            intent.putExtra(MoviesConstants.MOVIE_RANK_EXTRA, movie.getVoteAverage().toString());
            intent.putExtra(MoviesConstants.MOVIE_POSTER_EXTRA, BASE_URL_IMG + movie.getPosterPath());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            return intent;
        }
    }

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view, context, movies);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());
        Glide.with(context.getApplicationContext())
                .load(BASE_URL_IMG + movies.get(position).getPosterPath())
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
                .centerCrop()
                .into(holder.moviePosther);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
