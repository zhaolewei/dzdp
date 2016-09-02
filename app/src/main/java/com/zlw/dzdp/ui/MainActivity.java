package com.zlw.dzdp.ui;

import java.util.ArrayList;
import java.util.List;

import com.zlw.dzdp.R;
import com.zlw.dzdp.adapter.MainViewPagerAdapter;
import com.zlw.dzdp.ui.fragment.HomeFragemnt;
import com.zlw.dzdp.ui.fragment.TuanFragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    // 控件
    private TabLayout tabLayout;
    private ViewPager vp;
    // 全局数据集
    private List<ImageView> imageViews; // Tab中的Imageview集（用于修改透明度达到变色效果）

    private String[] texts = new String[]{"首页", "团购", "发现", "我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        bindEvent();
    }

    protected void initData() {
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new HomeFragemnt());
        fragments.add(new TuanFragment());
        fragments.add(new TestFragment());
        fragments.add(new TestFragment());
        vp.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), fragments));
        vp.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(vp);

        // 设置TabLayout中的Tab样式
        imageViews = new ArrayList<ImageView>();
        for (int i = 0; i < fragments.size(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(R.layout.item_main_tab); // 设置Tab
            // 提取ImageView
            ImageView img = (ImageView) tab.getCustomView().findViewById(R.id.tab_icon);
            imageViews.add(img);
            if (i != 0) {
                img.setImageAlpha(0);
            }
            TextView textView = (TextView) tab.getCustomView().findViewById(R.id.item_main_tab_text);
            textView.setText(texts[i]);

        }
        // tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//适合很多tab
        tabLayout.setTabMode(TabLayout.MODE_FIXED);// tab均分,适合少的tab
    }

    /**
     * 绑定事件
     */
    protected void bindEvent() {
        vp.addOnPageChangeListener(this);
    }

    protected void initView() {
        tabLayout = (TabLayout) findViewById(R.id.main_tabLayout);
        vp = (ViewPager) findViewById(R.id.main_vp);
    }

    // ViewPager.OnPageChangeListener
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        /**
         * 经测试发现：|界面0|-------|界面1|--------|界面2|
         * —position=0—|—position=1—|—position=2—... 属性变化时可逆的
         *
         *
         */

        ImageView imgView = imageViews.get(position);
        int offset = (int) (positionOffset * 255);

        if (position < imageViews.size() - 1) {
            ImageView imgView2 = imageViews.get(position + 1);
            imgView.setImageAlpha(255 - offset);
            imgView2.setImageAlpha(offset);
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int arg0) {
        // TODO Auto-generated method stub

    }

}
