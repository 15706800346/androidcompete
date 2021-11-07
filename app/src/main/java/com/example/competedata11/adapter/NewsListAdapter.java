package com.example.competedata11.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.competedata11.R;
import com.example.competedata11.bean.NewsListBean;
import com.example.competedata11.news.NewsDetailedActivity;
import com.example.competedata11.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter extends BaseAdapter {
    private List<NewsListBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;


    public NewsListAdapter(Context context) {
        this.context = context;
    }

    public List<NewsListBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<NewsListBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rowsBeanList == null ? 0 : rowsBeanList.size();
    }

    @Override
    public NewsListBean.RowsBean getItem(int position) {
        return rowsBeanList == null ? null : rowsBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.news_list, null);
            viewHolder=new ViewHolder();
            viewHolder.ivNews = convertView.findViewById(R.id.iv_news);
            viewHolder.tvTitlle = convertView.findViewById(R.id.tv_titlle);
            viewHolder.tvContent = convertView.findViewById(R.id.tv_content);
            viewHolder.tvReadnum = convertView.findViewById(R.id.tv_readnum);
            viewHolder.tvLikenum = convertView.findViewById(R.id.tv_likenum);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        final  NewsListBean.RowsBean rowsBean=getItem(position);
        if(rowsBean!=null){
            viewHolder.tvTitlle.setText(rowsBean.getTitle());
            viewHolder.tvContent.setText(rowsBean.getContent());
            viewHolder.tvReadnum.setText("阅读人数："+rowsBean.getReadNum());
            viewHolder.tvLikenum.setText("点赞人数："+rowsBean.getLikeNum());
            Glide.with(context)
                    .load(Constants.WEB_URL+rowsBean.getCover())
                    .into(viewHolder.ivNews);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, NewsDetailedActivity.class);
                intent.putExtra("news",rowsBean);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        private ImageView ivNews;
        private TextView tvTitlle;
        private TextView tvContent;
        private TextView tvReadnum;
        private TextView tvLikenum;
    }
}
