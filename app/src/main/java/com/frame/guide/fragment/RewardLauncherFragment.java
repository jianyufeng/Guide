package com.frame.guide.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.frame.guide.R;

/**
 * Created by Administrator on 2017/10/11.
 */

public class RewardLauncherFragment extends LauncherBaseFragment{
    private ImageView ivGold;
    private ImageView ivReward;
    private Bitmap goldBitmap;

    private boolean starterd= false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reward_launcher, null);

        ivGold = (ImageView) rootView.findViewById(R.id.iv_gold);
        ivReward = (ImageView)rootView.findViewById(R.id.iv_reward);
        //获取硬币的高度
        goldBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.icon_gold);
        startAnimation();
        return rootView;
    }

    @Override
    public void startAnimation() {
        starterd = true;
        //向下移动动画，硬币的高度*2+80;
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, goldBitmap.getHeight() * 2 + 80);
        translateAnimation.setDuration(500);
        translateAnimation.setFillAfter(true);

        ivGold.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //移动动画结束开启缩放动画
                if (starterd){
                    ivReward.setVisibility(View.VISIBLE);
                    Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.reward_launcher);
                    ivReward.startAnimation(anim);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                                //缩放动画结束 开启透明动画
                                AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
                                alphaAnimation.setDuration(1000);
                                ivReward.startAnimation(alphaAnimation);
                                alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animation animation) {
                                        //透明动画结束隐藏图片
                                        ivReward.setVisibility(View.GONE);

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {

                                    }
                                });
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void stopAnimation() {
        starterd = false;//结束动画时标示符设置为false
        ivGold.clearAnimation();//清空view上的动画

    }
}
