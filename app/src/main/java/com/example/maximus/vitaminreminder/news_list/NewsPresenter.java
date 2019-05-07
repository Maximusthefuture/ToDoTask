package com.example.maximus.vitaminreminder.news_list;

import com.example.maximus.vitaminreminder.data.NewsListRepository;
import com.example.maximus.vitaminreminder.data.NewsRepository;
import com.example.maximus.vitaminreminder.data.newsdata.News;

import java.util.List;


//TODO
public class NewsPresenter implements NewsContract.Presenter, NewsListRepository.OnFinishedListener {

    private NewsContract.View mView;

    NewsListRepository newsRepository;


    public NewsPresenter(NewsContract.View mView) {
        this.mView = mView;
        newsRepository = new NewsRepository();
    }

    @Override
    public void loadNews() {
        newsRepository.getNewsList(this, "medicine");
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void start() {
        newsRepository.getNewsList(this, "medicine");
    }


    @Override
    public void onFinished(List<News> newsArrayList) {
        mView.setDataToRecyclerView(newsArrayList);
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
