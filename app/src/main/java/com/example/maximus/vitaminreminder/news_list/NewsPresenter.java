package com.example.maximus.vitaminreminder.news_list;

import com.example.maximus.vitaminreminder.data.NewsListRepository;
import com.example.maximus.vitaminreminder.data.NewsRepository;
import com.example.maximus.vitaminreminder.data.newsdata.News;

import java.util.List;


//TODO
public class NewsPresenter implements NewsContract.Presenter {

    private NewsContract.View mView;

    private NewsListRepository newsRepository;


    public NewsPresenter(NewsContract.View mView) {
        this.mView = mView;
        newsRepository = new NewsRepository();
    }

    @Override
    public void loadNews(boolean forceUpdate) {
        loadNews(forceUpdate, true);
    }


    private void loadNews(boolean forceUpdate, final boolean showLoadingUi) {

            if (showLoadingUi) {
                mView.showLoadingIndicator(true);
            }
            newsRepository.getNewsList(new NewsListRepository.OnFinishedListener() {
                @Override
                public void onFinished(List<News> newsArrayList) {

                    mView.setDataToRecyclerView(newsArrayList);
                    mView.showNews();

                    if (showLoadingUi) {
                        mView.showLoadingIndicator(false);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    mView.showNoNews();
                    mView.showLoadingIndicator(false);
                }
            }, "medicine");

        }




    @Override
    public void onDestroy() {
        this.mView = null;
    }

    @Override
    public void start() {
      loadNews(false);

    }

}
