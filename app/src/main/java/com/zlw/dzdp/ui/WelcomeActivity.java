package com.zlw.dzdp.ui;

import com.zlw.dzdp.R;
import com.zlw.dzdp.utils.SharedUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * 欢迎页 功能描述： 在启动应用后进入改界面，3秒延迟后跳转到主页 延迟方法：1. 使用handler延迟 2. 使用Timer定时器
 * 
 * @author 123
 *
 */
public class WelcomeActivity extends Activity {

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			if (SharedUtils.isFirst(getBaseContext())) {
				// 第一次进入，跳转到向导页
				Intent intent = new Intent(WelcomeActivity.this, WelcomeGuideActivity.class);
				startActivity(intent);
				SharedUtils.putIsFirstBoolean(getBaseContext(), false);
			} else {
				// 跳转到主页
				Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
				startActivity(intent);
			}

			finish();
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		// 方法一：使用handler延迟
		handler.sendEmptyMessageDelayed(0, 3000);

	}

}
