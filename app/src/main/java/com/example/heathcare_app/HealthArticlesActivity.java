package com.example.heathcare_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HealthArticlesActivity extends AppCompatActivity {

    private ListView articleListView;
    private List<HealthArticle> articleList;
    private HealthArticleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles);

        articleListView = findViewById(R.id.articleListView);

        articleList = new ArrayList<>();
        articleList.add(new HealthArticle("Bài báo 1", "Mô tả bài báo 1", "https://picsum.photos/200", "Nội dung bài báo 1"));
        articleList.add(new HealthArticle("Bài báo 2", "Mô tả bài báo 2", "https://picsum.photos/200", "Nội dung bài báo 2"));
        // Thêm nhiều bài báo khác vào articleList...

        adapter = new HealthArticleAdapter(this, articleList);
        articleListView.setAdapter(adapter);

        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HealthArticle selectedArticle = articleList.get(position);
                Intent intent = new Intent(HealthArticlesActivity.this, HealthArticlesDetailsActivity.class);
                intent.putExtra("title", selectedArticle.getTitle());
                intent.putExtra("description", selectedArticle.getDescription());
                intent.putExtra("imageUrl", selectedArticle.getImageUrl());
                intent.putExtra("content", selectedArticle.getContent());
                startActivity(intent);
            }
        });
    }
}
