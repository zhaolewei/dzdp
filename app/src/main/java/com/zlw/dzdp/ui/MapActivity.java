package com.zlw.dzdp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.zlw.dzdp.R;
import com.zlw.dzdp.bean.Goods;
import com.zlw.dzdp.bean.Shop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlw on 2016/8/22 0022.
 */
public class MapActivity extends Activity implements LocationSource, AMapLocationListener, AMap.OnMapLoadedListener, AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener, AMap.InfoWindowAdapter {


    List<Goods> list = new ArrayList<Goods>();
    //Map地图相关
    @ViewInject(R.id.search_map)
    private MapView mMapView;
    private AMap aMap;

    //定位相关

    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;

    private double lon, lat; //经纬度


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_map);
        ViewUtils.inject(this);

        mMapView.onCreate(savedInstanceState);
        //初始化
        if (aMap == null) {
            aMap = mMapView.getMap();
            aMap.setLocationSource(this);
            aMap.setMyLocationEnabled(true);  //设置可以触发定位；默认flase
            aMap.setOnMapLoadedListener(this); //设置Amap加载成功的时间监听
            aMap.setOnMarkerClickListener(this);
            aMap.setInfoWindowAdapter(this); //设置自定义window样式
            aMap.setOnInfoWindowClickListener(this);//设置点击windowInfo的事件监听器
            aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
            // 设置定位的类型为定位模式，参见类AMap。
            aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
            aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 卫星地图模式
        }

    }


    @OnClick({R.id.map_back, R.id.map_refresh})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.map_back:
                finish();
                break;
            case R.id.map_refresh:
                loadData(lon, lat, 1000);
                break;
            default:
                break;
        }

    }


    /**
     * 按照定位地址和搜索半径加载周边信息
     */
    private void loadData(double lon, double lat, int radius) {

        //服务端还没写 此处模拟数据
        Goods goods = new Goods();
        Shop shop = new Shop();
        shop.setLat(38.0145);
        shop.setLon(112.452);
        shop.setAddress("中北大学XXX");
        shop.setArea("尖草坪区");
        shop.setName("校园商店");
        goods.setShop(shop);
        goods.setTitle("大怡宝");
        goods.setCategoryId("1");
        goods.setPrice("3.00");
        list.add(goods);

        goods = new Goods();
        shop = new Shop();
        shop.setLat(38.0146);
        shop.setLon(112.451);
        shop.setAddress("中北大学AAA");
        shop.setArea("尖草坪区");
        shop.setName("校园商店2");
        goods.setShop(shop);
        goods.setTitle("小怡宝");
        goods.setPrice("2.00");
        goods.setCategoryId("2");
        list.add(goods);

        //进行地图缩放
        aMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(lat, lon), 16, 0, 30)));
        addMarker(list);

    }

    private void addMarker(List<Goods> list) {

        MarkerOptions markerOptions = null;
        for (Goods goods : list) {
            Shop shop = goods.getShop();
            markerOptions = new MarkerOptions();

            //进行标记
            markerOptions.position(new LatLng(shop.getLat(), shop.getLon()));  //标记位置
            markerOptions.title("" + shop.getName()).snippet("￥" + goods.getPrice()); //标记标题
            //设置图标
            if ("1".equals(goods.getCategoryId())) {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_landmark1));
            } else if ("2".equals(goods.getCategoryId())) {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_landmark2));
            } else {
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_landmark1));
            }

            aMap.addMarker(markerOptions).setObject(goods);
        }

    }


    //管理生命周期
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mMapView.onSaveInstanceState(outState);
    }


    /**
     * AMapLocationListener 定位监听接口实现
     */
    @Override
    public void onLocationChanged(AMapLocation location) {

        if (location != null && location.getLatitude() != 0) {
            lon = location.getLongitude();
            lat = location.getLatitude();
            mListener.onLocationChanged(location);
            Log.i("zlw", "定位信息  ---lon：" + lon + ";lat: " + lat);
            loadData(lon, lat, 1000);
        }

    }


    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public void onMapLoaded() {

    }
}
