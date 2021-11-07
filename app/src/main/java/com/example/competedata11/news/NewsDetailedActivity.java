package com.example.competedata11.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.competedata11.HomeActivity;
import com.example.competedata11.R;
import com.example.competedata11.adapter.NewsListAdapter;
import com.example.competedata11.api.HttpbinServices;
import com.example.competedata11.bean.NewsListBean;
import com.example.competedata11.home.HomeSkip1Activity;
import com.example.competedata11.myview.MyListView;
import com.example.competedata11.utils.Constants;
import com.example.competedata11.utils.Jsonparse;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewsDetailedActivity extends AppCompatActivity {

    private ImageView ivBack;
    private ImageView ivNewsDetailed;
    private TextView tvNewsContent;
    private TextView tvContentnum;
    private EditText etContent;
    private ListView myviewNews;
    private NewsListBean.RowsBean rowsBean;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private NewsListAdapter newsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detailed);
        Intent intent=getIntent();
        rowsBean=(NewsListBean.RowsBean)intent.getSerializableExtra("news");
        retrofit=new Retrofit.Builder().baseUrl(Constants.WEB_URL).build();
        httpbinServices=retrofit.create(HttpbinServices.class);
        if(rowsBean==null){
            return;
        }
        setData();
        initView();
        initData();

    }

    private void setData() {
        Call<ResponseBody> call=httpbinServices.getNewsList();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string();
                    List<NewsListBean.RowsBean> rowsBeanList= Jsonparse.getmInstance().getNewsList(res);
                    newsListAdapter.setRowsBeanList(rowsBeanList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    private void initData() {
        tvNewsContent.setText(rowsBean.getContent());
        tvContentnum.setText("评论条数："+rowsBean.getCommentNum());
        Glide.with(this)
                .load(Constants.WEB_URL+rowsBean.getCover())
                .into(ivNewsDetailed);

    }

    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewsDetailedActivity.this, HomeActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
        ivNewsDetailed = findViewById(R.id.iv_news_detailed);
        tvNewsContent = findViewById(R.id.tv_news_content);
        tvContentnum = findViewById(R.id.tv_contentnum);
        etContent = findViewById(R.id.et_content);
        myviewNews = findViewById(R.id.list_detailed);
        newsListAdapter=new NewsListAdapter(NewsDetailedActivity.this);
        myviewNews.setAdapter(newsListAdapter);
    }
}