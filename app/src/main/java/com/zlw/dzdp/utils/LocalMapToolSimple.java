package com.zlw.dzdp.utils;

import android.content.Context;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.location.Poi;
import com.ypy.eventbus.EventBus;
import com.zlw.dzdp.bean.LocalInfo;
import com.zlw.dzdp.bean.MessageEvent;

import java.util.ArrayList;
import java.util.List;

public class LocalMapToolSimple {

    private Context context;
    // 定位相关
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = null;

    public LocalMapToolSimple(Context context) {
        this.context = context;
        initLocation();
    }

    /**
     * 开启定位
     */
    public void start() {
        initLocation();
        mLocationClient.start();
    }

    /**
     * 关闭定位
     */
    public void close() {
        if (mLocationClient == null)
            return;
        mLocationClient.stop();
    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        mLocationClient = new LocationClient(context); // 声明LocationClient类
        myListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myListener); // 注册监听函数

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationMode.Hight_Accuracy);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");// 可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000 * 10;
        option.setScanSpan(0);// 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);// 可选，默认false,设置是否使用gps
        option.setLocationNotify(true);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);// 可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);// 可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);// 可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);// 可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);// 可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    private class MyLocationListener implements BDLocationListener {
        private LocalInfo localInfo = new LocalInfo();

        @Override
        public void onReceiveLocation(BDLocation location) {
            // Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            localInfo.setTime(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            localInfo.setError_code(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            localInfo.setLatitude(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            localInfo.setLongitude(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            localInfo.setRadius(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                localInfo.setAddr(location.getAddrStr());
                // 运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                localInfo.setOperationers(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            localInfo.setLocationdescribe(location.getLocationDescribe());
            List<Poi> list = location.getPoiList();// POI数据
            List<String> pois = new ArrayList<String>();
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                localInfo.setSize(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                    pois.add(p.getId() + " " + p.getName() + " " + p.getRank());
                }
                localInfo.setPois(pois);
            }
//            Log.i("zlw", "location——city："+location.getCity());
//            Log.i("zlw", "location——city："+sb.toString());
//            Log.i("zlw", "location——city："+location.getCityCode());
//            Log.i("zlw", "location——city："+location.getAddrStr());
//            Log.i("zlw", "location——city："+location.getLocationDescribe());

            //以获取到城市信息通知更新UI
            EventBus.getDefault().post(new MessageEvent(location.getCity()));

        }


    }


}
