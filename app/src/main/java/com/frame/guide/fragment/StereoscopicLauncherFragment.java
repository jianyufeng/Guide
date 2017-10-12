package com.frame.guide.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.frame.guide.R;

/**
 * Created by Administrator on 2017/10/12.
 */

public class StereoscopicLauncherFragment extends LauncherBaseFragment implements View.OnClickListener {
    private ImageView imgView_immediate_experience;
    private static final float ZOOM_MAX = 1.3f;
    private static final  float ZOOM_MIN = 1.0f;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stereoscopic_launcher, container, false);

        imgView_immediate_experience=(ImageView) rootView.findViewById(R.id.imgView_immediate_experience);
        imgView_immediate_experience.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void startAnimation() {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(new ScaleAnimation(ZOOM_MIN,ZOOM_MAX,ZOOM_MIN,ZOOM_MAX
        , Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f));
        animationSet.addAnimation(new AlphaAnimation(1.0f,0.8f));
        animationSet.setDuration(500);
        animationSet.setInterpolator(new AccelerateInterpolator());
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                /**
                 * 缩小动画
                 */
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.addAnimation(new ScaleAnimation(ZOOM_MAX, ZOOM_MIN, ZOOM_MAX,ZOOM_MIN, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f));
                animationSet.addAnimation(new AlphaAnimation(0.8f, 1.0f));
                animationSet.setDuration(600);
                animationSet.setInterpolator(new DecelerateInterpolator());
                animationSet.setFillAfter(false);
                // 实现心跳的View
                imgView_immediate_experience.startAnimation(animationSet);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imgView_immediate_experience.startAnimation(animationSet);
    }

    @Override
    public void stopAnimation() {
        imgView_immediate_experience.clearAnimation();
    }

    @Override
    public void onClick(View view) {
        Toast toast = Toast.makeText(getContext(), "跳转到主页", Toast.LENGTH_LONG);
        ImageView imageView = new ImageView(getContext());
//        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setImageResource(R.mipmap.ic_launcher);
        toast.setView(imageView);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();

    }
}
