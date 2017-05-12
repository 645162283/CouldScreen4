package com.example.administrator.couldscreen4;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.inAnimation;
import static android.R.attr.offset;
import static com.example.administrator.couldscreen4.Contants.BANNER;

public class MainActivity extends AppCompatActivity {

    private int offset = 0;// 当前页编号
    private int one;
    private int i;
    private ViewPager viewPager;


    private FrameLayout container;
    private Homepage fra_home;
    private JumpedColorSpace fra_col;
    private ListWenke fra_wenke;
    private ListSchool fra_school;
    private int fra_state = 1;
    private ImageView picbanner;
    private ArrayList<ImageView> PicsBanner;
    private ViewPager ViewBanner;
    private Context context;
    private int banneramount;
    public state state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.flags = (WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        getWindow().setAttributes(lp);
        context = getApplicationContext();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main);
        container = (FrameLayout)findViewById(R.id.container_homepage);
        View v = getWindow().getDecorView();
        viewPager = (ViewPager) v.findViewById(R.id.banner);
        change(1);
        click(v);

    }
    private void hideAll(FragmentTransaction transaction){
        if(fra_home != null){
            transaction.hide(fra_home);
        }
        if(fra_col !=null){
            transaction.hide(fra_col);
        }
        if(fra_wenke !=null){
            transaction.hide(fra_wenke);
        }
        if(fra_school != null){
            transaction.hide(fra_school);
        }
    }
    public void change(int fra_state){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (fra_state){
            case 1:
                hideAll(transaction);
                if(fra_home == null){
                    fra_home = new Homepage();
                    transaction.add(R.id.container_homepage,fra_home);
                }
                else {
                    transaction.show(fra_home);
                }
                break;
            case 2:
                hideAll(transaction);
                if(fra_col == null){
                    fra_col = new JumpedColorSpace();
                    transaction.add(R.id.container_homepage,fra_col);

                }
                else {
                    transaction.show(fra_col);
                }
                break;
            case 3:
                hideAll(transaction);
                if(fra_wenke == null){
                    fra_wenke = new ListWenke();
                    transaction.add(R.id.container_homepage,fra_wenke);
                }
                else {
                    transaction.show(fra_wenke);
                }
                break;
            case 4:
                hideAll(transaction);
                if(fra_school == null){
                    fra_school = new ListSchool();
                    transaction.add(R.id.container_homepage,fra_school);
                }
                else {
                    transaction.show(fra_school);
                }
                break;
        }
        transaction.commit();
    }
    public class state{
        int n;

        public void setN(int n) {
            this.n = n;
        }

        public int getN() {
            return n;
        }
    }
    public int getstate(){
        return state.getN();
    }
    public void setstate(int n){
        state.setN(n);
    }
    public class JsonMsg{
        ArrayList<Banner> data;
        String msg;
        String status;
        public void setData(ArrayList<Banner> data) {
            this.data = data;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
        public void setStatus(String status) {
            this.status = status;
        }
        public String getMsg() {
            return msg;
        }
        public String getStatus() {
            return status;
        }
        public ArrayList<Banner> getData() {
            return data;
        }
        public int getDataSize(){
            if(data != null){
                return data.size();
            }
            else{
                return 0;
            }
        }
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
    public void click(View v){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection conn = (HttpURLConnection) new URL(BANNER).openConnection();
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
            JsonMsg jsonmsg = gson.fromJson(s,JsonMsg.class);
            PicsBanner =new ArrayList<ImageView>();
            picbanner = new ImageView(context);
            Glide.with(MainActivity.this).load(jsonmsg.getData().get(1).getImgUrl()).into(picbanner);
            picbanner.setScaleType(ImageView.ScaleType.FIT_XY);
            PicsBanner.add(picbanner);
            PicsBanner.add(picbanner);
            PagerAdapter mPagerAdapter = new PagerAdapter(){
                @Override
                //获取当前窗体界面数
                public int getCount() {
                    // TODO Auto-generated method stub
                    return PicsBanner.size();
                }

                @Override
                //判断是否由对象生成界面
                public boolean isViewFromObject(View arg0, Object arg1) {
                    // TODO Auto-generated method stub
                    return arg0==arg1;
                }
                //使从ViewGroup中移出当前View
                public void destroyItem(View arg0, int arg1, Object arg2) {
                    ((ViewPager) arg0).removeView(PicsBanner.get(arg1));
                }
                //返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
                public Object instantiateItem(View arg0, int arg1){
                    ((ViewPager)arg0).addView(PicsBanner.get(arg1));
                    return PicsBanner.get(arg1);
                }
            };
            viewPager.setAdapter(mPagerAdapter);
            viewPager.setCurrentItem(0);//设置viewPager的初始界面为第一个界面
            viewPager.addOnPageChangeListener(new MyOnPageChangeListener());//添加切换界面的监听器
        }

    };
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
    public void SetSelected(int x){
        findViewById(R.id.guide1).setSelected(false);
        findViewById(R.id.guide2).setSelected(false);
        findViewById(R.id.guide3).setSelected(false);
        findViewById(R.id.guide4).setSelected(false);
        findViewById(R.id.guide5).setSelected(false);
        switch (x){
            case 1:
                findViewById(R.id.guide1).setSelected(true);
                break;
            case 2:
                findViewById(R.id.guide2).setSelected(true);
                break;
            case 3:
                findViewById(R.id.guide3).setSelected(true);
                break;
            case 4:
                findViewById(R.id.guide4).setSelected(true);
                break;
            case 5:
                findViewById(R.id.guide5).setSelected(true);
                break;
        }
    }
}
