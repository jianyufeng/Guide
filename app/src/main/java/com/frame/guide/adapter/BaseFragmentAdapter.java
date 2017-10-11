package com.frame.guide.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.frame.guide.fragment.LauncherBaseFragment;

import java.util.List;


/**
 * Viewpager适配器
 * @author apple
 *
 */
public class BaseFragmentAdapter extends FragmentStatePagerAdapter {
	private List<LauncherBaseFragment> list;
	public BaseFragmentAdapter(FragmentManager fm, List<LauncherBaseFragment> list) {
		super(fm);
		this.list = list;
	}


	@Override
	public Fragment getItem(int position) {
		return list.get(position);
	}

	@Override
	public int getCount() {
		return list.size();
	}
}
