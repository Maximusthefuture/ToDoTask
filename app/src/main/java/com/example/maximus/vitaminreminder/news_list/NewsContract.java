package com.example.maximus.vitaminreminder.news_list;

import com.example.maximus.vitaminreminder.BasePresenter;
import com.example.maximus.vitaminreminder.BaseView;
import com.example.maximus.vitaminreminder.data.newsdata.News;

import java.util.List;

public interface NewsContract {

    interface View extends BaseView<Presenter> {

        void showNews();

        void showNoNews();

        void showLoadingIndicator(boolean active);

        void setDataToRecyclerView(List<News> newsArrayList);
    }


    interface Presenter extends BasePresenter {

        void loadNews(boolean forceUpdate);

    }
}
