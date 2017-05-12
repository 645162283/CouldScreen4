package com.example.administrator.couldscreen4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.couldscreen4.R;
import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/26.
 */

public class Homepage extends Fragment{
    private ViewPager viewPager;
    private ArrayList<View> pageview;
    private int offset = 0;// 当前页编号
    private JumpedColorSpace fra_col;
    private int one;
    private int i;
    private HomepageWenke fra_wenke;
    private ImageView news1_pic;
    private ImageView news2_pic;
    private ImageView news3_pic;
    private TextView news1_title;
    private TextView news2_title;
    private TextView news3_title;
    private TextView news1_content;
    private TextView news2_content;
    private TextView news3_content;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page,container,false);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        LayoutInflater inflaterx =getLayoutInflater(savedInstanceState);
        View view1 = inflaterx.inflate(R.layout.home_page_wenke,null);

        view1.findViewById(R.id.check_more_wenke).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.change(3);
            }
        });
        //WebView webView = view1.findViewById()
        View view2 = inflaterx.inflate(R.layout.home_page_school,null);
        view2.findViewById(R.id.check_more_school).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.change(4);
            }
        });
        news1_pic = (ImageView) view2.findViewById(R.id.mes1);
        news2_pic = (ImageView) view2.findViewById(R.id.mes2);
        news3_pic = (ImageView) view2.findViewById(R.id.mes3);
        news1_title = (TextView) view2.findViewById(R.id.mes1_title);
        news2_title = (TextView) view2.findViewById(R.id.mes2_title);
        news3_title = (TextView) view2.findViewById(R.id.mes3_title);
        news1_content = (TextView) view2.findViewById(R.id.mes1_content);
        news2_content = (TextView) view2.findViewById(R.id.mes2_content);
        news3_content = (TextView) view2.findViewById(R.id.mes3_content);
        pageview =new ArrayList<View>();
        pageview.add(view1);
        pageview.add(view2);
        view.findViewById(R.id.sign).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.change(2);
                //mainActivity.setstate(1);
            }
        });
        view.findViewById(R.id.welfare).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.change(2);

            }
        });
        view.findViewById(R.id.action).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.change(2);

            }
        });
        view.findViewById(R.id.red_packet).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.change(2);

            }
        });
        //数据适配器
        PagerAdapter mPagerAdapter = new PagerAdapter(){
            @Override
            //获取当前窗体界面数
            public int getCount() {
                // TODO Auto-generated method stub
                return pageview.size();
            }

            @Override
            //判断是否由对象生成界面
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0==arg1;
            }
            //使从ViewGroup中移出当前View
            public void destroyItem(View arg0, int arg1, Object arg2) {
                ((ViewPager) arg0).removeView(pageview.get(arg1));
            }
            //返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
            public Object instantiateItem(View arg0, int arg1){
                ((ViewPager)arg0).addView(pageview.get(arg1));
                return pageview.get(arg1);
            }
        };
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setCurrentItem(0);//设置viewPager的初始界面为第一个界面
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());//添加切换界面的监听器
        // 设置viewPager的监听事件
        new Thread(new ThreadShow()).start();
        click(view);
        click2(view);
        click3(view);
        return view;

    }
    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {
                case 0:
                    /**
                     * TranslateAnimation的四个属性分别为
                     * float fromXDelta 动画开始的点离当前View X坐标上的差值
                     * float toXDelta 动画结束的点离当前View X坐标上的差值
                     * float fromYDelta 动画开始的点离当前View Y坐标上的差值
                     * float toYDelta 动画开始的点离当前View Y坐标上的差值
                     **/
                    animation = new TranslateAnimation(one, 0, 0, 0);
                    break;
                case 1:
                    animation = new TranslateAnimation(offset, one, 0, 0);
                    break;
            }
            //arg0为切换到的页的编码
        }
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {}
        @Override
        public void onPageScrollStateChanged(int arg0) {}
    }
    private class ThreadShow implements Runnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (true) {
                try {
                    Thread.sleep(30000);
                    if (offset == 0){
                        viewPager.setCurrentItem(1);
                        offset = 1;
                    }
                    else if (offset == 1){
                        viewPager.setCurrentItem(0);
                        offset = 0;
                    }
                    else{
                        viewPager.setCurrentItem(0);
                        offset = 0;
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.out.println("thread error...");
                }
            }
        }
    }
    public class JsonMsgWenke{
        private String data;
        private String msg;
        private String status;
    }
    public class Banner{
        String id;
        String imgUrl;
        String title;
        String type;


        public void setId(String id) {
            this.id = id;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public String getTitle() {
            return title;
        }

        public String getType() {
            return type;
        }
    }
    public class colorLump{
        String id;
        String imgUrl;
        String title;
        String type;


        public void setId(String id) {
            this.id = id;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public String getTitle() {
            return title;
        }

        public String getType() {
            return type;
        }
    }
    class state{
        private String info;
        private int code;
    }
    class data{
        private int newsId;
        private String newsTitle;
        private int catalogId;
        private String newsSource;
        private String imgUrl;
        private String newsContent;
        private String upTime;
        public String getImgUrl(){
            return this.imgUrl;
        }

        public String getNewsTitle() {
            return newsTitle;
        }
    }
    class news{
        state state;
        data data;

        public Homepage.data getData() {
            return data;
        }

        public Homepage.state getState() {
            return state;
        }
    }
    public void click(View v){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection conn = (HttpURLConnection) new URL("http://221.226.251.244:5188/wenkor_service/school/news/2/detail").openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    int code = conn.getResponseCode();
                    if (code == 200){
                        InputStream is = conn.getInputStream();
                        String content =GetJson.readStream(is);
                        Message msg = new Message();
                        msg.obj = content;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    Handler handler = new Handler(){
        int x = 0;
        @Override
        public void handleMessage(Message msg) {
            //tv1.setText(msg.obj+"");
            String s = msg.obj.toString();
            Gson gson = new Gson();
            news jsonmsg = gson.fromJson(s,news.class);
            Glide.with(Homepage.this).load(jsonmsg.getData().getImgUrl()).into(news1_pic);
            news1_pic.setScaleType(ImageView.ScaleType.FIT_XY);
            //news1_title.setText(jsonmsg.getData().getNewsTitle());
        }

    };
    public void click2(View v){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection conn = (HttpURLConnection) new URL("http://221.226.251.244:5188/wenkor_service/school/news/3/detail").openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    int code = conn.getResponseCode();
                    if (code == 200){
                        InputStream is = conn.getInputStream();
                        String content =GetJson.readStream(is);
                        Message msg = new Message();
                        msg.obj = content;
                        handler2.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    Handler handler2 = new Handler(){
        int x = 0;
        @Override
        public void handleMessage(Message msg) {
            //tv1.setText(msg.obj+"");
            String s = msg.obj.toString();
            Gson gson = new Gson();
            news jsonmsg2 = gson.fromJson(s,news.class);
            Glide.with(Homepage.this).load(jsonmsg2.getData().getImgUrl()).into(news2_pic);
            news2_pic.setScaleType(ImageView.ScaleType.FIT_XY);
            news2_title.setText(jsonmsg2.getData().getNewsTitle());
        }

    };
    public void click3(View v){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection conn = (HttpURLConnection) new URL("http://221.226.251.244:5188/wenkor_service/school/news/4/detail").openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    int code = conn.getResponseCode();
                    if (code == 200){
                        InputStream is = conn.getInputStream();
                        String content =GetJson.readStream(is);
                        Message msg = new Message();
                        msg.obj = content;
                        handler3.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    Handler handler3 = new Handler(){
        int x = 0;
        @Override
        public void handleMessage(Message msg) {
            //tv1.setText(msg.obj+"");
            String s = msg.obj.toString();
            Gson gson = new Gson();
            news jsonmsg3 = gson.fromJson(s,news.class);
            Glide.with(Homepage.this).load(jsonmsg3.getData().getImgUrl()).into(news3_pic);
            news3_pic.setScaleType(ImageView.ScaleType.FIT_XY);
            news3_title.setText(jsonmsg3.getData().getNewsTitle());
        }

    };
}
//
