package com.zlw.dzdp.ui;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.zlw.dzdp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 欢迎向导页 功能描述： 使用Viewpager实现页面的滑动
 * 
 * 注意点：instantiateItem() 中添加试图 container.addView(list.get(position)); 应后返回该VIew，
 * 
 * @author 123
 *
 */
public class WelcomeGuideActivity extends Activity implements OnPageChangeListener {

	@ViewInject(R.id.guide_vp)
	private ViewPager guide_vp;

	private List<View> list;

	@ViewInject(R.id.bt_in)
	private Button bt_in;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_guide);
		ViewUtils.inject(this);

		initViewPager();

	}

	/**
	 * 初始化适配器
	 */
	private void initViewPager() {

		list = new ArrayList<View>();

		ImageView img1 = new ImageView(this);
		img1.setImageResource(R.drawable.guide_01);
		list.add(img1);
		ImageView img2 = new ImageView(this);
		img2.setImageResource(R.drawable.guide_02);
		list.add(img2);
		ImageView img3 = new ImageView(this);
		img3.setImageResource(R.drawable.guide_03);
		list.add(img3);

		guide_vp.setAdapter(new MyGuidePagerAdapter());
		guide_vp.setOnPageChangeListener(this);
	}

	@OnClick(R.id.bt_in)
	public void onClick(View v) {
		// 跳转
		Intent intent = new Intent(WelcomeGuideActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	class MyGuidePagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(list.get(position));
			return list.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(list.get(position));
		}
	}

	/**
	 * *OnPageChangeListener接口实现方法
	 */
	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int position) {

		if (position < list.size() - 1) {
			bt_in.setVisibility(View.GONE);
		} else {
			bt_in.setVisibility(View.VISIBLE);
		}

	}

}
