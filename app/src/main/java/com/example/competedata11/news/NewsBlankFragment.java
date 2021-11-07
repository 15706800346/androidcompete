package com.example.competedata11.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.competedata11.R;
import com.example.competedata11.adapter.MyFragmentAdapter;
import com.example.competedata11.adapter.NewsBannerAdapter;
import com.example.competedata11.api.HttpbinServices;
import com.example.competedata11.bean.NewsBanerBean;
import com.example.competedata11.news.fragment.NewsTab0BlankFragment;
import com.example.competedata11.news.fragment.NewsTab1BlankFragment;
import com.example.competedata11.news.fragment.NewsTab2BlankFragment;
import com.example.competedata11.news.fragment.NewsTab3BlankFragment;
import com.example.competedata11.news.fragment.NewsTab4BlankFragment;
import com.example.competedata11.news.fragment.NewsTab5BlankFragment;
import com.example.competedata11.utils.Constants;
import com.example.competedata11.utils.Jsonparse;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsBlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsBlankFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SearchView searchNews;
    private Banner bannerNews;
    private TextView tvClass0;
    private TextView tvClass1;
    private TextView tvClass2;
    private TextView tvClass3;
    private TextView tvClass4;
    private TextView tvClass5,tv_current;
    private ViewPager2 viewNews;

    public NewsBlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsBlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsBlankFragment newInstance(String param1, String param2) {
        NewsBlankFragment fragment = new NewsBlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private View rootview = null;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private MyFragmentAdapter myFragmentAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootview == null) {
            rootview = inflater.inflate(R.layout.fragment_news_blank, container, false);
        }
        retrofit=new Retrofit.Builder().baseUrl(Constants.WEB_URL).build();
        httpbinServices=retrofit.create(HttpbinServices.class);
        initView();
        initBanner();
        initPager();
        return rootview;
    }

    private void initPager() {
        viewNews = rootview.findViewById(R.id.view_news);
        ArrayList<Fragment> fragments=new ArrayList<>();
        fragments.add(NewsTab0BlankFragment.newInstance("1","2"));
        fragments.add(NewsTab1BlankFragment.newInstance("1","2"));
        fragments.add(NewsTab2BlankFragment.newInstance("1","2"));
        fragments.add(NewsTab3BlankFragment.newInstance("1","2"));
        fragments.add(NewsTab4BlankFragment.newInstance("1","2"));
        fragments.add(NewsTab5BlankFragment.newInstance("1","2"));
        myFragmentAdapter=new MyFragmentAdapter(getChildFragmentManager(),getLifecycle(),fragments);
        viewNews.setAdapter(myFragmentAdapter);
        viewNews.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void changeTab(int position) {
        tv_current.setSelected(false);
        switch (position){
            case R.id.tv_class0:
                viewNews.setCurrentItem(0);
            case 0:
                tv_current=tvClass0;
                tv_current.setSelected(true);
                break;
            case R.id.tv_class1:
                viewNews.setCurrentItem(1);
            case 1:
                tv_current=tvClass1;
                tv_current.setSelected(true);
                break;
            case R.id.tv_class2:
                viewNews.setCurrentItem(2);
            case 2:
                tv_current=tvClass2;
                tv_current.setSelected(true);
                break;
            case R.id.tv_class3:
                viewNews.setCurrentItem(3);
            case 3:
                tv_current=tvClass3;
                tv_current.setSelected(true);
                break;
            case R.id.tv_class4:
                viewNews.setCurrentItem(4);
            case 4:
                tv_current=tvClass4;
                tv_current.setSelected(true);
                break;
            case R.id.tv_class5:
                viewNews.setCurrentItem(5);
            case 5:
                tv_current=tvClass5;
                tv_current.setSelected(true);
                break;
        }
    }

    private void initBanner() {
        Call<ResponseBody> call=httpbinServices.getNewsBanner();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string();
                    List<NewsBanerBean.RowsBean> rowsBeanList=Jsonparse.getmInstance().getNewsBaners(res);
                    bannerNews.setImageLoader(new NewsBannerAdapter());
                    bannerNews.setImages(rowsBeanList);
                    bannerNews.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    private void initView() {
        searchNews = rootview.findViewById(R.id.search_news);
        bannerNews = rootview.findViewById(R.id.banner_news);
        tvClass0 = rootview.findViewById(R.id.tv_class0);
        tvClass1 = rootview.findViewById(R.id.tv_class1);
        tvClass2 = rootview.findViewById(R.id.tv_class2);
        tvClass3 = rootview.findViewById(R.id.tv_class3);
        tvClass4 = rootview.findViewById(R.id.tv_class4);
        tvClass5 = rootview.findViewById(R.id.tv_class5);

        tv_current=tvClass0;
        tvClass0.setSelected(true);

        tvClass0.setOnClickListener(this);
        tvClass1.setOnClickListener(this);
        tvClass2.setOnClickListener(this);
        tvClass3.setOnClickListener(this);
        tvClass4.setOnClickListener(this);
        tvClass5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        changeTab(v.getId());
    }
}