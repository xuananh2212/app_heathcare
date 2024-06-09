package com.example.heathcare_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class HealthArticlesDetailsActivity extends AppCompatActivity {

    private ImageView articleImage;
    private TextView articleTitle, articleDescription, articleContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles_details);
        articleImage = findViewById(R.id.articleImage);
        articleTitle = findViewById(R.id.articleTitle);
        articleDescription = findViewById(R.id.articleDescription);
        articleContent = findViewById(R.id.articleContent);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        String imageUrl = intent.getStringExtra("imageUrl");
        String content = intent.getStringExtra("content");

        Glide.with(this).load(imageUrl).into(articleImage);
        articleTitle.setText(title);
        articleDescription.setText(description);
        articleContent.setText(content);
    }
}
