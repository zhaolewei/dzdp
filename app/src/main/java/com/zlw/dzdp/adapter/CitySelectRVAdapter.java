package com.zlw.dzdp.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zlw.dzdp.R;
import com.zlw.dzdp.bean.City;

import java.util.List;

/**
 * 城市列表 RecycleView 的数据适配器
 * Created by zlw on 2016/8/18 0018.
 */
public class CitySelectRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<City> list;

    private int position;
    private MyItemClickListener mListener;


    private StringBuffer buf = new StringBuffer();  //纪录城市索引，

    public CitySelectRVAdapter(List<City> list) {
        this.list = list;
    }

    public void setList(List<City> list) {
        this.list = list;
    }


    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mListener = listener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_city_select, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        MyViewHolder mHolder = (MyViewHolder) holder;
        mHolder.position = position;
        String name = list.get(position).getCity_name();
        String sortkey = list.get(position).getCity_sortkey();

        if (position == getFirstIndex(sortkey)) {
            //并未出现该索引
            buf.append(sortkey);
            mHolder.tv_sortket.setVisibility(View.VISIBLE);
            mHolder.tv_sortket.setText(list.get(position).getCity_sortkey());
        } else {
            mHolder.tv_sortket.setVisibility(View.GONE);
        }
        mHolder.tv_city.setText(name);
    }

    public int getFirstIndex(String sortkey) {
        for (int i = 0; i < list.size(); i++) {
            String sort = list.get(i).getCity_sortkey().toUpperCase();
            if (sort.equals(sortkey)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private int position;

        public TextView tv_sortket;
        public TextView tv_city;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_sortket = (TextView) itemView.findViewById(R.id.item_city_select_tv_sortkey);
            tv_city = (TextView) itemView.findViewById(R.id.item_city_select_tv_city);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(v,position);
        }

    }

    public interface MyItemClickListener {
        public void onItemClick(View view, int postion);
    }


}
