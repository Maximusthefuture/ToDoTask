package com.example.maximus.vitaminreminder.data;

import com.example.maximus.vitaminreminder.data.newsdata.News;

import java.util.List;

public interface NewsListRepository {

    interface OnFinishedListener {

        void onFinished(List<News> newsArrayList);

        void onFailure(Throwable t);
    }

    void getNewsList(OnFinishedListener onFinishedListener, String query); //Page number no needed
}
