package com.example.heathcare_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.heathcare_app.api.ApiService;
import com.example.heathcare_app.model.Article;
import com.example.heathcare_app.model.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthArticlesActivity extends AppCompatActivity {

    private ListView articleListView;
    private List<Article> articleList;
    private HealthArticleAdapter articleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles);

        articleListView = findViewById(R.id.articleListView);
        fetchArticles();
        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article selectedArticle = articleList.get(position);
                Intent intent = new Intent(HealthArticlesActivity.this, HealthArticlesDetailsActivity.class);
                intent.putExtra("title", selectedArticle.getTitle());
                intent.putExtra("description", selectedArticle.getDescription());
                intent.putExtra("imageUrl", selectedArticle.getImage());
                intent.putExtra("content", selectedArticle.getContent());
                startActivity(intent);
            }
        });
    }
    private void fetchArticles() {
        Call<ArticleResponse> call = ApiService.apiService.getArticles();
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                if (response.isSuccessful()) { // Đây là phương thức kiểm tra response code
                    ArticleResponse apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getData() != null) {
                        List<Article> articles = apiResponse.getData().getArticles();
                        articleList = new ArrayList<>();
                        articleList.addAll(articles);
                        articleAdapter = new HealthArticleAdapter(HealthArticlesActivity.this, articleList);
                        articleListView.setAdapter(articleAdapter);
                    }
                } else {
                    // Xử lý lỗi từ server
                    Log.e("MainActivity", "Server Error");
                }
            }
            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                t.printStackTrace();
                // Handle network error
            }
        });
    }
}
