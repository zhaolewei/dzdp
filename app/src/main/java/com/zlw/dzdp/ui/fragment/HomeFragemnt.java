package com.zlw.dzdp.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ypy.eventbus.EventBus;
import com.zlw.dzdp.R;
import com.zlw.dzdp.bean.MessageEvent;
import com.zlw.dzdp.ui.CityActivity;
import com.zlw.dzdp.utils.LocalMapToolSimple;

import org.w3c.dom.Text;

public class HomeFragemnt extends Fragment {
    public static final int RESQUEST_CODE_SELECT_CITY = 1;

    @ViewInject(R.id.index_top_tv_city)
    private TextView tv_city;

    @ViewInject(R.id.home_nav_sort)
    private GridView gv_nav;

//    private LocalMapToolSimple localMapToolSimple;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_index, container);
        ViewUtils.inject(this, view);
        EventBus.getDefault().register(this);

//        localMapToolSimple = new LocalMapToolSimple(getContext());
//        localMapToolSimple.start(); //获取城市信息 并通过EventBus回传CityName

        gv_nav.setAdapter(new NavAdapter(getContext()));

        return view;
    }

    @OnClick(R.id.index_top_tv_city)
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), CityActivity.class);
        startActivityForResult(intent, RESQUEST_CODE_SELECT_CITY);
    }

    /**
     * 处理返回值结果
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESQUEST_CODE_SELECT_CITY && resultCode == Activity.RESULT_OK) {
            String city_name = data.getStringExtra("cityName");
            tv_city.setText(city_name);
        }

    }

    /**
     * 接收事件信息：获取到城市信息
     *
     * @param event
     */
    public void onEventMainThread(MessageEvent event) {
        String city = event.message;
        tv_city.setText(city);
    }


    @Override
    public void onDestroy() {
//        if (null != localMapToolSimple) {
//            localMapToolSimple.close();
//        }
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    /**
     * 首页Nav GridView 的数据适配器
     */
   public class NavAdapter extends BaseAdapter {


        private Context context;

        public NavAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 14;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyHolder mHolder = null;
            if(convertView == null){
                convertView=  LayoutInflater.from(context).inflate(R.layout.item_home_nav,parent,false);
                mHolder = new MyHolder();
                ViewUtils.inject(mHolder,convertView);
                convertView.setTag(mHolder);
            }else{
                mHolder = (MyHolder) convertView.getTag();
            }

            //加载数据
            return convertView;
        }
        public class MyHolder{
            @ViewInject(R.id.item_home_tv)
            public TextView tv_desc;

            @ViewInject(R.id.item_home_iv)
            public ImageView iv_img;

        }


    }




}
