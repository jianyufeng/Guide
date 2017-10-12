package com.frame.guide.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.frame.guide.R;

/**
 * Created by Administrator on 2017/10/12.
 */

public class PrivateMessageLauncherFragment extends LauncherBaseFragment {
    private ImageView ivLikeVideo;
    private ImageView ivThinkReward;
    private ImageView ivWatchMovie;
    private ImageView ivThisWeek;
    private boolean started = false;//是否开启动画

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rooView = inflater.inflate(R.layout.fragment_private_message_launcher, container, false);
        ivLikeVideo = (ImageView) rooView.findViewById(R.id.iv_private_message_like_video);
        ivThinkReward = (ImageView) rooView.findViewById(R.id.iv_private_message_think_reward);
        ivWatchMovie = (ImageView) rooView.findViewById(R.id.iv_private_message_watch_movie);
        ivThisWeek = (ImageView) rooView.findViewById(R.id.private_message_this_week);
        return rooView;
    }

    @Override
    public void startAnimation() {
        started = true;
        /**
         * 每次开启动画前先隐藏控件
         */
        ivLikeVideo.setVisibility(View.GONE);
        ivThinkReward.setVisibility(View.GONE);
        ivWatchMovie.setVisibility(View.GONE);
        ivThisWeek.setVisibility(View.GONE);
        ivLikeVideo.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (started) {
                    likeVideoAnimation();
                }
            }
        }, 500);

    }

    /**
     * 好喜欢你的视频
     */
    private void likeVideoAnimation() {
        ivLikeVideo.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.private_message_launcher);
        ivLikeVideo.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (started)
                    thinkReward();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 谢谢你的打赏
     */
    private void thinkReward() {
        ivThinkReward.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.private_message_launcher);
        ivThinkReward.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (started)
                    watchMovie();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 一起看个电影呗
     */
    private void watchMovie() {
        ivWatchMovie.setVisibility(View.VISIBLE);
        Animation watchAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.private_message_launcher);
        ivWatchMovie.startAnimation(watchAnimation);
        watchAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (started)
                    thisWeek();
            }
        });
    }

    /**
     * 好啊  这周末有空
     */
    private void thisWeek() {
        ivThisWeek.setVisibility(View.VISIBLE);
        Animation thisWeekAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.private_message_launcher);
        ivThisWeek.startAnimation(thisWeekAnimation);
    }

    @Override
    public void stopAnimation() {
        started = false;
        /**
         * 清空所有控件上的动画
         */
        ivLikeVideo.clearAnimation();
        ivThinkReward.clearAnimation();
        ivWatchMovie.clearAnimation();
        ivThisWeek.clearAnimation();

    }
}
