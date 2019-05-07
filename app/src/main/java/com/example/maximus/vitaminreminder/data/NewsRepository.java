package com.example.maximus.vitaminreminder.data;

import android.util.Log;

import com.example.maximus.vitaminreminder.data.newsdata.News;
import com.example.maximus.vitaminreminder.data.newsdata.NewsListResponse;
import com.example.maximus.vitaminreminder.network.ApiClient;
import com.example.maximus.vitaminreminder.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.maximus.vitaminreminder.network.ApiClient.API_KEY;

public class NewsRepository implements NewsListRepository {

    private final String TAG = "NewsRepository";


    @Override
    public void getNewsList(final OnFinishedListener onFinishedListener, String query) {


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<NewsListResponse> call = apiService.getEverything(query, API_KEY);
        call.enqueue(new Callback<NewsListResponse>() {
            @Override
            public void onResponse(Call<NewsListResponse> call, Response<NewsListResponse> response) {
                List<News> news = response.body().getArticles();
                Log.d(TAG, "Number of news received: " + news.size());
                onFinishedListener.onFinished(news);
            }

            @Override
            public void onFailure(Call<NewsListResponse> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }
}
