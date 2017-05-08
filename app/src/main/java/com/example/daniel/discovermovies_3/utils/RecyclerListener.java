package com.example.daniel.discovermovies_3.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Daniel on 5/3/17.
 */

public abstract class RecyclerListener extends RecyclerView.OnScrollListener {

    LinearLayoutManager linearManager;

    public RecyclerListener(LinearLayoutManager linearLayoutManager) {
        this.linearManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = linearManager.getChildCount();
        int totalItemCount = linearManager.getItemCount();
        int firstVisibleItemPosition = linearManager.findFirstVisibleItemPosition();

        if (!isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0) {
                loadMoreItems();
            }
        }

    }

    protected abstract void loadMoreItems();

    public abstract boolean isLastPage();

}
