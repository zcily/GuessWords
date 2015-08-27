package com.ckt.lookguess.demo;

import java.util.ArrayList;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.gitonway.lee.niftymodaldialogeffects.lib.R;
import com.yx.utils.SPUtils;
import com.yx.utils.T;

public class WelcomMain extends Activity {
	private static final String KEY_SHOW_WELCOM = "show_welcom";
	private boolean isFirstWelcom = false;

	private ViewPager mViewPager;
	private ImageView mPage0;
	private ImageView mPage1;
	private ImageView mPage2;
	private ImageView mPage3;
	private ArrayList<View> views = new ArrayList<View>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		EventBus.getDefault().register(this);
		
		if(SPUtils.contains(this, KEY_SHOW_WELCOM)){
			int num = (Integer) SPUtils.get(this, KEY_SHOW_WELCOM, 0);
			SPUtils.put(this, KEY_SHOW_WELCOM, num++);
			setContentView(R.layout.welcom);
			isFirstWelcom = false;
		} else {
			SPUtils.put(this, KEY_SHOW_WELCOM, 1);
			setContentView(R.layout.first_welcom);
			isFirstWelcom = true;
		}
		initView(isFirstWelcom);
	}

	private void initView(boolean isFirst) {
		if (isFirst) {
			mViewPager = (ViewPager) findViewById(R.id.whatsnew_viewpager);
			mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
			mViewPager.setPageTransformer(true, new DepthPageTransformer());  
			mPage0 = (ImageView) findViewById(R.id.page0);
			mPage1 = (ImageView) findViewById(R.id.page1);
			mPage2 = (ImageView) findViewById(R.id.page2);
			mPage3 = (ImageView) findViewById(R.id.page3);
			WelcomPagerAdapter mPagerAdapter = new WelcomPagerAdapter(this);
			mViewPager.setAdapter(mPagerAdapter);
		} else {
			skipActivity(3);
		}
	}

	@Subscriber(tag = "welcomGotoLogin")  
    private void welcomGotoMain(int min) {  
		skipActivity(min);
    }  
	
	/**
	 * 寤惰繜澶氬皯绉掕繘鍏ヤ富鐣岄潰
	 * 
	 * @param sec 绉�
	 */
	private void skipActivity(int sec) {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (isFirstWelcom) {
					//T.showShort(WelcomMain.this, "鏈�悗涓�紶浜�);
				} else {
					//T.showShort(WelcomMain.this, "涓嶆槸绗竴娆″惎鍔�);
				}
				Intent intent = new Intent(WelcomMain.this,LoginActivity.class);
				startActivity(intent);
				WelcomMain.this.finish();
			}
		}, 1000 * sec);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {
		public void onPageSelected(int page) {
			switch (page) {
			case 0:
				mPage0.setImageDrawable(getResources().getDrawable(
						R.drawable.page_now));
				mPage1.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				break;
			case 1:
				mPage1.setImageDrawable(getResources().getDrawable(
						R.drawable.page_now));
				mPage0.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				mPage2.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				break;
			case 2:
				mPage2.setImageDrawable(getResources().getDrawable(
						R.drawable.page_now));
				mPage1.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				mPage3.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				break;
			case 3:
				mPage3.setImageDrawable(getResources().getDrawable(
						R.drawable.page_now));
				mPage2.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				break;
			}
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		public void onPageScrollStateChanged(int arg0) {
		}
	}
}
