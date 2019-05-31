package com.example.maximus.vitaminreminder.news_list;


import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.maximus.vitaminreminder.R;
import com.example.maximus.vitaminreminder.data.newsdata.News;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment implements NewsContract.View {

    public NewsAdapter newsAdapter;
    public List<News> newsList;
    public NewsContract.Presenter mPresenter;
    public RecyclerView recyclerView;
    public LinearLayoutManager linearLayoutManager;
    private TextView pleaseCheckConnection;
    private ProgressBar progressBar;


    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newsList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        newsAdapter = new NewsAdapter(newsList, getContext());

        mPresenter = new NewsPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_news, container, false);
        // Inflate the layout for this fragment


        final ScrollSwipeRefreshLayout swipeRefreshLayout =
                root.findViewById(R.id.refresh_layout);



        progressBar = root.findViewById(R.id.progress_bar);
        recyclerView = root.findViewById(R.id.rv_news_list);
        pleaseCheckConnection = root.findViewById(R.id.please_check_connection);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(newsAdapter);

        swipeRefreshLayout.setScrollUp(recyclerView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadNews(false);
                newsAdapter.notifyDataSetChanged();
            }
        });


        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void showNews() {
//        mPresenter.loadNews();
//        newsAdapter.replaceData(newsList);
        pleaseCheckConnection.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        newsAdapter.notifyDataSetChanged();


    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
//        showNews();
    }

    @Override
    public void showNoNews() {
        pleaseCheckConnection.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingIndicator(final boolean active) {
//        progressBar.setVisibility(View.VISIBLE);
//        recyclerView.setVisibility(View.INVISIBLE);
//
        final SwipeRefreshLayout swipe =
                getView().findViewById(R.id.refresh_layout);

        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(active);
            }
        });


    }

    @Override
    public void setDataToRecyclerView(List<News> newsArrayList) {
        newsList.addAll(newsArrayList);
        newsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    public static class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
        Context context;

        private List<News> newsList;

        public NewsAdapter(List<News> news, Context context) {
            this.newsList = news;
            this.context = context;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            View itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.news_card, viewGroup, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            final News news = newsList.get(position);

            holder.newsTitle.setText(news.getTitle());

            holder.newsDescription.setText(news.getDescription());

            Glide.with(context)
                    .load(news.getUrlToImage())
                    .into(holder.newsImage);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, news.getUrl(), Toast.LENGTH_SHORT).show();

                }
            });

        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }

        public void replaceData(List<News> list) {
            setList(list);
            notifyDataSetChanged();
        }

        public void setList(List<News> list) {
            newsList = list;
        }

        public  static class MyViewHolder extends RecyclerView.ViewHolder {

            private TextView newsTitle;

            private TextView newsDescription;

            private ImageView newsImage;


            public MyViewHolder(View itemView) {
                super(itemView);

                newsTitle = itemView.findViewById(R.id.news_title);

                newsDescription = itemView.findViewById(R.id.news_description);

                newsImage = itemView.findViewById(R.id.image_news);

            }
        }

    }
}



