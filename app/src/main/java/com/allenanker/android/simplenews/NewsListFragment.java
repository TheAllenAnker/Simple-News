package com.allenanker.android.simplenews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;
import java.util.Random;

public class NewsListFragment extends Fragment {
    private List<News> mNewsList;
    private int mType;
    private RecyclerView mNewsRecyclerView;
    private NewsAdapter mNewsAdapter;
    private CallBacks mCallBacks;

    public NewsListFragment newInstance(int type) {
        NewsListFragment newsListFragment = new NewsListFragment();
        newsListFragment.setType(type);
        return newsListFragment;
    }

    public void setType(int type) {
        mType = type;
    }

    public interface CallBacks {
        void onNewsSelected(News news);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallBacks = (CallBacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBacks = null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        mNewsRecyclerView = view.findViewById(R.id.news_rv);
        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI(mType);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mImageView;
        private TextView mTextView_title;
        private TextView mTextView_des;
        private Button mCollectButton;
        private News mNews;

        private int flag;

        public NewsHolder(View itemView) {
            super(itemView);
            mTextView_title = itemView.findViewById(R.id.news_title);
            mTextView_des = itemView.findViewById(R.id.news_des);
            mCollectButton = itemView.findViewById(R.id.collect);
            mImageView = itemView.findViewById(R.id.news_img);
            itemView.setOnClickListener(this);

            mCollectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (flag) {
                        case 0:
                            mCollectButton.setActivated(false);
                            flag = 1;
                            NewsLab.get(getActivity()).addNews(mNews);
                            break;
                        case 1:
                            mCollectButton.setActivated(true);
                            flag = 0;
                            NewsLab.get(getActivity()).deleteNews(mNews);
                            break;
                    }
                }
            });
            Random random = new Random();
            int i = random.nextInt(9);
        }

        public void bind(News news) {
            mNews = news;
            if (NewsLab.get(getActivity()).getNews(mNews.getid()) == null) {
                flag = 0;
                mCollectButton.setActivated(true);
            } else {
                flag = 1;
                mCollectButton.setActivated(false);
            }
            mTextView_title.setText(news.getTitle());
            mTextView_des.setText(news.getDes());
            fillImageView("http:" + mNews.getImageUrl());
        }

        @Override
        public void onClick(View v) {
            mCallBacks.onNewsSelected(mNews);
        }

        private void fillImageView(String imageUrl) {
            Glide.with(getContext())
                    .load(imageUrl).into(mImageView);
        }
    }

    private class NewsAdapter extends RecyclerView.Adapter<NewsHolder> {

        Context mContext;
        List<News> mNewsList;

        public NewsAdapter(Context context, List<News> newsList) {
            mContext = context;
            mNewsList = newsList;
        }

        public void display(String url) {

        }

        @NonNull
        @Override
        public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, parent, false);
            return new NewsHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.bind(news);
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }

    public void updateUI(int type) {
        NewsLab newsLab = NewsLab.get(getActivity());
        mNewsList = newsLab.getNews(type);
        mNewsAdapter = new NewsAdapter(this.getActivity(), mNewsList);
        mNewsRecyclerView.setAdapter(mNewsAdapter);
        mNewsAdapter.notifyDataSetChanged();
        Glide.with(this).load(R.drawable.finalback).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                mNewsRecyclerView.setBackground(resource);
            }
        });
    }
}