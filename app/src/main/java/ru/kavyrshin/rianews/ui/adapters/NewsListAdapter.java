package ru.kavyrshin.rianews.ui.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.kavyrshin.rianews.R;
import ru.kavyrshin.rianews.domain.global.models.News;

public class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface NewsListAdapterListener {
        void onNewsClick(String url);
    }

    public static final int NEWS_VIEW_TYPE = 123;

    private NewsListAdapterListener listener;

    private ArrayList<News> newsArrayList = new ArrayList();


    public NewsListAdapter(NewsListAdapterListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, parent, false);

        return (RecyclerView.ViewHolder) new NewsViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == NEWS_VIEW_TYPE) {
            NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
            newsViewHolder.onBind(newsArrayList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return NEWS_VIEW_TYPE;
    }

    public void setNewsArrayList(List<News> newsList) {
        this.newsArrayList.addAll(newsList);
        notifyDataSetChanged();
    }

    public void clearNewsArrayList() {
        newsArrayList.clear();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private News news;
        private NewsListAdapterListener listener;

        private ImageView imageView;
        private TextView tvTitle;
        private TextView tvSubTitle;

        public NewsViewHolder(View itemView, NewsListAdapterListener newsListAdapterListener) {
            super(itemView);
            this.listener = newsListAdapterListener;

            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            this.tvSubTitle = (TextView) itemView.findViewById(R.id.tvSubTitle);
        }

        public void onBind(News news) {
            this.news = news;

            Picasso.get().load(news.getImgUrl()).into(imageView);
            tvTitle.setText(news.getName());
        }

        @Override
        public void onClick(View view) {

        }
    }

}