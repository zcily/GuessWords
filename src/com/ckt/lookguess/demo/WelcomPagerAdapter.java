package com.ckt.lookguess.demo;

import java.util.ArrayList;

import org.simple.eventbus.EventBus;

import com.gitonway.lee.niftymodaldialogeffects.lib.R;

import android.content.Context;
import android.provider.Contacts;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WelcomPagerAdapter extends PagerAdapter {

	private ArrayList<View> views;
	private Context mContext;
	private LayoutInflater mLi;
	
	public WelcomPagerAdapter(Context context){
		mContext = context;
		mLi = LayoutInflater.from(mContext);
		views = new ArrayList<View>();
		initPagerAdapter();
	}
	
	private void initPagerAdapter(){
		if(views != null){
			views.clear();
		}else{
			views = new ArrayList<View>();
			views.clear();
		}
		View view1 = mLi.inflate(R.layout.whats_news_gallery1, null);
        View view2 = mLi.inflate(R.layout.whats_news_gallery2, null);
        View view3 = mLi.inflate(R.layout.whats_news_gallery3, null);
        View view4 = mLi.inflate(R.layout.whats_news_gallery4, null);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);  
        
        ((Button)view4.findViewById(R.id.what_news_start_button)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EventBus.getDefault().post(1, "welcomGotoLogin");
			}
		});
	}
	
	@Override
	public int getCount() {
		return this.views.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	public void destroyItem(View container, int position, Object object) {
		((ViewPager)container).removeView(views.get(position));
	}
	
	//ҳ��view
	public Object instantiateItem(View container, int position) {
		((ViewPager)container).addView(views.get(position));
		return views.get(position);
	}

}
