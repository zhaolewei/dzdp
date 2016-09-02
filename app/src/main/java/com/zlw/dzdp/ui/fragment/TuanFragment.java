package com.zlw.dzdp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zlw.dzdp.R;
import com.zlw.dzdp.adapter.CitySelectRVAdapter;
import com.zlw.dzdp.adapter.TuanRVAdapter;
import com.zlw.dzdp.bean.Goods;
import com.zlw.dzdp.bean.ResponseObject;
import com.zlw.dzdp.myconst.URL_Const;
import com.zlw.dzdp.ui.GoodsDetailActivity;
import com.zlw.dzdp.utils.JsonTools;
import com.zlw.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlw on 2016/8/20 0020.
 */
public class TuanFragment extends Fragment {
    protected List<Goods> list = new ArrayList<>();

    @ViewInject(R.id.tuan_rv)
    private PullLoadMoreRecyclerView tuan_rv;

    private TuanRVAdapter rvAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.i("zlw", "======TuanFragment========");
        View view = inflater.inflate(R.layout.fragment_tuan_index, null);
        ViewUtils.inject(this, view);
//        tuan_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        tuan_rv.setFooterViewText("正在加载...");//设置上拉刷新文字
        tuan_rv.setLinearLayout();
        //设置监听事件
        tuan_rv.setOnPullLoadMoreListener(new PullLoadMoreListener());
        tuan_rv.setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.empty_view, null));//setEmptyView

        rvAdapter = new TuanRVAdapter(list);
        rvAdapter.setOnItemClickListener(new CitySelectRVAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                //Item点击事件：进去商品详情信息页
                Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                startActivity(intent);
            }
        });
        tuan_rv.setAdapter(rvAdapter);
        Log.i("zlw", "" + getGoodsAsync(null, null, 1, 3).size());
        return view;
    }

    /**
     * 刷新数据List界面适配器
     */
    public void flushList() {
        rvAdapter.setList(list);
        rvAdapter.notifyDataSetChanged();
    }

    public List<Goods> getGoodsAsync(String cityId, String catId, int page, int size) {

        Log.i("zlw", "---getGoodsAsync：");
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        if (cityId != null) {
            params.put("cityId", cityId);
        }
        if (catId != null) {
            params.put("catId", catId);
        }
        params.put("page", page);
        params.put("size", size);

        client.post(URL_Const.URL_GOODS, params, new AsyncHttpResponseHandler() {
            // 回调接口
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {

                Log.i("zlw", "statusCode" + statusCode);

                if (statusCode == 200) {

                    String str = new String(responseBody);
                    Log.i("zlw", "json:" + str);
                    ResponseObject<List<Goods>> responseObject = JsonTools.fromJson(str, new TypeToken<ResponseObject<List<Goods>>>() {
                    }.getType());
                    list = responseObject.getDatas();
                    Log.i("zlw", "list:" + list.size());
                }

                flushList();
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
                Log.i("zlw", "请求失败！！！");
            }
        });
        return list;
    }


    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {
        /**
         * 上拉刷新回调
         */
        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), "刷新中", Toast.LENGTH_SHORT).show();
                    tuan_rv.setPullLoadMoreCompleted();
                }
            }, 2000);

        }

        /**
         * 下拉加载回调
         */
        @Override
        public void onLoadMore() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), "加载中", Toast.LENGTH_SHORT).show();
                    tuan_rv.setPullLoadMoreCompleted();
                }
            }, 2000);
        }
    }

}
