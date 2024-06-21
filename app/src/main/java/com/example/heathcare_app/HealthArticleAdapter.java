package com.example.heathcare_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heathcare_app.model.Article;

import java.util.List;

public class HealthArticleAdapter extends BaseAdapter {

    private Context context;
    private List<Article> articleList;

    public HealthArticleAdapter(Context context, List<Article> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    @Override
    public int getCount() {
        return articleList.size();
    }

    @Override
    public Object getItem(int position) {
        return articleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_health_article, parent, false);
        }

        Article article = articleList.get(position);

        ImageView articleImage = convertView.findViewById(R.id.articleImage);
        TextView articleTitle = convertView.findViewById(R.id.articleTitle);
        TextView articleDescription = convertView.findViewById(R.id.articleDescription);

        Glide.with(context).load(article.getImage()).into(articleImage);
        articleTitle.setText(article.getTitle());
        articleDescription.setText(article.getDescription());

        return convertView;
    }
}

