package com.frame.guide.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frame.guide.R;

/**
 * Created by Administrator on 2017/10/11.
 */

public class RewardLauncherFragment extends LauncherBaseFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reward_launcher, null);

        return rootView;
    }

    @Override
    public void startAnimation() {

    }

    @Override
    public void stopAnimation() {

    }
}
