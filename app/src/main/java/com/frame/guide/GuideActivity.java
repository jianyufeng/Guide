package com.frame.guide;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.frame.guide.adapter.BaseFragmentAdapter;
import com.frame.guide.fragment.LauncherBaseFragment;
import com.frame.guide.fragment.RewardLauncherFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导界面
 */
public class GuideActivity extends FragmentActivity {

    private ViewPager vp;
    private ImageView[] tips;
    private List<LauncherBaseFragment> list = new ArrayList<LauncherBaseFragment>();

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
            if (i==0){
                imageView.setBackgroundResource(R.drawable.page_indicator_focused);
            }else {
                imageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
            tips[i] = imageView;
            group.addView(imageView,params);
        }

        //初始化三个fragment添加的集合中
        vp = findViewById(R.id.vp);
//        vp.setBackGround();
        RewardLauncherFragment rewardLauncherFragment = new RewardLauncherFragment();
        list.add(rewardLauncherFragment);
        BaseFragmentAdapter fragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), list);
        vp.setAdapter(fragmentAdapter);
        vp.setOffscreenPageLimit(2);
        vp.setCurrentItem(0);
        vp.addOnPageChangeListener(changeListener);
    }
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
