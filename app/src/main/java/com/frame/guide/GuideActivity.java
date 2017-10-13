package com.frame.guide;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.frame.guide.adapter.BaseFragmentAdapter;
import com.frame.guide.fragment.LauncherBaseFragment;
import com.frame.guide.fragment.PrivateMessageLauncherFragment;
import com.frame.guide.fragment.RewardLauncherFragment;
import com.frame.guide.fragment.StereoscopicLauncherFragment;
import com.frame.guide.view.GuideViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导界面
 * jian1 分支 个地方
 */
public class GuideActivity extends FragmentActivity {

    private GuideViewPager vp;
    private ImageView[] tips;
    private List<LauncherBaseFragment> list = new ArrayList<>();
    private int curretSelect = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        //添加点点
        ViewGroup group = findViewById(R.id.viewGroup);
        tips = new ImageView[3];
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 20;
            params.rightMargin = 20;
            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                imageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
            tips[i] = imageView;
            group.addView(imageView, params);
        }

        //初始化三个fragment添加的集合中
        vp = findViewById(R.id.vp);
        vp.setBackGround(BitmapFactory.decodeResource(getResources(),R.drawable.bg_kaka_launcher));
        RewardLauncherFragment rewardLauncherFragment = new RewardLauncherFragment();
        PrivateMessageLauncherFragment privateMessageLauncherFragment = new PrivateMessageLauncherFragment();
        StereoscopicLauncherFragment stereoscopicLauncherFragment = new StereoscopicLauncherFragment();
        list.add(rewardLauncherFragment);
        list.add(privateMessageLauncherFragment);
        list.add(stereoscopicLauncherFragment);
        BaseFragmentAdapter fragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), list);
        vp.setAdapter(fragmentAdapter);
        vp.setOffscreenPageLimit(2);
        vp.setCurrentItem(curretSelect);
        vp.addOnPageChangeListener(changeListener);
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setImageBackground(position);//改变点点的切换效果
            //开始播放动画
            LauncherBaseFragment fragment = list.get(position);
            list.get(curretSelect).stopAnimation();//停止上个的动画
            fragment.startAnimation();
            curretSelect = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void setImageBackground(int position) {
        for (int i = 0; i < tips.length; i++) {
            if (i == position) {
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vp.clearOnPageChangeListeners();
    }

    //跳转到主界面  销毁当前Activity
    public void startMainActivity(){
       startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
