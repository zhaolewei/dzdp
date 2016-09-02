package com.zlw.dzdp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zlw.dzdp.R;
import com.zlw.dzdp.bean.Goods;

import java.util.List;

/**
 * Created by zlw on 2016/8/20 0020.
 */
public class TuanRVAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Goods> list;

    private CitySelectRVAdapter.MyItemClickListener mListener;

    public TuanRVAdapter(List<Goods> list) {
        this.list = list;
    }

    public void setList(List<Goods> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_tuan, parent, false);
        MyHolder mHolder = new MyHolder(view);

        return mHolder;
    }

    public void setOnItemClickListener(CitySelectRVAdapter.MyItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder mHolder = (MyHolder) holder;
        mHolder.position = position;

        String title = list.get(position).getTitle();
        String sortTitle = list.get(position).getSortTitle();
        String price = list.get(position).getPrice();
        String value = list.get(position).getValue();
        String imgUrl = list.get(position).getImgUrl();

        mHolder.tv_title.setText(title);
        mHolder.tv_sort_title.setText(sortTitle);
        mHolder.tv_price.setText("￥" + price);
        mHolder.tv_value.setText("￥" + value);
        Log.i("zlw", "imgUrl:" + imgUrl);
        Picasso.with(context).load(imgUrl).placeholder(R.drawable.test_tuan_img1).into(mHolder.iv_img);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public int position;


        public TextView tv_title;
        public TextView tv_sort_title;
        public TextView tv_price;
        public TextView tv_value;
        public ImageView iv_img;


        public MyHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_title = (TextView) itemView.findViewById(R.id.item_tuan_tv_title);
            tv_sort_title = (TextView) itemView.findViewById(R.id.item_tuan_tv_sorttitle);
            tv_price = (TextView) itemView.findViewById(R.id.item_tuan_tv_price);
            tv_value = (TextView) itemView.findViewById(R.id.item_tuan_tv_value);
            iv_img = (ImageView) itemView.findViewById(R.id.item_tuan_iv);
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(v, position);
        }
    }

    public interface MyItemClickListener {
        public void onItemClick(View view, int postion);
    }
}
