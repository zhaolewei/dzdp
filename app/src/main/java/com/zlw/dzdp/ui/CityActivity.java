package com.zlw.dzdp.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zlw.dzdp.R;
import com.zlw.dzdp.adapter.CitySelectRVAdapter;
import com.zlw.dzdp.bean.City;
import com.zlw.dzdp.bean.ResponseObject;
import com.zlw.dzdp.myconst.URL_Const;
import com.zlw.dzdp.utils.JsonTools;
import com.zlw.dzdp.utils.pinyinsort.ISideBarSelectCallBack;
import com.zlw.dzdp.utils.pinyinsort.SideBar2;
import com.zlw.dzdp.utils.pinyinsort.SortMode;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择城市
 * Created by zlw on 2016/8/18 0018.
 */
public class CityActivity extends AppCompatActivity implements CitySelectRVAdapter.MyItemClickListener{


    @ViewInject(R.id.city_select_rv)
    private RecyclerView rv;
    private CitySelectRVAdapter rvAdapter;

    private List<City> list = new ArrayList<City>();


    private List<SortMode> mList = new ArrayList<SortMode>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_select);
        ViewUtils.inject(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new CitySelectRVAdapter(list);
        rvAdapter.setOnItemClickListener(this);  //Item点击事件
        rv.setAdapter(rvAdapter);
        getCitysAsync();

        //sideBar事件关联：
        SideBar2 sideBar2 = (SideBar2) findViewById(R.id.sidebar2);
        sideBar2.setOnStrSelectCallBack(new ISideBarSelectCallBack() {
            @Override
            public void onSelectStr(int index, String selectStr) {
                int position = rvAdapter.getFirstIndex(selectStr);

                if(position != -1){
                    rv.scrollToPosition(position);
                }
            }
        });

    }

    /**
     * 刷新数据List界面适配器
     */
    public void flushList() {

//list排序（此处服务端已拍好顺序，此处不用排序）
//        for (int i = 0; i < list.size(); i++) {
//            mList.add(new SortMode(list.get(i).getCity_name(), list.get(i).getCity_sortkey()));
//        }
//
//        mList = PinyinSortHelper.makeSort(mList);
        rvAdapter.setList(list);
        rvAdapter.notifyDataSetChanged();
    }

    public List<City> getCitysAsync() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        client.post(URL_Const.URL_CITYS, params, new AsyncHttpResponseHandler() {
            // 回调接口
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {

                if (statusCode == 200) {
                    String str = new String(responseBody);
                    ResponseObject<List<City>> responseObject = JsonTools.fromJson(str, new TypeToken<ResponseObject<List<City>>>() {
                    }.getType());
                    list = responseObject.getDatas();

                    flushList();
                }
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
            }
        });
        return list;
    }


    @Override
    public void onItemClick(View view, int postion) {
        Intent intent = new Intent();

        String city_name = list.get(postion).getCity_name();
        intent.putExtra("cityName",city_name);
        setResult(RESULT_OK,intent);
        finish();
    }
}
