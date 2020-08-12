package com.shubamvirdi.newsapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shubamvirdi.newsapp.Activities.DetailedNews;
import com.shubamvirdi.newsapp.ModelClasses.Article;
import com.shubamvirdi.newsapp.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private static final String TAG = NewsAdapter.class.getSimpleName();
    private List<Article> mList;
    private Context context;

    public NewsAdapter(List<Article> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.news_row, parent, false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.setData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView newsTitle, newsContent, newsDate, newsTime;
        private ImageView newsImage;
        private Article article;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsContent = itemView.findViewById(R.id.newsContent);
            newsDate = itemView.findViewById(R.id.newsDate);
            newsTime = itemView.findViewById(R.id.newsTime);
            newsImage = itemView.findViewById(R.id.newsImage);

            // click listener on the news card
            itemView.setOnClickListener(view -> {
                // pass the url to detailed news activity
                Intent i = new Intent(context, DetailedNews.class);
                i.putExtra("url", article.getUrl());
                context.startActivity(i);
            });
        }

        public void setData(Article article) {
            // get the article object
            this.article = article;

            // set the title of news
            newsTitle.setText(article.getTitle());

            // set the content of the news
            newsContent.setText(article.getContent());

            // extract the published at from news
            String publishedAt = article.getPublishedAt();

            // spilt date and time
            String[] dateAndTime = publishedAt.split("T");

            // get the date from published at
            String date = dateAndTime[0];

            // get the time from published at ignoring T at end
            String time = dateAndTime[1].substring(0, dateAndTime[1].length() - 1);

            // set date to news
            newsDate.setText("Date: " + date);

            // set time to news
            newsTime.setText("Time: " + time);

            // if news image is not null
            if (article.getUrlToImage() != null) {
                // load the image of the news
                Glide.with(context).load(article.getUrlToImage()).into(newsImage);
            } else {
                // load the placeholder news image
                Glide.with(context).load(R.drawable.news_placeholder).into(newsImage);
            }
        }
    }
}
