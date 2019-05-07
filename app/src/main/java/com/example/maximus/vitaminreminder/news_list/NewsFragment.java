package com.example.maximus.vitaminreminder.news_list;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maximus.vitaminreminder.R;
import com.example.maximus.vitaminreminder.data.newsdata.News;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements NewsContract.View {

    public NewsAdapter newsAdapter;
    public List<News> newsList;
    public NewsContract.Presenter mPresenter;
    public RecyclerView recyclerView;
    public LinearLayoutManager linearLayoutManager;


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
        newsAdapter = new NewsAdapter(newsList);
//
        mPresenter = new NewsPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_news, container, false);
        // Inflate the layout for this fragment

        recyclerView = root.findViewById(R.id.rv_news_list);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(newsAdapter);
        mPresenter.start();
        return root;
    }

    @Override
    public void showNews() {
//        mPresenter.loadNews();
//        newsAdapter.replaceData(newsList);

    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        mPresenter.start();
//    }

    @Override
    public void showNoNews() {

    }

    @Override
    public void showLoadingIndicator() {

    }

    @Override
    public void setDataToRecyclerView(List<News> newsArrayList) {
        newsList.addAll(newsArrayList);
        newsAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        mPresenter = presenter;
    }


    public static class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

        private List<News> newsList;

        public NewsAdapter(List<News> news) {
            this.newsList = news;
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

            News news = newsList.get(position);

            holder.newsTitle.setText(news.getTitle());

            holder.newsDescription.setText(news.getDescription());

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

            public TextView newsTitle;

            public TextView newsDescription;

            public MyViewHolder(View itemView) {
                super(itemView);

                newsTitle = itemView.findViewById(R.id.news_title);

                newsDescription = itemView.findViewById(R.id.news_description);

            }
        }

    }




}



