package com.example.competedata11.person;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.competedata11.HomeActivity;
import com.example.competedata11.R;
import com.example.competedata11.news.NewsDetailedActivity;

public class PerInfoActivity extends AppCompatActivity {

    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_info);
        initView();
    }

    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PerInfoActivity.this, HomeActivity.class);
                intent.putExtra("id",3);
                startActivity(intent);
            }
        });
    }
}